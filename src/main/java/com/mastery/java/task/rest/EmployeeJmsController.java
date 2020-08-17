package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeJmsController {

    @Autowired
    JmsProducer jmsProducer;

    @PostMapping(value = "/postemployee")
    public Employee postEmployee(@RequestBody Employee employee) {
        jmsProducer.send(employee);
        return employee;
    }

}
