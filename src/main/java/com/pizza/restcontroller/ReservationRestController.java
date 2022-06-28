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
    public ResponseEntity<List<ReservationDto>> findAllReservations(@RequestParam(name = "all", required = false, defaultValue = "false") Boolean all,
                                                                    @RequestParam(name = "clientId", required = false) Long clientId,
                                                                    @RequestParam(name = "locationId", required = false) Long locationId) {
        List<Reservation> allReservationsList = new ArrayList<>();

        if (all) {
            allReservationsList = reservationService.findAllReservations();
        } else if (clientId != null) {
            allReservationsList = reservationService.findReservationsByClientId(clientId);
        } else if (locationId != null) {
            allReservationsList = reservationService.findReservationsByLocationId(locationId);
        } else if (clientId != null && locationId != null) {
            allReservationsList = reservationService.findReservationsByClientIdAndLocationId(clientId, locationId);
        }

        List<ReservationDto> reservationDtoList = reservationConverter.convertFromEntityListToDtoList(allReservationsList);
        reservationService.addClientIdToDtoList(allReservationsList, reservationDtoList);
        reservationService.addLocationIdToDtoList(allReservationsList, reservationDtoList);

        return ResponseEntity.ok(reservationDtoList);
    }

    @GetMapping(value = "/single")
    public ResponseEntity<ReservationDto> findReservation(@RequestParam(name = "id", required = false) Long id,
                                                          @RequestParam(name = "name", required = false) String name) {
        Reservation reservation = new Reservation();

        if (id != null) {
            reservation = reservationService.findReservationById(id);
        } else if (name != null) {
            reservation = reservationService.findReservationByName(name);
        }

        ReservationDto reservationDto = reservationConverter.convertFromEntityToDto(reservation);
        reservationService.addClientIdToDto(reservation, reservationDto);
        reservationService.addLocationIdToDto(reservation, reservationDto);

        return ResponseEntity.ok(reservationDto);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> addReservation(@Valid @RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationConverter.convertFromDtoToEntity(reservationDto);
        Reservation savedReservation = reservationService.saveReservation(reservation, reservationDto);
        ReservationDto savedReservationDto = reservationConverter.convertFromEntityToDto(savedReservation);
        reservationService.addClientIdToDto(savedReservation, savedReservationDto);
        reservationService.addLocationIdToDto(savedReservation, savedReservationDto);

        return ResponseEntity.ok(savedReservationDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationConverter.convertFromDtoToEntity(reservationDto);
        Reservation updatedReservation = reservationService.updateReservation(id, reservation, reservationDto);
        ReservationDto updatedReservationDto = reservationConverter.convertFromEntityToDto(updatedReservation);
        reservationService.addClientIdToDto(updatedReservation, updatedReservationDto);
        reservationService.addLocationIdToDto(updatedReservation, updatedReservationDto);

        return ResponseEntity.ok(updatedReservationDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ReservationDto> deleteReservationById(@PathVariable Long id) {
        reservationService.deleteReservationById(id);

        return ResponseEntity.noContent().build();
    }
}
