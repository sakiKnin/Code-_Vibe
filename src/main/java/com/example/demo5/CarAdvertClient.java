package com.example.demo5;

import reactor.core.publisher.Mono;
import com.example.demo5.model.CarAdvertDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CarAdvertClient {

	private final WebClient client;

	// Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
	// We can use it to create a dedicated `WebClient` for our component.
	public CarAdvertClient(WebClient.Builder builder) {
		this.client = builder.baseUrl("http://localhost:8080").build();
	}

	public Mono<String> getMessage() {
		return this.client.get().uri("/car/adverts").accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(CarAdvertDTO.class)
				.map(CarAdvertDTO::getTitle);
	}

}

