package com.acme.cars.service;

import com.acme.cars.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException; // Importar exceção específica
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/** Serviço responsável pela geração, validação e extração de informações de tokens JWT. */
@Service
public class TokenService {

    private final String SECRET = "MY-SUPER-SECRET-1234"; // Nomes de constantes em UPPER_CASE

    /**
     Gera um token JWT para o usuário fornecido.
     'usuario' O usuário para o qual o token será gerado.
     'return' O token JWT gerado.
    */
    public String generateToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC512(SECRET);
        return JWT.create()
                .withIssuer("ACME.COM")
                .withSubject(usuario.getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)) // Expira em 7 dias (L para long)
                .withIssuedAt(LocalDateTime.now().toInstant(ZoneOffset.UTC)) // Data de emissão em UTC
                .withClaim("email", usuario.getEmail()) // Adiciona o email como uma claim personalizada
                .sign(algorithm);
    }

    /**
      Valida um token JWT e o decodifica.
      'token' O token JWT a ser validado.
      'return' O token decodificado (DecodedJWT) se for válido.
      'IllegalArgumentException' Se o token não comecar com "Bearer" ou for invalido.
      'SignatureVerificationException' Se a assinatura do token for invalida.
    */
    public DecodedJWT isValid(String token) {

        String cleanedToken = extractBearerToken(token);
        Algorithm algorithm = Algorithm.HMAC512(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("ACME.COM")
                .build();
        // A verificação lança exceções se o token for inválido.
        return verifier.verify(cleanedToken);
    }

    /**
      Extrai o ID do usuário (subject) de um token JWT decodificado.
      'decodedJWT' O token JWT decodificado.
      return O ID do usuário como String.
    */
    public String getUsuarioId(DecodedJWT decodedJWT) {

        return decodedJWT.getSubject();
    }

    /** EXPLICAÇAO DO CODIGO
      Remove o prefixo "Bearer " de um token JWT.
      'token' O token completo (ex: "Bearer <token_jwt>").
      'return' A string do token JWT sem o prefixo "Bearer ".
      'throws IllegalArgumentException' Se o token não começar com "Bearer".
     */
    private String extractBearerToken(String token){
        // Evitando Ifs aninhados com guarda de cláusula.
        if (!token.startsWith("Bearer ")) { // Adicionado espaço para remover corretamente
            throw new IllegalArgumentException("Token inválido: deve começar com 'Bearer '");
        }
        return token.replace("Bearer ", "").trim(); // Removendo "Bearer " e espaços em branco
    }
}