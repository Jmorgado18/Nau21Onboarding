package com.nau21.siscm.onboarding.Service;

import com.nau21.siscm.onboarding.DAOS.CustomerDAO;
import com.nau21.siscm.onboarding.Model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerDAO customerDAO;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName("John Doe");
        customer.setReference("REF123");

        doNothing().when(customerDAO).createCustomer(customer);

        customerService.createCustomer(customer);

        verify(customerDAO, times(1)).createCustomer(customer);
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCustomerName("Jane Doe");
        customer.setReference("REF124");

        doNothing().when(customerDAO).updateCustomer(customer);

        customerService.updateCustomer(customer);

        verify(customerDAO, times(1)).updateCustomer(customer);
    }

    @Test
    void testDeleteCustomer() {
        int customerId = 1;

        doNothing().when(customerDAO).deleteCustomer(customerId);

        customerService.deleteCustomer(customerId);

        verify(customerDAO, times(1)).deleteCustomer(customerId);
    }

    @Test
    void testGetCustomerById() {
        int customerId = 1;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setCustomerName("Alice Brown");
        customer.setReference("REF125");

        when(customerDAO.getCustomerById(customerId)).thenReturn(customer);

        Customer result = customerService.getCustomerById(customerId);

        assertNotNull(result);
        assertEquals("Alice Brown", result.getCustomerName());
        assertEquals("REF125", result.getReference());
        verify(customerDAO, times(1)).getCustomerById(customerId);
    }

    @Test
    void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setCustomerName("Customer A");
        customer1.setReference("REF127");

        Customer customer2 = new Customer();
        customer2.setCustomerName("Customer B");
        customer2.setReference("REF128");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerDAO.getAllCustomers()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Customer A", result.get(0).getCustomerName());
        assertEquals("Customer B", result.get(1).getCustomerName());
        verify(customerDAO, times(1)).getAllCustomers();
    }
}
