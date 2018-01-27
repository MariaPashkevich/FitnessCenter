package service;

import entity.User;

import java.util.List;

public interface UserService {

    User findByLogin(String login);
    List<User> findAllByRole(String role);
    User createUser(User user);
    User readUser(int id);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> findAllUsers();
}
