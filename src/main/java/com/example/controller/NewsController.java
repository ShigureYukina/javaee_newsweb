package com.example.controller;

import com.example.model.News;
import com.example.model.User;
import com.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Value("${upload.dir}")
	private String uploadDir;

	// 初始化上传目录
	@PostConstruct
	public void init() {
		 newsService.initUploadDir(uploadDir);
	}

	// 页面请求处理
	@GetMapping("/manage")
	public String managePage(Model model) {
		model.addAttribute("newsList", newsService.getAllNews());
		return "news/manage";
	}

	@GetMapping("/add")
	public String addPage() {
		return "news/add";
	}

	@GetMapping("/edit/{id}")
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("news", newsService.getNewsById(id));
		return "news/edit";
	}

	@GetMapping("/list")
	public String listPage(Model model) {
		model.addAttribute("newsList", newsService.getAllNews());
		return "news/list";
	}

	@GetMapping("/view/{id}")
	public String viewNews(@PathVariable Long id, Model model) {
		News news = newsService.getNewsById(id);
		if (news == null) {
			return "redirect:/news/list";
		}
		model.addAttribute("news", news);
		return "news/view";
	}

	// API请求处理
	@PostMapping("/upload")
	@ResponseBody
	public String uploadNews(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam String title, @RequestParam String content, @RequestParam String author) {
		try {
			return newsService.createNews(title, content, author, file);
		} catch (Exception e) {
			return "添加失败: " + e.getMessage();
		}
	}

	@PostMapping("/update")
	@ResponseBody
	public String updateNews(@RequestParam Long id, @RequestParam String title,
			@RequestParam String content, @RequestParam String author,
			@RequestParam(required = false) MultipartFile file) {
		try {
			return newsService.updateNews(id, title, content, author, file);
		} catch (Exception e) {
			return "更新失败: " + e.getMessage();
		}
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public String deleteNews(@PathVariable Long id) {
		try {
			newsService.deleteNews(id);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败: " + e.getMessage();
		}
	}

	// 编辑新闻页面
	@GetMapping("/edit/{id}")
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("news", newsService.getNewsById(id));
		return "news/edit";
	}

	// 简化后的更新方法
	@PostMapping("/update")
	@ResponseBody
	public String updateNews(@RequestParam Long id, @RequestParam String title,
			@RequestParam String content, @RequestParam String author,
			@RequestParam(required = false) MultipartFile file) {
		try {
			News news = newsService.getNewsById(id);
			news.setTitle(title);
			news.setContent(content);
			news.setAuthor(author);
			news.setUpdateTime(new Date());

			if (file != null && !file.isEmpty()) {
				deleteImage(news.getImageUrl());
				news.setImageUrl(saveImage(file, news.getId()));
			}

			newsService.saveNews(news);
			return "更新成功";
		} catch (Exception e) {
			return "更新失败: " + e.getMessage();
		}
	}

	// 新闻列表页面
	@GetMapping("/list")
	public String listPage(HttpSession session, Model model) {
		List<News> newsList = newsService.getAllNews();
		model.addAttribute("newsList", newsList);
		return "news/list";
	}

	// 查看新闻详情
	@GetMapping("/view/{id}")
	public String viewNews(@PathVariable Long id, Model model) {
		News news = newsService.getNewsById(id);
		if (news == null) {
			return "redirect:/news/list";
		}
		model.addAttribute("news", news);
		return "news/view";
	}

	// 添加新闻页面
	@GetMapping("/add")
	public String addPage() {
		return "news/add";
	}

	// 删除新闻图片
	@PostMapping("/deleteImage/{id}")
	@ResponseBody
	public String deleteImage(@PathVariable Long id) {
		try {
			return newsService.deleteNewsImage(id);
		} catch (Exception e) {
			return "删除失败: " + e.getMessage();
		}
	}
}