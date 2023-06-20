package fr.bpifrance.crafts.orders.repositories;

import fr.bpifrance.crafts.orders.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class OrderRepository {

    private Map<Long, Order> registry = new HashMap<>();

    public Optional<Order> findById(Long orderId) {
        if (registry.containsKey(orderId)) {
            return Optional.of(registry.get(orderId));
        }
        return Optional.empty();
    }

    public void save(Order order) {
        registry.put(order.getId(), order);
    }
}
