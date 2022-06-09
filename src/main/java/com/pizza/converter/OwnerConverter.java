package com.pizza.converter;

import com.pizza.dto.OwnerDto;
import com.pizza.entity.Owner;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OwnerConverter extends BaseConverter<OwnerDto, Owner> {

    @Override
    public OwnerDto convertFromEntityToDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        BeanUtils.copyProperties(owner, ownerDto);

        return ownerDto;
    }

    @Override
    public Owner convertFromDtoToEntity(OwnerDto ownerDto) {
        Owner owner = new Owner();
        BeanUtils.copyProperties(ownerDto, owner);

        return owner;
    }
}
