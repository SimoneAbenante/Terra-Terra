package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "controller", "dao", "dto", "rep", "service" })
@SpringBootApplication
public class TerraTerraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerraTerraApplication.class, args);
	}

}
