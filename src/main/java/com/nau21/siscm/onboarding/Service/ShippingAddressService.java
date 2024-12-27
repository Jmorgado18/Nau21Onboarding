package com.nau21.siscm.onboarding.Service;

import com.nau21.siscm.onboarding.Model.ShippingAddress;
import java.util.List;

public interface ShippingAddressService {
    void saveShippingAddress(ShippingAddress shippingAddress);
    void updateShippingAddress(ShippingAddress shippingAddress);
    void deleteShippingAddress(int id);
    ShippingAddress getShippingAddress(int id);
    List<ShippingAddress> getAllShippingAddresses();
}
