package com.pizza.service;

import com.pizza.dto.ReservationDto;
import com.pizza.entity.Client;
import com.pizza.entity.Location;
import com.pizza.entity.Reservation;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
    private Reservation reservation3;
    private Reservation reservation4;
    private Location location1;
    private Location location2;
    private Client client1;
    private Client client2;

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
        reservation1.setId(1L);

        reservation2 = new Reservation("Reservation2", client1, location2);
        reservation2.setId(2L);

        reservation3 = new Reservation("Reservation3", client2, location1);
        reservation3.setId(3L);

        reservation4 = new Reservation("Reservation4", client1, location1);
        reservation4.setId(4L);
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
        ReservationDto reservationDto = new ReservationDto("Reservation1", client1.getId(), location1.getId());

        when(reservationRepository.save(eq(reservation1))).thenReturn(reservation1);
        when(locationService.findLocationById(eq(location1.getId()))).thenReturn(location1);
        when(clientService.findClientById(eq(client1.getId()))).thenReturn(client1);

        reservationService.saveReservation(reservation1, reservationDto);
    }

    @Test
    public void findAllReservations() {
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2, reservation3);

        when(reservationRepository.findAll()).thenReturn(reservations);

        assertEquals(reservations.size(), reservationService.findAllReservations().size());

        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    public void findReservationsByClientIdTest() {
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);

        when(reservationRepository.findReservationsByClientId(client1.getId())).thenReturn(reservations);

        assertEquals(reservations.size(), reservationService.findReservationsByClientId(client1.getId()).size());

        verify(reservationRepository, times(1)).findReservationsByClientId(client1.getId());
    }

    @Test
    public void findReservationsByLocationIdTest() {
        List<Reservation> reservations = Arrays.asList(reservation1, reservation3);

        when(reservationRepository.findReservationsByLocationId(eq(location1.getId()))).thenReturn(reservations);

        assertEquals(reservations.size(), reservationService.findReservationsByLocationId(location1.getId()).size());

        verify(reservationRepository, times(1)).findReservationsByLocationId(location1.getId());
    }

    @Test
    public void findReservationsByClientIdAndLocationIdTest() {
        List<Reservation> reservations = Arrays.asList(reservation1, reservation4);

        when(reservationRepository.findReservationsByClientIdAndLocationId(eq(client1.getId()), eq(location1.getId()))).thenReturn(reservations);

        assertEquals(reservations.size(), reservationService.findReservationsByClientIdAndLocationId(client1.getId(), location1.getId()).size());

        verify(reservationRepository, times(1)).findReservationsByClientIdAndLocationId(client1.getId(), location1.getId());
    }

    @Test
    public void findExistentReservationByIdTest() {
        when(reservationRepository.findById(reservation1.getId())).thenReturn(Optional.of(reservation1));

        Reservation reservationById = reservationService.findReservationById(reservation1.getId());

        assertEquals(reservation1.getId(), reservationById.getId());

        verify(reservationRepository, times(1)).findById(reservation1.getId());
    }

    @Test
    public void findNonexistentReservationByIdTest() {
        when(reservationRepository.findById(reservation1.getId())).thenThrow(new NotFoundException("No such reservation found.", "reservation.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> reservationRepository.findById(reservation1.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such reservation found.");
    }

    @Test
    public void findExistentReservationByNameTest() {
        when(reservationRepository.findByName(reservation1.getName())).thenReturn(Optional.of(reservation1));

        Reservation reservationById = reservationService.findReservationByName(reservation1.getName());

        assertEquals(reservation1.getName(), reservationById.getName());

        verify(reservationRepository, times(1)).findByName(reservation1.getName());
    }

    @Test
    public void findNonexistentReservationByNameTest() {
        when(reservationRepository.findByName(reservation1.getName())).thenThrow(new NotFoundException("No such reservation found.", "reservation.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> reservationRepository.findByName(reservation1.getName()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such reservation found.");
    }

    @Test
    public void updateReservationTest() {
        when(reservationRepository.findById(eq(reservation1.getId()))).thenReturn(Optional.of(reservation1));
        when(clientService.findClientById(eq(client1.getId()))).thenReturn(client1);
        when(locationService.findLocationById(eq(location2.getId()))).thenReturn(location2);

        reservation1.setLocation(location2);
        when(reservationRepository.save(eq(reservation1))).thenReturn(reservation1);

        ReservationDto reservationDto = new ReservationDto("Reservation1", client1.getId(), location2.getId());

        Reservation updatedReservation = reservationService.updateReservation(reservation1.getId(), reservation1, reservationDto);

        assertEquals(reservation1.getId(), updatedReservation.getId());
        assertEquals(reservation1.getName(), updatedReservation.getName());
    }

    @Test
    public void deleteExistentReservationById() {
        when(reservationRepository.findById(reservation1.getId())).thenReturn(Optional.of(reservation1));
        doNothing().when(reservationRepository).deleteById(reservation1.getId());

        reservationService.deleteReservationById(reservation1.getId());

        verify(reservationRepository, times(1)).deleteById(reservation1.getId());
    }

    @Test
    public void deleteNonexistentReservationByNameTest() {
        when(reservationRepository.findById(reservation1.getId())).thenThrow(new NotFoundException("No such reservation found.", "reservation.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> reservationRepository.findById(reservation1.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such reservation found.");
    }
}
