package fr.bpifrance.crafts.orders;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class OrderManagement {

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
