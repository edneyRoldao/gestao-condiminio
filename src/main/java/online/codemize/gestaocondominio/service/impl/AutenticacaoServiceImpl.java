package online.codemize.gestaocondominio.service.impl;

import lombok.RequiredArgsConstructor;
import online.codemize.gestaocondominio.domain.Usuario;
import online.codemize.gestaocondominio.dto.AutenticacaoRequest;
import online.codemize.gestaocondominio.exception.SemAutorizacaoException;
import online.codemize.gestaocondominio.exception.UsuarioNotFoundException;
import online.codemize.gestaocondominio.service.AutenticacaoService;
import online.codemize.gestaocondominio.service.JWTService;
import online.codemize.gestaocondominio.service.UsuarioService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoServiceImpl implements AutenticacaoService {

    private final JWTService jwtService;
    private final UsuarioService usuarioService;

    @Override
    public String gerarToken(AutenticacaoRequest request) {
        try {
            var usuario = usuarioService.obterUsuario(request.email());
            validarSenha(usuario, request);
            return jwtService.gerarTokenAcesso(usuario);

        } catch (UsuarioNotFoundException e) {
            throw new SemAutorizacaoException("Credenciais inválidas");
        }
    }

    private void validarSenha(Usuario usuario, AutenticacaoRequest request) {
        var senhaDoBanco = usuario.getSenha();
        var senhaDoRequest = request.senha();
        var isSenhaInvalida = !BCrypt.checkpw(senhaDoRequest, senhaDoBanco);
        if (isSenhaInvalida)
            throw new SemAutorizacaoException("Credenciais inválidas");
    }

}
