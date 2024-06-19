package com.example.managment.domain.employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeePrinter {

    public static String getEmployeesGroupedByFuncao(List<Object[]> employeesGroupedByFuncao) {
        Map<String, List<Employee>> groupedEmployees = new HashMap<>();

        for (Object[] result : employeesGroupedByFuncao) {
            String funcao = (String) result[0];
            Employee employee = (Employee) result[1];

            if (!groupedEmployees.containsKey(funcao)) {
                groupedEmployees.put(funcao, new ArrayList<>());
            }

            groupedEmployees.get(funcao).add(employee);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.writeValueAsString(groupedEmployees);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}