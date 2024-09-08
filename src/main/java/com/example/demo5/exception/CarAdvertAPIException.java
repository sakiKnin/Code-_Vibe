package com.example.demo5.exception;

import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
public class CarAdvertAPIException extends RuntimeException {
    
    public CarAdvertAPIException(String message) {
        super(message);
    }
    
    Mono<ServerResponse> notFound(ServerRequest request){
            return ServerResponse.notFound().build();
    }
    
}