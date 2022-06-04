package com.pizza.dto;

import com.pizza.entity.Client;
import com.pizza.entity.ProductOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto extends PersonDto {

    private String clientCode;
    private List<ProductOrder> productOrders;

    public ClientDto(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth, String address) {
        super(firstName, lastName, phoneNumber, dateOfBirth, address);
    }
}
