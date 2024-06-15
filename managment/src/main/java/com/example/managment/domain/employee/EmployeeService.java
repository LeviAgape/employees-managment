package com.example.managment.domain.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Object[]> getEmployeesGroupedByFuncao() {
        return employeeRepository.findAllGroupedByFuncao();
    }

    public void applySalaryIncrease() {
        employeeRepository.applySalaryIncrease();
    }
}