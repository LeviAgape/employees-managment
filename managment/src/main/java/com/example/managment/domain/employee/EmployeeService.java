package com.example.managment.domain.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Object[]> getEmployeesGroupedByFuncao() {
        return employeeRepository.findAllGroupedByFuncao();
    }

    @Transactional
    public void applySalaryIncrease() {
        employeeRepository.applySalaryIncrease();
    }

    public List<Employee> getBirthdayOctoberDecember() {
        return employeeRepository.findBirthdayOctoberDecember();
    }

    public Employee getOldestEmployee() {
        return (Employee) employeeRepository.findAllOrderByDataNascimentoAsc();
    }

    public List<Object[]> getSalariosMinimos() {
        return employeeRepository.calculateSalaryMinimum();
    }

}