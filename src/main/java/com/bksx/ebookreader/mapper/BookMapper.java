package com.bksx.ebookreader.mapper;

import com.bksx.ebookreader.bean.Book;
import com.bksx.ebookreader.bean.UserBookListBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    /**
     * 添加书
     * @param book
     * @return
     */
    @Insert("insert into book(bid,bname,author,publishdate,coverimg,bookurl,date,useful) values(#{bid},#{bname},#{author},#{publishdate},#{coverimg},#{bookurl},#{date},#{useful})")
    int addBook(Book book);

    /**
     * 查询书籍 id 有多少个
     * @param bid
     * @return
     */
    @Select("select count(*) from book where bid = #{bid}")
    int findByBid(String bid);

    /**
     * 根据用户id 查询书籍列表
     * @param uid
     * @return
     */
    @Select("SELECT book.bid as bid, user_book.uid as uid, user_book.chapter as chapter,user_book.page as page, user_book.date as date,book.bname as bname, book.author as author,book.publishdate as publishdate,book.coverimg as coverimg,book.bookurl as bookurl FROM book left JOIN user_book on book.bid = user_book.bid WHERE user_book.uid is null or user_book.uid = #{uid} and book.bname like '%' || 'bname' || '%'  ORDER BY user_book.date desc,book.date DESC")
    @Results({
            @Result(property = "bid", column = "bid"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "chapter", column = "chapter"),
            @Result(property = "page", column = "page"),
            @Result(property = "date", column = "date"),
            @Result(property = "bname", column = "bname"),
            @Result(property = "author", column = "author"),
            @Result(property = "publishdate", column = "publishdate"),
            @Result(property = "coverimg", column = "coverimg"),
            @Result(property = "bookurl", column = "bookurl")
    })
    List<UserBookListBean> queryBookByUid(@Param("uid") String uid,@Param("bname") String bname);
}
