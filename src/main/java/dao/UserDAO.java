package dao;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends DAO<User>{

    User findByLogin(String login) throws SQLException;
    List<User> findAllByRole(String role) throws SQLException;
}
