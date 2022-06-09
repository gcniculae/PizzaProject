package com.pizza.repository;

import com.pizza.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findReservationsByClientId(Long clientId);

    List<Reservation> findReservationsByLocationId(Long locationId);

    Optional<Reservation> findByLocationId(Long locationId);

    Optional<Reservation> findByName(String name);

    @Query(value = "from Reservation r where r.client.id = :clientId and r.location.id = :locationId")
    List<Reservation> findReservationsByClientIdAndLocationId(Long clientId, Long locationId);
}
