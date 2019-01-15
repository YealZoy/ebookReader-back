package com.bksx.ebookreader.service;

import com.bksx.ebookreader.bean.Book;
import com.bksx.ebookreader.bean.UserBookListBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void addBook() {
        Book b = new Book();
        b.setBname("Angular权威指南");
        bookService.addBook(b);
    }

    @Test
    public void listBook() {
        List<UserBookListBean> listBeans = bookService.listBook("d78c16bf52234d35afd58bd40b185ab7","ztt");
        System.out.print(listBeans);
    }


}