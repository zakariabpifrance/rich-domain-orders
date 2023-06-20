package fr.bpifrance.crafts.orders.utils;

import fr.bpifrance.crafts.orders.model.Order;

public final class OrderUtils {

    public static boolean isEmpty(Order order) {
        if (null == order) {
            throw new RuntimeException("Order cannot be null");
        }
        return order.getItems().isEmpty();
    }

    private OrderUtils() {}
}
