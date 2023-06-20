package fr.bpifrance.crafts.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AddOrderItemServiceTest {

    private final OrderManagement orderManagement = new OrderManagement();

    @BeforeEach
    void setup() {
        Map<String, Double> orderItems = new HashMap<>();
        orderItems.put("XYZ12345", 48d);
        orderItems.put("TSH-FF0000-L", 156d);

        var order = new Order(1L, orderItems);

        orderManagement.save(order);
    }

    @Test
    void shouldAddOrderItem() {
        // Given
        var addOrderItemService = new AddOrderItemService(orderManagement);

        String newSku = "TSH-FFF-M";
        double price = 79d;

        // When
        addOrderItemService.addOrderItem(1L, newSku, price);
        Optional<Order> order = orderManagement.findById(1L);

        // Then
        assertTrue(order.isPresent());
        assertEquals(1L, order.get().getId());
        assertTrue(order.get().containsItem("TSH-FFF-M"));
        assertEquals(79d, order.get().getItemPrice("TSH-FFF-M"));
    }

    @Test
    void shouldThrowsNotFoundOrder() {
        // Given
        var addOrderItemService = new AddOrderItemService(orderManagement);

        String newSku = "TSH-FFF-M";
        double price = 79d;

        // When & Then
        assertThrows(RuntimeException.class, () -> addOrderItemService.addOrderItem(2L, newSku, price));
    }
}