package fr.bpifrance.crafts.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalculateOrderTotalAmountServiceTest {

    private final OrderManagement orderManagement = new OrderManagement();

    @BeforeEach
    void setup() {
        Map<String, Double> orderItems = new HashMap<>();
        orderItems.put("XYZ12345", 48d);
        orderItems.put("TSH-FF0000-L", 156d);
        orderItems.put("TSH-000-S", 78.78d);

        var order = new Order(1L, orderItems);

        orderManagement.save(order);
    }

    @Test
    void shouldCalculateOrderTotalAmount() {
        // Given
        var calculateOrderTotalAmountService = new CalculateOrderTotalAmountService(orderManagement);

        // When
        double totalAmount = calculateOrderTotalAmountService.totalAmount(1L);

        // Then
        assertEquals(282.78, totalAmount);;
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