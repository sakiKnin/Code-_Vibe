package com.example.demo5.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarAdvertDTO{
   @Valid
  
   public Long id;

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
   
    public CarAdvertDTO(Long id, String title, String fuelType, Double price, Boolean isNew, Integer mileage, LocalDateTime firstRegistration) {
        this.id = id;
        this.title = title;
        this.fuelType = fuelType;
        this.price = price;
        this.isNew = isNew;
        this.mileage = mileage;
        this.firstRegistration = firstRegistration;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public LocalDateTime getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(LocalDateTime firstRegistration) {
        this.firstRegistration = firstRegistration;
    }
   
}