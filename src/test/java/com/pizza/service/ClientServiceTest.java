package com.pizza.service;

import com.pizza.dto.ClientDto;
import com.pizza.entity.Client;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.ClientRepository;
import com.pizza.repository.ClientSpecification;
import com.pizza.repository.SearchCriteria;
import com.pizza.repository.SpecificationOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    private Client client1;
    private Client client2;
    private Client client3;
    private Client client4;
    private Client client5;

    @BeforeEach
    public void init() {
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientService(clientRepository);

        initializeClients();
    }

    private void initializeClients() {
        client1 = new Client.ClientBuilder()
                .setFirstName("Andrei")
                .setLastName("Popescu")
                .setDateOfBirth(LocalDate.of(1994, 10, 2))
                .setAddress("Ploiesti")
                .setPhoneNumber("0727009810")
                .build();
        client1.setId(1L);

        client2 = new Client.ClientBuilder()
                .setFirstName("Alexandru")
                .setLastName("Ionescu")
                .setDateOfBirth(LocalDate.of(1985, 4, 19))
                .setAddress("Ploiesti")
                .setPhoneNumber("0721070398")
                .build();
        client2.setId(2L);

        client3 = new Client.ClientBuilder()
                .setFirstName("Andrei")
                .setLastName("Ionescu")
                .setDateOfBirth(LocalDate.of(1977, 11, 9))
                .setAddress("Ploiesti")
                .setPhoneNumber("0729711092")
                .build();
        client3.setId(3L);

        client4 = new Client.ClientBuilder()
                .setFirstName("Ion")
                .setLastName("Georgescu")
                .setDateOfBirth(LocalDate.of(1989, 5, 7))
                .setAddress("Bucuresti")
                .setPhoneNumber("0724659202")
                .build();
        client4.setId(4L);

        client5 = new Client.ClientBuilder()
                .setFirstName("Anton")
                .setLastName("Stefanescu")
                .setDateOfBirth(LocalDate.of(1996, 8, 24))
                .setAddress("Ploiesti")
                .setPhoneNumber("0721973185")
                .build();
        client5.setId(5L);
    }

    @Test
    public void saveClientTest() {
        when(clientRepository.save(eq(client1))).thenReturn(client1);

        Client savedClient = clientService.saveClient(client1);
        assertThat(savedClient.getFirstName()).isSameAs(client1.getFirstName());

        verify(clientRepository, times(1)).save(client1);
    }

    @Test
    public void findAllClientsTest() {
        List<Client> clients = Arrays.asList(client1, client2);

        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> allClients = clientService.findAllClients();
        assertEquals(clients.size(), allClients.size());

        verify(clientRepository, times(1)).findAll();
    }

    @Test
