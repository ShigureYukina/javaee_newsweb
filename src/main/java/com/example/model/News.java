package com.example.model;

import lombok.Data;

import java.util.Date;

// 新闻实体类
@Data
public class News {
	// 新闻ID
	private Long id;
	// 新闻标题 
	private String title;
	// 新闻内容
	private String content;
	// 新闻图片URL
	private String imageUrl;
	// 新闻作者
	private String author;
	// 创建时间
	private Date createTime;
	// 最后更新时间
	private Date updateTime;
}