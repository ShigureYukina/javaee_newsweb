package com.example.dao;

import com.example.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    // 根据用户名和密码查找管理员
    @Select("SELECT * FROM admin WHERE username = #{username} AND password = #{password}")
    Admin findByUsernameAndPassword(String username, String password);
    
    // 根据用户名查找管理员
    @Select("SELECT * FROM admin WHERE username = #{username}")
    Admin findByUsername(String username);
} 