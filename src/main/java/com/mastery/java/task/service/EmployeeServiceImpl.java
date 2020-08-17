package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.DataNotFoundException;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        try {
            log.debug("Getting employee by id...");
            return employeeRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            log.warn("Employee with id = " + id + " not found.");
            throw new EmployeeNotFoundException(id);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        log.debug("Getting all employees...");
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            log.warn("Data not found.");
            throw new DataNotFoundException();
        }
        return employees;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        try {
            log.debug("Updating employee by id...");
            employeeRepository.findById(id).get();
            employee.setEmployee_id(id);
            return employeeRepository.saveAndFlush(employee);
        } catch (NoSuchElementException e) {
            log.warn("Employee with id = " + id + " not found.");
            throw new EmployeeNotFoundException(id);
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        log.debug("Deleting employee by id...");
        if (employeeRepository.existsById(id)) {
            log.debug("Employee deleted successfully.");
            employeeRepository.deleteById(id);
        } else {
            log.warn("Employee with id = " + id + " not found.");
            throw new EmployeeNotFoundException(id);
        }
    }
}
