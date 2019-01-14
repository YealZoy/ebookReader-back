package com.bksx.ebookreader.bean;

import lombok.Data;

// 书列表bean
@Data
public class UserBookListBean{

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

    // 书名称
    private String bname;

    // 作者
    private String author;

    // 出版日期
    private String publishdate;

    // 封面 地址
    private String coverimg;

    //书 存放地址
    private String bookurl;

    // 是否可用
    private String useful;
}
