package dao.impl;

import connection.ConnectionPool;
import dao.CustomerDAO;
import entity.Customer;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private static final String FIND_ALL_CUSTOMERS = "SELECT CUSTOMER_ID, USER_ID, FIRST_NAME, LAST_NAME, DOB, EMAIL, PHONE, GENDER, INFORMATION, DISCOUNT FROM CUSTOMER";
    private static final String FIND_BY_GENDER = "SELECT CUSTOMER_ID, USER_ID, FIRST_NAME, LAST_NAME, DOB, EMAIL, PHONE, GENDER, INFORMATION, DISCOUNT FROM CUSTOMER WHERE GENDER=?";
    private static final String CREATE_CUSTOMER = "INSERT INTO `CUSTOMER` (`USER_ID`,`FIRST_NAME`,`LAST_NAME`, `DOB`, `EMAIL`, `PHONE`, `GENDER`, `INFORMATION`, `DISCOUNT`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String READ_CUSTOMER = "SELECT CUSTOMER_ID, USER_ID, FIRST_NAME, LAST_NAME, DOB, EMAIL, PHONE, GENDER, INFORMATION, DISCOUNT FROM CUSTOMER WHERE CUSTOMER_ID=?";
    private static final String UPDATE_CUSTOMER = "UPDATE CUSTOMER SET EMAIL=?, PHONE=?, INFORMATION=?, DISCOUNT=? WHERE CUSTOMER_ID=?";
    private static final String DELETE_CUSTOMER = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?";

    public Customer create(Customer customer) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, customer.getUserId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setDate(4,(Date) customer.getDateOfBirth());
            statement.setString(5, customer.getEmail());
            statement.setString(6, customer.getPhoneNumber());
            statement.setBoolean(7, customer.isGender());
            statement.setString(8, customer.getInformation());
            statement.setInt(9, customer.getDiscount());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                customer.setCustomerId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            connection.close();
        }
        return customer;
    }

    public Customer read(int id) throws SQLException {
        Customer customer = new Customer();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(READ_CUSTOMER);
            statement.setInt(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()){
               customer = customerFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return customer;
    }

    public void update(Customer customer) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_CUSTOMER);
            statement.setInt(1, customer.getUserId());
            statement.setString(2, customer.getPhoneNumber());
            statement.setString(3, customer.getInformation());
            statement.setInt(4, customer.getDiscount());
            statement.setInt(5, customer.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_CUSTOMER);
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public List<Customer> findAll() throws SQLException {
        List<Customer> customerList = new ArrayList<Customer>();
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_CUSTOMERS);
            while (resultSet.next()){
                customerList.add(customerFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return customerList;
    }

    public List<Customer> findAllByGender(boolean gender) throws SQLException {
        List<Customer> customerList = new ArrayList<Customer>();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_BY_GENDER);
            statement.setBoolean(1, gender);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                customerList.add(customerFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return customerList;
    }

    private Customer customerFromResultSet(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
        customer.setUserId(resultSet.getInt("USER_ID"));
        customer.setFirstName(resultSet.getString("FIRST_NAME"));
        customer.setLastName(resultSet.getString("LAST_NAME"));
        customer.setDateOfBirth(resultSet.getDate("DOB"));
        customer.setEmail(resultSet.getString("EMAIL"));
        customer.setPhoneNumber(resultSet.getString("PHONE"));
        customer.setGender(resultSet.getBoolean("GENDER"));
        customer.setInformation(resultSet.getString("INFORMATION"));
        customer.setDiscount(resultSet.getInt("DISCOUNT"));
        return customer;
    }
}
