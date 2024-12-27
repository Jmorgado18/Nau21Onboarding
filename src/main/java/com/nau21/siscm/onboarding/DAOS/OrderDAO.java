package com.nau21.siscm.onboarding.DAOS;

import com.nau21.siscm.onboarding.Model.Order;
import java.util.List;

public interface OrderDAO {
    void createOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(int id);
    Order getOrderById(int id);
    List<Order> getAllOrders();
}
