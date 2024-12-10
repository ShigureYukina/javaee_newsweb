package com.example.service;

import com.example.model.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;

    @Test
    public void testGetAllNews() {
        List<News> newsList = newsService.getAllNews();
        assertNotNull(newsList);
    }

    @Test
    public void testGetNewsById() {
        News news = newsService.getNewsById(1L);
        assertNotNull(news);
        assertEquals(Long.valueOf(1L), news.getId());
    }
} 