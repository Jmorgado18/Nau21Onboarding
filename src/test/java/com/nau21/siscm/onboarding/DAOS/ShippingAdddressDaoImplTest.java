package com.nau21.siscm.onboarding.DAOS;

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

class ShippingAddressDaoImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ShippingAddressDaoImpl shippingAddressDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveShippingAddress_ShouldPersistShippingAddress() {

        ShippingAddress address = new ShippingAddress();
        address.setId(1);
        address.setStreet("123 Main St");


        shippingAddressDao.saveShippingAddress(address);


        verify(entityManager, times(1)).persist(address);
    }

    @Test
    void updateShippingAddress_ShouldMergeShippingAddress() {

        ShippingAddress address = new ShippingAddress();
        address.setId(1);
        address.setStreet("456 Updated St");


        shippingAddressDao.updateShippingAddress(address);


        verify(entityManager, times(1)).merge(address);
    }

    @Test
    void deleteShippingAddress_ShouldRemoveAddressIfExists() {

        ShippingAddress address = new ShippingAddress();
        address.setId(1);
        when(entityManager.find(ShippingAddress.class, 1)).thenReturn(address);


        shippingAddressDao.deleteShippingAddress(1);


        verify(entityManager, times(1)).find(ShippingAddress.class, 1);
        verify(entityManager, times(1)).remove(address);
    }

    @Test
    void deleteShippingAddress_ShouldDoNothingIfAddressDoesNotExist() {

        when(entityManager.find(ShippingAddress.class, 1)).thenReturn(null);


        shippingAddressDao.deleteShippingAddress(1);


        verify(entityManager, times(1)).find(ShippingAddress.class, 1);
        verify(entityManager, never()).remove(any());
    }

    @Test
    void getShippingAddress_ShouldReturnAddress() {

        ShippingAddress address = new ShippingAddress();
        address.setId(1);
        address.setStreet("123 Main St");
        when(entityManager.find(ShippingAddress.class, 1)).thenReturn(address);


        ShippingAddress result = shippingAddressDao.getShippingAddress(1);


        assertNotNull(result);
        assertEquals("123 Main St", result.getStreet());
        verify(entityManager, times(1)).find(ShippingAddress.class, 1);
    }

    @Test
    void getAllShippingAddresses_ShouldReturnListOfAddresses() {

        List<ShippingAddress> addresses = new ArrayList<>();
        ShippingAddress address1 = new ShippingAddress();
        address1.setId(1);
        address1.setStreet("123 Main St");

        ShippingAddress address2 = new ShippingAddress();
        address2.setId(2);
        address2.setStreet("456 Another St");

        addresses.add(address1);
        addresses.add(address2);


        TypedQuery<ShippingAddress> queryMock = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT s FROM ShippingAddress s", ShippingAddress.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(addresses);


        List<ShippingAddress> result = shippingAddressDao.getAllShippingAddresses();


        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("123 Main St", result.get(0).getStreet());
        assertEquals("456 Another St", result.get(1).getStreet());
        verify(entityManager, times(1)).createQuery("SELECT s FROM ShippingAddress s", ShippingAddress.class);
        verify(queryMock, times(1)).getResultList();
    }
}
