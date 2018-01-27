package service.impl;

import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import entity.Order;
import service.OrderService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO = new OrderDAOImpl();

    public Order creteOrder(Order order) {
        try{
            order = orderDAO.create(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public Order readOrder(int id) {
        Order order = new Order();
        try{
            order = orderDAO.read(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return order;
    }

    public void updateOrder(Order order) {
        try{
            orderDAO.update(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        try{
            orderDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> findAllByCustomerId(int customerId) {
        List<Order> orderList = new ArrayList<Order>();
        try{
            orderList = orderDAO.findAllByCustomerId(customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<Order> findAllByTrainerId(int trainerId) {
        List<Order> orderList = new ArrayList<Order>();
        try{
            orderList = orderDAO.findAllByCustomerId(trainerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<Order> findAllOrders() {
        List<Order> orderList = new ArrayList<Order>();
        try{
            orderList= orderDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
