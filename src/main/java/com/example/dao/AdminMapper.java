package com.example.dao;

import com.example.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE username = #{username}")
    Admin findByUsername(String username);
} 