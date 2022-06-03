package com.pizza.converter;

import com.pizza.dto.BaseEntityDto;
import com.pizza.entity.BaseEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<D extends BaseEntityDto, E extends BaseEntity> {

    public abstract D convertFromEntityToDto(E entity);

    public abstract E convertFromDtoToEntity(D dto);

    public List<D> convertFromEntityListToDtoList(List<E> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::convertFromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<E> convertFromDtoListToEntityList(List<D> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
        }

        return dtoList.stream()
                .map(this::convertFromDtoToEntity)
                .collect(Collectors.toList());
    }
}
