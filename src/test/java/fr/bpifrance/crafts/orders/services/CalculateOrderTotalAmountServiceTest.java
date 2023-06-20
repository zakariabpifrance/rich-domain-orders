package fr.bpifrance.crafts.orders.services;

import fr.bpifrance.crafts.orders.model.Order;
import fr.bpifrance.crafts.orders.model.OrderItem;
import fr.bpifrance.crafts.orders.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CalculateOrderTotalAmountServiceTest {

    private final OrderRepository orderRepository = new OrderRepository();

    @BeforeEach
    void setup() {
        var order = new Order();
        order.setId(1L);

        Set<OrderItem> orderItems = new HashSet<>();
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setSku("XYZ12345");
        orderItem1.setPrice(48d);
        orderItem1.setQuality(2);

        orderItems.add(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setSku("TSH-FF0000-L");
        orderItem2.setPrice(156d);
        orderItem2.setQuality(1);

        orderItems.add(orderItem2);

        OrderItem orderItem3 = new OrderItem();
        orderItem3.setSku("TSH-000-S");
        orderItem3.setPrice(78.78d);
        orderItem3.setQuality(1);

        orderItems.add(orderItem3);

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
        assertEquals(330.78, totalAmount);;
    }

    @Test
    void shouldReturnZeroFroWhenOrderIsEmpty() {
        // Given
        var order = new Order();
        order.setId(2L);

        order.setItems(new HashSet<>());
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