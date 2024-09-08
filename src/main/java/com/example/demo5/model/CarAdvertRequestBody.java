package com.example.demo5.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarAdvertRequestBody {
   
   @Valid
    
   @NotNull(message = "Title is mandatory")
   @NotBlank(message = "Title can not be blank")
   public String title;

   @JsonProperty("fuel_type")
   @NotNull(message = "Fuel type is mandatory")
   @NotBlank(message = "Fuel type can not be blank")
   public String fuelType;

   @NotNull(message = "Title is mandatory")
   @Min(value = 1, message = "Price must be a positive number") 
   public Double price;

   @JsonProperty("is_new")
   public Boolean isNew;

   @NotNull(message = "Mileage is mandatory")
   @Min(value = 1, message = "Price must be a positive number") 
   public Integer mileage;
   
   @JsonProperty("first_registration")
   @NotNull(message = "First registration is mandatory")
   public LocalDateTime firstRegistration;
    
}
