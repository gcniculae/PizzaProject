package com.pizza.restcontroller;

import com.pizza.dto.OwnerDto;
import com.pizza.entity.Owner;
import com.pizza.service.OwnerService;
import com.pizza.converter.OwnerConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/owner")
@CrossOrigin(origins = "*")
public class OwnerRestController {

    private final OwnerService ownerService;
    private final OwnerConverter ownerTransformer;

    public OwnerRestController(OwnerService ownerService, OwnerConverter ownerTransformer) {
        this.ownerService = ownerService;
        this.ownerTransformer = ownerTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OwnerDto> findOwnerById(@PathVariable("id") Long id) {
        Owner owner = ownerService.findOwnerById(id);
        OwnerDto ownerDto = ownerTransformer.transformFromEntityToDto(owner);

        return ResponseEntity.ok(ownerDto);
    }

    @PostMapping
    public ResponseEntity<OwnerDto> addOwner(@RequestBody OwnerDto ownerDto) {
        Owner owner = ownerTransformer.transformFromDtoToEntity(ownerDto);
        Owner savedOwner = ownerService.saveOwner(owner);
        OwnerDto savedOwnerDto = ownerTransformer.transformFromEntityToDto(savedOwner);

        return ResponseEntity.ok(savedOwnerDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<OwnerDto> updateOwner(Long id, @RequestBody OwnerDto ownerDto) {
        Owner owner = ownerTransformer.transformFromDtoToEntity(ownerDto);
        Owner updatedOwner = ownerService.updateOwner(id, owner);
        OwnerDto updatedOwnerDto = ownerTransformer.transformFromEntityToDto(updatedOwner);

        return ResponseEntity.ok(updatedOwnerDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<OwnerDto> deleteOwnerById(@PathVariable("id") Long id) {
        ownerService.deleteOwnerById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{firstName}/{lastName}")
    public ResponseEntity<OwnerDto> deleteOwnerByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        ownerService.deleteOwnerByFirstNameAndLastName(firstName, lastName);

        return ResponseEntity.noContent().build();
    }
}
