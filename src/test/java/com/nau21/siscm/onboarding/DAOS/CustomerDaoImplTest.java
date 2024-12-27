package com.nau21.siscm.onboarding.DAOS;

import com.nau21.siscm.onboarding.Model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerDaoImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CustomerDaoImpl customerDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer_ShouldPersistCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCustomerName("John Doe");

        // Act
        customerDao.createCustomer(customer);

        // Assert
        verify(entityManager, times(1)).persist(customer);
    }

    @Test
    void updateCustomer_ShouldMergeCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCustomerName("John Doe Updated");

        // Act
        customerDao.updateCustomer(customer);

        // Assert
        verify(entityManager, times(1)).merge(customer);
    }

    @Test
    void deleteCustomer_ShouldRemoveCustomerIfExists() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1);
        when(entityManager.find(Customer.class, 1)).thenReturn(customer);

        // Act
        customerDao.deleteCustomer(1);

        // Assert
        verify(entityManager, times(1)).find(Customer.class, 1);
        verify(entityManager, times(1)).remove(customer);
    }

    @Test
    void deleteCustomer_ShouldDoNothingIfCustomerDoesNotExist() {
        // Arrange
        when(entityManager.find(Customer.class, 1)).thenReturn(null);

        // Act
        customerDao.deleteCustomer(1);

        // Assert
        verify(entityManager, times(1)).find(Customer.class, 1);
        verify(entityManager, never()).remove(any());
    }

    @Test
    void getCustomerById_ShouldReturnCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCustomerName("John Doe");
        when(entityManager.find(Customer.class, 1)).thenReturn(customer);

        // Act
        Customer result = customerDao.getCustomerById(1);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getCustomerName());
        verify(entityManager, times(1)).find(Customer.class, 1);
    }

    @Test
    void getAllCustomers_ShouldReturnListOfCustomers() {
        // Arrange: Criação de uma lista de clientes simulada
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setCustomerName("John Doe");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setCustomerName("Jane Doe");

        customers.add(customer1);
        customers.add(customer2);

        // Mock do TypedQuery
        TypedQuery<Customer> queryMock = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT c FROM Customer c", Customer.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(customers);

        // Act: Chamada do método
        List<Customer> result = customerDao.getAllCustomers();

        // Assert: Verificações
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getCustomerName());
        assertEquals("Jane Doe", result.get(1).getCustomerName());
        verify(entityManager, times(1)).createQuery("SELECT c FROM Customer c", Customer.class);
        verify(queryMock, times(1)).getResultList();
    }
}
