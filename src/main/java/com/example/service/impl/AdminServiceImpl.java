package com.example.service.impl;

import com.example.dao.AdminMapper;
import com.example.model.Admin;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 管理员服务实现类，提供管理员相关的业务操作
@Service
public class AdminServiceImpl implements AdminService {

	// 注入管理员数据访问层
	@Autowired
	private AdminMapper adminMapper;

	// 管理员登录方法
	@Override
	public Admin login(String username, String password) {
		return adminMapper.findByUsernameAndPassword(username, password);
	}
}