package com.bksx.ebookreader.mapper;

import com.bksx.ebookreader.bean.Wechart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WechartMapper {
    @Insert("insert into wechart(openid,nickname,sex,province,city,country,headimgurl) values (#{openid},#{nickname},#{sex},#{province},#{city},#{country},#{headimgurl})")
    int addWechart(Wechart wechart);
}
