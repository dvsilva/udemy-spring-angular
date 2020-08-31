package io.github.dvsilva.clientes.rest.exception;

public class UsuarioCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = -2071829620147880648L;

	public UsuarioCadastradoException(String login) {
		super("Usuário já cadastrado para o login " + login);
	}

}
