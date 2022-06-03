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


}
