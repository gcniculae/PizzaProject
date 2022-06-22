package com.pizza.service;

import com.pizza.entity.Client;
import com.pizza.entity.Location;
import com.pizza.entity.Reservation;
import com.pizza.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private LocationService locationService;

    @Mock
    private ClientService clientService;

    private Reservation reservation1;
    private Reservation reservation2;
    private Location location1;
    private Location location2;
    private Client client1;

    @BeforeEach
    public void init() {
        reservationRepository = mock(ReservationRepository.class);
        locationService = mock(LocationService.class);
        clientService = mock(ClientService.class);
        reservationService = new ReservationService(reservationRepository, locationService, clientService);

        initializeLocations();

        initializeClients();

        initializeReservations();
    }

    private void initializeReservations() {
        reservation1 = new Reservation("Reservation1", client1, location1);
        reservation2 = new Reservation("Reservation2", client1, location2);
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
    }

    private void initializeLocations() {
        location1 = new Location();
        location1.setName("Location1");
        location1.setAddress("Ploiesti");
        location1.setId(1L);

        location2 = new Location();
        location2.setName("Location2");
        location2.setAddress("Ploiesti");
        location2.setId(2L);
    }

    @Test
    public void saveReservation() {
        when(reservationRepository.save(eq(reservation1))).thenReturn(reservation1);
    }
}
