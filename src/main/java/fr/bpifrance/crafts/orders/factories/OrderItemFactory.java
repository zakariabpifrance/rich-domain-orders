package fr.bpifrance.crafts.orders.factories;

import fr.bpifrance.crafts.orders.model.Order;

public class OrderItemFactory {

    public static void createOrderItem(Order order, String sku, Double price) {
        if (null == order || null == order.getItems()) {
            return;
        }
        order.getItems().put(sku, price);
    }

    private OrderItemFactory() {}
}
