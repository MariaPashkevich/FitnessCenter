package service.impl;

import dao.CustomerDAO;
import dao.impl.CustomerDAOImpl;
import entity.Customer;
import service.CustomerService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO = new CustomerDAOImpl();

    public Customer createCustomer(Customer customer) {
        try{
            customer = customerDAO.create(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer readCustomer(int id) {
        Customer customer = new Customer();
        try{
            customer = customerDAO.read(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void updateCustomer(Customer customer) {
        try{
            customerDAO.update(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int id) {
        try{
            customerDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> findCustomersByGender(boolean gender) {
        List<Customer> customerList = new ArrayList<Customer>();
        try{
            customerList = customerDAO.findAllByGender(gender);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public List<Customer> findAllCustomers() {
        List<Customer> customerList = new ArrayList<Customer>();
        try{
            customerList = customerDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;    }
}
