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
        return userMapper.findAll(); // 假设您在 UserMapper 中有 findAll 方法
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id); // 假设您在 UserMapper 中有 deleteById 方法
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.findById(id); // 假设您在 UserMapper 中有 findById 方法
    }

    @Override
    public void updateUser(User user) {
        userMapper.update(user); // 假设您在 UserMapper 中有 update 方法
    }
} 