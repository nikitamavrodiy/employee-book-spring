package com.skypro.employee;

import com.skypro.employee.exception.EmployeeIllegalArgumentException;
import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeService();
        Stream.of(
                new EmployeeRequest("TestOne","TestOne",1,5000),
                new EmployeeRequest("TestTwo","TestTwo",1,6000),
                new EmployeeRequest("TestThree","TestThree",1,7000),
                new EmployeeRequest("TestFour","TestFour",2,8000),
                new EmployeeRequest("TestFive","TestFive",2,9000)
        ).forEach(employeeService::addEmployee);
        employeeService.getAllEmployees().iterator().next().setCounter(0);
    }

    @Test
    public void addEmployee(){
        EmployeeRequest request = new EmployeeRequest(
                "Valid", "Valid", 3, 5000);
        Employee result = employeeService.addEmployee(request);
        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getDepartment(), result.getDepartment());
        assertEquals(request.getSalary(), result.getSalary());

        Assertions
                .assertThat(employeeService.getAllEmployees()
                .contains(result));
    }

    @Test
    public void addEmployeeThrowException(){
        EmployeeRequest request = new EmployeeRequest(
                "Valid23", "Vali2d", 3, 5000);
        assertThrows(EmployeeIllegalArgumentException.class, () -> employeeService.addEmployee(request));
    }

    @Test
    public void addEmployeeCapitalize() {
        Employee result = employeeService.addEmployee(new EmployeeRequest("valid", "valid", 3, 5000));
        assertEquals("Valid", result.getFirstName());
        assertEquals("Valid", result.getLastName());
    }

    @Test
    public void listEmployees() {
        Collection<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees).hasSize(5);
        Assertions.assertThat(employees)
                .first()
                .extracting(Employee::getFirstName)
                .isEqualTo("TestOne");

    }

    @Test
    public void sumOfSalaries() {
        int sum = employeeService.getSalarySum();
        Assertions.assertThat(sum).isEqualTo(35_000);
    }

    @Test
    public void employeeWithMaxSalary() {
        Employee employee = employeeService.getEmployeeMaximumWage();
        Assertions.assertThat(employee)
                .isNotNull()
                .extracting(Employee::getFirstName)
                .isEqualTo("TestFive");
    }

    @Test
    public void employeeWithMinSalary() {
        Employee employee = employeeService.getEmployeeMinimumWage();
        Assertions.assertThat(employee)
                .isNotNull()
                .extracting(Employee::getFirstName)
                .isEqualTo("TestOne");
    }

    @Test
    public void removeEmployee(){
        employeeService.removeEmployee(employeeService.getAllEmployees().iterator().next().getId());
        Collection<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees).hasSize(4);
    }

    @Test
    public void salaryHigh() {
        Collection<Employee> employees = employeeService.getSalaryHigh();

        Assertions.assertThat(employees).hasSize(2);

    }
}
