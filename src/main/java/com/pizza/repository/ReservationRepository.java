package com.pizza.repository;

import com.pizza.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByLocationId(Long locationId);

    Optional<Reservation> findByName(String name);
}
