package com.pizza.service;

import com.pizza.dto.ReservationDto;
import com.pizza.entity.Reservation;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final LocationService locationService;
    private final ClientService clientService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, LocationService locationService, ClientService clientService) {
        this.reservationRepository = reservationRepository;
        this.locationService = locationService;
        this.clientService = clientService;
    }

    public Reservation saveReservation(Reservation reservation, ReservationDto reservationDto) {
        reservation.setClient(clientService.findClientById(reservationDto.getClientId()));
        reservation.setLocation(locationService.findLocationById(reservationDto.getLocationId()));

        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findReservationsByClientId(Long clientId) {
        return reservationRepository.findReservationsByClientId(clientId);
    }

    public List<Reservation> findReservationsByLocationId(Long locationId) {
        return reservationRepository.findReservationsByLocationId(locationId);
    }

    public List<Reservation> findReservationsByClientIdAndLocationId(Long clientId, Long locationId) {
        return reservationRepository.findReservationsByClientIdAndLocationId(clientId, locationId);
    }

    public Reservation findReservationById(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if (optionalReservation.isPresent()) {
            return optionalReservation.get();
        } else {
            throw new NotFoundException("No such reservation found.", "reservation.not.found");
        }
    }

    public Reservation findReservationByName(String name) {
        Optional<Reservation> optionalReservation = reservationRepository.findByName(name);

        if (optionalReservation.isPresent()) {
            return optionalReservation.get();
        } else {
            throw new NotFoundException("No such reservation found.", "reservation.not.found");
        }
    }

    public Reservation updateReservation(Long id, Reservation reservation, ReservationDto reservationDto) {
        Reservation reservationById = findReservationById(id);
        reservation.setId(reservationById.getId());

        return saveReservation(reservation, reservationDto);
    }

    public void deleteReservationById(Long id) {
        Reservation reservationById = findReservationById(id);

        reservationRepository.deleteById(reservationById.getId());
    }

    public void addClientIdToDto(Reservation reservation, ReservationDto reservationDto) {
        reservationDto.setClientId(reservation.getClient().getId());
    }

    public void addLocationIdToDto(Reservation reservation, ReservationDto reservationDto) {
        reservationDto.setLocationId(reservation.getLocation().getId());
    }

    public void addClientIdToDtoList(List<Reservation> allReservationsList, List<ReservationDto> reservationDtoList) {
        for (int index = 0; index < reservationDtoList.size(); index++) {
            reservationDtoList.get(index).setClientId(allReservationsList.get(index).getClient().getId());
        }
    }

    public void addLocationIdToDtoList(List<Reservation> allReservationsList, List<ReservationDto> reservationDtoList) {
        for (int index = 0; index < reservationDtoList.size(); index++) {
            reservationDtoList.get(index).setLocationId(allReservationsList.get(index).getLocation().getId());
        }
    }
}
