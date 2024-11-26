package com.example.dao;

import com.example.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsMapper {
    // 获取所有新闻，按创建时间降序排序
    @Select("SELECT * FROM news ORDER BY create_time DESC")
    List<News> findAll();

    // 根据ID查找新闻
    @Select("SELECT * FROM news WHERE id = #{id}")
    News findById(Long id);

    // 插入新闻，并返回自动生成的ID
    @Insert("INSERT INTO news (title, content, image_url, author, create_time, update_time) VALUES (#{title}, #{content}, #{imageUrl}, #{author}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(News news);

    // 更新新闻信息
    @Update("UPDATE news SET title=#{title}, content=#{content}, image_url=#{imageUrl}, author=#{author}, update_time=#{updateTime} WHERE id=#{id}")
    void update(News news);

    // 根据ID删除新闻
    @Delete("DELETE FROM news WHERE id = #{id}")
    void deleteById(Long id);
} 