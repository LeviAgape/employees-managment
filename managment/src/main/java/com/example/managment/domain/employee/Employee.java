package com.example.managment.domain.employee;

import jakarta.persistence.*;
import lombok.*;

@Table(name="employee")
@Entity(name="employee")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Integer born;

    private Integer salary;

    private String role;
}
