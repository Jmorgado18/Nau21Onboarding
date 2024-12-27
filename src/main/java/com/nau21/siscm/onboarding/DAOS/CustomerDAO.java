package com.nau21.siscm.onboarding.DAOS;

import com.nau21.siscm.onboarding.Model.Customer;
import java.util.List;

public interface CustomerDAO {
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
}
