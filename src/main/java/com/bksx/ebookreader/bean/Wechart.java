package com.bksx.ebookreader.bean;

import lombok.Data;

// 微信登录用户表
@Data
public class Wechart {
    // 微信openid
    private String openid;

    // 昵称
    private String nickname;

    // 性别
    private String sex;

    // 省份
    private String province;

    // 城市
    private String city;

    // 国家
    private String country;

    // 头像
    private String headimgurl;
}
