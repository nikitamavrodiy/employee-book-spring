package com.skypro.employee.record;

public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private int department;
    private int salary;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartmnet() {
        return department;
    }

    public void setDepartment(int departmnet) {
        this.department = departmnet;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
