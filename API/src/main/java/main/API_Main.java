package main;


import java.util.Collections;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({ "controller", "dao", "dto", "repository", "service", "feign" })
@EntityScan(basePackages = { "dao" })
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
@EnableJpaRepositories(basePackages = { "repository" })
public class API_Main {

	public static void main(String[] args) {
		SpringApplication.run(API_Main.class, args);
	}
	
	@Autowired 
	EntityManagerFactory entityManagerFactory;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {

		return new ApiInfo(

				"Terra Terra",

				"Terra-Terra API",

				"API 1.0",

				"User List",

				new Contact("User", "localhost:8085/", "Mail"),

				"License of API", "API license URL", Collections.emptyList());

	}

}
