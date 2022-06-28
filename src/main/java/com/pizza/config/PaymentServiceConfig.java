package com.pizza.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "payment-service")
@Getter
@Setter
public class PaymentServiceConfig {

    private String baseUrl;
    private String updatePaymentUrl;
}
