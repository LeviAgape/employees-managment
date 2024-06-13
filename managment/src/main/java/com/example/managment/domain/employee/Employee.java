package com.example.managment.domain.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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


    private String name;

    private Integer born;

    private Integer salary;

    private String role;

    public Employee(RequestEmployee requestEmployee){

        this.name = requestEmployee.name();
        this.born = requestEmployee.born();
        this.salary = requestEmployee.salary();
        this.role = requestEmployee.role();
    }

}
