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
        // 如果密码为空，则不更新密码
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            userMapper.updateWithoutPassword(user);
        } else {
            userMapper.updateWithPassword(user);
        }
    }

    @Override
    public boolean checkUsernameExists(String username, Long id) {
        return userMapper.checkUsernameExists(username, id) > 0;
    }

    @Override
    public boolean checkEmailExists(String email, Long id) {
        return userMapper.checkEmailExists(email, id) > 0;
    }
} 