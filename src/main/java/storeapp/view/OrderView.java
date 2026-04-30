package storeapp.view;

import storeapp.domain.Order;
import storeapp.services.OrderService;
import storeapp.utils.CustomerFormValidation;
import java.util.List;

public class OrderView {

    private final OrderService orderService;

    public OrderView(OrderService orderService) {
        this.orderService = orderService;
    }

    public void createOrder(int customerId) {
        Order order = orderService.createOrder(customerId);
        if (order != null) {
            System.out.println("Pedido creado: " + order.toString());
        }
    }

    public void viewMyOrders(int customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        if (orders.isEmpty()) {
            System.out.println("No tienes pedidos.");
        } else {
            for (Order order : orders) {
                System.out.println(order.toString());
            }
        }
    }

    public void viewOrderById() {
        int id = CustomerFormValidation.validateInt("Ingrese ID del pedido:");
        Order order = orderService.getOrderById(id);
        if (order != null) {
            System.out.println(order.toString());
        } else {
            System.out.println("Pedido no encontrado.");
        }
    }
}
