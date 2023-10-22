package com.banka.billpayment.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbit.queue.name}")//configuration dosyamızdaki configurationı otamatik bizim bu degiskenimize enject ediyor
    private String queueName;

    @Value("${rabbit.routing.name}")
    private String routingName;

    @Value("${rabbit.exchange.name}")
    private String exchangeName;

    @Autowired
    private ConnectionFactory connectionFactory;
    @Bean
    public Queue queue(){
        return new Queue(queueName,true);
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchangeName);

    }
    @Bean
    public Binding binding(final Queue queue, final DirectExchange directExchange){ //constructor Injection
        return BindingBuilder.bind(queue).to(directExchange).with(routingName);
    }
}
