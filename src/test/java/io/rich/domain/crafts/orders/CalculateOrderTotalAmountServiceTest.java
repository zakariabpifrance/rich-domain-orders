package io.rich.domain.crafts.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CalculateOrderTotalAmountServiceTest {

    private final OrderManagement orderManagement = new OrderManagement();

    @BeforeEach
    void setup() {
        var order = new Order(1L);

        order.addOrderItem("XYZ12345", 48d, 2);
        order.addOrderItem("TSH-FF0000-L", 156d);
        order.addOrderItem("TSH-000-S", 78.78d);

        orderManagement.save(order);
    }

    @Test
    void shouldCalculateOrderTotalAmount() {
        // Given
        var calculateOrderTotalAmountService = new CalculateOrderTotalAmountService(orderManagement);

        // When
        double totalAmount = calculateOrderTotalAmountService.totalAmount(1L);

        // Then
        assertEquals(330.78, totalAmount);;
    }

    @Test
    void shouldReturnZeroFroWhenOrderIsEmpty() {
        // Given
        var order = new Order(2L);

        orderManagement.save(order);

        var calculateOrderTotalAmountService = new CalculateOrderTotalAmountService(orderManagement);

        // When
        double totalAmount = calculateOrderTotalAmountService.totalAmount(2L);

        // Then
        assertEquals(0, totalAmount);;
    }

    @Test
    void shouldThrowsNotFoundOrder() {
        // Given
        var calculateOrderTotalAmountService = new CalculateOrderTotalAmountService(orderManagement);

        // When & Then
        assertThrows(RuntimeException.class, () -> calculateOrderTotalAmountService.totalAmount(3L));
    }
}