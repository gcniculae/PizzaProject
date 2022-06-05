package com.pizza.service;

import com.pizza.entity.Client;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostConstruct
    public void createClients() {
        List<Client> initialClients = new ArrayList<>();
        initialClients.add(new Client.ClientBuilder().setFirstName("Claudiu").setLastName("Alexandrescu").setPhoneNumber("0720000000").setDateOfBirth(LocalDate.of(1980, 8, 14)).setAddress("Ploiesti").build());
        initialClients.add(new Client.ClientBuilder().setFirstName("Marin").setLastName("Stefanescu").setPhoneNumber("0720000050").setDateOfBirth(LocalDate.of(1994, 2, 4)).setAddress("Ploiesti").build());
        initialClients.add(new Client.ClientBuilder().setFirstName("Florin").setLastName("Albulescu").setPhoneNumber("0720002340").setDateOfBirth(LocalDate.of(1999, 12, 1)).setAddress("Ploiesti").build());
        initialClients.add(new Client.ClientBuilder().setFirstName("Dan").setLastName("Stoicescu").setPhoneNumber("0720560010").setDateOfBirth(LocalDate.of(1990, 9, 22)).setAddress("Ploiesti").build());
        initialClients.add(new Client.ClientBuilder().setFirstName("Marius").setLastName("Danescu").setPhoneNumber("0724456097").setDateOfBirth(LocalDate.of(1975, 7, 19)).setAddress("Ploiesti").build());

        clientRepository.saveAll(initialClients);
    }

    public Client saveClient(Client client) {
        Client newClient = new Client();
        if (findAllClients().stream().noneMatch(element -> element.getId().equals(client.getId()))) {
            client.setClientCode(newClient.getClientCode());
        } else {
            client.setClientCode(findClientById(client.getId()).getClientCode());
        }

        return clientRepository.save(client);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new NotFoundException("No such client found.", "client.not.found");
        }
    }

    public List<Client> findClientsByAddress(String address) {
        return clientRepository.findByAddressContainingIgnoreCase(address);
    }

    public List<Client> findClientsByFirstName(String firstName) {
        return clientRepository.findByFirstName(firstName);
    }

    public List<Client> findClientsByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    public Client findClientByPhoneNumber(String phoneNumber) {
        Optional<Client> optionalClient = clientRepository.findByPhoneNumber(phoneNumber);

        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new NotFoundException("No such client found.", "client.not.found");
        }
    }

    public Client findClientByClientCode(String clientCode) {
        Optional<Client> optionalClient = clientRepository.findByClientCode(clientCode);

        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new NotFoundException("No such client found.", "client.not.found");
        }
    }

    public Client updateClient(Long id, Client client) {
        Client clientById = findClientById(id);
        client.setId(clientById.getId());

        return saveClient(client);
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    public void deleteClientByClientCode(String clientCode) {
        clientRepository.deleteByClientCode(clientCode);
    }

    @Transactional
    public void deleteClientByPhoneNumber(String phoneNumber) {
        clientRepository.deleteByPhoneNumber(phoneNumber);
    }
}
