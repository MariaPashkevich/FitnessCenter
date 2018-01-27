package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entity.User;
import service.UserService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDAO userDAO = new UserDAOImpl();

    public User findByLogin(String login) {
        User user = new User();
        try{
            user = userDAO.findByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> findAllByRole(String role) {
        List<User> userList = new ArrayList<User>();
        try{
            userList = userDAO.findAllByRole(role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User createUser(User user) {
        try{
            user = userDAO.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User readUser(int id) {
        User user = new User();
        try{
            user = userDAO.read(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(User user) {
        try{
            userDAO.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try{
            userDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<User>();
        try{
            userList = userDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
