package com.example.dao;

import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface UserMapper {
	// 根据用户名和密码查找用户
	@Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	@Insert("INSERT INTO users (username, password, email) VALUES (#{username}, #{password}, #{email})")
	void insertUser(User user);

	@Select("SELECT * FROM users")
	List<User> findAll(); // 获取所有用户

	@Select("SELECT * FROM users WHERE id = #{id}")
	User findById(Long id); // 根据ID查找用户

	@Update("UPDATE users SET username=#{username}, password=#{password}, email=#{email} WHERE id=#{id}")
	void update(User user); // 更新用户信息

	@Delete("DELETE FROM users WHERE id = #{id}")
	void deleteById(Long id); // 根据ID删除用户
}