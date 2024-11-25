package com.example.controller;

import com.example.model.News;
import com.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Value("${upload.dir:/uploads}")
	private String uploadDir;

	@GetMapping("/list")
	public String listNews(Model model) {
		model.addAttribute("newsList", newsService.getAllNews());
		return "news/list";
	}

	@GetMapping("/view/{id}")
	public String viewNews(@PathVariable Long id, Model model) {
		model.addAttribute("news", newsService.getNewsById(id));
		return "news/view";
	}

	@PostMapping("/upload")
	@ResponseBody
	public String uploadNews(@RequestParam("file") MultipartFile file,
			@RequestParam("title") String title,
			@RequestParam("content") String content) {
		try {
			News news = new News();
			news.setTitle(title);
			news.setContent(content);
			// 处理文件上传
			if (!file.isEmpty()) {
				// 创建上传目录
				File uploadPath = new File(uploadDir);
				if (!uploadPath.exists()) {
					uploadPath.mkdirs();
				}

				// 生成唯一文件名
				String originalFilename = file.getOriginalFilename();
				String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
				String filename = UUID.randomUUID().toString() + extension;

				// 保存文件
				Path filePath = Paths.get(uploadDir, filename);
				Files.write(filePath, file.getBytes());

				// 设置图片URL
				news.setImageUrl("/uploads/" + filename);
			}

			newsService.saveNews(news);
			return "上传成功";
		} catch (Exception e) {
			return "上传失败: " + e.getMessage();
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

	@GetMapping("/manage")
	public String managePage(Model model) {
		List<News> newsList = newsService.getAllNews();
		model.addAttribute("newsList", newsList);
		return "news/manage";
	}
}