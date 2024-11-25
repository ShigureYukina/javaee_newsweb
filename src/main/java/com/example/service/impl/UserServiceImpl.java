package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean validateUser(String username, String password) {
		// 简单实现
		return "user".equals(username) && "123456".equals(password);
	}
}