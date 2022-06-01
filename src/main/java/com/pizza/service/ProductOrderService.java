package com.pizza.service;

import com.pizza.entity.ProductOrder;
import com.pizza.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;

    @Autowired
    public ProductOrderService(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    public void saveProductOwner(ProductOrder productOrder) {
        productOrderRepository.save(productOrder);
    }

    public ProductOrder findProductOrderById(Long id) {
        Optional<ProductOrder> optionalProductOrder = productOrderRepository.findById(id);

        if (optionalProductOrder.isPresent()) {
            return optionalProductOrder.get();
        } else {
            throw new NoSuchElementException("No such product owner.");
        }
    }

    public void deleteProductOrderServiceById(Long id) {
        productOrderRepository.deleteById(id);
    }
}
