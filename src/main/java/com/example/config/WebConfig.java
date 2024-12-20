package com.example.config;

import com.example.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 * 用于配置跨域请求(CORS)和登录拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${upload.dir}")
	private String uploadDir;

	@Autowired
	private LoginInterceptor loginInterceptor;

	/**
	 * 配置跨域请求
	 * 允许GET、POST、PUT、DELETE、OPTIONS方法
	 * 预检请求缓存时间为3600秒
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedHeaders("*")
				.maxAge(3600);
	}

	/**
	 * 配置登录拦截器
	 * 拦截所有需要登录才能访问的新闻管理接口
	 * 排除公开访问的接口
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/user/login", "/user/register", "/admin/login");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 添加图片文件夹资源映射
		registry.addResourceHandler("/uploads/**")
				.addResourceLocations("file:" + uploadDir + "/");
	}
}