package com.example.librarymanagmentsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Library MANAGMENT SYSTEM API ",
				version = "1.0.0",
				description = "Library API Documentation"
		),
		servers = @Server(
				url = "http://localhost:8081",
				description = "Library OPEN API url"
		)
)
//http://localhost:8081/swagger-ui/index.html#/
public class LibraryManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagmentSystemApplication.class, args);
	}

}
