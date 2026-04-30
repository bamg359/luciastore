package storeapp.repository;

import storeapp.domain.Order;
import storeapp.domain.enums.OrderStatus;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    List<Order> orders = new ArrayList<>();

    public Order saveOrder(Order order){
        orders.add(order);
        return order;
    }

    public List<Order> findAllOrders(){
        return orders;
    }

    public List<Order> findOrdersByCustomerId(int customerId){
        List<Order> customerOrders = new ArrayList<>();
        for(Order order : orders){
            if(order.getCustomerId() == customerId){
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    public Order findOrderById(int id){
        for(Order order : orders){
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }

    public void updateOrderStatus(int id, OrderStatus status){
        for(Order order : orders){
            if(order.getId() == id){
                order.setStatus(status);
                break;
            }
        }
    }
}
