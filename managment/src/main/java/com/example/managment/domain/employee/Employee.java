package com.example.managment.domain.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    private LocalDate data_nascimento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private BigDecimal salario;

    private String funcao;


    public Employee(RequestEmployee requestEmployee){

        this.nome = requestEmployee.nome();
        this.data_nascimento = requestEmployee.data_nascimento();
        this.salario = requestEmployee.salario();
        this.funcao = requestEmployee.funcao();
    }

}
