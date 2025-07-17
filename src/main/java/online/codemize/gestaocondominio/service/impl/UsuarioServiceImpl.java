package online.codemize.gestaocondominio.service.impl;

import lombok.RequiredArgsConstructor;
import online.codemize.gestaocondominio.converter.UsuarioConverter;
import online.codemize.gestaocondominio.domain.Usuario;
import online.codemize.gestaocondominio.dto.UsuarioRequest;
import online.codemize.gestaocondominio.exception.AssociacaoUsuarioUnidadeException;
import online.codemize.gestaocondominio.exception.CriacaoUsuarioException;
import online.codemize.gestaocondominio.exception.UsuarioNotFoundException;
import online.codemize.gestaocondominio.repository.UnidadeRepository;
import online.codemize.gestaocondominio.repository.UsuarioRepository;
import online.codemize.gestaocondominio.service.UsuarioService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import static online.codemize.gestaocondominio.utils.AppUtil.isEmailInvalid;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioConverter converter;
    private final UsuarioRepository repository;
    private final UnidadeRepository unidadeRepository;

    @Override
    public void criar(UsuarioRequest request) {
        validarEmail(request);
        Usuario usuario = converter.apply(request);
        salvarUsuario(usuario);
    }

    @Override
    public Usuario obterUsuario(String email) {
        return repository
                .findFirstByEmail(email)
                .orElseThrow(UsuarioNotFoundException::new);
    }

    @Override
    public void atualizarSenha(String email, String novaSenha) {
        var usuario = obterUsuario(email);
        var senha = BCrypt.hashpw(novaSenha, BCrypt.gensalt());
        usuario.setSenha(senha);
        salvarUsuario(usuario);
    }

    private void validarEmail(UsuarioRequest request) {
        if (isEmailInvalid(request.email()))
            throw new CriacaoUsuarioException("email invalido");
    }

    private void salvarUsuario(Usuario usuario) {
        try {
            repository.save(usuario);

        } catch (Exception e) {
            var erro = e.getMessage();
            if (e.getMessage().contains("Duplicate entry")) {
                erro = "chave duplicada";
            }
            throw new CriacaoUsuarioException("erro ao salvar no banco - " + erro);
        }
    }

    @Override
    public void associarUnidade(String emailUsuario, String registroUnidade) {
        var usuario = repository.findFirstByEmail(emailUsuario)
                .orElseThrow(() -> new AssociacaoUsuarioUnidadeException("usuario nao encontrado em nossa base de dados"));

        var unidade = unidadeRepository.findFirstByRegistro(registroUnidade)
                .orElseThrow(() -> new AssociacaoUsuarioUnidadeException("unidade nao encontrada em nossa base de dados"));

        unidade.setUsuario(usuario);

        unidadeRepository.save(unidade);
    }

}
