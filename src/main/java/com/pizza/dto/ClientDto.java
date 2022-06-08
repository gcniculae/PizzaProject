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
//    private List<ProductOrder> productOrders;

    public ClientDto(ClientDtoBuilder clientDtoBuilder) {
        this.clientCode = clientDtoBuilder.clientCode;
//        this.productOrders = clientDtoBuilder.productOrders;
    }

    public static class ClientDtoBuilder extends PersonDtoBuilder<ClientDtoBuilder> {

        private String clientCode;
//        private List<ProductOrder> productOrders;

        public ClientDtoBuilder setClientCode(String clientCode) {
            this.clientCode = clientCode;
            return this;
        }

//        public ClientDtoBuilder setProductOrderList(List<ProductOrder> productOrders) {
//            this.productOrders = productOrders;
//            return this;
//        }

        @Override
        public ClientDtoBuilder getThisDto() {
            return this;
        }

        @Override
        public ClientDto buildDto() {
            return new ClientDto(this);
        }
    }
}
