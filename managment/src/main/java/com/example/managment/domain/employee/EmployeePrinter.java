package com.example.managment.domain.employee;

import com.example.managment.domain.employee.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeePrinter {

    public static String getEmployeesGroupedByFuncaoAsJson(List<Object[]> employeesGroupedByFuncao) {
        // Map para agrupar os funcionários por função
        Map<String, List<Employee>> groupedEmployees = new HashMap<>();

        // Itera sobre os resultados da consulta
        for (Object[] result : employeesGroupedByFuncao) {
            String funcao = (String) result[0];  // Obtém a função
            Employee employee = (Employee) result[1];  // Obtém o funcionário

            // Verifica se a função já está no mapa
            if (!groupedEmployees.containsKey(funcao)) {
                groupedEmployees.put(funcao, new ArrayList<>());
            }

            // Adiciona o funcionário à lista da função correspondente
            groupedEmployees.get(funcao).add(employee);
        }

        // Converter para JSON usando Jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(groupedEmployees);
        } catch (JsonProcessingException e) {
            e.printStackTrace();  // Ou tratar exceção conforme necessário
            return null;
        }
    }
}