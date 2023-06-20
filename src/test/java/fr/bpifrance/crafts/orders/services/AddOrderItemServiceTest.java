package fr.bpifrance.crafts.orders.services;

import fr.bpifrance.crafts.orders.model.Order;
import fr.bpifrance.crafts.orders.model.OrderItem;
import fr.bpifrance.crafts.orders.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddOrderItemServiceTest {

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
    void shouldAddOrderItem() {
        // Given
        var addOrderItemService = new AddOrderItemService(orderRepository);

        String newSku = "TSH-FFF-M";
        double price = 79d;

        // When
        addOrderItemService.addOrderItem(1L, newSku, price, 10);
        Optional<Order> order = orderRepository.findById(1L);

        // Then
        assertTrue(order.isPresent());
        assertEquals(1L, order.get().getId());
        Set<OrderItem> items = order.get().getItems();
        assertTrue(items.stream().anyMatch(item -> item.getSku().equals("TSH-FFF-M")));
        assertEquals(79d, items.stream().filter(item -> item.getSku().equals("TSH-FFF-M"))
                .map(OrderItem::getPrice).findFirst().get());
    }

    @Test
    void shouldThrowsNotFoundOrder() {
        // Given
        var addOrderItemService = new AddOrderItemService(orderRepository);

        String newSku = "TSH-FFF-M";
        double price = 79d;

        // When & Then
        assertThrows(RuntimeException.class, () -> addOrderItemService.addOrderItem(2L, newSku, price, 1));
    }
}