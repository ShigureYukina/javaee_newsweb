package com.example.service.impl;

import com.example.dao.NewsMapper;
import com.example.model.News;
import com.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> getAllNews() {
        return newsMapper.findAll();
    }

    @Override
    public News getNewsById(Long id) {
        return newsMapper.findById(id);
    }

    @Override
    public void saveNews(News news) {
        if (news.getId() == null) {
            newsMapper.insert(news);
        } else {
            newsMapper.update(news);
        }
    }

    @Override
    public void deleteNews(Long id) {
        newsMapper.deleteById(id);
    }
} 