package com.example.service;

import com.example.model.User;

import java.util.List;

public interface UserService {
	User login(String username, String password);// 用户登录

	void register(User user);// 注册用户

	List<User> getAllUsers(); // 获取所有用户

	void deleteUser(Long id); // 删除用户

	User getUserById(Long id); // 根据ID获取用户

	void updateUser(User user); // 更新用户信息

}