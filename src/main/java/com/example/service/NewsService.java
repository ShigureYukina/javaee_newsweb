package com.example.service;

import com.example.model.News;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Date;

// 新闻服务接口
public interface NewsService {
	// 初始化上传目录
	void initUploadDir(String uploadDir);

	// 基本CRUD操作
	List<News> getAllNews();
	News getNewsById(Long id);
	String createNews(String title, String content, String author, MultipartFile file) throws IOException;
	String updateNews(Long id, String title, String content, String author, MultipartFile file) throws IOException;
	void deleteNews(Long id);
	String deleteNewsImage(Long id) throws IOException;

	// 文件处理
	String saveImage(MultipartFile file, Long newsId) throws IOException;
	void deleteImage(String imageUrl) throws IOException;

	// 条件查询
	List<News> searchNews(String title, String author, Date startDate, Date endDate);

	// 选择性更新
	boolean updateNewsSelective(News news);
}