package com.bksx.ebookreader.mapper;

import com.bksx.ebookreader.bean.UserBook;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserBookMapper {

    @Insert("insert into user_book(uid,bid,chapter,page,date,useful) values (#{uid},#{bid},#{chapter},#{page},#{date},#{useful})")
    int adduserBook(UserBook userBook);

    @Select("select count(*) from user_book where uid = #{uid} and bid = #{bid}")
    int findByUidBid(@Param("uid") String uid, @Param("bid") String bid);

    @Update("update user_book set chapter = #{chapter},page = #{page}, date = #{date} where uid = #{uid} and bid = #{bid}")
    int updateRecord(UserBook userBook);
}
