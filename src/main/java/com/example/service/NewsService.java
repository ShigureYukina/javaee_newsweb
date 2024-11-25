package com.example.service;

import com.example.model.News;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    News getNewsById(Long id);
    void saveNews(News news);
    void deleteNews(Long id);
} 