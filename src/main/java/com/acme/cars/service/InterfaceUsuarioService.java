package com.acme.cars.service;

import com.acme.cars.model.Usuario;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
  Interface que define o contrato para o serviço de Usuário.
  permitindo que as dependências sejam em abstrações e não em implementações concretas.
*/
public interface InterfaceUsuarioService {

    Optional<Usuario> findByEmail(String email);


    List<Usuario> findAll(Pageable pageable);


    Optional<Usuario> findById(Long id);


    Usuario salvar(Usuario usuario);


    void deletar(Long id);


    Usuario atualizar(Long id, Usuario usuarioAtualizado);


    long count(); /*** Conta o número total de usuários. */
}


