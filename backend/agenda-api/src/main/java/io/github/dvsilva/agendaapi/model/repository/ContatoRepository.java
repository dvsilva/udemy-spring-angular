package io.github.dvsilva.agendaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.dvsilva.agendaapi.model.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
	
}