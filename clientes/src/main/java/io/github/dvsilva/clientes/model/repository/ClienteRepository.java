package io.github.dvsilva.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dvsilva.clientes.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
