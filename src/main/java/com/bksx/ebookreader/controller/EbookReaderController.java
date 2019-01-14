package com.bksx.ebookreader.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EbookReaderController {


    @PostMapping("/login")
    public String login(){
        return "hello";
    }
}
