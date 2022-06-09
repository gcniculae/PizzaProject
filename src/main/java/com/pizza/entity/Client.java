package com.pizza.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Client extends Person {

    private String clientCode;

    @OneToMany(mappedBy = "client")
    private List<ProductOrder> productOrders;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;

    public Client(ClientBuilder clientBuilder) {
        super(clientBuilder);
        this.clientCode = "C" + UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    public Client() {
        this.clientCode = "C" + UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    public static class ClientBuilder extends PersonBuilder<ClientBuilder> {

        private String clientCode;
        private List<ProductOrder> productOrders;
        private List<Reservation> reservations;

        public ClientBuilder setClientCode(String clientCode) {
            this.clientCode = clientCode;
            return this;
        }

        public ClientBuilder setProductOrderList(List<ProductOrder> productOrders) {
            this.productOrders = productOrders;
            return this;
        }

        public ClientBuilder setReservationsList(List<Reservation> reservations) {
            this.reservations = reservations;
            return this;
        }

        @Override
        public ClientBuilder getThis() {
            return this;
        }

        @Override
        public Client build() {
            return new Client(this);
        }
    }
}
