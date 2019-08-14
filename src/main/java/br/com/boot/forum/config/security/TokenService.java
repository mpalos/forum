package br.com.boot.forum.config.security;

import br.com.boot.forum.modelo.Usuario;
import com.sun.prism.shader.DrawSemiRoundRect_RadialGradient_REPEAT_AlphaTest_Loader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date today = new Date();
        Date exp = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API do forum")
                .setSubject(logado.getId().toString())
                .setIssuedAt(today)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
