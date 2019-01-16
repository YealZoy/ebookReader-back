package com.bksx.ebookreader.service;

import com.bksx.ebookreader.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLEncoder;

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

    @Test
    public void tt()throws Exception{
        String oauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        String redirect_uri = URLEncoder.encode("http://ebookreader.zhengyuyan.com/callback", "utf-8"); ;
        oauthUrl =  oauthUrl.replace("APPID","wx578d3b84be4aa096").replace("REDIRECT_URI",redirect_uri).replace("SCOPE","snsapi_userinfo");
        System.out.print(oauthUrl);
    }
}