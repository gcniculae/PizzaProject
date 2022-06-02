package com.pizza.transformer;

import com.pizza.dto.ClientDto;
import com.pizza.entity.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClientTransformer {

    public ClientDto transformFromClientToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(client, clientDto);

        return clientDto;
    }

    public Client transformFromClientDtoToClient(ClientDto clientDto) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);

        return client;
    }
}
