package com.nau21.siscm.onboarding.Service;

import com.nau21.siscm.onboarding.DAOS.ShippingAddressDAO;
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

class ShippingAddressServiceImplTest {

    @Mock
    private ShippingAddressDAO shippingAddressDAO;

    @InjectMocks
    private ShippingAddressServiceImpl shippingAddressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveShippingAddress() {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setStreet("123 Main St");
        shippingAddress.setCity("New York");
        shippingAddress.setCountryCode("US");
        shippingAddress.setZipCode("10001");

        doNothing().when(shippingAddressDAO).saveShippingAddress(shippingAddress);

        shippingAddressService.saveShippingAddress(shippingAddress);

        verify(shippingAddressDAO, times(1)).saveShippingAddress(shippingAddress);
    }

    @Test
    void testUpdateShippingAddress() {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(1);
        shippingAddress.setStreet("456 Another St");
        shippingAddress.setCity("Los Angeles");
        shippingAddress.setCountryCode("US");
        shippingAddress.setZipCode("90001");

        doNothing().when(shippingAddressDAO).updateShippingAddress(shippingAddress);

        shippingAddressService.updateShippingAddress(shippingAddress);

        verify(shippingAddressDAO, times(1)).updateShippingAddress(shippingAddress);
    }

    @Test
    void testDeleteShippingAddress() {
        int addressId = 1;

        doNothing().when(shippingAddressDAO).deleteShippingAddress(addressId);

        shippingAddressService.deleteShippingAddress(addressId);

        verify(shippingAddressDAO, times(1)).deleteShippingAddress(addressId);
    }

    @Test
    void testGetShippingAddress() {
        int addressId = 1;

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(addressId);
        shippingAddress.setStreet("123 Main St");
        shippingAddress.setCity("New York");
        shippingAddress.setCountryCode("US");
        shippingAddress.setZipCode("10001");

        when(shippingAddressDAO.getShippingAddress(addressId)).thenReturn(shippingAddress);

        ShippingAddress result = shippingAddressService.getShippingAddress(addressId);

        assertNotNull(result);
        assertEquals("123 Main St", result.getStreet());
        assertEquals("New York", result.getCity());
        assertEquals("US", result.getCountryCode());
        assertEquals("10001", result.getZipCode());
        verify(shippingAddressDAO, times(1)).getShippingAddress(addressId);
    }

    @Test
    void testGetAllShippingAddresses() {
        ShippingAddress address1 = new ShippingAddress();
        address1.setStreet("123 Main St");
        address1.setCity("New York");
        address1.setCountryCode("US");
        address1.setZipCode("10001");

        ShippingAddress address2 = new ShippingAddress();
        address2.setStreet("456 Another St");
        address2.setCity("Los Angeles");
        address2.setCountryCode("US");
        address2.setZipCode("90001");

        List<ShippingAddress> addresses = Arrays.asList(address1, address2);

        when(shippingAddressDAO.getAllShippingAddresses()).thenReturn(addresses);

        List<ShippingAddress> result = shippingAddressService.getAllShippingAddresses();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("123 Main St", result.get(0).getStreet());
        assertEquals("456 Another St", result.get(1).getStreet());
        verify(shippingAddressDAO, times(1)).getAllShippingAddresses();
    }
}
