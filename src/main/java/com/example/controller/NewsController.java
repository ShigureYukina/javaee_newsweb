package com.example.controller;

import com.example.model.Admin;
import com.example.model.News;
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
	private String uploadDir;  // 文件上传目录

	@PostConstruct
	public void init() {
		// 初始化上传目录
		try {
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
				System.out.println("Created upload directory: " + uploadPath.toAbsolutePath());
			}
			// 设置目录权限：所有用户可读写执行
			File uploadDir = uploadPath.toFile();
			uploadDir.setReadable(true, false);
			uploadDir.setWritable(true, false);
			uploadDir.setExecutable(true, false);
		} catch (IOException e) {
			throw new RuntimeException("Could not create upload directory!", e);
		}
	}

	@GetMapping("/manage")
	public String managePage(Model model, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) return "redirect:/admin/login";
		model.addAttribute("newsList", newsService.getAllNews());
		return "news/manage";
	}

	// 新闻上传处理
	@PostMapping("/upload")
	@ResponseBody
	public String uploadNews(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam String title, @RequestParam String content, @RequestParam String author, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) return "请先登录";
		try {
			News news = new News();
			news.setTitle(title);
			news.setContent(content);
			news.setAuthor(author);
			Date now = new Date();
			news.setCreateTime(now);
			news.setUpdateTime(now);  // 初始时更新时间等于创建时间
			
			// 先保存新闻获取ID
			
			newsService.saveNews(news);
			
			// 处理图片上传
			if (file != null && !file.isEmpty()) {
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				String filename = "news_" + news.getId() + extension;  // 使用新闻ID作为文件名
				Files.write(Paths.get(uploadDir, filename), file.getBytes());
				news.setImageUrl("uploads/" + filename);
				// 更新新闻记录
				newsService.saveNews(news);
			}

			return "添加成功";
		} catch (Exception e) {
			return "添加失败: " + e.getMessage();
		}
	}

	// 删除新闻
	@PostMapping("/delete/{id}")
	@ResponseBody
	public String deleteNews(@PathVariable Long id, HttpSession session) {
		if (session.getAttribute("admin") == null) return "请先登录";
		try {
			newsService.deleteNews(id);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败: " + e.getMessage();
		}
	}

	@GetMapping("/edit/{id}")
	public String editPage(@PathVariable Long id, Model model, HttpSession session) {
		if (session.getAttribute("admin") == null) return "redirect:/admin/login";
		model.addAttribute("news", newsService.getNewsById(id));
		return "news/edit";
	}

	// 更新新闻
	@PostMapping("/update")
	@ResponseBody
	public String updateNews(@RequestParam Long id, @RequestParam String title,
			@RequestParam String content, @RequestParam String author,
			@RequestParam(required = false) MultipartFile file,
			HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) return "请先登录";
		try {
			News news = newsService.getNewsById(id);
			news.setTitle(title);
			
			news.setContent(content);
			news.setAuthor(author);
			news.setUpdateTime(new Date());
			
			// 处理新图片上传
			if (file != null && !file.isEmpty()) {
				// 删除旧图片
				if (news.getImageUrl() != null) {
					
					Path oldImagePath = Paths.get(uploadDir, news.getImageUrl().substring(news.getImageUrl().lastIndexOf("/") + 1));
					Files.deleteIfExists(oldImagePath);
				}
				
				// 保存新图片
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				String filename = "news_" + news.getId() + extension;  // 使用新闻ID作为文件名
				Files.write(Paths.get(uploadDir, filename), file.getBytes());
				news.setImageUrl("uploads/" + filename);
			}
			
			newsService.saveNews(news);
			return "更新成功";
		} catch (Exception e) {
			return "更新失败: " + e.getMessage();
		}
	}

	@GetMapping("/list")
	public String listPage(Model model) {
		List<News> newsList = newsService.getAllNews();
		model.addAttribute("newsList", newsList);
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

	@GetMapping("/add")
	public String addPage(HttpSession session) {
		// 检查是否已登录
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/admin/login";
		}
		return "news/add";
	}

	@PostMapping("/deleteImage/{id}")
	@ResponseBody
	public String deleteImage(@PathVariable Long id, HttpSession session) {
		if (session.getAttribute("admin") == null) return "请先登录";
		try {
			News news = newsService.getNewsById(id);
			if (news == null) {
				return "新闻不存在";
			}

			// 删除图片文件
			if (news.getImageUrl() != null) {
				Path imagePath = Paths.get(uploadDir, news.getImageUrl().substring(news.getImageUrl().lastIndexOf("/") + 1));
				Files.deleteIfExists(imagePath);
				
				// 更新数据库记录
				news.setImageUrl(null);
				news.setUpdateTime(new Date());
				newsService.saveNews(news);
			}
			return "删除成功";
		} catch (Exception e) {
			return "删除失败: " + e.getMessage();
		}
	}
}