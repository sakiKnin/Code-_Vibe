package com.example.demo5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
               contact = @Contact(
                       name = "Boris Sarić",
                       email = "boris.saric@gmail.com",
                       url = "https://github.com/sakiKnin"
               ),
               description = "OpenAPI for car adverts",
               title = "Open API - Car Adverts",
               version = "1.0.0",
               license = @License(
                       name = "Code Vibe",
                       url = "https://www.codevibe.ai/"
               ),
               termsOfService = "Test terms"
        ),
        servers = {
            @Server(
                    description = "Local ENV",
                    url = "http://localhost:8080"
            )
        },
        tags = {
            @Tag(
                    name = "Get all adverts",
                    description = "Endpoint fetches all car adverts and sorts them by id"
            ),
            @Tag(
                    name = "Get advert by id",
                    description = "Endpoint fetches a car advert"
            ),
            @Tag(
                    name = "Create an advert",
                    description = "Endpoint creats a car advert"
            ),
            @Tag(
                    name = "Edit an advert",
                    description = "Endpoint edits a car advert"
            ),
            @Tag(
                    name = "Remove an advert",
                    description = "Endpoint removes a car advert"
            )
        },
        security = {
            @SecurityRequirement(
                    name = "basicAuth"
            )
        }
)
 
@SecurityScheme(
        name = "basicAuth",
        description = "Basic auth",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "Basic",
        in = SecuritySchemeIn.HEADER
)
 
public class Demo5Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo5Application.class, args);
	}

}
