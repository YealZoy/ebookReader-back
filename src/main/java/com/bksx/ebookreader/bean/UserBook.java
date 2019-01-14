package com.bksx.ebookreader.bean;

import lombok.Data;

// 书 用户 关联表
@Data
public class UserBook {
    // 书id
    private String bid;

    // 用户id
    private String uid;

    // 章节
    private String chapter;

    // 页码
    private String page;

    // 产生记录时间
    private String date;

    // 是否可用
    private String useful;
}
