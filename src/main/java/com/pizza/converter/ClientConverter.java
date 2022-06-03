package com.pizza.converter;

import com.pizza.dto.ClientDto;
import com.pizza.entity.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<ClientDto, Client> {

    @Override
    public ClientDto transformFromEntityToDto(Client client) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(client, clientDto);

        return clientDto;
    }

    @Override
    public Client transformFromDtoToEntity(ClientDto clientDto) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);

        return client;
    }
}
