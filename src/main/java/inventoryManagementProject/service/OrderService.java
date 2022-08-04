package inventoryManagementProject.service;

import inventoryManagementProject.entity.Order;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order saveOrder(Order order);

    Order getOrderById(Long orderId);

    Order updateOrder(Order order);

    double countTotalPrice(Order order);

    void deleteOrderById(Long orderId);

}
