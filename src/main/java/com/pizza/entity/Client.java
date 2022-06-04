package com.pizza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client extends Person {

    private String clientCode;

    @OneToMany(mappedBy = "client")
    private List<ProductOrder> productOrders;

    public Client(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth, String address) {
        super(firstName, lastName, phoneNumber, dateOfBirth, address);
        this.clientCode = "C" + UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }
}
