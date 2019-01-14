package com.bksx.ebookreader.service;

import com.bksx.ebookreader.bean.User;
import com.bksx.ebookreader.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名和密码登录
     * @param name 用户名
     * @param password 密码
     * @return
     */
    public User login(String name,String password){
        return userMapper.findUserByNamePassword(name,password);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    public int registerUser(User user){
        if(userMapper.finaByname(user.getUname()) > 0){
            return 0;
        }
        user.setUid(UUID.randomUUID().toString().replaceAll("-",""));
        user.setUseful("1");
        user.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return userMapper.addUser(user);
    }

    /**
     * 根据用户名 查询库里有多少个
     * @param name
     * @return
     */
    public int volidateUname(String name){
        return userMapper.finaByname(name);
    }

    /**
     * 根据用户id 查询库里有多少个
     * @param uid
     * @return
     */
    public int volidateUid(String uid){
        return userMapper.findByUid(uid);
    }
}
