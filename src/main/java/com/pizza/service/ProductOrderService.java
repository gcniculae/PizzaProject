package com.pizza.service;

import com.pizza.entity.ProductOrder;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final ClientService clientService;

    @Autowired
    public ProductOrderService(ProductOrderRepository productOrderRepository, ClientService clientService) {
        this.productOrderRepository = productOrderRepository;
        this.clientService = clientService;
    }

    public ProductOrder saveProductOrder(ProductOrder productOrder, Long clientId) {
        productOrder.setClient(clientService.findClientById(clientId));

        return productOrderRepository.save(productOrder);
    }

    public List<ProductOrder> findAllProductOrders() {
        return productOrderRepository.findAll();
    }

    public List<ProductOrder> findProductOrdersByClientId(Long clientId) {
        return productOrderRepository.findByClientId(clientId);
    }

    public ProductOrder findProductOrderById(Long id) {
        Optional<ProductOrder> optionalProductOrder = productOrderRepository.findById(id);

        if (optionalProductOrder.isPresent()) {
            return optionalProductOrder.get();
        } else {
            throw new NotFoundException("No such product order found.", "productOrder.not.found");
        }
    }

    public ProductOrder updateProductOrder(Long id, ProductOrder productOrder, Long clientId) {
        ProductOrder productOrderById = findProductOrderById(id);
        productOrder.setId(productOrderById.getId());

        return saveProductOrder(productOrder, clientId);
    }

    public void deleteProductOrderServiceById(Long id) {
        ProductOrder productOrderById = findProductOrderById(id);

        productOrderRepository.deleteById(productOrderById.getId());
    }
}
