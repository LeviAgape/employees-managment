package com.example.managment.controllers;

import com.example.managment.domain.employee.Employee;
import com.example.managment.domain.employee.EmployeeRepository;
import com.example.managment.domain.employee.EmployeeService;
import com.example.managment.domain.employee.RequestEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/ordem/alfabetica")

    public ResponseEntity getOrderName(){
        var employeesOrderByName = repository.findAllByOrderByNomeAsc();

        return  ResponseEntity.ok(employeesOrderByName);
    }


    @GetMapping("/salario")
    public ResponseEntity getSalary(){
        var employeesSalary = repository.sumOfAllSalaries();
        return ResponseEntity.ok(employeesSalary);
    }

    @GetMapping("/separado-por-funcao")
    public ResponseEntity getEmployeesGroupedByFuncao(){
        var employeeFuncao = repository.findAllGroupedByFuncao();
        return ResponseEntity.ok(employeeFuncao);
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
            employee.setNome(data.nome());
            employee.setData_nascimento(data.data_nascimento());
            employee.setSalario(data.salario());
            employee.setFuncao(data.funcao());
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
