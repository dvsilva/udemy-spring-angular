package io.github.dvsilva.clientes.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // spring precisa desse construtor
public class ServicoPrestadoDTO {

	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	@NotEmpty(message = "{campo.data.obrigatorio}")
	private String preco;
	
	@NotEmpty(message = "{campo.preco.obrigatorio}")
	private String data;

	@NotNull(message = "{campo.cliente.obrigatorio}")
	private Integer idCliente;
	
}
