package fr.bpifrance.crafts.orders;

import java.util.*;

public final class Order {

    private final Long id;
    private final Map<String, Double> items = new HashMap<>(5);

    public Order(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    void addOrderItem(String sku, Double price) {
        this.items.put(sku, price);
    }

    double totalAmount() {
        if (isEmpty()) {
            return 0;
        }
        return this.items.values().stream()
            .reduce(0d, Double::sum);
    }

    void removeItem(String sku) {
        if (isEmpty()) {
            throw new RuntimeException("Cannot remove item from an empty order !");
        }
        if (containsItem(sku)) {
            this.items.remove(sku);
        } else {
            throw new RuntimeException("Cannot remove item [%s], reason: item no found !" .formatted(sku));
        }
    }

    boolean containsItem(String sku) {
        return this.items.containsKey(sku);
    }

    double getItemPrice(String sku) {
        return this.items.get(sku);
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