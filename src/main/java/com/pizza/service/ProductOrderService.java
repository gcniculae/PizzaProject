package com.pizza.service;

import com.pizza.dto.ProductOrderDto;
import com.pizza.entity.Client;
import com.pizza.entity.Pizza;
import com.pizza.entity.ProductOrder;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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

        Double pizzaTotalPrice = getPizzaTotalPrice(productOrder);

        if (productOrder.getPaymentId() == null) {
            String savePayment = paymentClientService.savePayment(productOrder.getClient().getId(), pizzaTotalPrice);
            productOrder.setPaymentId(extractPaymentId(savePayment));
        }

        return productOrderRepository.save(productOrder);
    }

    private Double getPizzaTotalPrice(ProductOrder productOrder) {
        return productOrder.getPizzas()
                .stream()
                .map(Pizza::getPrice)
                .toList()
                .stream()
                .reduce(0.0, Double::sum);
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
            String paymentByIdResponse = paymentClientService.findPaymentById(id);

            ProductOrder productOrder = optionalProductOrder.get();
            productOrder.setPaymentId(extractPaymentId(paymentByIdResponse));

            return productOrder;
        } else {
            throw new NotFoundException("No such product order found.", "productOrder.not.found");
        }
    }

    public ProductOrder updateProductOrder(Long id, ProductOrder productOrder, ProductOrderDto productOrderDto) {
        ProductOrder productOrderById = findProductOrderById(id);
        productOrder.setId(productOrderById.getId());

        productOrder.setClient(clientService.findClientById(productOrderDto.getClientId()));
        productOrder.setPizzas(pizzaService.findPizzasByIds(productOrderDto.getPizzasIds()));

        String updatePayment = paymentClientService.updatePayment(productOrderById.getPaymentId(), productOrder.getClient().getId(), getPizzaTotalPrice(productOrder));
        productOrder.setPaymentId(extractPaymentId(updatePayment));

        return saveProductOrder(productOrder, productOrderDto);
    }

    public void deleteProductOrderServiceById(Long id) {
        ProductOrder productOrderById = findProductOrderById(id);

        productOrderRepository.deleteById(productOrderById.getId());
    }

    public void addPizzasIdsToDto(ProductOrderDto productOrderDto, ProductOrder productOrder) {
        productOrderDto.setPizzasIds(pizzaService.findIdsByPizza(productOrder.getPizzas()));
    }

    public void addClientId(ProductOrderDto productOrderDto, Client client) {
        productOrderDto.setClientId(client.getId());
    }

    public void addPizzasIdsToDtoList(List<ProductOrder> allProductOrdersList, List<ProductOrderDto> productOrdersDto) {
        for (int index = 0; index < productOrdersDto.size(); index++) {
            productOrdersDto.get(index).setPizzasIds(pizzaService.findIdsByPizza(allProductOrdersList.get(index).getPizzas()));
        }
    }

    public void addClientIdToDtoList(List<ProductOrder> allProductOrdersList, List<ProductOrderDto> productOrdersDto) {
        for (int index = 0; index < productOrdersDto.size(); index++) {
            productOrdersDto.get(index).setClientId(allProductOrdersList.get(index).getClient().getId());
        }
    }

    private Long extractPaymentId(String paymentByIdResponse) {
        long id;

        try {
            JSONObject obj = new JSONObject(paymentByIdResponse);
            id = obj.getLong("id");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return id;
    }
}
