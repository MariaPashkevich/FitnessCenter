package dao.impl;

import connection.ConnectionPool;
import dao.UserDAO;
import entity.User;


import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    private static final String FIND_ALL_USERS = "SELECT USER_ID, LOGIN, PASSWORD, ROLE FROM USER";
    private static final String FIND_ALL_USERS_BY_ROLE = "SELECT USER_ID, LOGIN, PASSWORD, ROLE FROM USER WHERE ROLE=?";
    private static final String FIND_USER_BY_LOGIN = "SELECT USER_ID, LOGIN, PASSWORD, ROLE FROM USER WHERE LOGIN=?";
    private static final String CREATE_USER = "INSERT INTO `USER` (`LOGIN`,`PASSWORD`,`ROLE`) VALUES (?, ?, ?)";
    private static final String READ_USER = "SELECT USER_ID, LOGIN, PASSWORD, ROLE FROM USER WHERE USER_ID=?";
    private static final String UPDATE_USER = "UPDATE USER SET LOGIN=?, PASSWORD=? ROLE=? WHERE USER_ID=?";
    private static final String DELETE_USER = "DELETE FROM USER WHERE USER_ID=?";

    public User create(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getRole());
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if(resultSet.next()){
                    user.setUserId(resultSet.getInt(1));
                }
                resultSet.close();
        } catch(SQLException e){
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return user;
    }

    public User read(int id) throws SQLException{
        User user = new User();
        Connection connection = null;
        try {
            PreparedStatement statement = null;
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(READ_USER);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = userFromResultSet(resultSet);
            }
            resultSet.close();
        } catch(SQLException e){
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return user;
    }

    public void update(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getUserId());
            statement.executeUpdate();
        } catch(SQLException e){
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
            statement = connection.prepareStatement(DELETE_USER);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public List<User> findAll() throws SQLException {
        List<User> userList = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);
            while(resultSet.next()){
                userList.add(userFromResultSet(resultSet));
            }
            resultSet.close();
        } catch(SQLException e){
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return userList;
    }

    public User findByLogin(String login) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        User user = new User();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = userFromResultSet(resultSet);
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
        return user;
    }

    public List<User> findAllByRole(String role) throws SQLException {
        List<User> userList = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_ALL_USERS_BY_ROLE);
            statement.setString(1, role);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                userList.add(userFromResultSet(resultSet));
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
        return userList;
    }

    private User userFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("USER_ID"));
        user.setLogin(resultSet.getString("LOGIN"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setRole(resultSet.getString("ROLE"));
        return user;
    }
}
