package com.pizza.service;

import com.pizza.config.PaymentServiceConfig;
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
    private final PaymentServiceConfig paymentServiceConfig;

    @Autowired
    public PaymentClientService(RestTemplate restTemplate, PaymentServiceConfig paymentServiceConfig) {
        this.restTemplate = restTemplate;
        this.paymentServiceConfig = paymentServiceConfig;
    }

    public String savePayment(long clientId, Double amount) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setClientId(clientId);
        paymentDto.setAmount(amount);

        HttpEntity<PaymentDto> request = new HttpEntity<>(paymentDto);

//        return restTemplate.postForObject(paymentServiceConfig.getBaseUrl(), request, String.class);

        return restTemplate.exchange(paymentServiceConfig.getBaseUrl(), HttpMethod.POST, request, String.class).getBody();
    }

    public String updatePayment(Long productOrderId, long clientId, Double amount) {
        String updateUrl = paymentServiceConfig.getBaseUrl() + "/" + productOrderId;

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setClientId(clientId);
        paymentDto.setAmount(amount);

        HttpEntity<PaymentDto> request = new HttpEntity<>(paymentDto);

        return restTemplate.exchange(updateUrl, HttpMethod.PUT, request, String.class).getBody();
    }

    public String findAllPayments() {
        String resourceUrl = paymentServiceConfig.getBaseUrl() + "?all=true";

        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);

        return response.getBody();
    }

    public String findPaymentById(Long id) {
        String resourceUrl = paymentServiceConfig.getBaseUrl() + "/" + id;

//        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
//
//        return response.getBody();

        PaymentDto paymentDto = new PaymentDto();
        HttpEntity<PaymentDto> request = new HttpEntity<>(paymentDto);

        return restTemplate.exchange(resourceUrl, HttpMethod.GET, request, String.class).getBody();
    }
}
