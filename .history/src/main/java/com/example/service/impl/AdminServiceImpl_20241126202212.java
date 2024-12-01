package com.example.service.impl;

import com.example.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public boolean validateAdmin(String username, String password) {
        // 简单实现，实际应该查询数据库并加密密码
        return "admin".equals(username) && "123456".equals(password);
    }
} 