package com.acme.cars.controller;

import com.acme.cars.dto.AuthUserDTO;
import com.acme.cars.exception.AuthenticationException;
import com.acme.cars.exception.RecursoNaoEncontradoException;
import com.acme.cars.model.Usuario;
import com.acme.cars.payload.AuthPayload;
import com.acme.cars.service.SecurityService;
import com.acme.cars.service.TokenService;
import com.acme.cars.service.InterfaceUsuarioService; // Importar a interface do serviço
import com.auth0.jwt.exceptions.SignatureVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest; // Importar PageRequest
import org.springframework.data.domain.Pageable; // Importar Pageable

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
  Controlador REST para operações relacionadas a usuários.
  Lida com requisições HTTP e orquestra as chamadas para os serviços de usuário e segurança.
*/
@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final InterfaceUsuarioService usuarioService;
    private final SecurityService securityService;
    private final TokenService tokenService;

    /**
      Lista todos os usuários com suporte a paginação.
       page Número da página (padrão: 0).
       size Tamanho da página (padrão: 9999).
      return ResponseEntity contendo a lista paginada de usuários e o total de itens no cabeçalho.
    */
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuario(
            @RequestHeader(value = "page", defaultValue = "0") int page,
            @RequestHeader(value = "size", defaultValue = "9999") int size) {
        log.info("Requisição para listar usuários - Página: {}, Tamanho: {}", page, size);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Count", String.valueOf(usuarioService.count())); // Adiciona o total de registros no cabeçalho

        // Criar um objeto Pageable e passá-lo ao serviço.
        Pageable pageable = PageRequest.of(page, size);
        List<Usuario> allUsuarios = usuarioService.findAll(pageable); // Passa Pageable ao invés de int, int

        return new ResponseEntity<>(allUsuarios, headers, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> autenticate(@RequestBody AuthUserDTO authUserDTO){
        try {
            String authenticateToken = securityService.authenticate(authUserDTO);
            log.info("Usuário {} autenticado com sucesso.", authUserDTO.email());
            return ResponseEntity.ok(new AuthPayload(authenticateToken));
        } catch (AuthenticationException ex) {
            log.warn("Falha na autenticação para o email {}: {}", authUserDTO.email(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioSalvo = usuarioService.salvar(usuario);
        log.info("Usuário salvo com ID: {}", usuarioSalvo.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado){
        try {
            Usuario usuario = usuarioService.atualizar(id, usuarioAtualizado);
            log.info("Usuário com ID {} atualizado.", id);
            return ResponseEntity.ok(usuario);
        } catch (RecursoNaoEncontradoException e) {
            log.warn("Tentativa de atualizar usuário com ID {} falhou: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            usuarioService.deletar(id);
            log.info("Usuário com ID {} deletado.", id);
            return ResponseEntity.noContent().build();
        } catch (RecursoNaoEncontradoException e) {
            log.warn("Tentativa de deletar usuário com ID {} falhou: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/my-profile")
    public ResponseEntity<?> getMyProfile(
            @RequestHeader(value = "authorization", required = true) String token) {
        try {
            String userId = tokenService.getUsuarioId(tokenService.isValid(token));
            Optional<Usuario> byId = usuarioService.findById(Long.valueOf(userId));
            return byId.map(ResponseEntity::ok).orElseGet(() -> {
                log.warn("Perfil do usuário com ID {} não encontrado após validação do token.", userId);
                return ResponseEntity.notFound().build();
            });
        } catch (SignatureVerificationException ex) {
            log.error("Token de autorização inválido ou expirado: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "Não Autorizado: Token inválido ou expirado."));
        } catch (IllegalArgumentException ex) {
            log.error("Formato de token inválido: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Requisição inválida: " + ex.getMessage()));
        }
    }
}
