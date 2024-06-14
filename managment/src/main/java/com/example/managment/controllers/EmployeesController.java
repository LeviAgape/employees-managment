package com.example.managment.controllers;

import com.example.managment.domain.employee.Employee;
import com.example.managment.domain.employee.EmployeeRepository;
import com.example.managment.domain.employee.RequestEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PostMapping
    public ResponseEntity registerEmployee(@RequestBody RequestEmployee data){

        Employee newEmployee = new Employee(data);
        repository.save(newEmployee);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateEmployee(@RequestBody RequestEmployee data){
        Optional<Employee> optionalEmployee = repository.findById(data.id());
        if (optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee.setName(data.name());
            employee.setBorn(data.born());
            employee.setSalary(data.salary());
            employee.setRole(data.role());
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id){
        Optional<Employee> optionalEmployee = repository.findById(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
