package com.example.managment.domain.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@Table(name="employee")
@Entity(name="employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private String nome;

    private Date data_nascimento;

    private Float salario;

    private String funcao;


    public Employee(RequestEmployee requestEmployee){

        this.nome = requestEmployee.nome();
        this.data_nascimento = requestEmployee.data_nascimento();
        this.salario = requestEmployee.salario();
        this.funcao = requestEmployee.funcao();
    }

}
