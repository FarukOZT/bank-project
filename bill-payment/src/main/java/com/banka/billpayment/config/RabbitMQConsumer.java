package com.banka.billpayment.config;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    @Value("${rabbit.queue.name}")//configuration dosyamızdaki configurationı otamatik bizim bu degiskenimize enject ediyor
    private String queueName;

    @RabbitListener(queues = "${rabbit.queue.name}")
    public void consumeMessage(Message message) {
        String messageBody = new String(message.getBody());
        System.out.println("Received message: " + messageBody);
    }
}
