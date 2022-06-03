package com.pizza.service;

import com.pizza.entity.Reservation;
import com.pizza.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation saveReservation(Reservation reservation) {
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
            throw new NoSuchElementException("No such reservation.");
        }
    }

    public Reservation findReservationByLocationId(Long locationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findByLocationId(locationId);

        if (optionalReservation.isPresent()) {
            return optionalReservation.get();
        } else {
            throw new NoSuchElementException("No such reservation.");
        }
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation reservationById = findReservationById(id);
        reservation.setId(reservationById.getId());

        return saveReservation(reservation);
    }

    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }
}
