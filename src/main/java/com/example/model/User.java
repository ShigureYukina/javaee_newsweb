package com.example.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email; // 可选字段
    private Date createTime;
} 