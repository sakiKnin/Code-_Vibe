package com.example.demo5;

import com.example.demo5.model.CarAdvertDTO;
import com.example.demo5.model.CarAdvertRequestBody;
import com.example.demo5.exception.ValidationExceptionAPIResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.example.demo5.exception.CarAdvertAPIException;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import org.springframework.http.HttpStatus;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration(proxyBeanMethods = false)
public class CarAdvertRouter {
	
        @RouterOperations(
                {
                    @RouterOperation(
                            path = "/car/adverts/",
                            produces = {
                                MediaType.APPLICATION_JSON_VALUE
                            },
                            beanClass = CarAdvertHandler.class, 
                            method = RequestMethod.GET, 
                            beanMethod = "getAllCarAdverts",
                            operation = @Operation(
                                tags = {"Get all adverts"},
                                description = "Returns a list of adverts and sorts them by query attribute sortby, if present, if not sorts by id",
                                summary = "Endpoint fetches all car adverts and sorts them by quer param sortby, if not provided sorts by id",
                                operationId = "getAllCarAdverts", 
                                responses = {
                                    @ApiResponse(responseCode = "200", 
                                            description = "successful operation",
                                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CarAdvertDTO.class)))
                                    ),
                                    @ApiResponse(responseCode = "401", 
                                            description = "Unauthorized user."
                                    )
                                },
                                parameters = {
                                    @Parameter(
                                        in = ParameterIn.QUERY,
                                        name = "sortby",
                                        description = "Parameter to sort the adverts, sort by title,fuel_type, is_new,milege and first_registration",
                                        required = false
                                    )
                                }
                            )
                    ),
                    @RouterOperation(
                            path = "/car/adverts/{id}", 
                            produces = {
                                MediaType.APPLICATION_JSON_VALUE
                            },
                            beanClass = CarAdvertHandler.class, 
                            method = RequestMethod.GET, 
                            beanMethod = "getCarAdvertById",
                            operation = @Operation(
                                    tags = {"Get advert by id"},
                                    description = "Returns one advert form the cart_advert table with status 200 if present, if not returns status 404",
                                    summary = "Endpoint returns an advert selected by id",
                                    operationId = "getCarAdvertById", 
                                    responses = {
                                        @ApiResponse(responseCode = "200", 
                                                description = "successful operation",
                                                content = @Content(schema = @Schema(implementation = CarAdvertDTO.class))),
                                        @ApiResponse(responseCode = "404", 
                                            description = "Non existing advert"
                                        ),
                                        @ApiResponse(responseCode = "401", 
                                                description = "Unauthorized user"
                                        )
                                    },
                                    parameters = {
                                        @Parameter(in = ParameterIn.PATH, 
                                                        name = "id",
                                                        description = "Car advert id"
                                        )
                                    }
                            )
                    ),
                    @RouterOperation(
                            path = "/car/adverts/", 
                            produces = {
                                MediaType.APPLICATION_JSON_VALUE
                            }, 
                            method = RequestMethod.POST, 
                            beanClass = CarAdvertHandler.class, 
                            beanMethod = "insertCarAdvert",
                            operation = @Operation(
                                tags = {"Create an advert"},
                                description = "Inserts an advert in the cart_advert table",
                                summary = "Endpoint creats a car advert",
                                operationId = "insertCarAdvert", 
                                responses = {
                                    @ApiResponse(responseCode = "200", 
                                        description = "Successful operation", 
                                        content = @Content(schema = @Schema(implementation = CarAdvertDTO.class))
                                    ),
                                    @ApiResponse(responseCode = "400", 
                                        description = "Json is invalid or cannot be parsed"
                                    ),
                                    @ApiResponse(responseCode = "422", 
                                        description = "Unprocessable entity, validation failed",
                                        content = @Content(schema = @Schema(implementation = ValidationExceptionAPIResponse.class))
                                    ),
                                    @ApiResponse(responseCode = "401", 
                                        description = "Unauthorized user."
                                    )
                                },
                                requestBody = @RequestBody(
                                        content = @Content(
                                            schema = @Schema(implementation = CarAdvertRequestBody.class)
                                        ),
                                            description = "The request body should include the following details: title, price, mileage, fuel type, a flag indicating whether the car is used, and the date of first registration."
                                )
                            )
                                
                    ),
                    @RouterOperation(
                            path = "/car/adverts/", 
                            produces = {
                                MediaType.APPLICATION_JSON_VALUE
                            }, 
                            method = RequestMethod.PUT, 
                            beanClass = CarAdvertHandler.class, 
                            beanMethod = "editCarAdvert",
                            operation = @Operation(
                                tags = {"Edit an advert"},
                                description = "Edits an advert in the cart_advert table",
                                summary = "Endpoint edits an advert",
                                operationId = "editCarAdvert", 
                                responses = {
                                    @ApiResponse(responseCode = "200", 
                                        description = "Successful operation", 
                                        content = @Content(schema = @Schema(implementation = CarAdvertDTO.class))
                                    ),
                                    @ApiResponse(responseCode = "400", 
                                        description = "Json is invalid or cannot be parsed"
                                    ),
                                    @ApiResponse(responseCode = "422", 
                                        description = "Unprocessable entity, validation failed",
                                        content = @Content(schema = @Schema(implementation = ValidationExceptionAPIResponse.class))
                                    ),
                                    @ApiResponse(responseCode = "401", 
                                        description = "Unauthorized user."
                                    )
                                },
                                requestBody = @RequestBody(
                                        content = @Content(
                                            schema = @Schema(implementation = CarAdvertDTO.class)),
                                            description = "The request body should include the following details: id, title, price, mileage, fuel type, a flag indicating whether the car is used, and the date of first registration."
                                        )
                                )
                                
                    ),
                    @RouterOperation(
                            path = "/car/adverts/{id}",
                            method = RequestMethod.DELETE, 
                            beanClass = CarAdvertHandler.class, 
                            beanMethod = "removeCarAdvertById",
                            operation = @Operation(
                                tags = {"Remove an advert"},
                                description = "Removes an advert from the cart_advert table, id should be provided",
                                summary = "Endpoint removes a car advert",
                                operationId = "removeCarAdvertById", 
                                responses = {
                                    @ApiResponse(responseCode = "204", 
                                        description = "Successful operation"
                                    ),
                                    @ApiResponse(responseCode = "404", 
                                            description = "Non existing advert"
                                        ),
                                    @ApiResponse(responseCode = "401", 
                                        description = "Unauthorized user."
                                    )
                                },
                                parameters = {
                                        @Parameter(in = ParameterIn.PATH, 
                                                        name = "id",
                                                        description = "Car advert id"
                                        )
                                    }
                            )
                                
                    )
                }
        )
        
        @Bean
	public RouterFunction<ServerResponse> route(CarAdvertHandler advertHandler) {
		return RouterFunctions
			.route()
                            .path("/car/adverts", b -> b
                                .nest(accept(MediaType.APPLICATION_JSON), b1 -> b1
                                    .GET("/", accept(MediaType.APPLICATION_JSON), advertHandler::getAllCarAdverts)
                                    .GET("/{id}", accept(MediaType.APPLICATION_JSON), advertHandler::getCarAdvertById)
                                    .POST("/", accept(MediaType.APPLICATION_JSON), advertHandler::insertCarAdvert)
                                    .PUT("/", accept(MediaType.APPLICATION_JSON), advertHandler::editCarAdvert)
                                    .DELETE("/{id}", accept(MediaType.APPLICATION_JSON), advertHandler::removeCarAdvertById)
                            )
                            .filter((request, next) -> {    
                                    if (request.headers().header("Authorization").isEmpty()) {
                                        return ServerResponse.status(HttpStatus.UNAUTHORIZED).bodyValue("Authorization header is missing");
                                    }else if(!request.headers().header("Authorization").get(0).equals("Basic dXNlcjpwYXNzd29yZA==")){
                                        return ServerResponse.status(HttpStatus.UNAUTHORIZED).bodyValue("Wrong credetials");
                                    }else{
                                        return next.handle(request);
                                    }
                            })
                        )
                        //.onError(ElementNotFoundException.class,errorHandler::elementNotFoundHandler)
                        .build();
	}
}
