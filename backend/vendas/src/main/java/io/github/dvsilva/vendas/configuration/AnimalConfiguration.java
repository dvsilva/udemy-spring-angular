package io.github.dvsilva.vendas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.dvsilva.vendas.model.Animal;

@Configuration
public class AnimalConfiguration {

	@Bean(name = "cachorro")
	public Animal cachorro() {
		return new Animal() {
			
			@Override
			public void fazerBarulho() {
				System.out.println("Au au");
			}
		};
	}
	
	@Bean(name = "gato")
	public Animal gato() {
		return new Animal() {
			
			@Override
			public void fazerBarulho() {
				System.out.println("Miau");
			}
		};
	}
}
