package dao;

import entity.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order>{

    List<Order> findAllByCustomerId(int customerId);
    List<Order> findAllByTrainerId(int trainerId);
}
