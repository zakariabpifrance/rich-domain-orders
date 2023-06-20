package fr.bpifrance.crafts.orders.services;

import fr.bpifrance.crafts.orders.model.Order;
import fr.bpifrance.crafts.orders.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddOrderItemServiceTest {

    private final OrderRepository orderRepository = new OrderRepository();

    @BeforeEach
    void setup() {
        var order = new Order();
        order.setId(1L);

        Map<String, Double> orderItems = new HashMap<>();
        orderItems.put("XYZ12345", 48d);
        orderItems.put("TSH-FF0000-L", 156d);

        order.setItems(orderItems);

        orderRepository.save(order);
    }

    @Test
    void shouldAddOrderItem() {
        // Given
        var addOrderItemService = new AddOrderItemService(orderRepository);

        String newSku = "TSH-FFF-M";
        double price = 79d;

        // When
        addOrderItemService.addOrderItem(1L, newSku, price);
        Optional<Order> order = orderRepository.findById(1L);

        // Then
        assertTrue(order.isPresent());
        assertEquals(1L, order.get().getId());
        assertTrue(order.get().getItems().containsKey("TSH-FFF-M"));
        assertEquals(79d, order.get().getItems().get("TSH-FFF-M"));
    }

    @Test
    void shouldThrowsNotFoundOrder() {
        // Given
        var addOrderItemService = new AddOrderItemService(orderRepository);

        String newSku = "TSH-FFF-M";
        double price = 79d;

        // When & Then
        assertThrows(RuntimeException.class, () -> addOrderItemService.addOrderItem(2L, newSku, price));
    }
}