package org.example.controller;

/** copy by ShigureYukina,from 2024/11/25-上午11:04 */

import org.example.model.News;
import org.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public String listNews(Model model) {
        List<News> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);
        return "admin/news/list";
    }

    @GetMapping("/{id}")
    public String showNews(@PathVariable Long id, Model model) {
        News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "admin/news/show";
    }

    @GetMapping("/new")
    public String newNewsForm(Model model) {
        model.addAttribute("news", new News());
        return "admin/news/form";
    }

    @PostMapping
    public String createNews(@ModelAttribute News news) {
        newsService.addNews(news);
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public String editNewsForm(@PathVariable Long id, Model model) {
        News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "admin/news/form";
    }

    @PutMapping("/{id}")
    public String updateNews(@PathVariable Long id, @ModelAttribute News news) {
        news.setId(id);
        newsService.updateNews(news);
        return "redirect:/news/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return "redirect:/news";
    }
}