package com.example.dao;

import com.example.model.News;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Date;

@Mapper
@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface NewsMapper {
	// 基本查询
	@Select("SELECT * FROM news ORDER BY create_time DESC")
	List<News> findAll();

	@Select("SELECT * FROM news WHERE id = #{id}")
	News findById(Long id);

	// 动态SQL查询
	@Select("SELECT * FROM news WHERE 1=1 " +
		   "${title != null ? 'AND title LIKE CONCAT(\"%\", #{title}, \"%\")' : ''} " +
		   "${author != null ? 'AND author = #{author}' : ''} " +
		   "${startDate != null ? 'AND create_time >= #{startDate}' : ''} " +
		   "${endDate != null ? 'AND create_time <= #{endDate}' : ''} " +
		   "ORDER BY create_time DESC")
	List<News> findByCondition(@Param("title") String title, 
							  @Param("author") String author,
							  @Param("startDate") Date startDate,
							  @Param("endDate") Date endDate);

	@Insert("INSERT INTO news (title, content, image_url, author, create_time, update_time) " +
		   "VALUES (#{title}, #{content}, #{imageUrl}, #{author}, #{createTime}, #{updateTime})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(News news);

	@Update("UPDATE news SET title=#{title}, content=#{content}, " +
			"image_url=#{imageUrl}, author=#{author}, update_time=#{updateTime} WHERE id=#{id}")
	void update(News news);

	// 选择性更新
	@Update("<script>" +
			"UPDATE news SET update_time=#{updateTime} " +
			"<if test='title != null'>, title=#{title}</if>" +
			"<if test='content != null'>, content=#{content}</if>" +
			"<if test='imageUrl != null'>, image_url=#{imageUrl}</if>" +
			"<if test='author != null'>, author=#{author}</if>" +
			"WHERE id=#{id}" +
			"</script>")
	int updateSelective(News news);

	@Delete("DELETE FROM news WHERE id = #{id}")
	void deleteById(Long id);
}