package com.app.example.crud;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Ejemplo de crud", version = "1.0", description = "Ejemplo de crud para servicios básicos"))
public class ExampleCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleCrudApplication.class, args);
	}

}
