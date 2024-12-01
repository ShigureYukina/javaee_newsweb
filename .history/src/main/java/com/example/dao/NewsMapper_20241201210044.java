package com.example.dao;

import com.example.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 新闻数据访问接口
 * 处理新闻表的数据库操作
 */
@Mapper
public interface NewsMapper {
	/**
	 * 获取所有新闻
	 * 按创建时间降序排序，最新的新闻排在前面
	 */
	@Select("SELECT * FROM news ORDER BY create_time DESC")
	List<News> findAll();

	/**
	 * 根据ID查找新闻
	 * @param id 新闻ID
	 * @return 新闻对象，如果不存在返回null
	 */
	@Select("SELECT * FROM news WHERE id = #{id}")
	News findById(Long id);

	/**
	 * 插入新闻
	 * @param news 新闻对象
	 * 使用@Options注解获取自动生成的ID
	 */
	@Insert("INSERT INTO news (title, content, image_url, author, create_time, update_time) " +
			"VALUES (#{title}, #{content}, #{imageUrl}, #{author}, #{createTime}, #{updateTime})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(News news);

	/**
	 * 更新新闻信息
	 * @param news 新闻对象
	 * 更新除ID外的所有字段
	 */
	@Update("UPDATE news SET title=#{title}, content=#{content}, " +
			"image_url=#{imageUrl}, author=#{author}, update_time=#{updateTime} " +
			"WHERE id=#{id}")
	void update(News news);

	/**
	 * 根据ID删除新闻
	 * @param id 新闻ID
	 */
	@Delete("DELETE FROM news WHERE id = #{id}")
	void deleteById(Long id);
}