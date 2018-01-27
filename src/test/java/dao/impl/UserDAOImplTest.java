package dao.impl;

import dao.UserDAO;
import entity.User;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class UserDAOImplTest {

    UserDAO userDAO = new UserDAOImpl();

    @Test
    public void testFindAll() throws Exception {
        List<User> userList = userDAO.findAll();
        for(User user: userList){
            System.out.println(user);
        }
    }

}