package com.skypro.employee;

import com.skypro.employee.exception.EmployeeIllegalArgumentException;
import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import com.skypro.employee.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    private final List<Employee> employees = List.of(
            new Employee("TestOne","TestOne",1,5000),
            new Employee("TestTwo","TestTwo",1,6000),
            new Employee("TestThree","TestThree",1,7000),
            new Employee("TestFour","TestFour",2,8000),
            new Employee("TestFive","TestFive",2,9000)
    );

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        when(employeeService.getAllEmployees())
                .thenReturn(employees);
    }

    @Test
    void getEmployeeByDepartment() {
        Collection<Employee> employeeList = this.departmentService.getDepartmentEmployees(1);
        assertThat(employeeList)
                .hasSize(3)
                .contains(employees.get(0),
                        employees.get(1),
                        employees.get(2));
    }

    @Test
    void sumOfSalariesByDepartment() {
        int sum = this.departmentService.getSumOfSalariesByDepartment(1);
        assertThat(sum).isEqualTo(18_000);
    }

    @Test
    void maxSalaryInDepartment() {
        int max = this.departmentService.getMaxSalaryByDepartment(2);
        assertThat(max).isEqualTo(9000);
    }

    @Test
    void minSalaryDepartment() {
        int min = this.departmentService.getMinSalaryByDepartment(2);
        assertThat(min).isEqualTo(8000);
    }

    @Test
    void groupedEmployees() {
        Map<Integer, List<Employee>> groupedEmployees = this.departmentService
                .getEmployeesGroupedByDepartments();

        assertThat(groupedEmployees)
                .hasSize(2)
                .containsEntry(1, List.of(employees.get(0), employees.get(1), employees.get(2)))
                .containsEntry(2, List.of(employees.get(3), employees.get(4)));

    }

    @Test
    void shouldReturnEmptyMapWhenNoEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(emptyList());
        Map<Integer, List<Employee>> groupedEmployees = this.departmentService
                .getEmployeesGroupedByDepartments();

        assertTrue(groupedEmployees.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenNoEmployeesForMaxSalaryByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(emptyList());

        assertThrows(EmployeeIllegalArgumentException.class,
                () -> departmentService.getMaxSalaryByDepartment(1));
    }

    @Test
    void shouldThrowExceptionWhenNoEmployeesForMinSalaryByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(emptyList());

        assertThrows(EmployeeIllegalArgumentException.class,
                () -> departmentService.getMinSalaryByDepartment(1));
    }
}
