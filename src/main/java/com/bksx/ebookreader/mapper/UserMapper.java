package com.bksx.ebookreader.mapper;

import com.bksx.ebookreader.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 通过用户名和密码查询用户信息
     * @param uname 用户名
     * @param password 密码
     * @return
     */
    @Select("SELECT * FROM user WHERE uname = #{uname} and password = #{password}")
    User findUserByNamePassword(@Param("uname") String uname, @Param("password") String password);

    /**
     * 添加user 用户
     * @param user
     * @return
     */
    @Insert("insert into user(uid,uname,openid,password,headimgurl,date,useful) values(#{uid},#{uname},#{openid},#{password},#{headimgurl},#{date},#{useful})")
    int addUser(User user);

    /**
     * 根据用户 去查询 库里有没有相同的用户名
     * @param uname
     * @return
     */
    @Select("select count(*) as count from user where uname = #{uname}")
    int finaByname(String uname);

    @Select("select count(*) as count from user where uid = #{uid}")
    int findByUid(String uid);
}
