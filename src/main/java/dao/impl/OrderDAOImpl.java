package dao.impl;

import connection.ConnectionPool;
import dao.OrderDAO;
import entity.Order;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO{

    private static final String FIND_ALL_ORDERS = "SELECT ORDER_ID, CUSTOMER_ID, TRAINER_ID, `NUMBER`, PRICE FROM `ORDER`";
    private static final String FIND_ALL_BY_CUSTOMER_ID = "SELECT ORDER_ID, CUSTOMER_ID, TRAINER_ID, `NUMBER`, PRICE FROM `ORDER` WHERE CUSTOMER_ID=?";
    private static final String FIND_ALL_BY_TRAINER_ID = "SELECT ORDER_ID, CUSTOMER_ID, TRAINER_ID, `NUMBER`, PRICE FROM `ORDER` WHERE TRAINER_ID=?";
    private static final String CREATE_ORDER = "INSERT INTO `ORDER` (`CUSTOMER_ID`, `TRAINER_ID`, `NUMBER`, `PRICE`) VALUES (?, ?, ?, ?)";
    private static final String READ_ORDER = "SELECT ORDER_ID, CUSTOMER_ID, TRAINER_ID, `NUMBER`, PRICE FROM `ORDER` WHERE ORDER_ID=?";
    private static final String UPDATE_ORDER = "UPDATE `ORDER` SET TRAINER_ID=?, `NUMBER`=?, PRICE=? WHERE ORDER_ID=?";
    private static final String DELETE_ORDER = "DELETE FROM `ORDER` WHERE  ORDER_ID=?";

    public Order create(Order order) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, (int) order.getCustomerId());
            statement.setInt(2, (int) order.getTrainerId());
            statement.setInt(3, order.getTrainingNumber());
            statement.setDouble(4, order.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                order.setCustomerId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return order;
    }

    public Order read(int id) throws SQLException {
        Order order = new Order();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(READ_ORDER);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                order = orderFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return order;
    }

    public void update(Order order) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_ORDER);
            statement.setInt(1, (int) order.getTrainerId());
            statement.setInt(2, order.getTrainingNumber());
            statement.setDouble(3, order.getPrice());
            statement.setInt(4, (int) order.getOrderId());
            statement.executeUpdate();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_ORDER);
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public List<Order> findAll() throws SQLException {
        List<Order> orderList = new ArrayList<Order>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_ORDERS);
            while (resultSet.next()){
                orderList.add(orderFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return orderList;
    }

    public List<Order> findAllByCustomerId(int customerId) throws SQLException {
        List<Order> orderList = new ArrayList<Order>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_ALL_BY_CUSTOMER_ID);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                orderList.add(orderFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return orderList;
    }

    public List<Order> findAllByTrainerId(int trainerId) throws SQLException {
        List<Order> orderList = new ArrayList<Order>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_ALL_BY_TRAINER_ID);
            statement.setInt(1, trainerId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                orderList.add(orderFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return orderList;
    }

    private Order orderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt("ORDER_ID"));
        order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
        order.setTrainerId(resultSet.getInt("TRAINER_ID"));
        order.setTrainingNumber(resultSet.getInt("NUMBER"));
        order.setPrice(resultSet.getDouble("PRICE"));
        return order;
    }
}
