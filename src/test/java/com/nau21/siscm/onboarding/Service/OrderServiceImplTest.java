package com.nau21.siscm.onboarding.Service;

import com.nau21.siscm.onboarding.DAOS.OrderDAO;
import com.nau21.siscm.onboarding.Model.Customer;
import com.nau21.siscm.onboarding.Model.Order;
import com.nau21.siscm.onboarding.Model.ShippingAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Mock
    private OrderDAO orderDAO;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        Order order = new Order();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCustomerName("John Doe");

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(1);
        shippingAddress.setStreet("123 Main St");
        shippingAddress.setCity("New York");
        shippingAddress.setCountryCode("US");
        shippingAddress.setZipCode("10001");

        order.setCustomer(customer);
        order.setShippingAddress(shippingAddress);

        doNothing().when(orderDAO).createOrder(order);

        orderService.createOrder(order);

        verify(orderDAO, times(1)).createOrder(order);
    }

    @Test
    void testUpdateOrder() {
        Order order = new Order();
        order.setId(1);

        Customer customer = new Customer();
        customer.setId(2);
        customer.setCustomerName("Jane Smith");

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(2);
        shippingAddress.setStreet("456 Another St");
        shippingAddress.setCity("Los Angeles");
        shippingAddress.setCountryCode("US");
        shippingAddress.setZipCode("90001");

        order.setCustomer(customer);
        order.setShippingAddress(shippingAddress);

        doNothing().when(orderDAO).updateOrder(order);

        orderService.updateOrder(order);

        verify(orderDAO, times(1)).updateOrder(order);
    }

    @Test
    void testDeleteOrder() {
        int orderId = 1;

        doNothing().when(orderDAO).deleteOrder(orderId);

        orderService.deleteOrder(orderId);

        verify(orderDAO, times(1)).deleteOrder(orderId);
    }

    @Test
    void testGetOrderById() {
        int orderId = 1;

        Order order = new Order();
        order.setId(orderId);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setCustomerName("John Doe");

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(1);
        shippingAddress.setStreet("123 Main St");
        shippingAddress.setCity("New York");
        shippingAddress.setCountryCode("US");
        shippingAddress.setZipCode("10001");

        order.setCustomer(customer);
        order.setShippingAddress(shippingAddress);

        when(orderDAO.getOrderById(orderId)).thenReturn(order);

        Order result = orderService.getOrderById(orderId);

        assertNotNull(result);
        assertEquals("John Doe", result.getCustomer().getCustomerName());
        assertEquals("123 Main St", result.getShippingAddress().getStreet());
        verify(orderDAO, times(1)).getOrderById(orderId);
    }

    @Test
    void testGetAllOrders() {
        Order order1 = new Order();
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setCustomerName("Customer A");

        ShippingAddress shippingAddress1 = new ShippingAddress();
        shippingAddress1.setId(1);
        shippingAddress1.setStreet("123 Main St");
        shippingAddress1.setCity("New York");
        shippingAddress1.setCountryCode("US");
        shippingAddress1.setZipCode("10001");

        order1.setCustomer(customer1);
        order1.setShippingAddress(shippingAddress1);

        Order order2 = new Order();
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setCustomerName("Customer B");

        ShippingAddress shippingAddress2 = new ShippingAddress();
        shippingAddress2.setId(2);
        shippingAddress2.setStreet("456 Another St");
        shippingAddress2.setCity("Los Angeles");
        shippingAddress2.setCountryCode("US");
        shippingAddress2.setZipCode("90001");

        order2.setCustomer(customer2);
        order2.setShippingAddress(shippingAddress2);

        List<Order> orders = Arrays.asList(order1, order2);

        when(orderDAO.getAllOrders()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Customer A", result.get(0).getCustomer().getCustomerName());
        assertEquals("Customer B", result.get(1).getCustomer().getCustomerName());
        verify(orderDAO, times(1)).getAllOrders();
    }
}
