package com.nau21.siscm.onboarding.DAOS;

import com.nau21.siscm.onboarding.Model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }
}
