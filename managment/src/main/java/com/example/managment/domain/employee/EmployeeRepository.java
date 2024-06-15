package com.example.managment.domain.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findAllByOrderByNomeAsc();
    @Query("SELECT SUM(e.salario) FROM employee e")
    Integer sumOfAllSalaries();
    @Query("SELECT e.funcao, e FROM employee e ORDER BY e.funcao, e.nome")
    List<Object[]> findAllGroupedByFuncao();

}
