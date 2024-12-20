package com.example.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    // 用户ID
    private Long id;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 邮箱
    private String email; 
    // 用户创建时间
    private Date createTime;
} 