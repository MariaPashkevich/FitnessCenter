package service;

import entity.Order;

import java.util.List;

public interface OrderService {

    Order creteOrder(Order order);
    Order readOrder(int id);
    void updateOrder(Order order);
    void deleteOrder(int id);
    List<Order> findAllByCustomerId(int customerId);
    List<Order> findAllByTrainerId(int trainerId);
    List<Order> findAllOrders();
}
