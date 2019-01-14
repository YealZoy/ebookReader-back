package com.bksx.ebookreader.service;

import com.bksx.ebookreader.bean.Wechart;
import com.bksx.ebookreader.mapper.WechartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechartService {

    @Autowired
    private WechartMapper wechartMapper;

    /**
     * 添加微信用户
     * @param wechart 微信
     * @return
     */
    public int addWechart(Wechart wechart){
        return wechartMapper.addWechart(wechart);
    }
}
