package com.nau21.siscm.onboarding.DAOS;

import com.nau21.siscm.onboarding.Model.Customer;
import com.nau21.siscm.onboarding.Model.Items;
import com.nau21.siscm.onboarding.Model.Order;
import com.nau21.siscm.onboarding.Model.ShippingAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderDaoImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private OrderDaoImpl orderDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_ShouldPersistOrder() {
        Order order = new Order();
        order.setId(1);

        orderDao.createOrder(order);

        verify(entityManager, times(1)).persist(order);
    }

    @Test
    void updateOrder_ShouldMergeOrder() {

        Order order = new Order();
        order.setId(1);


        orderDao.updateOrder(order);


        verify(entityManager, times(1)).merge(order);
    }

    @Test
    void deleteOrder_ShouldRemoveOrderIfExists() {

        Order order = new Order();
        order.setId(1);
        when(entityManager.find(Order.class, 1)).thenReturn(order);


        orderDao.deleteOrder(1);


        verify(entityManager, times(1)).find(Order.class, 1);
        verify(entityManager, times(1)).remove(order);
    }

    @Test
    void deleteOrder_ShouldDoNothingIfOrderDoesNotExist() {

        when(entityManager.find(Order.class, 1)).thenReturn(null);


        orderDao.deleteOrder(1);


        verify(entityManager, times(1)).find(Order.class, 1);
        verify(entityManager, never()).remove(any());
    }

    @Test
    void getOrderById_ShouldReturnOrder() {

        Order order = new Order();
        order.setId(1);
        Customer customer = new Customer();
        ShippingAddress shippingAddress = new ShippingAddress();
        order.setCustomer(customer);
        order.setShippingAddress(shippingAddress);

        when(entityManager.find(Order.class, 1)).thenReturn(order);


        Order result = orderDao.getOrderById(1);


        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(customer, result.getCustomer());
        assertEquals(shippingAddress, result.getShippingAddress());
        verify(entityManager, times(1)).find(Order.class, 1);
    }

    @Test
    void getAllOrders_ShouldReturnListOfOrders() {

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setId(1);

        Order order2 = new Order();
        order2.setId(2);

        orders.add(order1);
        orders.add(order2);


        TypedQuery<Order> queryMock = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT o FROM Order o", Order.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(orders);


        List<Order> result = orderDao.getAllOrders();


        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        verify(entityManager, times(1)).createQuery("SELECT o FROM Order o", Order.class);
        verify(queryMock, times(1)).getResultList();
    }
}
