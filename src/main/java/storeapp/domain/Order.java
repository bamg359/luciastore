package storeapp.domain;

import storeapp.domain.enums.OrderStatus;
import java.util.List;

public class Order {

    private int id;
    private int customerId;
    private List<Integer> productIds; // IDs de productos
    private double total;
    private OrderStatus status;

    public Order(int id, int customerId, List<Integer> productIds, double total, OrderStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.productIds = productIds;
        this.total = total;
        this.status = status;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pedido ID: " + id + ", Cliente ID: " + customerId + ", Productos: " + productIds + ", Total: $" + total + ", Estado: " + status.getDescription();
    }
}
