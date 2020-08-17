package com.mastery.java.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "employee")
@ApiModel(description = "Employee model.")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    @ApiModelProperty(
            value = "The unique Id of employee.",
            required = false,
            position = 1,
            example = "1",
            hidden = false)
    private Long employee_id;

    @Column(name = "first_name")
    @ApiModelProperty(
            value = "The employee's first name.",
            required = true,
            position = 2,
            example = "first_name",
            hidden = false)
    @NotNull(message = "First name cannot be null")
    private String first_name;

    @Column(name = "last_name")
    @ApiModelProperty(
            value = "The employee's last name.",
            required = true,
            position = 3,
            example = "last_name",
            hidden = false)
    @NotNull(message = "Last name cannot be null")
    private String last_name;

    @Column(name = "department_id")
    @ApiModelProperty(
            value = "The employee's department id.",
            required = true,
            position = 4,
            example = "100",
            hidden = false)
    @NotNull(message = "Department id cannot be null")
    private int department_id;

    @Column(name = "job_title")
    @ApiModelProperty(
            value = "The employee's job title.",
            required = true,
            position = 5,
            example = "job title",
            hidden = false)
    @NotNull(message = "Job title cannot be null")
    private String job_title;

    @Column(name = "gender")
    @ApiModelProperty(
            value = "The employee's gender.",
            required = true,
            position = 6,
            example = "MALE OR FEMALE",
            hidden = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    public Employee() {
    }

    public Employee(Long employee_id, String first_name, String last_name,
                    int department_id, String job_title, Gender gender) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.department_id = department_id;
        this.job_title = job_title;
        this.gender = gender;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", department_id=" + department_id +
                ", job_title='" + job_title + '\'' +
                ", gender=" + gender +
                '}';
    }
}
