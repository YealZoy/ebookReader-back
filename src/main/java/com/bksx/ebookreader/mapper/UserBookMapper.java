package com.bksx.ebookreader.mapper;

import com.bksx.ebookreader.bean.UserBook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBookMapper {

    @Insert("insert into user_book(uid,bid,chapter,page,date,useful) values (#{uid},#{bid},#{chapter},#{page},#{date},#{useful})")
    int adduserBook(UserBook userBook);
}
