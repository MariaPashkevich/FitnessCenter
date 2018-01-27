package service.impl;

import entity.Order;
import org.testng.annotations.Test;
import service.OrderService;

import java.util.List;

import static org.testng.Assert.*;

public class OrderServiceImplTest {

    OrderService orderService = new OrderServiceImpl();

    @Test
    public void testFindAllOrders() throws Exception {
        List<Order> orderList = orderService.findAllOrders();
        for(Order order: orderList){
            System.out.println(order.toString());
        }
    }
}