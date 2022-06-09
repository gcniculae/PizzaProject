package com.pizza.service;

import com.pizza.entity.Menu;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final PizzeriaService pizzeriaService;

    public MenuService(MenuRepository menuRepository, PizzeriaService pizzeriaService) {
        this.menuRepository = menuRepository;
        this.pizzeriaService = pizzeriaService;
    }

    public Menu saveMenu(Menu menu, Long pizzeriaId) {
        menu.setPizzeria(pizzeriaService.findPizzeriaById(pizzeriaId));

        return menuRepository.save(menu);
    }

    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    public Menu findMenuById(Long id) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);

        if (optionalMenu.isPresent()) {
            return optionalMenu.get();
        } else {
            throw new NotFoundException("No such menu found.", "menu.not.found");
        }
    }

    public Menu findMenuByName(String name) {
        Optional<Menu> optionalMenu = menuRepository.findByName(name);

        if (optionalMenu.isPresent()) {
            return optionalMenu.get();
        } else {
            throw new NotFoundException("No such menu found.", "menu.not.found");
        }
    }

    public Menu updateMenu(Long id, Menu menu, Long pizzeriaId) {
        Menu menuById = findMenuById(id);
        menu.setId(menuById.getId());

        return saveMenu(menu, pizzeriaId);
    }

    public void deleteMenuById(Long id) {
        menuRepository.deleteById(id);
    }
}
