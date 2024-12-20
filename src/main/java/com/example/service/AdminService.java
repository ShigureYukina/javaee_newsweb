package com.example.service;

import com.example.model.Admin;

public interface AdminService {
	// 管理员登录
	Admin login(String username, String password);
}