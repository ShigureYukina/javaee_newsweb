package com.example.dao;

import com.example.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
	// 根据用户名和密码查找用户
	@Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	// 插入新用户
	@Insert("INSERT INTO users (username, password, email) VALUES (#{username}, #{password}, #{email})")
	void insertUser(User user);

	// 获取所有用户
	@Select("SELECT * FROM users")
	List<User> findAll();

	// 根据ID查找用户
	@Select("SELECT * FROM users WHERE id = #{id}")
	User findById(Long id);

	// 更新用户信息
	@Update("UPDATE users SET username=#{username}, email=#{email} WHERE id=#{id}")
	void updateUser(User user);


	// 根据ID删除用户
	@Delete("DELETE FROM users WHERE id = #{id}")
	void deleteById(Long id);

}