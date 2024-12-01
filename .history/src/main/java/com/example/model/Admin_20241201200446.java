package com.example.model;

import lombok.Data;

// 管理员实体类
@Data
public class Admin {
	// 管理员ID
	private Long id;
	// 用户名
	private String username;
	// 密码
	private String password;
}