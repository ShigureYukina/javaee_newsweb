package com.example.service;

import com.example.model.News;

import java.util.List;

// 新闻服务接口
public interface NewsService {
	// 获取所有新闻列表
	List<News> getAllNews();

	// 根据ID获取新闻
	News getNewsById(Long id);

	// 保存或更新新闻
	void saveNews(News news);

	// 删除新闻
	void deleteNews(Long id);
}