package com.nau21.siscm.onboarding.DAOS;

import com.nau21.siscm.onboarding.Model.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createOrder(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void updateOrder(Order order) {
        entityManager.merge(order);
    }

    @Override
    public void deleteOrder(int id) {
        Order order = entityManager.find(Order.class, id);
        if (order != null) {
            entityManager.remove(order);
        }
    }

    @Override
    public Order getOrderById(int id) {
        Order order = entityManager.find(Order.class, id);
       // System.out.println("Order fetched: " + order);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
}
