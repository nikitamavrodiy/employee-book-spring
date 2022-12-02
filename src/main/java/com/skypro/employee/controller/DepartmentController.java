package com.skypro.employee.controller;

import com.skypro.employee.exception.EmployeeIllegalArgumentException;
import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department/{id}/employees")
    public Collection<Employee> getDepartmentEmployees(@PathVariable("id") int department) {
        return departmentService.getDepartmentEmployees(department);
    }

    @GetMapping("/department/{id}/salary/sum")
    public int getSumOfSalariesByDepartment(@PathVariable("id") int department) {
        return departmentService.getSumOfSalariesByDepartment(department);
    }

    @GetMapping("/department/{id}/salary/max")
    public int getMaxSalaryByDepartment(@PathVariable("id") int department) {
        return departmentService.getMaxSalaryByDepartment(department);
    }

    @GetMapping("/department/{id}/salary/min")
    public int getMinSalaryByDepartment(@PathVariable("id") int department) {
        return departmentService.getMinSalaryByDepartment(department);
    }

    @GetMapping("/department/employees/grouped")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartments() {
        return departmentService.getEmployeesGroupedByDepartments();
    }


}
