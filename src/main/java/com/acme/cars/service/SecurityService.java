package com.acme.cars.service;

import com.acme.cars.dto.AuthUserDTO;
import com.acme.cars.exception.AuthenticationException;
import com.acme.cars.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/** Serviço responsável pela lógica de segurança e autenticação de usuários. */
@Service
@RequiredArgsConstructor
public class SecurityService {

    private final InterfaceUsuarioService usuarioService;
    private final TokenService tokenService;

    /**
      Autentica um usuário com base em suas credenciais (email e senha).
      authUserDTO Objeto DTO contendo o email e a senha do usuário.
      O token de autenticação JWT se as credenciais forem válidas.
      AuthenticationException Se o usuário ou a senha estiverem incorretos.
    */
    public String authenticate(AuthUserDTO authUserDTO) throws AuthenticationException {
        // Uso de Optional para tratamento seguro de ausência de usuário.
        Optional<Usuario> optionalUser = usuarioService.findByEmail(authUserDTO.email());

        // Evitando Ifs aninhados com guard clauses para melhor legibilidade. Se o usuário não for encontrado pelo email, lança exceção imediatamente.
        if (optionalUser.isEmpty()) {
            throw new AuthenticationException("Usuário ou senha incorretos");
        }

        Usuario usuario = optionalUser.get();

        // Se a senha não corresponder, lança exceção.
        if (!usuario.getPassword().equals(authUserDTO.password())) {
            throw new AuthenticationException("Usuário ou senha incorretos");
        }

        // Se ambos estiverem corretos, gera e retorna o token, autentica e gerar token.
        return tokenService.generateToken(usuario);
    }
}
