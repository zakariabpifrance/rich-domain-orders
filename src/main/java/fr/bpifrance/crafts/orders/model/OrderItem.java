package fr.bpifrance.crafts.orders.model;

import java.util.Objects;

public class OrderItem {
    private String sku;
    private double price;
    private int quality;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
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
