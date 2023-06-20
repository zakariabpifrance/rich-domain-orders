package fr.bpifrance.crafts.orders.factories;

import fr.bpifrance.crafts.orders.model.Order;
import fr.bpifrance.crafts.orders.model.OrderItem;

public class OrderItemFactory {

    public static void createOrderItem(Order order, String sku, Double price, int quantity) {
        if (null == order || null == order.getItems()) {
            return;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setSku(sku);
        orderItem.setPrice(price);
        orderItem.setQuality(quantity);
        order.getItems().add(orderItem);
    }

    private OrderItemFactory() {}
}
