package fr.bpifrance.crafts.orders.services;

import fr.bpifrance.crafts.orders.model.Order;
import fr.bpifrance.crafts.orders.model.OrderItem;
import fr.bpifrance.crafts.orders.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RemoveOrderItemServiceTest {

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

        order.setItems(orderItems);

        orderRepository.save(order);
    }

    @Test
    void shouldRemoveOrderItem() {
        // Given
        var removeOrderItemService = new RemoveOrderItemService(orderRepository);

        String skuToBeRemoved = "XYZ12345";

        // When
        removeOrderItemService.removeItem(1L, skuToBeRemoved);
        Optional<Order> order = orderRepository.findById(1L);

        // Then
        assertTrue(order.isPresent());
        assertEquals(1L, order.get().getId());
        assertFalse(order.get().getItems().stream().anyMatch(item -> item.getSku().equals("XYZ12345")));
    }

    @Test
    void shouldThrowsNotFoundOrder() {
        // Given
        var removeOrderItemService = new RemoveOrderItemService(orderRepository);

        String skuToBeRemoved = "TSH-FFF-M";

        // When & Then
        assertThrows(RuntimeException.class, () -> removeOrderItemService.removeItem(2L, skuToBeRemoved));
    }

    @Test
    void shouldThrowsErrorWhenOrderIsEmpty() {
        // Given
        var order = new Order();
        order.setId(3L);

        order.setItems(new HashSet<>());
        orderRepository.save(order);

        String skuToBeRemoved = "TSH-FFF-M";

        var removeOrderItemService = new RemoveOrderItemService(orderRepository);

        // When & Then
        assertThrows(RuntimeException.class, () -> removeOrderItemService.removeItem(3L, skuToBeRemoved));
    }

    @Test
    void shouldThrowsErrorWhenOrderItemNotFound() {
        // Given
        var removeOrderItemService = new RemoveOrderItemService(orderRepository);

        String skuToBeRemoved = "fake-sku";

        // When a Then
        assertThrows(RuntimeException.class, () -> removeOrderItemService.removeItem(3L, skuToBeRemoved));
    }
}