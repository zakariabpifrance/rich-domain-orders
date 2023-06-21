package io.rich.domain.crafts.orders;

public class CalculateOrderTotalAmountService {
    private final OrderManagement orderManagement;

    public CalculateOrderTotalAmountService(OrderManagement orderManagement) {
        this.orderManagement = orderManagement;
    }

    public double totalAmount(Long orderId) {
        var order = orderManagement.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found !"));
        return order.totalAmount();
    }
}
