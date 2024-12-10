package com.example.dao;

import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface UserMapper {
	// 根据用户名和密码查找用户
	@Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	@Insert("INSERT INTO users (username, password, email) VALUES (#{username}, #{password}, #{email})")
	void insertUser(User user);
}