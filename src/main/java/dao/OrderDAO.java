package dao;

import entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends DAO<Order>{

    List<Order> findAllByCustomerId(int customerId) throws SQLException;
    List<Order> findAllByTrainerId(int trainerId) throws SQLException;
}
