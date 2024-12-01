package com.example.service;

import com.example.model.News;

import java.util.List;

/**
 * 新闻服务接口
 * 提供新闻的增删改查等基本操作
 */
public interface NewsService {
	/**
	 * 获取所有新闻列表
	 * 按创建时间降序排序
	 * @return 新闻列表
	 */
	List<News> getAllNews();

	/**
	 * 根据ID获取新闻详情
	 * @param id 新闻ID
	 * @return 新闻对象，如果不存在返回null
	 */
	News getNewsById(Long id);

	/**
	 * 保存或更新新闻
	 * 如果news.id为null则新增，否则更新
	 * @param news 新闻对象
	 */
	void saveNews(News news);

	/**
	 * 根据ID删除新闻
	 * @param id 新闻ID
	 */
	void deleteNews(Long id);
}