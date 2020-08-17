package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {
    private static final Logger log = LogManager.getLogger();

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("queue")
    String queue;

    public void send(Employee employee) {
        log.debug("Send Message: " + employee);
        jmsTemplate.convertAndSend(queue, employee);
    }
}