//    @DisplayName("Test findClientById")
    public void findClientByIdTest() {
        Optional<Client> optionalClient1 = Optional.of(client1);
        when(clientRepository.findById(eq(1L))).thenReturn(optionalClient1);

        Client clientById = clientService.findClientById(1L);
        assertEquals(clientById, optionalClient1.get());
        verify(clientRepository, times(1)).findById(1L);

        Optional<Client> optionalClient2 = clientRepository.findById(5L);
        when(optionalClient2).thenReturn(Optional.empty());
        assertFalse(optionalClient2.isPresent());
    }

    @Test
    public void findClientsByFirstNameTest() {
        List<Client> clients = Arrays.asList(client1, client3);

        when(clientRepository.findByFirstName(eq(client1.getFirstName()))).thenReturn(clients);
        assertEquals(clients.size(), clientService.findClientsByFirstName(client1.getFirstName()).size());
        verify(clientRepository, times(1)).findByFirstName(client1.getFirstName());
    }

    @Test
    public void findClientsByLastNameTest() {
        List<Client> clientsByLastName = Arrays.asList(client2, client3);

        when(clientRepository.findByLastName(eq(client2.getLastName()))).thenReturn(clientsByLastName);

        assertEquals(clientsByLastName.size(), clientService.findClientsByLastName(client2.getLastName()).size());

        verify(clientRepository, times(1)).findByLastName(client2.getLastName());
    }

    @Test
    public void findClientByPhoneNumberTest() {
        Optional<Client> byPhoneNumber = clientRepository.findByPhoneNumber(eq(client1.getPhoneNumber()));
        when(byPhoneNumber).thenReturn(Optional.of(client1));

        Client clientByPhoneNumber = clientService.findClientByPhoneNumber(client1.getPhoneNumber());
        assertThat(clientByPhoneNumber.getPhoneNumber()).isSameAs(client1.getPhoneNumber());
    }

    @Test
    public void findClientByClientCodeTest() {
        when(clientRepository.findByClientCode(eq(client1.getClientCode()))).thenReturn(Optional.of(client1));

        assertThat(clientService.findClientByClientCode(client1.getClientCode()).getClientCode()).isSameAs(client1.getClientCode());

        verify(clientRepository).findByClientCode(client1.getClientCode());
    }

    @Test
    public void findClientsBornInTimeframeTest() {
        List<Client> clients = Arrays.asList(client2, client3, client4);

        LocalDate startDate = LocalDate.of(1980, 1, 1);
        LocalDate endDate = LocalDate.of(1990, 1, 1);

        when(clientRepository.findClientsBornInTimeframe(eq(startDate), eq(endDate))).thenReturn(clients);

        assertEquals(clients.size(), clientService.findClientsBornInTimeframe(startDate, endDate).size());

        verify(clientRepository).findClientsBornInTimeframe(startDate, endDate);
    }

    @Test
    public void updateClientTest() {
        when(clientRepository.findById(eq(1L))).thenReturn(Optional.of(client1));

        String newAddress = "Bucuresti";
        LocalDate newDateOfBirth = LocalDate.of(1992, 3, 14);
        client1.setAddress(newAddress);
        client1.setDateOfBirth(newDateOfBirth);

        when(clientRepository.save(client1)).thenReturn(client1);

        String addressForUpdatedClient = clientService.updateClient(1L, client1).getAddress();
        LocalDate dateOfBirthForUpdatedClient = clientService.updateClient(1L, client1).getDateOfBirth();
        assertEquals(addressForUpdatedClient, client1.getAddress());
        assertEquals(dateOfBirthForUpdatedClient, client1.getDateOfBirth());
    }

    @Test
    public void deleteExistentClientByIdTest() {
        when(clientRepository.findById(eq(client1.getId()))).thenReturn(Optional.of(client1));
        doNothing().when(clientRepository).deleteById(client1.getId());

        clientService.deleteClientById(client1.getId());

        verify(clientRepository).findById(client1.getId());
    }

    @Test
    public void deleteNonexistentClientByIdTest() {
        when(clientRepository.findById(eq(client1.getId()))).thenThrow(new NotFoundException("No such client.", "client.not.found"));

        NotFoundException notFoundException = assertThrows(
                NotFoundException.class,
                () -> clientService.findClientById(client1.getId()));

        assertNotNull(notFoundException);
        assertEquals("No such client.", notFoundException.getMessage());
    }

    @Test
    public void deleteExistentClientByClientCodeTest() {
        when(clientRepository.findByClientCode(eq(client1.getClientCode()))).thenReturn(Optional.of(client1));
        doNothing().when(clientRepository).deleteByClientCode(client1.getClientCode());

        clientService.deleteClientByClientCode(client1.getClientCode());

        verify(clientRepository).findByClientCode(client1.getClientCode());
    }

    @Test
    public void deleteNonexistentClientByClientCodeTest() {
        when(clientRepository.findByClientCode(eq(client1.getClientCode()))).thenThrow(new NotFoundException("No such client.", "client.not.found"));

        NotFoundException notFoundException = assertThrows(
                NotFoundException.class,
                () -> clientService.findClientByClientCode(client1.getClientCode()));

        assertNotNull(notFoundException);
        assertEquals("No such client.", notFoundException.getMessage());
    }

    @Test
    public void deleteExistentClientByPhoneNumberTest() {
        when(clientRepository.findByPhoneNumber(eq(client1.getPhoneNumber()))).thenReturn(Optional.of(client1));
        doNothing().when(clientRepository).deleteByPhoneNumber(eq(client1.getPhoneNumber()));

        clientService.deleteClientByPhoneNumber(client1.getPhoneNumber());

        verify(clientRepository).findByPhoneNumber(client1.getPhoneNumber());
    }

    @Test
    public void deleteNonexistentClientByPhoneNumberTest() {
        when(clientRepository.findByPhoneNumber(eq(client1.getPhoneNumber()))).thenThrow(new NotFoundException("No such client.", "client.not.found"));

        NotFoundException notFoundException = assertThrows(
                NotFoundException.class,
                () -> clientService.findClientByPhoneNumber(client1.getPhoneNumber())
        );

        assertNotNull(notFoundException);
        assertEquals("No such client.", notFoundException.getMessage());
    }

    @Test
    public void findClientsUsingSpecificationTest() {
        ClientDto clientDto = new ClientDto.ClientDtoBuilder()
                .setFirstNameForDto("Andrei")
                .buildDto();

        ClientSpecification clientFilter = new ClientSpecification();
        clientFilter.add(new SearchCriteria("firstName", clientDto.getFirstName(), SpecificationOperation.EQUAL, false));

        List<Client> clients = new ArrayList<>();
        clients.add(new Client.ClientBuilder().setFirstName(clientDto.getFirstName()).build());

        when(clientRepository.findAll(Specification.where(any(ClientSpecification.class)))).thenReturn(clients);

        assertEquals(clients.size(), clientService.findClientsUsingSpecification(clientDto).size());
    }

    @Test
    public void findClientsUnder30LivingInCertainAreaTest() {
        List<Client> clients = Arrays.asList(client1, client5);

        ClientDto clientDto = new ClientDto.ClientDtoBuilder()
                .setDateOfBirthForDto(LocalDate.of(1992, 6, 14))
                .setAddressForDto("Ploiesti")
                .buildDto();

        ClientSpecification clientDateOfBirth = new ClientSpecification();
        clientDateOfBirth.add(new SearchCriteria("dateOfBirth", LocalDate.of(1992, 6, 14), SpecificationOperation.GREATER_THAN, true));

        ClientSpecification clientAddress = new ClientSpecification();
        clientAddress.add(new SearchCriteria("address", clientDto.getAddress(), SpecificationOperation.MATCH, false));

//        when(clientRepository.findAll(Specification.where(any(ClientSpecification.class)).and(any(ClientSpecification.class)))).thenReturn(clients);
        when(clientRepository.findAll(any(Specification.class))).thenReturn(clients);


        int size = clientService.findClientsUnder30LivingInCertainArea(clientDto).size();
        assertEquals(clients.size(), size);
    }
}
