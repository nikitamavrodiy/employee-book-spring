package com.skypro.employee.service;

import com.skypro.employee.exception.EmployeeIllegalArgumentException;
import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();


    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (StringUtils.isAnyBlank(employeeRequest.getFirstName(), employeeRequest.getLastName())
                || !StringUtils.isAlpha(employeeRequest.getFirstName() + employeeRequest.getLastName())) {

            throw new EmployeeIllegalArgumentException();
        }
        Employee employee = new Employee(StringUtils.capitalize(employeeRequest.getFirstName()),
                StringUtils.capitalize(employeeRequest.getLastName()),
                employeeRequest.getDepartmnet(),
                employeeRequest.getSalary());

        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return this.employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getEmployeeMinimumWage() {
        return this.employees.values().stream()
                .min(Comparator.comparing(Employee::getSalary)).get();
    }

    public Employee getEmployeeMaximumWage() {
        return this.employees.values().stream()
                .max(Comparator.comparing(Employee::getSalary)).get();
    }

    public Collection<Employee> getSalaryHigh() {
        var average = employees.values().stream()
                .mapToInt(Employee::getSalary)
                .summaryStatistics()
                .getAverage();
        return employees.values().stream()
                .filter(e -> e.getSalary() > average)
                .collect(Collectors.toList());
    }
}
