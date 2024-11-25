package org.example.service;

/** copy by ShigureYukina,from 2024/11/24-下午5:02 */

import org.example.model.News;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    News getNewsById(Long id);
    void addNews(News news);
    void updateNews(News news);
    void deleteNews(Long id);
}