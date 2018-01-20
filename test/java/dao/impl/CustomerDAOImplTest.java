package dao.impl;

import dao.CustomerDAO;
import entity.Customer;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CustomerDAOImplTest {

    CustomerDAO customerDAOImpl = new CustomerDAOImpl();

    @Test
    public void testFindAll() throws Exception {

        List<Customer> customerList = customerDAOImpl.findAll();
        for(Customer customer: customerList){
            System.out.println(customer.toString());
        }
    }

}