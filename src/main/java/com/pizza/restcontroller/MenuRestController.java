package com.pizza.restcontroller;

import com.pizza.converter.MenuConverter;
import com.pizza.dto.MenuDto;
import com.pizza.entity.Menu;
import com.pizza.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/menus")
@CrossOrigin(origins = "*")
public class MenuRestController {

    private final MenuService menuService;
    private final MenuConverter menuConverter;

    @Autowired
    public MenuRestController(MenuService menuService, MenuConverter menuConverter) {
        this.menuService = menuService;
        this.menuConverter = menuConverter;
    }

    @GetMapping
    public ResponseEntity<List<MenuDto>> getAllMenus() {
        List<Menu> allMenus = menuService.findAllMenus();
        List<MenuDto> menuDtoList = menuConverter.convertFromEntityListToDtoList(allMenus);
        menuService.addPizzeriaIdToDtoList(allMenus, menuDtoList);

        return ResponseEntity.ok(menuDtoList);
    }

    @GetMapping(path = "/single")
    public ResponseEntity<MenuDto> getMenuById(@RequestParam(name = "id", required = false) Long id,
                                               @RequestParam(name = "name", required = false) String name) {
        Menu menu = new Menu();

        if (id != null) {
            menu = menuService.findMenuById(id);
        } else if (name != null) {
            menu = menuService.findMenuByName(name);
        }

        MenuDto menuDto = menuConverter.convertFromEntityToDto(menu);
        menuService.addPizzeriaIdToDto(menu, menuDto);

        return ResponseEntity.ok(menuDto);
    }

    @PostMapping
    public ResponseEntity<MenuDto> addMenu(@Valid @RequestBody MenuDto menuDto) {
        Menu savedMenu = menuService.saveMenu(menuConverter.convertFromDtoToEntity(menuDto), menuDto.getPizzeriaId());
        MenuDto savedMenuDto = menuConverter.convertFromEntityToDto(savedMenu);
        menuService.addPizzeriaIdToDto(savedMenu, savedMenuDto);

        return ResponseEntity.ok(savedMenuDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> updateMenu(@Valid @PathVariable Long id, @RequestBody MenuDto menuDto) {
        Menu updatedMenu = menuService.updateMenu(id, menuConverter.convertFromDtoToEntity(menuDto), menuDto.getPizzeriaId());
        MenuDto updatedMenuDto = menuConverter.convertFromEntityToDto(updatedMenu);
        menuService.addPizzeriaIdToDto(updatedMenu, updatedMenuDto);

        return ResponseEntity.ok(updatedMenuDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<MenuDto> deleteMenuById(@PathVariable Long id) {
        menuService.deleteMenuById(id);

        return ResponseEntity.noContent().build();
    }
}
