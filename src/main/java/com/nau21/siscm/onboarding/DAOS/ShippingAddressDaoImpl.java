package com.nau21.siscm.onboarding.DAOS;

import com.nau21.siscm.onboarding.Model.ShippingAddress;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ShippingAddressDaoImpl implements ShippingAddressDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveShippingAddress(ShippingAddress shippingAddress) {
        entityManager.persist(shippingAddress);
    }

    @Override
    public void updateShippingAddress(ShippingAddress shippingAddress) {
        entityManager.merge(shippingAddress);
    }

    @Override
    public void deleteShippingAddress(int id) {
        ShippingAddress address = entityManager.find(ShippingAddress.class, id);
        if (address != null) {
            entityManager.remove(address);
        }
    }

    @Override
    public ShippingAddress getShippingAddress(int id) {
        return entityManager.find(ShippingAddress.class, id);
    }

    @Override
    public List<ShippingAddress> getAllShippingAddresses() {
        return entityManager.createQuery("SELECT s FROM ShippingAddress s", ShippingAddress.class).getResultList();
    }
}
