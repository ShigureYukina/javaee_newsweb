package com.example.service;

import com.example.model.User;

public interface UserService {
    User login(String username, String password);
    void register(User user);
} 