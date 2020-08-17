package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import com.mastery.java.task.service.EmployeeServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    //EmployeeServiceImpl employeeService;
            EmployeeService employeeService;

    @PostMapping
    @ApiOperation(value = "Create a new Employee.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "CREATED"),
                    @ApiResponse(code = 404, message = "NOT_FOUND")
            })
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@ApiParam(value = "Employee object that should be created.", required = true)
                                @RequestBody @Valid Employee employee) {
        log.debug("ADD employee - {}", employee);
        return employeeService.addEmployee(employee);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Find employee by Id.",
            notes = "Provide an Id to look up specific employee from database.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "NOT_FOUND")
            })
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@ApiParam(value = "Id value for employee object you need to retrieve.", required = true)
                                    @PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all employees from database.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "NOT_FOUND")
            })
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        log.debug("GET all employees");
        return employeeService.getAllEmployees();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update an existing employee by Id.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "NOT_FOUND")
            })
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@ApiParam(value = "Id value for employee you need to update.", required = true)
                                   @PathVariable(value = "id")
                                           Long id,
                                   @ApiParam(value = "The employee object, which need to update.", required = true)
                                   @RequestBody Employee employee) {
        log.debug("UPDATE employee - {}", employee);
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(
            value = "Delete employee by Id.",
            notes = "Provide an Id to delete specific employee from the database.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "NO_CONTENT"),
                    @ApiResponse(code = 404, message = "NOT_FOUND")
            })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@ApiParam(value = "Id value for the employee you need to delete.", required = true)
                               @PathVariable Long id) {
        log.debug("DELETE employee id = {}", id);
        employeeService.deleteEmployeeById(id);
    }
}
