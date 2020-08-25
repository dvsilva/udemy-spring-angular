package io.github.dvsilva.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dvsilva.clientes.model.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}
