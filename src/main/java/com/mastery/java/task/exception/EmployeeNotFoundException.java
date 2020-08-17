package com.mastery.java.task.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {

        super(String.format("Employee with Id %d not found", id));
    }

}
