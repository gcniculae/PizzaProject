package com.pizza.restcontroller;

import com.pizza.converter.ReservationConverter;
import com.pizza.dto.ReservationDto;
import com.pizza.entity.Reservation;
import com.pizza.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/reservations")
@CrossOrigin(origins = "*")
public class ReservationRestController {

    private final ReservationService reservationService;
    private final ReservationConverter reservationConverter;

    public ReservationRestController(ReservationService reservationService, ReservationConverter reservationConverter) {
        this.reservationService = reservationService;
        this.reservationConverter = reservationConverter;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ReservationDto>> findAllReservations() {
        return ResponseEntity.ok(reservationConverter.convertFromEntityListToDtoList(reservationService.findAllReservations()));
    }

    @GetMapping
    public ResponseEntity<ReservationDto> findReservation(@RequestParam(name = "id", required = false) Long id,
                                                          @RequestParam(name = "name", required = false) String name,
                                                          @RequestParam(name = "locationId", required = false) Long locationId) {
        Reservation reservation = new Reservation();

        if (id != null) {
            reservation = reservationService.findReservationById(id);
        } else if (name != null) {
            reservation = reservationService.findReservationByName(name);
        } else if (locationId != null) {
            reservation = reservationService.findReservationByLocationId(locationId);
        }

        return ResponseEntity.ok(reservationConverter.convertFromEntityToDto(reservation));
    }

    @PostMapping
    public ResponseEntity<ReservationDto> addReservation(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationConverter.convertFromDtoToEntity(reservationDto);
        Reservation savedReservation = reservationService.saveReservation(reservation);

        return ResponseEntity.ok(reservationConverter.convertFromEntityToDto(reservation));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationConverter.convertFromDtoToEntity(reservationDto);
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);

        return ResponseEntity.ok(reservationConverter.convertFromEntityToDto(updatedReservation));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ReservationDto> deleteReservationById(@PathVariable Long id) {
        reservationService.deleteReservationById(id);

        return ResponseEntity.noContent().build();
    }
}
