package com.pizza.restcontroller;

import com.pizza.dto.ProductOrderDto;
import com.pizza.service.RabbitMQSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rabbitmq")
public class RabbitMQRestController {

    private final RabbitMQSenderService rabbitMQSenderService;

    @Autowired
    public RabbitMQRestController(RabbitMQSenderService rabbitMQSenderService) {
        this.rabbitMQSenderService = rabbitMQSenderService;
    }

    @GetMapping(path = "/producer")
    public String producer(@RequestParam("clientId") Long clientId, @RequestParam("pizzasIds") List<Long> pizzasIds) {
        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setClientId(clientId);
        productOrderDto.setPizzasIds(pizzasIds);
        rabbitMQSenderService.send(productOrderDto);

        return "Message sent to the RabbitMQ successfully";
    }
}
