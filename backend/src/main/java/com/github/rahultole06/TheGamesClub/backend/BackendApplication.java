package com.github.rahultole06.TheGamesClub.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BackendApplication.class, args);

	}
}
