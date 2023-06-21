package io.rich.domain.crafts.orders;


public class RemoveOrderItemService {
    private final OrderManagement orderManagement;

    public RemoveOrderItemService(OrderManagement orderManagement) {
        this.orderManagement = orderManagement;
    }

    public void removeItem(Long orderId, String sku) {
        var order = orderManagement.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found !"));

        order.removeItem(sku);
    }
}
