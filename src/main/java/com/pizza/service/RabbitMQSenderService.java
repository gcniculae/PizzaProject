package com.pizza.service;

import com.pizza.dto.ProductOrderDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSenderService {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitMQSenderService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Value("${pizza-project.rabbitmq.exchange}")
    private String exchange;

    @Value("${pizza-project.rabbitmq.routingKey}")
    private String routingKey;

    public void send(ProductOrderDto productOrder) {
        amqpTemplate.convertAndSend(exchange, routingKey, productOrder);
        System.out.println("Send message: " + productOrder);
    }
}
