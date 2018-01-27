package service.impl;

import entity.Customer;
import org.testng.annotations.Test;
import service.CustomerService;

import java.util.List;

import static org.testng.Assert.*;

public class CustomerServiceImplTest {

    CustomerService customerService = new CustomerServiceImpl();

    @Test
    public void testFindAllCustomers() throws Exception {

        List<Customer> customerList = customerService.findAllCustomers();
        for(Customer customer: customerList){
            System.out.println(customer.toString());
        }
    }
}