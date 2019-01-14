package com.bksx.ebookreader.service;

import com.bksx.ebookreader.bean.UserBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBookServiceTest {

    @Autowired
    private UserBookService userBookService;

    @Test
    public void addUserBook() {
        UserBook u = new UserBook();
        u.setBid("0b725452965243c9a796eb5ae540e0aa");
        u.setUid("b0e8a5eae77a406a9df069e8a56c804a");
        u.setChapter("3");
        int i = userBookService.addUserBook(u);
        System.out.print(i);
    }
}