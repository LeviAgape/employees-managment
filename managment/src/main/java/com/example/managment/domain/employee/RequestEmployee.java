package com.example.managment.domain.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.UUID;

public record RequestEmployee(
        String id,

        String nome,

        Date data_nascimento,

        Float salario,

        String funcao

        ) {

    public RequestEmployee{
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("name não pode ser nulo ou vazio");
        }
        if (funcao == null || funcao.isBlank()){
            throw new IllegalArgumentException("name não pode ser nulo");
        }
    }
}

