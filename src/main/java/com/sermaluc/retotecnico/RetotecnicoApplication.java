package com.sermaluc.retotecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.sermaluc.retotecnico.model")
@SpringBootApplication
public class RetotecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetotecnicoApplication.class, args);
	}

}
