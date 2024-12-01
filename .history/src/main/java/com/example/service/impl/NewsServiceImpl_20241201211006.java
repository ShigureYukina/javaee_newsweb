package com.example.service.impl;

import com.example.dao.NewsMapper;
import com.example.model.News;
import com.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 新闻服务的实现类，提供新闻的基本操作
@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;

	// 获取所有新闻
	@Override
	public List<News> getAllNews() {
		return newsMapper.findAll();
	}
	// 根据ID获取特定新闻
	@Override
	public News getNewsById(Long id) {
		return newsMapper.findById(id);
	}
	// 保存新闻。如果新闻ID为空，则插入新新闻；否则，更新现有新闻。
	@Override
	public void saveNews(News news) {
		if (news.getId() == null) {
			newsMapper.insert(news);
		} else {
			newsMapper.update(news);
		}
	}

	// 删除指定ID的新闻
	@Override
	public void deleteNews(Long id) {
		newsMapper.deleteById(id);
	}
}