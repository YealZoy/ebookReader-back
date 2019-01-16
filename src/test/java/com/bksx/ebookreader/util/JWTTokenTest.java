package com.bksx.ebookreader.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class JWTTokenTest {
    @Autowired
    private JWTToken jwtToken;

    @Test
    public void createJWT() throws Exception{
        String token = jwtToken.createJWT();
        System.out.print(token);
    }

    @Test
    public void volidateToken() throws Exception{
        String token = jwtToken.createJWT();
        System.out.println(token);
        System.out.println(jwtToken.volidateToken(token));
        System.out.println(jwtToken.volidateToken(token + "jjjjj"));
    }
}