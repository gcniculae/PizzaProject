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

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu saveMenu(Menu menu) {
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

    public Menu updateMenu(Long id, Menu menu) {
        Menu menuById = findMenuById(id);
        menu.setId(menuById.getId());

        return saveMenu(menu);
    }

    public void deleteMenuById(Long id) {
        menuRepository.deleteById(id);
    }
}
