package fr.bpifrance.crafts.orders;

import java.util.Objects;

final class OrderItem {

    private final String sku;
    private final Double price;
    private final int quantity;

    OrderItem(String sku, Double price, int quantity) {
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
    }

    String sku() {
        return sku;
    }

    Double totalPrice() {
        return price * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(sku, orderItem.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }
}
