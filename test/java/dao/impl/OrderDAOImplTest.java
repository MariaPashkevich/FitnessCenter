package dao.impl;

import dao.OrderDAO;
import entity.Order;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class OrderDAOImplTest {

    OrderDAO orderDAO = new OrderDAOImpl();

    @Test
    public void testFindAll() throws Exception {
        List<Order> orderList = orderDAO.findAll();
        for(Order order: orderList){
            System.out.println(order.toString());
        }
    }

}