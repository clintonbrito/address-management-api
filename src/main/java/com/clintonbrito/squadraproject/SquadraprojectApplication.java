package com.clintonbrito.squadraproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SquadraprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SquadraprojectApplication.class, args);
	}

}
