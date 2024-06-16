package com.example.managment.domain.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RequestEmployee(
        String id,

        String nome,

        LocalDate data_nascimento,

        BigDecimal salario,

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

