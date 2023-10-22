package com.banka.billpayment.config;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {
    @Value("${rabbit.exchange.name}")
    private String exchangeName;
    @Value("${rabbit.routing.name}")
    private String routingName;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        Message msg = MessageBuilder.withBody(message.getBytes())
                .setContentType("application/json")
                .build();
        rabbitTemplate.convertAndSend(exchangeName, routingName, msg);
    }
}
