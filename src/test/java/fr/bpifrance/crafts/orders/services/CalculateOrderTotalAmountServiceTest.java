package fr.bpifrance.crafts.orders.services;

import fr.bpifrance.crafts.orders.model.Order;
import fr.bpifrance.crafts.orders.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalculateOrderTotalAmountServiceTest {

    private final OrderRepository orderRepository = new OrderRepository();

    @BeforeEach
    void setup() {
        var order = new Order();
        order.setId(1L);

        Map<String, Double> orderItems = new HashMap<>();
        orderItems.put("XYZ12345", 48d);
        orderItems.put("TSH-FF0000-L", 156d);
        orderItems.put("TSH-000-S", 78.78d);

        order.setItems(orderItems);

        orderRepository.save(order);
    }

    @Test
    void shouldCalculateOrderTotalAmount() {
        // Given
        var calculateOrderTotalAmountService = new CalculateOrderTotalAmountService(orderRepository);

        // When
        double totalAmount = calculateOrderTotalAmountService.totalAmount(1L);

        // Then
        assertEquals(282.78, totalAmount);;
    }

    @Test
    void shouldReturnZeroFroWhenOrderIsEmpty() {
        // Given
        var order = new Order();
        order.setId(2L);

        Map<String, Double> orderItems = new HashMap<>();
        order.setItems(orderItems);
        orderRepository.save(order);

        var calculateOrderTotalAmountService = new CalculateOrderTotalAmountService(orderRepository);

        // When
        double totalAmount = calculateOrderTotalAmountService.totalAmount(2L);

        // Then
        assertEquals(0, totalAmount);;
    }

    @Test
    void shouldThrowsNotFoundOrder() {
        // Given
        var calculateOrderTotalAmountService = new CalculateOrderTotalAmountService(orderRepository);

        // When & Then
        assertThrows(RuntimeException.class, () -> calculateOrderTotalAmountService.totalAmount(3L));
    }
}