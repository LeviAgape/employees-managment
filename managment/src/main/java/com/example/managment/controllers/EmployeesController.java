package com.example.managment.controllers;

import com.example.managment.domain.employee.Employee;
import com.example.managment.domain.employee.EmployeeRepository;
import com.example.managment.domain.employee.RequestEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeRepository repository;
    @GetMapping
    public ResponseEntity getAllEmployees(){
        var allEmployees = repository.findAll();
        return  ResponseEntity.ok(allEmployees);
    }

    //TODO
    @PostMapping
    public ResponseEntity registerEmployee(@RequestBody RequestEmployee data){
        Employee newEmployee = new Employee(data);
        repository.save(newEmployee);
        return ResponseEntity.ok().build();
    }
}
