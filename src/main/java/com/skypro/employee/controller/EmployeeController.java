package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * HTTP методы
 * GET
 * POST
 * PUT
 * PATCH
 * DELETE
 */

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum(){
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employees/salary/min")
    public Employee getEmployeeWageMinimum() {
        return this.employeeService.getEmployeeMinimumWage();
    }

    @GetMapping("/employees/salary/max")
    public Employee getEmployeeWageMaximum() {
        return this.employeeService.getEmployeeMaximumWage();
    }

    @GetMapping("employees/high-salary")
    public Collection<Employee> getEmployeeSalaryHigh() {
        return this.employeeService.getSalaryHigh();
    }
}
