package com.nau21.siscm.onboarding.Service;

import com.nau21.siscm.onboarding.DAOS.OrderDAO;
import com.nau21.siscm.onboarding.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void createOrder(Order order) {
        orderDAO.createOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public void deleteOrder(int id) {
        orderDAO.deleteOrder(id);
    }

    @Override
    public Order getOrderById(int id) {
        Order order = orderDAO.getOrderById(id);
       // System.out.println("Order in service: " + order);
        return order;

    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }
}
