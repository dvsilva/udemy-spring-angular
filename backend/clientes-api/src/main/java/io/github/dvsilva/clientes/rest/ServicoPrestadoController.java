package io.github.dvsilva.clientes.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.dvsilva.clientes.model.entity.Cliente;
import io.github.dvsilva.clientes.model.entity.ServicoPrestado;
import io.github.dvsilva.clientes.model.repository.ClienteRepository;
import io.github.dvsilva.clientes.model.repository.ServicoPrestadoRepository;
import io.github.dvsilva.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.dvsilva.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
// @CrossOrigin("http://localhost:4200")
public class ServicoPrestadoController {

	private final ServicoPrestadoRepository repository;
	private final ClienteRepository clienteRepository;
	private final BigDecimalConverter bigDecimalConverter;

	// @RequiredArgsConstructor -> cria esse método
	// public ServicoPrestadoController(ServicoPrestadoRepository repository,
	// ClienteRepository clienteRepository) {
	// this.repository = repository;
	// this.clienteRepository = clienteRepository;
	// }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@Valid @RequestBody ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		Integer idCliente = dto.getIdCliente();
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));

		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

		return repository.save(servicoPrestado);
	}

	@GetMapping
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes) {

		return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
	}

	/**
	 * @GetMapping public List<ServicoPrestado> obterTodos() { return
	 *             repository.findAll(); }
	 */

	@GetMapping("{id}")
	public ServicoPrestado acharPorId(@PathVariable Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ServicoPrestado não encontrado"));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		repository.findById(id).map(ServicoPrestado -> {
			repository.delete(ServicoPrestado);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ServicoPrestado não encontrado"));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @Valid @RequestBody ServicoPrestado ServicoPrestadoAtualizado) {
		repository.findById(id).map(ServicoPrestado -> {
			// ServicoPrestado.setNome(ServicoPrestadoAtualizado.getNome());
			// ServicoPrestado.setCpf(ServicoPrestadoAtualizado.getCpf());
			return repository.save(ServicoPrestado);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ServicoPrestado não encontrado"));
	}
}
