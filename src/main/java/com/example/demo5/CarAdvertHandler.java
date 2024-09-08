package com.example.demo5;

import java.util.*;
import com.example.demo5.exception.CarAdvertAPIException;
import com.example.demo5.model.CarAdvertDTO;
import com.example.demo5.model.CarAdvertRequestBody;
import com.example.demo5.exception.ValidationExceptionAPIResponse;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import org.springframework.validation.Validator;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.reactive.function.BodyExtractors;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

@Service
public class CarAdvertHandler {
    
    private final ICarAdvertRepository advertRepository;
    private final Validator validator;
    
    @Autowired
    public CarAdvertHandler(ICarAdvertRepository advertRepository, Validator validator) {
        this.advertRepository = advertRepository;
        this.validator = validator;
    }
 
    public Mono<ServerResponse> getAllCarAdverts(ServerRequest request) {
                String sortBy = request.queryParam("sortby").orElse("id");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromPublisher(CarAdvertMapper.toCarAdvertDTOList(this.advertRepository.getAll(sortBy)), CarAdvertDTO.class));
    }
     
    public Mono<ServerResponse> getCarAdvertById(ServerRequest request) {
                long id = Long.parseLong(request.pathVariable("id"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromPublisher(CarAdvertMapper.toMonoCarAdvertDTO(this.advertRepository.findAdvertById(id)), CarAdvertDTO.class));
    }
    
    public Mono<ServerResponse> insertCarAdvert(ServerRequest request) {
                return request.body(BodyExtractors.toMono(CarAdvertDTO.class))
                    .flatMap(body -> {
                        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(body, CarAdvertRequestBody.class.getName());
                        validator.validate(body, errors);
                        if (errors.hasErrors()) {
                            List<String> errorMessages = errors.getAllErrors().stream()
                                        .map(ObjectError::getDefaultMessage)
                                        .collect(Collectors.toList());
                            return ServerResponse.status(422).bodyValue(new ValidationExceptionAPIResponse(errorMessages));
                        }
                        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromPublisher(CarAdvertMapper.toMonoCarAdvertDTO(this.advertRepository.create(body.getTitle(),body.getFuelType(),body.getPrice(),body.getIsNew(),body.getMileage(),body.getFirstRegistration())), CarAdvertDTO.class));
                    })
                .onErrorResume(e -> ServerResponse.status(400).bodyValue("Error: " + e.getMessage()));
    }
    
    public Mono<ServerResponse> editCarAdvert(ServerRequest request) {
                return request.body(BodyExtractors.toMono(CarAdvertDTO.class))
                    .flatMap(body -> {
                        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(body, CarAdvertDTO.class.getName());
                        validator.validate(body, errors);
                        if (errors.hasErrors()) {
                            List<String> errorMessages = errors.getAllErrors().stream()
                                        .map(ObjectError::getDefaultMessage)
                                        .collect(Collectors.toList());
                            return ServerResponse.status(422).bodyValue(new ValidationExceptionAPIResponse(errorMessages));
                        }
                        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromPublisher(CarAdvertMapper.toMonoCarAdvertDTO(this.advertRepository.edit(body.getId(), body.getTitle(),body.getFuelType(),body.getPrice(),body.getIsNew(),body.getMileage(),body.getFirstRegistration())), CarAdvertDTO.class));
                    })
                .onErrorResume(e -> ServerResponse.status(400).bodyValue("Error: " + e.getMessage()));
    }
    
    public Mono<ServerResponse> removeCarAdvertById(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return Mono.fromCallable(() -> {
            return advertRepository.remove(id);
        }).flatMap(deleted -> deleted ? 
                ServerResponse.noContent().build() : 
                ServerResponse.notFound().build())
            .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));
        }

}
