package com.example.model;

import java.util.Date;
import lombok.Data;

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