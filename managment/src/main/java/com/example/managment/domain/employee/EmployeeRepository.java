package com.example.managment.domain.employee;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findAllByOrderByNomeAsc();
    @Query("SELECT SUM(e.salario) FROM employee e")
    Integer sumOfAllSalaries();
    @Query("SELECT e.funcao, e FROM employee e ORDER BY e.funcao, e.nome")
    List<Object[]> findAllGroupedByFuncao();
    @Transactional
    @Modifying
    @Query("UPDATE employee e SET e.salario = e.salario * 1.1")  // Aumento de 10%
    void applySalaryIncrease();
}
