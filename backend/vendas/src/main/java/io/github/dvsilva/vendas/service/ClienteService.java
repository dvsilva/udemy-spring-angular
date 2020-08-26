package io.github.dvsilva.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dvsilva.vendas.model.Cliente;
import io.github.dvsilva.vendas.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	/**
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@Autowired
	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	*/
	
	public void salvar(Cliente cliente) {
		validar(cliente);
		this.clienteRepository.persistir(cliente);
	}

	private void validar(Cliente cliente) {
		// aplica validacoes		
	}
}
