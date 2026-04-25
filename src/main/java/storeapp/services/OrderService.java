package storeapp.services;

import storeapp.domain.Order;
import storeapp.domain.enums.OrderStatus;
import java.util.List;

public interface OrderService {

    public Order createOrder(int customerId);
    public List<Order> getOrdersByCustomer(int customerId);
    public Order getOrderById(int id);
    public void updateOrderStatus(int id, OrderStatus status);
}
