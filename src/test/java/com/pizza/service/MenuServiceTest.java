package com.pizza.service;

import com.pizza.entity.Menu;
import com.pizza.entity.Pizzeria;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @InjectMocks
    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private PizzeriaService pizzeriaService;

    private Menu menu1;
    private Menu menu2;
    private Pizzeria pizzeria;

    @BeforeEach
    public void init() {
        menuRepository = mock(MenuRepository.class);
        pizzeriaService = mock(PizzeriaService.class);
        menuService = new MenuService(menuRepository, pizzeriaService);

        initializePizzeria();

        initializeMenus();
    }

    private void initializePizzeria() {
        pizzeria = new Pizzeria.PizzeriaBuilder()
                .setName("Pizzeria")
                .build();
        pizzeria.setId(1L);
    }

    private void initializeMenus() {
        menu1 = new Menu("Menu1", pizzeria);
        menu1.setId(1L);

        menu2 = new Menu("Menu2", pizzeria);
        menu2.setId(2L);
    }

    @Test
    public void saveMenuTest() {
        when(menuRepository.save(eq(menu1))).thenReturn(menu1);
        when(pizzeriaService.findPizzeriaById(pizzeria.getId())).thenReturn(pizzeria);

        Menu savedMenu = menuService.saveMenu(menu1, pizzeria.getId());

        assertEquals(menu1.getId(), savedMenu.getId());

        verify(menuRepository, times(1)).save(menu1);
    }

    @Test
    public void findAllMenusTest() {
        List<Menu> menus = Arrays.asList(menu1, menu2);

        when(menuRepository.findAll()).thenReturn(menus);

        assertEquals(menus.size(), menuService.findAllMenus().size());

        verify(menuRepository, times(1)).findAll();
    }

    @Test
    public void findMenuByIdTest() {
        when(menuRepository.findById(eq(menu1.getId()))).thenReturn(Optional.of(menu1));

        Menu menuById = menuService.findMenuById(menu1.getId());

        assertEquals(menu1.getId(), menuById.getId());
        verify(menuRepository, times(1)).findById(menuById.getId());
    }

    @Test
    public void findExistentMenuByIdTest() {
        when(menuRepository.findById(eq(menu1.getId()))).thenThrow(new NotFoundException("No such menu found.", "menu.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> menuRepository.findById(menu1.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such menu found.");
    }

    @Test
    public void findMenuByNameTest() {
        when(menuRepository.findByName(eq(menu1.getName()))).thenReturn(Optional.of(menu1));

        Menu menuByName = menuService.findMenuByName(menu1.getName());

        assertEquals(menu1.getId(), menuByName.getId());

        verify(menuRepository).findByName(menu1.getName());
    }

    @Test
    public void updateMenuTest() {
        when(menuRepository.findById(eq(menu1.getId()))).thenReturn(Optional.of(menu1));
        menu1.setName("Menu3");
        when(pizzeriaService.findPizzeriaById(eq(pizzeria.getId()))).thenReturn(pizzeria);
        when(menuRepository.save(eq(menu1))).thenReturn(menu1);

        Menu updatedMenu = menuService.updateMenu(menu1.getId(), menu1, pizzeria.getId());

        assertEquals(menu1.getId(), updatedMenu.getId());
    }

    @Test
    public void deleteExistentMenuByIdTest() {
        when(menuRepository.findById(eq(menu1.getId()))).thenReturn(Optional.of(menu1));
        doNothing().when(menuRepository).deleteById(menu1.getId());

        menuService.deleteMenuById(menu1.getId());

        verify(menuRepository, times(1)).deleteById(menu1.getId());
    }

    @Test
    public void deleteNonexistentMenuByIdTest() {
        when(menuRepository.findById(eq(menu1.getId()))).thenThrow(new NotFoundException("No such menu found.", "menu.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> menuRepository.findById(menu1.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such menu found.");
    }
}
