package com.global_solution.gs_api.models;

public record RestValidationError(
                Integer code,
                String field,
                String message) {

}
