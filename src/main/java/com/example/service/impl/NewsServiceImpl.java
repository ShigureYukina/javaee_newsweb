package com.example.service.impl;

import com.example.dao.NewsMapper;
import com.example.model.News;
import com.example.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    
    private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
    
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> getAllNews() {
        log.info("正在获取所有新闻");
        List<News> newsList = newsMapper.findAll();
        log.info("获取到 {} 条新闻", newsList.size());
        return newsList;
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