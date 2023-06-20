package fr.bpifrance.crafts.orders.services;

import fr.bpifrance.crafts.orders.factories.OrderItemFactory;
import fr.bpifrance.crafts.orders.repositories.OrderRepository;

public class AddOrderItemService {
    private final OrderRepository orderRepository;

    public AddOrderItemService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrderItem(Long orderId, String sku, Double price) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found !"));

        OrderItemFactory.createOrderItem(order, sku, price);
        orderRepository.save(order);
    }
}
