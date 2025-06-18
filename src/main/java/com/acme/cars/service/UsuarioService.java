package com.acme.cars.service;

import com.acme.cars.exception.RecursoNaoEncontradoException;
import com.acme.cars.model.Usuario;
import com.acme.cars.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Serviço responsável pelas operações de negócio relacionadas a usuários. */
@Service
@RequiredArgsConstructor
public class UsuarioService implements InterfaceUsuarioService {

    private final EntityManager entityManager;
    private final UsuarioRepository usuarioRepository;

    /**
      Busca um usuário pelo seu endereço de e-mail.
      parametro 'email' O e-mail do usuário.
      'return' Um Optional contendo o usuário, se encontrado; Optional.empty() caso contrário.
    */
    @Override
    public Optional<Usuario> findByEmail(String email) {

        Usuario foundUser = usuarioRepository.findByEmail(email);
        // Retorna Optional.of(foundUser) se o usuário for encontrado, senão Optional.empty().
        return Optional.ofNullable(foundUser);
    }

    /**
      Lista todos os usuários de forma paginada.
      parametro 'pageable' Objeto Pageable contendo informações de paginação (número da página, tamanho).
      return Uma lista de usuários para a página solicitada.
    */
    @Override
    public List<Usuario> findAll(Pageable pageable) {

        return usuarioRepository.findAll(pageable).stream().toList();
    }

    /**
      Busca um usuário pelo seu identificador único.
      Remove a senha do objeto Usuario antes de retorná-lo para segurança.
      parametro 'id' O ID do usuário.
      'return' Um Optional contendo o usuário sem a senha, se encontrado.
     */
    @Override
    public Optional<Usuario> findById(Long id) {
        Optional<Usuario> byId = usuarioRepository.findById(id);
        if (byId.isPresent()) {
            Usuario usuario = byId.get();
            usuario.setPassword(null); // Define a senha como null antes de retornar
            return Optional.of(usuario);
        }
        return Optional.empty();
    }


    @Override
    public Usuario salvar(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Override
    public void deletar(Long id) {
        // Primeiro verifica se existe para lançar a exceção correta, depois deleta.
        usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id));
        usuarioRepository.deleteById(id);
    }

    /**
      Atualiza os dados de um usuário existente.
      parametro 'id' O ID do usuário a ser atualizado.
      parametro 'usuarioAtualizado' Os novos dados do usuário.
      'return' O usuário com os dados atualizados.
      'throws RecursoNaoEncontradoException' Se o usuário não for encontrado para atualizar.
    */
    @Override
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        // Nomes significativos: 'usuarioAtualizado' indica o propósito do parâmetro.
        // Evitando Ifs aninhados: Lançamento de exceção como guarda de cláusula.
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id);
        }
        usuarioAtualizado.setId(id); // Garante que o ID do usuário a ser atualizado seja o do path.
        return usuarioRepository.save(usuarioAtualizado);
    }

    /**
      Retorna a contagem total de usuários registrados.
      'return' Retorna o número total de usuários.
    */
    @Override
    public long count(){
        return usuarioRepository.count();
    }
}