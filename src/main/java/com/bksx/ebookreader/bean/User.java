package com.bksx.ebookreader.bean;

import lombok.Data;

// 用户表

@Data
public class User {
    // 主键
    private String uid;

    // 姓名
    private String uname;

    // 微信的openid
    private String openid;

    // 密码
    private String password;

    // 头像链接地址
    private String headimgurl;

    // 注册时间
    private String date;

    // 是否可用
    private String useful;
}
