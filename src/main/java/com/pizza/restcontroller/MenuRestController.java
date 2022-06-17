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
        return ResponseEntity.ok(menuConverter.convertFromEntityListToDtoList(menuService.findAllMenus()));
    }

    @GetMapping(path = "/menu")
    public ResponseEntity<MenuDto> getMenuById(@RequestParam(name = "id", required = false) Long id,
                                               @RequestParam(name = "name", required = false) String name) {
        Menu menu = new Menu();

        if (id != null) {
            menu = menuService.findMenuById(id);
        } else if (name != null) {
            menu = menuService.findMenuByName(name);
        }

        return ResponseEntity.ok(menuConverter.convertFromEntityToDto(menu));
    }

    @PostMapping
    public ResponseEntity<MenuDto> addMenu(@Valid @RequestBody MenuDto menuDto) {
        return ResponseEntity.ok(menuConverter.convertFromEntityToDto(menuService.saveMenu(menuConverter.convertFromDtoToEntity(menuDto), menuDto.getPizzeriaId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> updateMenu(@Valid @PathVariable Long id, @RequestBody MenuDto menuDto) {
        return ResponseEntity.ok(menuConverter.convertFromEntityToDto(menuService.updateMenu(id, menuConverter.convertFromDtoToEntity(menuDto), menuDto.getPizzeriaId())));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<MenuDto> deleteMenuById(@PathVariable Long id) {
        menuService.deleteMenuById(id);

        return ResponseEntity.noContent().build();
    }
}
