package com.example.dao;

import com.example.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsMapper {
    @Select("SELECT * FROM news ORDER BY create_time DESC")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "content", column = "content"),
        @Result(property = "imageUrl", column = "image_url"),
        @Result(property = "createTime", column = "create_time")
    })
    List<News> findAll();

    @Select("SELECT * FROM news WHERE id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "content", column = "content"),
        @Result(property = "imageUrl", column = "image_url"),
        @Result(property = "createTime", column = "create_time")
    })
    News findById(Long id);

    @Insert("INSERT INTO news (title, content, image_url, create_time) VALUES (#{title}, #{content}, #{imageUrl}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(News news);

    @Update("UPDATE news SET title = #{title}, content = #{content}, image_url = #{imageUrl} WHERE id = #{id}")
    void update(News news);

    @Delete("DELETE FROM news WHERE id = #{id}")
    void deleteById(Long id);
} 