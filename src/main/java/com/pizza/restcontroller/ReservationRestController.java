package com.pizza.restcontroller;

import com.pizza.converter.ReservationConverter;
import com.pizza.dto.ReservationDto;
import com.pizza.entity.Reservation;
import com.pizza.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @GetMapping
    public ResponseEntity<List<ReservationDto>> findAllReservations(@RequestParam(name = "allReservations", required = false, defaultValue = "false") Boolean allReservations,
                                                                    @RequestParam(name = "clientId", required = false) Long clientId,
                                                                    @RequestParam(name = "locationId", required = false) Long locationId) {
        List<Reservation> allReservationsList = new ArrayList<>();

        if (allReservations != null && allReservations) {
            allReservationsList = reservationService.findAllReservations();
        } else if (clientId != null) {
            allReservationsList = reservationService.findReservationsByClientId(clientId);
        } else if (locationId != null) {
            allReservationsList = reservationService.findReservationsByLocationId(locationId);
        } else if (clientId != null && locationId != null) {
            allReservationsList = reservationService.findReservationsByClientIdAndLocationId(clientId, locationId);
        }

        return ResponseEntity.ok(reservationConverter.convertFromEntityListToDtoList(allReservationsList));
    }

    @GetMapping(value = "/reservation")
    public ResponseEntity<ReservationDto> findReservation(@RequestParam(name = "id", required = false) Long id,
                                                          @RequestParam(name = "name", required = false) String name) {
        Reservation reservation = new Reservation();

        if (id != null) {
            reservation = reservationService.findReservationById(id);
        } else if (name != null) {
            reservation = reservationService.findReservationByName(name);
        }

        return ResponseEntity.ok(reservationConverter.convertFromEntityToDto(reservation));
    }

    @PostMapping
    public ResponseEntity<ReservationDto> addReservation(@Valid @RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationConverter.convertFromDtoToEntity(reservationDto);
        Reservation savedReservation = reservationService.saveReservation(reservation, reservationDto);

        return ResponseEntity.ok(reservationConverter.convertFromEntityToDto(savedReservation));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationConverter.convertFromDtoToEntity(reservationDto);
        Reservation updatedReservation = reservationService.updateReservation(id, reservation, reservationDto);

        return ResponseEntity.ok(reservationConverter.convertFromEntityToDto(updatedReservation));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ReservationDto> deleteReservationById(@PathVariable Long id) {
        reservationService.deleteReservationById(id);

        return ResponseEntity.noContent().build();
    }
}
