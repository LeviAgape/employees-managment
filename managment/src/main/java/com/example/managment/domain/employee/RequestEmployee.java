package com.example.managment.domain.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RequestEmployee(
        String id,

        String name,

        Integer born,

        Integer salary,

        String role) {

    public RequestEmployee{
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("name não pode ser nulo ou vazio");
        }
        if (role == null || role.isBlank()){
            throw new IllegalArgumentException("name não pode ser nulo");
        }
    }
}

