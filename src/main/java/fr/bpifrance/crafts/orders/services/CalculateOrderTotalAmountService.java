package fr.bpifrance.crafts.orders.services;

import fr.bpifrance.crafts.orders.repositories.OrderRepository;
import fr.bpifrance.crafts.orders.utils.OrderUtils;

public class CalculateOrderTotalAmountService {
    private final OrderRepository orderRepository;

    public CalculateOrderTotalAmountService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public double totalAmount(Long orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found !"));

        if (OrderUtils.isEmpty(order)) {
            return 0;
        }
        return order.getItems().values().stream()
            .reduce(0d, Double::sum);
    }
}
