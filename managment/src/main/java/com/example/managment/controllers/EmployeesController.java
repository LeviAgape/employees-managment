package com.example.managment.controllers;

import com.example.managment.domain.employee.*;
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
    @Autowired
    private EmployeeService employeeService;
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

    @GetMapping("/separado/por/funcao")
    public ResponseEntity<String> getEmployeesGroupedByFuncao() {
        List<Object[]> employeesGroupedByFuncao = employeeService.getEmployeesGroupedByFuncao();
        return ResponseEntity.ok(EmployeePrinter.getEmployeesGroupedByFuncao(employeesGroupedByFuncao));
    }

    @GetMapping("/nascidos/10/12")
    public List<Employee> getBirthdayOctoberDecember() {
        return employeeService.getBirthdayOctoberDecember();
    }

    @GetMapping("/mais/velho")
    public ResponseEntity<Employee> getOldestEmployee() {
        List<Employee> employees = repository.findAllOrderByDataNascimentoAsc();

        if (employees.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Employee oldestEmployee = employees.get(0);

        return ResponseEntity.ok(oldestEmployee);
    }

    @GetMapping("salarios/minimos")
    public ResponseEntity<List<Object[]>> getSalariosMinimos() {
        List<Object[]> salariosMinimos = employeeService.getSalariosMinimos();
        return ResponseEntity.ok(salariosMinimos);
    }

    @PostMapping
    public ResponseEntity registerEmployee(@RequestBody RequestEmployee data){
        Employee newEmployee = new Employee(data);
        repository.save(newEmployee);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/aumento/salarial")
    public String applySalaryIncrease() {
        employeeService.applySalaryIncrease();
        return "Aumento salarial aplicado com sucesso!";
    }

    @PutMapping ("/salario")
    public ResponseEntity getSalary(){
        var employeesSalary = repository.sumOfAllSalaries();
        return ResponseEntity.ok(employeesSalary);
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
