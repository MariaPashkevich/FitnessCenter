package service;

import entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer);
    Customer readCustomer(int id);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
    List<Customer> findCustomersByGender(boolean gender);
    List<Customer> findAllCustomers();
}
