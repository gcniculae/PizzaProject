package com.pizza.converter;

import com.pizza.dto.MenuDto;
import com.pizza.entity.Menu;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MenuConverter extends BaseConverter<MenuDto, Menu> {

    @Override
    public MenuDto transformFromEntityToDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        BeanUtils.copyProperties(menu, menuDto);

        return menuDto;
    }

    @Override
    public Menu transformFromDtoToEntity(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto, menu);

        return menu;
    }
}
