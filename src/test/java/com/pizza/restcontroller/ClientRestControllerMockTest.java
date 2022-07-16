package com.pizza.restcontroller;

import com.pizza.repository.ClientRepository;
import com.pizza.service.ClientService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientRestControllerMockTest {

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;
}
