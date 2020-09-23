package io.github.dvsilva.clientes.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dvsilva.clientes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByUsername(String username);

	// select count(*) > 0 from usuario where username = :login
	boolean existsByUsername(String username);

}
