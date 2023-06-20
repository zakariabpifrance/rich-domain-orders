package fr.bpifrance.crafts.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RemoveOrderItemServiceTest {

    private final OrderManagement orderManagement = new OrderManagement();

    @BeforeEach
    void setup() {
        var order = new Order(1L);

        order.addOrderItem("XYZ12345", 48d);
        order.addOrderItem("TSH-FF0000-L", 156d);

        orderManagement.save(order);
    }

    @Test
    void shouldRemoveOrderItem() {
        // Given
        var removeOrderItemService = new RemoveOrderItemService(orderManagement);

        String skuToBeRemoved = "XYZ12345";

        // When
        removeOrderItemService.removeItem(1L, skuToBeRemoved);
        Optional<Order> order = orderManagement.findById(1L);

        // Then
        assertTrue(order.isPresent());
        assertEquals(1L, order.get().getId());
        assertFalse(order.get().containsItem("XYZ12345"));
    }

    @Test
    void shouldThrowsNotFoundOrder() {
        // Given
        var removeOrderItemService = new RemoveOrderItemService(orderManagement);

        String skuToBeRemoved = "TSH-FFF-M";

        // When & Then
        assertThrows(RuntimeException.class, () -> removeOrderItemService.removeItem(2L, skuToBeRemoved));
    }

    @Test
    void shouldThrowsErrorWhenOrderIsEmpty() {
        // Given
        var order = new Order(3L);

        orderManagement.save(order);

        String skuToBeRemoved = "TSH-FFF-M";

        var removeOrderItemService = new RemoveOrderItemService(orderManagement);

        // When & Then
        assertThrows(RuntimeException.class, () -> removeOrderItemService.removeItem(3L, skuToBeRemoved));
    }

    @Test
    void shouldThrowsErrorWhenOrderItemNotFound() {
        // Given
        var removeOrderItemService = new RemoveOrderItemService(orderManagement);

        String skuToBeRemoved = "fake-sku";

        // When a Then
        assertThrows(RuntimeException.class, () -> removeOrderItemService.removeItem(3L, skuToBeRemoved));
    }
}