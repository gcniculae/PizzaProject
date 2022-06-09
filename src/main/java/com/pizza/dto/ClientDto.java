package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto extends PersonDto {

    private String clientCode;

    public ClientDto(ClientDtoBuilder clientDtoBuilder) {
        this.clientCode = clientDtoBuilder.clientCode;
    }

    public static class ClientDtoBuilder extends PersonDtoBuilder<ClientDtoBuilder> {

        private String clientCode;

        public ClientDtoBuilder setClientCode(String clientCode) {
            this.clientCode = clientCode;
            return this;
        }

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
