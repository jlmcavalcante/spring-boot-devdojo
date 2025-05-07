package com.jlmcavalcante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Definir os pacotes-base para que o app consiga ler os beans de outros pacotes.
// @ComponentScan(basePackages = {"outside.jlmcavalcante", "com.jlmcavalcante"})
public class AnimeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimeServiceApplication.class, args);

		// Visualizar todos os Beans que inicializaram com a aplicação:
		// var appicationContext = SpringApplication.run(AnimeServiceApplication.class, args);
		// Arrays.stream(appicationContext.getBeanDefinitionNames()).forEach(System.out::println);
	}
}
