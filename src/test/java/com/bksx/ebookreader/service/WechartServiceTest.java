package com.bksx.ebookreader.service;

import com.bksx.ebookreader.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * <p>Title: title</p>
 * <p>Description: descriptiotn </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: bksx</p>
 *
 * @author Administrator
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechartServiceTest {
    @Autowired
    private WechartService wechartService;

    @Test
    public void wechatLogin() {
        User user = wechartService.wechatLogin("{'openid':'o5RH55pYoNoLbPDf9QCHAAJLV0KE','nickname':'王建永','sex':1,'language':'zh_CN','city':'海淀','province':'北京','country':'中国','headimgurl':'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoMU01Oia68jG66rxCYFicFSELz9fR5LuHmhlfBsc3LAWyNJXItr9MbqxIXwhowws7HKUvyXLZFoUyA/132','privilege':[]}");
        System.out.print(user);
    }
}