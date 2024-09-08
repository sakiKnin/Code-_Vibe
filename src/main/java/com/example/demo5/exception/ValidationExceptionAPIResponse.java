package com.example.demo5.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ValidationExceptionAPIResponse {
    @JsonProperty("validation_errors")
    private List<String> validationErrors;

    public ValidationExceptionAPIResponse(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
