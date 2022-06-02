package com.pizza.restcontroller;

import com.pizza.dto.ClientDto;
import com.pizza.entity.Client;
import com.pizza.service.ClientService;
import com.pizza.transformer.ClientTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/client")
@CrossOrigin(origins = "*")
public class ClientRestController {

    private final ClientService clientService;
    private final ClientTransformer clientTransformer;

    @Autowired
    public ClientRestController(ClientService clientService, ClientTransformer clientTransformer) {
        this.clientService = clientService;
        this.clientTransformer = clientTransformer;
    }

    @GetMapping(path = "/allClients")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<Client> allClients = clientService.findAllClients();
        List<ClientDto> allClientsDto = new ArrayList<>();

//        for (Client client : allClients) {
//            allClientsDto.add(clientTransformer.transformFromClientToDto(client));
//        }

        allClientsDto = allClients.stream()
                .map(clientTransformer::transformFromClientToClientDto).collect(Collectors.toList());

        return ResponseEntity.ok(allClientsDto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable("id") Long id) {
        Client client = clientService.findClientById(id);
        ClientDto clientDto = clientTransformer.transformFromClientToClientDto(client);

        return ResponseEntity.ok(clientDto);
    }

    @GetMapping(path = "/{firstName}")
    public ResponseEntity<List<ClientDto>> findClientsByFirstName(@PathVariable("firstName") String firstName) {
        List<Client> clients = clientService.findClientsByFirstName(firstName);

        List<ClientDto> clientsDto = clients.stream()
                .map(clientTransformer::transformFromClientToClientDto).toList();

        return ResponseEntity.ok(clientsDto);
    }

    @GetMapping(path = "/{lastName}")
    public ResponseEntity<List<ClientDto>> findClientsByLastName(@PathVariable("lastName") String lastName) {
        List<Client> clients = clientService.findClientsByLastName(lastName);

        List<ClientDto> clientsDto = clients.stream()
                .map(clientTransformer::transformFromClientToClientDto).toList();

        return ResponseEntity.ok(clientsDto);
    }

    @GetMapping(path = "/{phoneNumber}")
    public ResponseEntity<ClientDto> findClientByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        Client client = clientService.findClientByPhoneNumber(phoneNumber);
        ClientDto clientDto = clientTransformer.transformFromClientToClientDto(client);

        return ResponseEntity.ok(clientDto);
    }

    @PostMapping
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto) {
        Client client = clientTransformer.transformFromClientDtoToClient(clientDto);
        Client savedClient = clientService.saveClient(client);
        ClientDto savedClientDto = clientTransformer.transformFromClientToClientDto(savedClient);

        return ResponseEntity.ok(savedClientDto);
    }

    @PutMapping
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto) {
        Client client = clientTransformer.transformFromClientDtoToClient(clientDto);
        Client savedClient = clientService.saveClient(client);
        ClientDto savedClientDto = clientTransformer.transformFromClientToClientDto(savedClient);

        return ResponseEntity.ok(savedClientDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ClientDto> deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteClientById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{clientCode}")
    public ResponseEntity<ClientDto> deleteClientByClientCode(@PathVariable("clientCode") String clientCode) {
        clientService.deleteClientByClientCode(clientCode);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{phoneNumber}")
    public ResponseEntity<ClientDto> deleteClientByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        clientService.findClientByPhoneNumber(phoneNumber);

        return ResponseEntity.noContent().build();
    }
}
