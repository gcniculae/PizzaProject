package com.pizza.restcontroller;

import com.pizza.dto.ClientDto;
import com.pizza.dto.ClientDtoFilter;
import com.pizza.entity.Client;
import com.pizza.service.ClientService;
import com.pizza.converter.ClientConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/clients")
@CrossOrigin(origins = "*")
public class ClientRestController {

    private final ClientService clientService;
    private final ClientConverter clientConverter;

    @Autowired
    public ClientRestController(ClientService clientService, ClientConverter clientConverter) {
        this.clientService = clientService;
        this.clientConverter = clientConverter;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients(@RequestParam(name = "allClients", required = false, defaultValue = "false") Boolean allClients,
                                                         @RequestParam(name = "firstName", required = false) String firstName,
                                                         @RequestParam(name = "lastName", required = false) String lastName,
                                                         @RequestParam(name = "address", required = false) String address,
                                                         @RequestParam(name = "startDate", required = false)
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                         @RequestParam(name = "endDate", required = false)
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Client> allClientsList = new ArrayList<>();

        if (allClients) {
            allClientsList = clientService.findAllClients();
        } else if (firstName != null) {
            allClientsList = clientService.findClientsByFirstName(firstName);
        } else if (lastName != null) {
            allClientsList = clientService.findClientsByLastName(lastName);
        } else if (address != null) {
            allClientsList = clientService.findClientsByAddress(address);
        } else if (startDate != null && endDate != null) {
            allClientsList = clientService.findClientsBornInTimeframe(startDate, endDate);
        }

        List<ClientDto> allClientsDto = clientConverter.convertFromEntityListToDtoList(allClientsList);

        return ResponseEntity.ok(allClientsDto);
    }

    @GetMapping(path = "/s")
    public ResponseEntity<List<ClientDto>> findClientsUsingSpecification(ClientDto clientDto) {
        List<Client> clientsByFirstNameUsingSpecification;

        if (clientDto.getAddress() != null) {
            clientsByFirstNameUsingSpecification = clientService.findClientsUnder30LivingInCertainArea(clientDto);
        } else {
            clientsByFirstNameUsingSpecification = clientService.findClientsUsingSpecification(clientDto);
        }
        return ResponseEntity.ok(clientConverter.convertFromEntityListToDtoList(clientsByFirstNameUsingSpecification));
    }

    @GetMapping(path = "/dto")
    public ResponseEntity<List<ClientDto>> findClientUsingClientDtoAndSpecification(@RequestBody List<ClientDtoFilter> clientDtoFilters) {
        List<Client> clientsUsingClientDtoFilterAndSpecification = clientService.findClientsUsingClientDtoFilterAndSpecification(clientDtoFilters);

        return ResponseEntity.ok(clientConverter.convertFromEntityListToDtoList(clientsUsingClientDtoFilterAndSpecification));
    }

//    @GetMapping(path = "/id/{id}")
//    public ResponseEntity<ClientDto> findClientById(@PathVariable("id") Long id) {
//        Client client = clientService.findClientById(id);
//        ClientDto clientDto = clientTransformer.transformFromClientToClientDto(client);
//
//        return ResponseEntity.ok(clientDto);
//    }
//
//    @GetMapping(path = "/first-name/{firstName}")
//    public ResponseEntity<List<ClientDto>> findClientsByFirstName(@PathVariable("firstName") String firstName) {
//        List<Client> clients = clientService.findClientsByFirstName(firstName);
//
//        List<ClientDto> clientsDto = clients.stream()
//                .map(clientTransformer::transformFromClientToClientDto).toList();
//
//        return ResponseEntity.ok(clientsDto);
//    }
//
//    @GetMapping(path = "/last-name/{lastName}")
//    public ResponseEntity<List<ClientDto>> findClientsByLastName(@PathVariable("lastName") String lastName) {
//        List<Client> clients = clientService.findClientsByLastName(lastName);
//
//        List<ClientDto> clientsDto = clients.stream()
//                .map(clientTransformer::transformFromClientToClientDto).toList();
//
//        return ResponseEntity.ok(clientsDto);
//    }
//
//    @GetMapping(path = "/client-code/{clientCode}")
//    public ResponseEntity<ClientDto> findClientByClientCode(@PathVariable String clientCode) {
//        Client client = clientService.findClientByClientCode(clientCode);
//        ClientDto clientDto = clientTransformer.transformFromClientToClientDto(client);
//
//        return ResponseEntity.ok(clientDto);
//    }
//
//    @GetMapping(path = "/phone-number/{phoneNumber}")
//    public ResponseEntity<ClientDto> findClientByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
//        Client client = clientService.findClientByPhoneNumber(phoneNumber);
//        ClientDto clientDto = clientTransformer.transformFromClientToClientDto(client);
//
//        return ResponseEntity.ok(clientDto);
//    }

    @GetMapping(path = "/single")
    public ResponseEntity<ClientDto> getClient(@RequestParam(name = "id", required = false) Long id,
                                               @RequestParam(name = "clientCode", required = false) String clientCode,
                                               @RequestParam(name = "phoneNumber", required = false) String phoneNumber) {
        Client client = new Client();

        if (id != null) {
            client = clientService.findClientById(id);
        } else if (clientCode != null) {
            client = clientService.findClientByClientCode(clientCode);
        } else if (phoneNumber != null) {
            client = clientService.findClientByPhoneNumber(phoneNumber);
        }

        ClientDto clientDto = clientConverter.convertFromEntityToDto(client);

        return ResponseEntity.ok(clientDto);
    }

    @PostMapping
    public ResponseEntity<ClientDto> addClient(@Valid @RequestBody ClientDto clientDto) {
        Client client = clientConverter.convertFromDtoToEntity(clientDto);
        Client savedClient = clientService.saveClient(client);
        ClientDto savedClientDto = clientConverter.convertFromEntityToDto(savedClient);

        return ResponseEntity.ok(savedClientDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDto clientDto) {
        Client client = clientConverter.convertFromDtoToEntity(clientDto);
        Client updatedClient = clientService.updateClient(id, client);
        ClientDto updatedClientDto = clientConverter.convertFromEntityToDto(updatedClient);

        return ResponseEntity.ok(updatedClientDto);
    }

//    @DeleteMapping(path = "/id/{id}")
//    public ResponseEntity<ClientDto> deleteClientById(@PathVariable("id") Long id) {
//        clientService.deleteClientById(id);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping(path = "/client-code/{clientCode}")
//    public ResponseEntity<ClientDto> deleteClientByClientCode(@PathVariable("clientCode") String clientCode) {
//        clientService.deleteClientByClientCode(clientCode);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping(path = "/phone-number/{phoneNumber}")
//    public ResponseEntity<ClientDto> deleteClientByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
//        clientService.deleteClientByPhoneNumber(phoneNumber);
//
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping
    public ResponseEntity<ClientDto> deleteClient(@RequestParam(name = "id", required = false) Long id,
                                                  @RequestParam(name = "clientCode", required = false) String clientCode,
                                                  @RequestParam(name = "phoneNumber", required = false) String phoneNumber) {
        if (id != null) {
            clientService.deleteClientById(id);
        } else if (clientCode != null) {
            clientService.deleteClientByClientCode(clientCode);
        } else if (phoneNumber != null) {
            clientService.deleteClientByPhoneNumber(phoneNumber);
        }

        return ResponseEntity.noContent().build();
    }
}
