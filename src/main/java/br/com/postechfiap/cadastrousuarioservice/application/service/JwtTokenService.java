package br.com.postechfiap.cadastrousuarioservice.application.service;

import br.com.postechfiap.cadastrousuarioservice.domain.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private static final String ISSUER = "fiap-hackaton";

    private final com.nimbusds.jose.jwk.RSAKey rsaKey;

    public String generateToken(UserDetailsImpl user) {
        try {
            RSAPrivateKey privateKey = rsaKey.toRSAPrivateKey();
            RSAPublicKey publicKey = rsaKey.toRSAPublicKey();

            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

            String roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsername())
                    .withClaim("roles", roles)
                    .withKeyId(rsaKey.getKeyID()) // Adiciona o ID da chave no token
                    .sign(algorithm);

        } catch (Exception exception){ // Captura Exception genérica por causa do .toRSAPrivateKey()
            throw new JWTCreationException("Erro ao gerar token com chave RSA.", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            RSAPublicKey publicKey = rsaKey.toRSAPublicKey();
            Algorithm algorithm = Algorithm.RSA256(publicKey, null);

            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception exception){
            throw new JWTVerificationException("Token inválido ou expirado.", exception);
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(4).toInstant();
    }
}