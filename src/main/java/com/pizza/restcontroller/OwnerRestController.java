package com.pizza.restcontroller;

import com.pizza.dto.OwnerDto;
import com.pizza.entity.Owner;
import com.pizza.service.OwnerService;
import com.pizza.transformer.OwnerTransformer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/owner")
@CrossOrigin(origins = "*")
public class OwnerRestController {

    private final OwnerService ownerService;
    private final OwnerTransformer ownerTransformer;

    public OwnerRestController(OwnerService ownerService, OwnerTransformer ownerTransformer) {
        this.ownerService = ownerService;
        this.ownerTransformer = ownerTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OwnerDto> findOwnerById(@PathVariable("id") Long id) {
        Owner owner = ownerService.findOwnerById(id);
        OwnerDto ownerDto = ownerTransformer.transformFromOwnerToOwnerDto(owner);

        return ResponseEntity.ok(ownerDto);
    }

    @PostMapping
    public ResponseEntity<OwnerDto> addOwner(@RequestBody OwnerDto ownerDto) {
        Owner owner = ownerTransformer.transformFromOwnerDtoToOwner(ownerDto);
        Owner savedOwner = ownerService.saveOwner(owner);
        OwnerDto savedOwnerDto = ownerTransformer.transformFromOwnerToOwnerDto(savedOwner);

        return ResponseEntity.ok(savedOwnerDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<OwnerDto> updateOwner(Long id, @RequestBody OwnerDto ownerDto) {
        Owner owner = ownerTransformer.transformFromOwnerDtoToOwner(ownerDto);
        Owner updatedOwner = ownerService.updateOwner(id, owner);
        OwnerDto updatedOwnerDto = ownerTransformer.transformFromOwnerToOwnerDto(updatedOwner);

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
