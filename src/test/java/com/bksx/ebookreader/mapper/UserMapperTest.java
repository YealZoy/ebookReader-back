package com.bksx.ebookreader.mapper;

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
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findByOpenId() {
        User user = userMapper.findByOpenId("o5RH55pYoNoLbPDf9QCHAAJLV0KE");
        System.out.print(user);
    }


}