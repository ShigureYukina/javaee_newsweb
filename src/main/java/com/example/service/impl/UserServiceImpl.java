package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(String username, String password) {
		return userMapper.findByUsernameAndPassword(username, password);
	}

	@Override
	public void register(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userMapper.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		userMapper.deleteById(id);
	}

	@Override
	public User getUserById(Long id) {
		return userMapper.findById(id);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

}