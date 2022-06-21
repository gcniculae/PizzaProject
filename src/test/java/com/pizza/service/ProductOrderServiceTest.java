package com.pizza.service;

import com.pizza.entity.Client;
import com.pizza.entity.ProductOrder;
import com.pizza.repository.ProductOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ProductOrderServiceTest {

    @InjectMocks
    private ProductOrderService productOrderService;

    @Mock
    private ProductOrderRepository productOrderRepository;

    @Mock
    private ClientService clientService;

    private ProductOrder productOrder1;
    private Client client;

    @BeforeEach
    public void init() {
        productOrderRepository = mock(ProductOrderRepository.class);
        clientService = mock(ClientService.class);
        productOrderService = new ProductOrderService(productOrderRepository, clientService);

        initializeClients();

        initializeProductOrders();
    }

    private void initializeProductOrders() {
        
    }

    private void initializeClients() {
    }
}
