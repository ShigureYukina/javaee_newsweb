package com.example.service.impl;

import com.example.dao.AdminMapper;
import com.example.model.Admin;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private AdminMapper adminMapper;
    
    @Override
    public Admin login(String username, String password) {
        Admin admin = adminMapper.findByUsername(username);
        if (admin != null && password.equals(admin.getPassword())) {
            return admin;
        }
        return null;
    }
} 