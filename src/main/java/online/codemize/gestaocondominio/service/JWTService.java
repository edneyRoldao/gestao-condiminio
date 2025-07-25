package online.codemize.gestaocondominio.service;

import online.codemize.gestaocondominio.domain.Usuario;

public interface JWTService {

    String gerarTokenAcesso(Usuario usuario);

    void validarToken(String token);

}
