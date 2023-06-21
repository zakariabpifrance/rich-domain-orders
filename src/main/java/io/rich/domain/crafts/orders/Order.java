package io.rich.domain.crafts.orders;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public final class Order {

    private final Long id;
    private final Set<OrderItem> items = new HashSet<>();

    public Order(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    void addOrderItem(String sku, Double price, int quantity) {
        this.items.add(new OrderItem(sku, price, quantity));
    }

    void addOrderItem(String sku, Double price) {
        addOrderItem(sku, price, 1);
    }

    double totalAmount() {
        if (isEmpty()) {
            return 0;
        }
        return this.items.stream()
            .map(OrderItem::totalPrice)
            .reduce(0d, Double::sum);
    }

    void removeItem(String sku) {
        if (isEmpty()) {
            throw new RuntimeException("Cannot remove item from an empty order !");
        }
        getItem(sku).ifPresentOrElse(
                this.items::remove,
                () -> {
                    throw new RuntimeException("Cannot remove item [%s], reason: item no found !" .formatted(sku));
                }
        );
    }

    boolean containsItem(String sku) {
        return getItem(sku).isPresent();
    }

    double getItemPrice(String sku) {
        return getItem(sku).map(OrderItem::totalPrice)
                .orElseThrow(() -> new IllegalStateException("Cannot found item [%s] !" .formatted(sku)));
    }

    private Optional<OrderItem> getItem(String sku) {
        return this.items.stream()
                .filter(item -> item.sku().equals(sku))
                .findFirst();
    }

    private boolean isEmpty() {
        return this.items.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}