package com.example.managment.domain.employee;

import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

public record RequestEmployee(String name, Integer born, Integer salary, String role) {
}
