package com.pizza.service;

import com.pizza.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {

    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    @BeforeEach
    public void init() {
        ownerRepository = mock(OwnerRepository.class);
        ownerService = new OwnerService(ownerRepository);
    }
}
