package com.pizza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client extends BaseEntity {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String clientCode;

    @OneToMany(mappedBy = "client")
    private List<ProductOrder> productOrders;

    public Client(String firstName, String lastName, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.clientCode = "C" + UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }
}
