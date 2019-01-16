package com.bksx.ebookreader.service;

import com.bksx.ebookreader.bean.UserBook;
import com.bksx.ebookreader.mapper.BookMapper;
import com.bksx.ebookreader.mapper.UserBookMapper;
import com.bksx.ebookreader.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserBookService {

    @Autowired
    private UserBookMapper userBookMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    /**
     * 添加用户-书
     * @param userBook
     * @return
     */
    public int addUserBook(UserBook userBook){
        //验证用户id
        if(userService.volidateUid(userBook.getUid()) <= 0){
            return 0;
        }

        // 验证书id
        if(bookService.volidateBookId(userBook.getBid()) <= 0){
            return 0;
        }

        userBook.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        if(userBookMapper.findByUidBid(userBook.getUid(),userBook.getBid()) <= 0){
            return userBookMapper.adduserBook(userBook);
        }else {
            return userBookMapper.updateRecord(userBook);
        }

    }
}
