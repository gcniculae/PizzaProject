package com.pizza.service;

import com.pizza.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentClientService {

    private final RestTemplate restTemplate;

    @Autowired
    public PaymentClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void savePayment(long clientId, Double amount) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setClientId(clientId);
        paymentDto.setAmount(amount);

        String resourceUrl = "http://localhost:8081/api/payments";

        HttpEntity<PaymentDto> request = new HttpEntity<>(paymentDto);

        restTemplate.exchange(resourceUrl, HttpMethod.POST, request, String.class);
    }
}
