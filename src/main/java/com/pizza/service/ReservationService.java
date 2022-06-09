package com.pizza.service;

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

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, LocationService locationService) {
        this.reservationRepository = reservationRepository;
        this.locationService = locationService;
    }

    public Reservation saveReservation(Reservation reservation, Long reservationId) {
        reservation.setLocation(locationService.findLocationById(reservationId));

        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
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

    public Reservation findReservationByLocationId(Long locationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findByLocationId(locationId);

        if (optionalReservation.isPresent()) {
            return optionalReservation.get();
        } else {
            throw new NotFoundException("No such reservation found.", "reservation.not.found");
        }
    }

    public Reservation updateReservation(Long id, Reservation reservation, Long reservationId) {
        Reservation reservationById = findReservationById(id);
        reservation.setId(reservationById.getId());

        return saveReservation(reservation, reservationId);
    }

    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }
}
