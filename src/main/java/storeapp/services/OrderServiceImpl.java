package storeapp.services;

import storeapp.domain.Order;
import storeapp.domain.Product;
import storeapp.domain.enums.OrderStatus;
import storeapp.repository.OrderRepository;
import storeapp.repository.ProductRepository;
import storeapp.utils.CustomerFormValidation;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order createOrder(int customerId) {
        Order order = new Order();
        List<Integer> productIds = new ArrayList<>();
        double total = 0.0;

        System.out.println("Creando pedido para cliente ID: " + customerId);

        // Mostrar productos disponibles
        List<Product> products = productRepository.findAllProducts();
        if(products.isEmpty()){
            System.out.println("No hay productos disponibles.");
            return null;
        }
        for(Product p : products){
            System.out.println(p.toString());
        }

        // Seleccionar productos
        while(true){
            int productId = CustomerFormValidation.validateInt("Ingrese ID del producto a agregar (0 para terminar):");
            if(productId == 0) break;
            Product product = productRepository.findProductById(productId);
            if(product != null && product.isState()){
                productIds.add(productId);
                total += product.getPrice();
                System.out.println("Producto agregado. Total actual: $" + total);
            } else {
                System.out.println("Producto no encontrado o inactivo.");
            }
        }

        if(productIds.isEmpty()){
            System.out.println("No se agregaron productos.");
            return null;
        }

        order.setId(generateOrderId());
        order.setCustomerId(customerId);
        order.setProductIds(productIds);
        order.setTotal(total);
        order.setStatus(OrderStatus.PENDING);

        return orderRepository.saveOrder(order);
    }

    @Override
    public List<Order> getOrdersByCustomer(int customerId) {
        return orderRepository.findOrdersByCustomerId(customerId);
    }

    @Override
    public Order getOrderById(int id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public void updateOrderStatus(int id, OrderStatus status) {
        orderRepository.updateOrderStatus(id, status);
    }

    private int generateOrderId(){
        int id = 1;
        while (orderRepository.findOrderById(id) != null) {
            id++;
        }
        return id;
    }
}
/*prueba de correcion */