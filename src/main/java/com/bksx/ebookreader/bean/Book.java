package com.bksx.ebookreader.bean;

import lombok.Data;

// 书
@Data
public class Book {
    // 书id
    private String bid;

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

    // 添加时间
    private String date;

    // 是否可用
    private String useful;
}
