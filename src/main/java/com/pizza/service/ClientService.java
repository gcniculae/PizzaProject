package com.pizza.service;

import com.pizza.entity.Client;
import com.pizza.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
        initialClients.add(new Client("Claudiu", "Alexandrescu", "0720000000"));
        initialClients.add(new Client("Marin", "Stefanescu", "0720000050"));
        initialClients.add(new Client("Florin", "Albulescu", "0720002340"));
        initialClients.add(new Client("Dan", "Stoicescu", "0720560010"));
        initialClients.add(new Client("Marius", "Danescu", "0724456097"));

        clientRepository.saveAll(initialClients);
    }

    public Client saveClient(Client client) {
        Client newClient = new Client(client.getFirstName(), client.getLastName(), client.getPhoneNumber());
        client.setClientCode(newClient.getClientCode());

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
            throw new NoSuchElementException("No such client.");
        }
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
            throw new NoSuchElementException("No such client.");
        }
    }

    public Client findClientByClientCode(String clientCode) {
        Optional<Client> optionalClient = clientRepository.findByClientCode(clientCode);

        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new NoSuchElementException("No such client.");
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
