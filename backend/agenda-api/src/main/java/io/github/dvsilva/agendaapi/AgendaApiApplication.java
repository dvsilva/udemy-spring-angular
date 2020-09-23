package io.github.dvsilva.agendaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgendaApiApplication {

	/**
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ContatoRepository repository) {
		return args -> {
			Contato contato = new Contato();
			contato.setNome("Fulano");
			contato.setEmail("fulano@email.com");
			contato.setFavorito(false);
			repository.save(contato);
		};
	}
	*/
	
	public static void main(String[] args) {
		SpringApplication.run(AgendaApiApplication.class, args);
	}

}
