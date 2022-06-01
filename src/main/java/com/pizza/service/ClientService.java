package com.pizza.service;

import com.pizza.entity.Client;
import com.pizza.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveClient(Client client) {
        clientRepository.save(client);
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

    public List<Client> findClientByFirstName(String firstName) {
        return clientRepository.findByFirstName(firstName);
    }

    public List<Client> findClientByLastName(String lastName) {
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

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    public void deleteClientByClientCode(String clientCode) {
        clientRepository.deleteByClientCode(clientCode);
    }
}
