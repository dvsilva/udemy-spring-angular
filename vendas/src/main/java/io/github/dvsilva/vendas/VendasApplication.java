package io.github.dvsilva.vendas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dvsilva.vendas.anotations.Cachorro;
import io.github.dvsilva.vendas.model.Animal;

@SpringBootApplication
@RestController
public class VendasApplication {

	// obtem da classe de conf MinhaConfiguration.java
	// @Autowired
	// @Qualifier("applicationName")
	// obtem do application.properties
	@Value("${application.name}")
	private String applicationName;

	// @Autowired
	// @Qualifier("gato")
	// @Gato
	@Cachorro
	private Animal animal;

	// executa comando apos inicializacao da app
	@Bean(name = "executarAnimal")
	public CommandLineRunner executar() {
		return args -> {
			this.animal.fazerBarulho();
		};
	}

	@GetMapping("/hello")
	public String helloWorld() {
		// return "hello world";
		return applicationName;
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
