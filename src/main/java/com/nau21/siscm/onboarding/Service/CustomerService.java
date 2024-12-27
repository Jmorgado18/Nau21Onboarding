package com.nau21.siscm.onboarding.Service;

import com.nau21.siscm.onboarding.Model.Customer;
import java.util.List;

public interface CustomerService {
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
}
