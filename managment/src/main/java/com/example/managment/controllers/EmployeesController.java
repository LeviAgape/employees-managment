package com.example.managment.controllers;

import com.example.managment.domain.employee.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public ResponseEntity getAllEmployees(){
        try{
            var allEmployees = repository.findAll();

            return  ResponseEntity.ok(allEmployees);
        }catch(Exception e){
            throw new RuntimeException("Falha ao obter os dados");
        }

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
    public ResponseEntity registerEmployee(@Valid @RequestBody RequestEmployee data){
        try{
            Employee newEmployee = new Employee(data);
            repository.save(newEmployee);
            return ResponseEntity.ok().body("Novo funcionário cadastrado");
        }catch(Exception e){
            throw new EntityNotFoundException("Usuário não foi cadastrado");
        }
    }

    @PostMapping("/aumento/salarial")
    public String applySalaryIncrease() {
        employeeService.applySalaryIncrease();
        return "Aumento salarial aplicado com sucesso!";
    }

    @PutMapping ("/salario")
    public ResponseEntity getSalary(){
        var employeesSalary = repository.sumOfAllSalaries();

        return ResponseEntity.ok().body("A soma dos salários é de: "+employeesSalary);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateEmployee(@Valid @RequestBody RequestEmployee data){
        Optional<Employee> optionalEmployee = repository.findById(data.id());
        if (optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee.setNome(data.nome());
            employee.setData_nascimento(data.data_nascimento());
            employee.setSalario(data.salario());
            employee.setFuncao(data.funcao());
            repository.save(employee);
            return ResponseEntity.ok().body("Funcionário atualizado com sucesso");
        } else {
            throw new EntityNotFoundException("Funcionario com esse id, não foi encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id){
            Optional<Employee> optionalEmployee = repository.findById(id);
            if (optionalEmployee.isPresent()){
                repository.deleteById(id);
                return ResponseEntity.ok().body("Funcionário com id: " + id + "deletado com sucesso");
            }else {
                throw new EntityNotFoundException();
            }
    }

}

