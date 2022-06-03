package com.pizza.converter;

import com.pizza.dto.BaseEntityDto;
import com.pizza.entity.BaseEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<D extends BaseEntityDto, E extends BaseEntity> {

    public abstract D transformFromEntityToDto(E entity);

    public abstract E transformFromDtoToEntity(D dto);

    public List<D> transformFromEntityListToDtoList(List<E> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::transformFromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<E> transformFromDtoListToEntityList(List<D> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
        }

        return dtoList.stream()
                .map(this::transformFromDtoToEntity)
                .collect(Collectors.toList());
    }
}
