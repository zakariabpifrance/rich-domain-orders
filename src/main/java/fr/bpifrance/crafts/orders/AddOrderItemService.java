package fr.bpifrance.crafts.orders;

public class AddOrderItemService {
    private final OrderManagement orderManagement;

    public AddOrderItemService(OrderManagement orderManagement) {
        this.orderManagement = orderManagement;
    }

    public void addOrderItem(Long orderId, String sku, Double price) {
        var order = orderManagement.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found !"));

        order.addOrderItem(sku, price);
        orderManagement.save(order);
    }
}
