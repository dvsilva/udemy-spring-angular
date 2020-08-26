package io.github.dvsilva.vendas.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import io.github.dvsilva.vendas.anotations.Development;

@Development
public class MinhaConfiguration {

	@Bean(name = "applicationName")
	public String applicationName() {
		return "Sistema de Vendas";
	}

	// executa comando apos inicializacao da app
	@Bean
	public CommandLineRunner executar() {
		return args -> {
			System.out.println("Rodando a configuração de desenvolvimento");
		};
	}
}
