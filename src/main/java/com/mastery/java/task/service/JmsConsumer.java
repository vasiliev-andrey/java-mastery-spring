package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dto.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private EmployeeRepository employeeRepository;

    @JmsListener(destination = "queue", containerFactory = "jmsListenerContainerFactory")
    public void receive(Employee employee) {
        log.debug("Recieved Message: " + employee);
        employeeRepository.saveAndFlush(employee);
    }
}
