package com.example.model;

import lombok.Data;

import java.util.Date;

@Data
public class News {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String author;
    private Date createTime;
    private Date updateTime;
} 