package online.codemize.gestaocondominio.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import online.codemize.gestaocondominio.domain.Usuario;
import online.codemize.gestaocondominio.dto.UsuarioPayload;
import online.codemize.gestaocondominio.service.JWTService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTServiceImpl implements JWTService {

    private static final String CHAVE_ASSINATURA_TOKEN = "AKfwBsPyvjtSmH24HrMxRnMxj6yiv2Bekd2DHqrEtE87Q8CiRokOYpdc8y3wY8L7CwoB5bIe4X9Pp4qSIT51CA==";

    @Override
    public String gerarTokenAcesso(Usuario usuario) {
        return Jwts
                .builder()
                .subject(usuario.getNome())
                .claims(construirPayload(usuario))
                .issuedAt(Date.from(Instant.now()))
                .expiration(obterDataExpiracaoToken())
                .signWith(obterSecretKey())
                .compact();
    }

    @Override
    public UsuarioPayload validarTokenExtrairPayload(String token) {
        return null;
    }

    private Map<String, Object> construirPayload(Usuario usuario) {
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("email", usuario.getEmail());
        mapa.put("admin", usuario.getAdmin());
        return mapa;
    }

    private Date obterDataExpiracaoToken() {
        var instant = Instant.now();
        var expiration = instant.plusSeconds(60);
        return Date.from(expiration);
    }

    private SecretKey obterSecretKey() {
        return Keys.hmacShaKeyFor(CHAVE_ASSINATURA_TOKEN.getBytes());
    }

}
