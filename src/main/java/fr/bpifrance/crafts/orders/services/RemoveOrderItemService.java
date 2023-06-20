package fr.bpifrance.crafts.orders.services;

import fr.bpifrance.crafts.orders.repositories.OrderRepository;
import fr.bpifrance.crafts.orders.utils.OrderUtils;

public class RemoveOrderItemService {
    private final OrderRepository orderRepository;

    public RemoveOrderItemService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void removeItem(Long orderId, String sku) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found !"));

        if (OrderUtils.isEmpty(order)) {
            throw new RuntimeException("Cannot remove item from an empty order !");
        }
        if (order.getItems().containsKey(sku)) {
            order.getItems().remove(sku);
        } else {
            throw new RuntimeException("Cannot remove item [%s], reason: item no found !" .formatted(sku));
        }
    }
}
