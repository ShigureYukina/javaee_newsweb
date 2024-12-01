package com.example.controller;

import com.example.model.News;
import com.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
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

	// 文件上传目录路径
	@Value("${upload.dir}")
	private String uploadDir;

	// 初始化上传目录
	@PostConstruct
	public void init() {
		try {
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			// 设置目录权限
			File uploadDir = uploadPath.toFile();
			uploadDir.setReadable(true, false);
			uploadDir.setWritable(true, false);
			uploadDir.setExecutable(true, false);
		} catch (IOException e) {
			throw new RuntimeException("Could not create upload directory!", e);
		}
	}

	@GetMapping("/manage")
	public String managePage(Model model) {
		model.addAttribute("newsList", newsService.getAllNews());
		return "news/manage";
	}

	// 上传新闻
	@PostMapping("/upload")
	@ResponseBody
	public String uploadNews(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam String title, @RequestParam String content, @RequestParam String author) {
		try {
			// 创建新闻对象
			News news = new News();
			news.setTitle(title);
			news.setContent(content);
			news.setAuthor(author);
			Date now = new Date();
			news.setCreateTime(now);
			news.setUpdateTime(now);

			// 保存新闻基本信息
			newsService.saveNews(news);

			// 处理图片上传
			if (file != null && !file.isEmpty()) {
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				String filename = "news_" + news.getId() + extension;
				Files.write(Paths.get(uploadDir, filename), file.getBytes());
				news.setImageUrl("uploads/" + filename);
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

	// 更新新闻
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

			// 处理图片更新
			if (file != null && !file.isEmpty()) {
				// 删除旧图片
				if (news.getImageUrl() != null) {
					Path oldImagePath = Paths.get(uploadDir,
							news.getImageUrl().substring(news.getImageUrl().lastIndexOf("/") + 1));
					Files.deleteIfExists(oldImagePath);
				}

				// 保存新图片
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				String filename = "news_" + news.getId() + extension;
				Files.write(Paths.get(uploadDir, filename), file.getBytes());
				news.setImageUrl("uploads/" + filename);
			}

			newsService.saveNews(news);
			return "更新成功";
		} catch (Exception e) {
			return "更新失败: " + e.getMessage();
		}
	}

	// 新闻列表页面
	@GetMapping("/list")
	public String listPage(Model model) {
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
			News news = newsService.getNewsById(id);
			if (news == null) {
				return "新闻不存在";
			}

			// 删除图片文件并更新数据库
			if (news.getImageUrl() != null) {
				Path imagePath = Paths.get(uploadDir,
						news.getImageUrl().substring(news.getImageUrl().lastIndexOf("/") + 1));
				Files.deleteIfExists(imagePath);

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