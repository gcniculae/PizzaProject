package com.pizza.service;

import com.pizza.entity.Menu;
import com.pizza.entity.Pizzeria;
import com.pizza.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    }

    @Test
    public void saveMenuTest() {
        when(menuRepository.save(eq(menu1))).thenReturn(menu1);
        when(pizzeriaService.findPizzeriaById(pizzeria.getId())).thenReturn(pizzeria);

        Menu savedMenu = menuService.saveMenu(menu1, pizzeria.getId());

        assertEquals(menu1.getId(), savedMenu.getId());

        verify(menuRepository, times(1)).save(menu1);
    }
}
