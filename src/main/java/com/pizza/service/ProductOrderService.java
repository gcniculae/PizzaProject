package com.pizza.service;

import com.pizza.dto.ProductOrderDto;
import com.pizza.entity.Pizza;
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
    private final PaymentClientService paymentClientService;

    private final PizzaService pizzaService;

    @Autowired
    public ProductOrderService(ProductOrderRepository productOrderRepository, ClientService clientService, PaymentClientService paymentClientService, PizzaService pizzaService) {
        this.productOrderRepository = productOrderRepository;
        this.clientService = clientService;
        this.paymentClientService = paymentClientService;
        this.pizzaService = pizzaService;
    }

    public ProductOrder saveProductOrder(ProductOrder productOrder, ProductOrderDto productOrderDto) {
        productOrder.setClient(clientService.findClientById(productOrderDto.getClientId()));
        productOrder.setPizzas(pizzaService.findPizzasByIds(productOrderDto.getPizzasIds()));

        Double pizzaTotalPrice = productOrder.getPizzas()
                .stream()
                .map(Pizza::getPrice)
                .toList()
                .stream()
                .reduce(0.0, Double::sum);

        paymentClientService.savePayment(productOrder.getClient().getId(), pizzaTotalPrice);

        return productOrderRepository.save(productOrder);
    }

    public void addPizzasIdsToDto(ProductOrderDto productOrderDto, ProductOrder productOrder) {
        productOrderDto.setPizzasIds(pizzaService.findIdsByPizza(productOrder.getPizzas()));
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

    public ProductOrder updateProductOrder(Long id, ProductOrder productOrder, ProductOrderDto productOrderDto) {
        ProductOrder productOrderById = findProductOrderById(id);
        productOrder.setId(productOrderById.getId());

        return saveProductOrder(productOrder, productOrderDto);
    }

    public void deleteProductOrderServiceById(Long id) {
        ProductOrder productOrderById = findProductOrderById(id);

        productOrderRepository.deleteById(productOrderById.getId());
    }
}
