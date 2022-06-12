package com.pizza.service;

import com.pizza.dto.ClientDto;
import com.pizza.dto.EmployeeDto;
import com.pizza.entity.Client;
import com.pizza.entity.Employee;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.ClientRepository;
import com.pizza.repository.ClientSpecification;
import com.pizza.repository.EmployeeSpecification;
import com.pizza.repository.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

//    @PostConstruct
//    public void createClients() {
//        List<Client> initialClients = new ArrayList<>();
//        initialClients.add(new Client.ClientBuilder().setFirstName("Andrei").setLastName("Alexandrescu").setPhoneNumber("0720000000").setDateOfBirth(LocalDate.of(1980, 8, 14)).setAddress("Ploiesti").build());
//        initialClients.add(new Client.ClientBuilder().setFirstName("Marin").setLastName("Stefanescu").setPhoneNumber("0720000050").setDateOfBirth(LocalDate.of(1994, 2, 4)).setAddress("Ploiesti").build());
//        initialClients.add(new Client.ClientBuilder().setFirstName("Andrei").setLastName("Albulescu").setPhoneNumber("0720002340").setDateOfBirth(LocalDate.of(1999, 12, 1)).setAddress("Ploiesti").build());
//        initialClients.add(new Client.ClientBuilder().setFirstName("Dan").setLastName("Stoicescu").setPhoneNumber("0720560010").setDateOfBirth(LocalDate.of(1990, 9, 22)).setAddress("Ploiesti").build());
//        initialClients.add(new Client.ClientBuilder().setFirstName("Marius").setLastName("Stefanescu").setPhoneNumber("0724456097").setDateOfBirth(LocalDate.of(1975, 7, 19)).setAddress("Ploiesti").build());
//
//        clientRepository.saveAll(initialClients);
//    }

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

    public List<Client> findClientsUsingSpecification(ClientDto clientDto) {
        ClientSpecification clientFilter = new ClientSpecification();

        if (clientDto.getFirstName() != null) {
            clientFilter.add(new SearchCriteria("firstName", clientDto.getFirstName(), "=="));
        } else if (clientDto.getLastName() != null) {
            clientFilter.add(new SearchCriteria("lastName", clientDto.getLastName(), "endsWith"));
        } else if (clientDto.getPhoneNumber() != null) {
            clientFilter.add(new SearchCriteria("phoneNumber", clientDto.getPhoneNumber(), "=="));
        } else if (clientDto.getDateOfBirth() != null) {
            clientFilter.add(new SearchCriteria("dateOfBirth", clientDto.getDateOfBirth(), "=="));
        } else if (clientDto.getAddress() != null) {
            clientFilter.add(new SearchCriteria("address", clientDto.getAddress(), "=="));
        }

        return clientRepository.findAll(Specification.where(clientFilter));
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

    public List<Client> findClientsBornInTimeframe(LocalDate startDate, LocalDate endDate) {
        return clientRepository.findClientsBornInTimeframe(startDate, endDate);
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
