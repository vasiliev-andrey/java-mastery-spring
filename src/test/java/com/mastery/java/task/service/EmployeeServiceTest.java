package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

  @InjectMocks EmployeeServiceImpl employeeService;

  @Mock EmployeeRepository employeeRepository;

  List<Employee> employeeList = new ArrayList<>();
  private Employee employee = new Employee(1L, "Mary", "Jane", 100, "QA", Gender.FEMALE);;
  private Long employee_id = 1L;

  @Test
  public void addEmployeeTest() {
    Employee employee = new Employee(1L, "Mary", "Jane", 100, "QA", Gender.FEMALE);
    when((employeeRepository).saveAndFlush(any(Employee.class))).thenReturn(employee);

    Employee returnedFactor = employeeService.addEmployee(employee);

    ArgumentCaptor<Employee> employeeArgument = ArgumentCaptor.forClass(Employee.class);

    verify(employeeRepository, times(1)).saveAndFlush(employeeArgument.capture());
    verifyNoMoreInteractions(employeeRepository);

    assertEmployee(returnedFactor, employee);
  }

  @Test
  public void getEmployeeByIdTest() {
    when(employeeRepository.findById(employee_id)).thenReturn(Optional.of(employee));

    Employee actualEmployee = employeeService.getEmployeeById(employee_id);

    assertEmployee(employee, actualEmployee);
    verify(employeeRepository, times(1)).findById(employee_id);
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test
  public void getAllEmployeesTest() {
    employeeList.add(employee);

    when(employeeRepository.findAll()).thenReturn(employeeList);

    List<Employee> actualList = employeeService.getAllEmployees();

    assertEquals(employeeList.size(), actualList.size());
    assertEquals(employeeList.size(), 1);
    assertEmployee(employeeList.get(0), employee);
    verify(employeeRepository, times(1)).findAll();
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test
  public void updateEmployeeTest() {
    when(employeeRepository.saveAndFlush(employee)).thenReturn(employee);
    when(employeeRepository.findById(employee_id)).thenReturn(Optional.of(employee));

    Employee actualEmployee = employeeService.updateEmployee(employee_id, employee);

    assertEmployee(employee, actualEmployee);
    verify(employeeRepository, times(1)).saveAndFlush(employee);
    verify(employeeRepository, times(1)).findById(employee_id);
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test(expected = EmployeeNotFoundException.class)
  public void updateEmployeeWhenEmployeeIsNotFoundTest() {
    employeeService.updateEmployee(employee_id, employee);

    verify(employeeRepository, times(1)).existsById(employee_id);
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test
  public void deleteEmployeeByIdTest() {
    when(employeeRepository.existsById(employee_id)).thenReturn(true);
    doNothing().when(employeeRepository).deleteById(employee_id);

    employeeService.deleteEmployeeById(employee_id);

    verify(employeeRepository, times(1)).deleteById(employee_id);
    verify(employeeRepository, times(1)).existsById(employee_id);
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test(expected = EmployeeNotFoundException.class)
  public void deleteEmployeeByIdWhenEmployeeIsNotFoundTest() {
    when(employeeRepository.existsById(employee_id)).thenReturn(false);

    employeeService.deleteEmployeeById(employee_id);

    verify(employeeRepository, times(1)).existsById(employee_id);
    verifyNoMoreInteractions(employeeRepository);
  }

  private void assertEmployee(Employee expected, Employee actual) {
    assertEquals(expected.getFirst_name(), actual.getFirst_name());
  }
}
