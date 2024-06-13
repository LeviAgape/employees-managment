package com.example.managment.domain.employee;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name="employee")
@Entity(name="employee")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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
