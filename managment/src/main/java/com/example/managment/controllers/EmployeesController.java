package com.example.managment.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @GetMapping
    public ResponseEntity getAllEmployees(){
        return  ResponseEntity.ok("deu ok");
    }
}
