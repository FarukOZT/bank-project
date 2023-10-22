package com.banka.billpayment.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQComponent {

    private RabbitTemplate rabbitTemplate;
    private AmqpAdmin amqpAdmin;

    @Autowired
    public RabbitMQComponent(RabbitTemplate rabbitTemplate, AmqpAdmin amqpAdmin) {
        this.rabbitTemplate = rabbitTemplate;
        this.amqpAdmin = amqpAdmin;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("myQueue", message);
    }

    public void createQueue(String queueName) {
        Queue queue = new Queue(queueName);
        amqpAdmin.declareQueue(queue);
    }
}
