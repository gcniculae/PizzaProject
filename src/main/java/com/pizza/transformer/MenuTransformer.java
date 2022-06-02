package com.pizza.transformer;

import com.pizza.dto.MenuDto;
import com.pizza.entity.Menu;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MenuTransformer {

    public MenuDto transformFromMenuToMenuDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        BeanUtils.copyProperties(menu, menuDto);

        return menuDto;
    }

    public Menu transformFromMenuDtoToMenu(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto, menu);

        return menu;
    }
}
