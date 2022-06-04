package com.pizza.dto;

import com.pizza.entity.Client;
import com.pizza.entity.ProductOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto extends BaseEntityDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String clientCode;
    private List<ProductOrder> productOrders;

    public ClientDto(String firstName, String lastName, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
