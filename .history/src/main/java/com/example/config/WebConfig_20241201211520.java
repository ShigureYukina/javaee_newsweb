// package com.example.config;

// import com.example.interceptor.LoginInterceptor;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// /**
//  * Web配置类
//  * 用于配置跨域请求(CORS)和登录拦截器
//  */
// @Configuration
// public class WebConfig implements WebMvcConfigurer {

// 	/**
// 	 * 配置跨域请求
// 	 * 允许所有来源访问API
// 	 * 允许GET、POST、PUT、DELETE、OPTIONS方法
// 	 * 允许所有请求头
// 	 * 预检请求缓存时间为3600秒
// 	 */
// 	@Override
// 	public void addCorsMappings(CorsRegistry registry) {
// 		registry.addMapping("/**")
// 				.allowedOrigins("*")
// 				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
// 				.allowedHeaders("*")
// 				.maxAge(3600);
// 	}

// 	/**
// 	 * 配置登录拦截器
// 	 * 拦截所有需要登录才能访问的新闻管理接口
// 	 * 排除公开访问的接口，如新闻列表、新闻查看、登录接口和上传文件访问
// 	 */
// 	@Override
// 	public void addInterceptors(InterceptorRegistry registry) {
// 		registry.addInterceptor(new LoginInterceptor())
// 				.addPathPatterns("/news/manage/**", "/news/add/**", "/news/edit/**", "/news/delete/**",
// 						"/news/update/**", "/news/deleteImage/**")
// 				.excludePathPatterns("/news/list", "/news/view/**", "/admin/login", "/uploads/**");
// 	}
// }