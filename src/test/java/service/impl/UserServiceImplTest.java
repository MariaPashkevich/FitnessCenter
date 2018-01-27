package service.impl;

import entity.User;
import org.testng.annotations.Test;
import service.UserService;

import java.util.List;

import static org.testng.Assert.*;

public class UserServiceImplTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void testFindAllUsers() throws Exception {
        List<User> userList = userService.findAllUsers();
        for(User user: userList){
            System.out.println(user);
        }
    }

}