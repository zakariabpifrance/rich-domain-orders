package fr.bpifrance.crafts.orders.services;

import fr.bpifrance.crafts.orders.model.OrderItem;
import fr.bpifrance.crafts.orders.repositories.OrderRepository;
import fr.bpifrance.crafts.orders.utils.OrderUtils;

import java.util.Set;

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
        if (containsItem(sku, order.getItems())) {
            OrderItem orderItemToRemove = new OrderItem();
            orderItemToRemove.setSku(sku);
            order.getItems().remove(orderItemToRemove);
        } else {
            throw new RuntimeException("Cannot remove item [%s], reason: item no found !" .formatted(sku));
        }
    }

    private boolean containsItem(String sku, Set<OrderItem> items) {
        return items.stream().anyMatch(item -> item.getSku().equals(sku));
    }
}
