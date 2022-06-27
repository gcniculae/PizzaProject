package com.pizza.service;

import com.pizza.config.PaymentServiceConfig;
import com.pizza.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentClientService {

    private final RestTemplate restTemplate;
    private final PaymentServiceConfig paymentServiceConfig;

    @Autowired
    public PaymentClientService(RestTemplate restTemplate, PaymentServiceConfig paymentServiceConfig) {
        this.restTemplate = restTemplate;
        this.paymentServiceConfig = paymentServiceConfig;
    }

    public void savePayment(long clientId, Double amount) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setClientId(clientId);
        paymentDto.setAmount(amount);

        HttpEntity<PaymentDto> request = new HttpEntity<>(paymentDto);

        restTemplate.exchange(paymentServiceConfig.getAddPaymentUrl(), HttpMethod.POST, request, String.class);
    }
}
