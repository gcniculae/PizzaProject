package com.pizza.transformer;

import com.pizza.dto.OwnerDto;
import com.pizza.entity.Owner;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OwnerTransformer {

    public OwnerDto transformFromOwnerToOwnerDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        BeanUtils.copyProperties(owner, ownerDto);

        return ownerDto;
    }

    public Owner transformFromOwnerDtoToOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();
        BeanUtils.copyProperties(ownerDto, owner);

        return owner;
    }
}
