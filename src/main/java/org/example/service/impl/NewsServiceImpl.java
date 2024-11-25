package org.example.service.impl;

/** copy by ShigureYukina,from 2024/11/24-下午5:02 */

import org.example.dao.NewsMapper;
import org.example.model.News;
import org.example.service.NewsService;
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
    public void addNews(News news) {
        newsMapper.insert(news);
    }

    @Override
    public void updateNews(News news) {
        newsMapper.update(news);
    }

    @Override
    public void deleteNews(Long id) {
        newsMapper.deleteById(id);
    }
}