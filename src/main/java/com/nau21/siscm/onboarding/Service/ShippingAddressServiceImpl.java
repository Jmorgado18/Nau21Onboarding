package com.nau21.siscm.onboarding.Service;

import com.nau21.siscm.onboarding.DAOS.ShippingAddressDAO;
import com.nau21.siscm.onboarding.Model.ShippingAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressDAO shippingAddressDAO;

    @Autowired
    public ShippingAddressServiceImpl(ShippingAddressDAO shippingAddressDAO) {
        this.shippingAddressDAO = shippingAddressDAO;
    }

    @Override
    public void saveShippingAddress(ShippingAddress shippingAddress) {
        shippingAddressDAO.saveShippingAddress(shippingAddress);
    }

    @Override
    public void updateShippingAddress(ShippingAddress shippingAddress) {
        shippingAddressDAO.updateShippingAddress(shippingAddress);
    }

    @Override
    public void deleteShippingAddress(int id) {
        shippingAddressDAO.deleteShippingAddress(id);
    }

    @Override
    public ShippingAddress getShippingAddress(int id) {
        return shippingAddressDAO.getShippingAddress(id);
    }

    @Override
    public List<ShippingAddress> getAllShippingAddresses() {
        return shippingAddressDAO.getAllShippingAddresses();
    }
}
