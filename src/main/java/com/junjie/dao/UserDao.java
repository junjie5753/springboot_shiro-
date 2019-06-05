package com.junjie.dao;


import com.junjie.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from user where username=#{username}")
    User  findUserByUsername(String username);
}
