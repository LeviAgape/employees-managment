package com.example.managment.domain.employee;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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
    @Query("SELECT e FROM employee e WHERE EXTRACT(MONTH FROM e.data_nascimento) IN (10, 12)")
    List<Employee> findBirthdayOctoberDecember();
    @Query(value = "SELECT e FROM employee e ORDER BY e.data_nascimento ASC")
    List<Employee> findAllOrderByDataNascimentoAsc();
    @Query(value = "SELECT e.nome AS nome_funcionario, e.salario AS salario_funcionario, e.salario / 1212.0 AS salarios_minimos FROM employee e")
    List<Object[]> calculateSalaryMinimum();

}
