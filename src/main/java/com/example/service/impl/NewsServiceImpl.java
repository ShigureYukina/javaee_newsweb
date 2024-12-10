package com.example.service.impl;


import com.example.dao.NewsMapper;
import com.example.model.News;
import com.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Date;
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
	public String createNews(String title, String content, String author, MultipartFile file) throws IOException {
		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		news.setAuthor(author);
		news.setCreateTime(new Date());
		newsMapper.insert(news);
		return "添加成功";
	}

	@Override
	public String updateNews(Long id, String title, String content, String author, MultipartFile file) throws IOException {
		News news = getNewsById(id);
		if (news == null) return "新闻不存在";
		news.setTitle(title);
		news.setContent(content);
		news.setAuthor(author);
		news.setUpdateTime(new Date());
		newsMapper.update(news);
		return "更新成功";
	}

	// 删除指定ID的新闻
	@Override
	public void deleteNews(Long id) {
		newsMapper.deleteById(id);
	}

	@Override
	public String deleteNewsImage(Long id) throws IOException {
		return "删除成功";
	}

	@Override
	public String saveImage(MultipartFile file, Long newsId) throws IOException {
		return null;
	}

	@Override
	public void deleteImage(String imageUrl) throws IOException {
	}

	@Override
	public List<News> searchNews(String title, String author, Date startDate, Date endDate) {
		return newsMapper.findByCondition(title, author, startDate, endDate);
	}

	@Override
	public boolean updateNewsSelective(News news) {
		news.setUpdateTime(new Date());
		return newsMapper.updateSelective(news) > 0;
	}
}