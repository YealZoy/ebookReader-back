package com.bksx.ebookreader.service;

import com.bksx.ebookreader.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void login() {
        User u = userService.login("zyy","0");
        System.out.print(u);
    }

    @Test
    public void registerUser() {
        User user = new User();
        user.setUname("zff");
        user.setPassword("0");
        int i = userService.registerUser(user);
        System.out.print(i);
    }

    @Test
    public void volidateUname() {
        int i = userService.volidateUname("yyz");
        System.out.print(i);
    }
}