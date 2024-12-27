package com.nau21.siscm.onboarding.Controller;

import com.nau21.siscm.onboarding.DTO.*;
import com.nau21.siscm.onboarding.Model.*;
import com.nau21.siscm.onboarding.Service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Mock
    private CustomerService customerService;

    @Mock
    private ItemsService itemsService;

    @Mock
    private ShippingAddressService shippingAddressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrdersSimplified() {

        Customer customer = new Customer();
        customer.setCustomerName("John Doe");

        Order order = new Order();
        order.setId(1);
        order.setCustomer(customer);
        order.setItems(Collections.emptyList());

        when(orderService.getAllOrders()).thenReturn(Collections.singletonList(order));


        ResponseEntity<List<SimpleOrderDTO>> response = orderController.getAllOrdersSimplified();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getCustomerName());
        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void testGetOrderDetailsByReference() {

        Customer customer = new Customer();
        customer.setCustomerName("John Doe");

        ShippingAddress address = new ShippingAddress();
        address.setStreet("123 Elm Street");
        address.setCity("Springfield");
        address.setCountryCode("US");
        address.setZipCode("12345");

        Items item = new Items();
        item.setName("Widget");
        item.setDescription("A useful widget");
        item.setPrice(19.99);
        item.setCurrency("USD");
        item.setQuantity(10);

        Order order = new Order();
        order.setId(1);
        order.setCustomer(customer);
        order.setShippingAddress(address);
        order.setItems(Collections.singletonList(item));

        when(orderService.getOrderById(1)).thenReturn(order);


        ResponseEntity<OrderDetailsDTO> response = orderController.getOrderDetailsByReference("1");


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getCustomerName());
        assertEquals("123 Elm Street, 12345 Springfield - US", response.getBody().getShippingAddress());
        assertEquals(1, response.getBody().getItems().size());
        verify(orderService, times(1)).getOrderById(1);
    }

    @Test
    void testDeleteOrder() {

        Order order = new Order();
        order.setId(1);

        when(orderService.getOrderById(1)).thenReturn(order);
        doNothing().when(orderService).deleteOrder(1);


        ResponseEntity<Void> response = orderController.deleteOrder(1);


        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderService, times(1)).getOrderById(1);
        verify(orderService, times(1)).deleteOrder(1);
    }

    @Test
    void testRegisterOrder() {

        RegisterOrderDTO registerOrderDTO = new RegisterOrderDTO();
        registerOrderDTO.setCustomerName("John Doe");

        ShippingAddressDTO addressDTO = new ShippingAddressDTO();
        addressDTO.setStreet("123 Elm Street");
        addressDTO.setCity("Springfield");
        addressDTO.setCountryCode("US");
        addressDTO.setZipCode("12345");
        registerOrderDTO.setShippingAddress(addressDTO);

        ItemDetailsDTO itemDTO = new ItemDetailsDTO();
        itemDTO.setName("Widget");
        itemDTO.setDescription("A useful widget");
        itemDTO.setPrice(19.99);
        itemDTO.setCurrency("USD");
        itemDTO.setQuantity(10);

        registerOrderDTO.setItems(Collections.singletonList(itemDTO));

        doNothing().when(customerService).createCustomer(any(Customer.class));
        doNothing().when(shippingAddressService).saveShippingAddress(any(ShippingAddress.class));
        doNothing().when(itemsService).createItem(any(Items.class));
        doNothing().when(orderService).createOrder(any(Order.class));


        ResponseEntity<String> response = orderController.registerOrder(registerOrderDTO);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(customerService, times(1)).createCustomer(any(Customer.class));
        verify(shippingAddressService, times(1)).saveShippingAddress(any(ShippingAddress.class));
        verify(itemsService, times(1)).createItem(any(Items.class));
        verify(orderService, times(1)).createOrder(any(Order.class));
    }
}
