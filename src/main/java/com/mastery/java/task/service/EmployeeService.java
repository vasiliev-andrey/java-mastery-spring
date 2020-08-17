package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;

import java.util.List;


public interface EmployeeService {
    public Employee addEmployee(Employee employee);

    public Employee getEmployeeById(Long id);

    public List<Employee> getAllEmployees();

    public Employee updateEmployee(Long id, Employee employee);

    public void deleteEmployeeById(Long id);
}
