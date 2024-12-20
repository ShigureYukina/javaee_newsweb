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

	/**
	 * 根据用户名和密码登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 登录成功返回Admin对象，失败返回null
	 */
	@Override
	public Admin login(String username, String password) {
		return adminMapper.findByUsernameAndPassword(username, password);
	}
}