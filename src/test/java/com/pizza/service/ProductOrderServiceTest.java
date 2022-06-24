package com.pizza.service;

import com.pizza.dto.ProductOrderDto;
import com.pizza.entity.Client;
import com.pizza.entity.Pizza;
import com.pizza.entity.ProductOrder;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.ProductOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductOrderServiceTest {

    @InjectMocks
    private ProductOrderService productOrderService;

    @Mock
    private ProductOrderRepository productOrderRepository;

    @Mock
    private ClientService clientService;

    @Mock
    private PaymentClientService paymentClientService;

    @Mock
    private PizzaService pizzaService;

    private ProductOrder productOrder1;
    private ProductOrder productOrder2;
    private ProductOrder productOrder3;
    private Client client1;
    private Client client2;

    private Pizza pizza1;
    private Pizza pizza2;

    @BeforeEach
    public void init() {
        productOrderRepository = mock(ProductOrderRepository.class);
        clientService = mock(ClientService.class);
        paymentClientService = mock(PaymentClientService.class);
        pizzaService = mock(PizzaService.class);
        productOrderService = new ProductOrderService(productOrderRepository, clientService, paymentClientService, pizzaService);

        initializeClients();

        initializePizzas();

        initializeProductOrders();
    }

    private void initializeProductOrders() {
        productOrder1 = new ProductOrder();
        productOrder1.setId(1L);
        productOrder1.setClient(client1);

        productOrder2 = new ProductOrder();
        productOrder2.setId(2L);
        productOrder2.setClient(client2);

        productOrder3 = new ProductOrder();
        productOrder3.setId(3L);
        productOrder3.setClient(client1);
    }

    private void initializePizzas() {
        pizza1 = new Pizza();
        pizza1.setName("Chicken");
        pizza1.setPrice(30.0);

        pizza2 = new Pizza();
        pizza2.setName("Cheese");
        pizza2.setPrice(25.0);
    }

    private void initializeClients() {
        client1 = new Client.ClientBuilder()
                .setFirstName("Andrei")
                .setLastName("Popescu")
                .setDateOfBirth(LocalDate.of(1994, 10, 2))
                .setAddress("Ploiesti")
                .setPhoneNumber("0727009810")
                .build();
        client1.setId(1L);

        client2 = new Client.ClientBuilder()
                .setFirstName("Alexandru")
                .setLastName("Ionescu")
                .setDateOfBirth(LocalDate.of(1985, 4, 19))
                .setAddress("Ploiesti")
                .setPhoneNumber("0721070398")
                .build();
        client2.setId(2L);
    }

    @Test
    public void saveProductOrderTest() {
        List<Pizza> pizzas = Arrays.asList(pizza1, pizza2);
        List<Long> pizzasIds = Arrays.asList(pizza1.getId(), pizza2.getId());

        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setClientId(client1.getId());
        productOrderDto.setPizzasIds(pizzasIds);

        when(clientService.findClientById(eq(client1.getId()))).thenReturn(client1);
        when(pizzaService.findPizzasByIds(eq(pizzasIds))).thenReturn(pizzas);
        productOrder1.setClient(client1);
        productOrder1.setPizzas(pizzas);
        when(productOrderRepository.save(eq(productOrder1))).thenReturn(productOrder1);

        ProductOrder savedProductOrder = productOrderService.saveProductOrder(productOrder1, productOrderDto);

        assertEquals(productOrder1.getId(), savedProductOrder.getId());

        verify(productOrderRepository, times(1)).save(productOrder1);
    }

    @Test
    public void findAllProductOrdersTest() {
        List<ProductOrder> productOrders = Arrays.asList(productOrder1, productOrder2);

        when(productOrderRepository.findAll()).thenReturn(productOrders);

        assertEquals(productOrders.size(), productOrderService.findAllProductOrders().size());

        verify(productOrderRepository, times(1)).findAll();
    }

    @Test
    public void findProductOrdersByClientIdTest() {
        List<ProductOrder> productOrders = Arrays.asList(productOrder1, productOrder3);

        when(productOrderRepository.findByClientId(eq(client1.getId()))).thenReturn(productOrders);

        assertEquals(productOrders.size(), productOrderService.findProductOrdersByClientId(client1.getId()).size());

        verify(productOrderRepository, times(1)).findByClientId(client1.getId());
    }

    @Test
    public void findExistentProductOrderByIdTest() {
        when(productOrderRepository.findById(eq(productOrder1.getId()))).thenReturn(Optional.of(productOrder1));

        ProductOrder productOrderById = productOrderService.findProductOrderById(productOrder1.getId());

        assertEquals(productOrder1.getId(), productOrderById.getId());

        verify(productOrderRepository, times(1)).findById(productOrder1.getId());
    }

    @Test
    public void findNonexistentProductOrderByIdTest() {
        when(productOrderRepository.findById(eq(productOrder1.getId()))).thenThrow(new NotFoundException("No such product order found.", "productOrder.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> productOrderRepository.findById(productOrder1.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such product order found.");
    }

    @Test
    public void updateProductOrderTest() {
        List<Long> pizzasIds = Arrays.asList(pizza1.getId(), pizza2.getId());

        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setClientId(client2.getId());
        productOrderDto.setPizzasIds(pizzasIds);

        when(clientService.findClientById(eq(client2.getId()))).thenReturn(client2);
        productOrder1.setClient(client2);
        when(productOrderRepository.save(eq(productOrder1))).thenReturn(productOrder1);
        when(productOrderRepository.findById(eq(productOrder1.getId()))).thenReturn(Optional.of(productOrder1));

        ProductOrder updatedProductOrder = productOrderService.updateProductOrder(productOrder1.getId(), productOrder1, productOrderDto);

        assertEquals(productOrder1.getId(), updatedProductOrder.getId());
        assertEquals(productOrder1.getClient(), updatedProductOrder.getClient());
    }

    @Test
    public void deleteExistentProductOrderById() {
        when(productOrderRepository.findById(eq(productOrder1.getId()))).thenReturn(Optional.of(productOrder1));
        doNothing().when(productOrderRepository).deleteById(productOrder1.getId());

        productOrderService.deleteProductOrderServiceById(productOrder1.getId());

        verify(productOrderRepository, times(1)).deleteById(productOrder1.getId());
    }

    @Test
    public void deleteNonexistentProductOrderById() {
        when(productOrderRepository.findById(eq(productOrder1.getId()))).thenThrow(new NotFoundException("No such product order found.", "productOrder.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> productOrderRepository.findById(productOrder1.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such product order found.");
    }
}
