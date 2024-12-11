package com.example.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取用户和管理员 session
		Object user = request.getSession().getAttribute("user");
		Object admin = request.getSession().getAttribute("admin");

		// 允许访问的路径
		String requestURI = request.getRequestURI();
		// 允许访问登录、注册和管理员登录页面
		if (requestURI.equals("/user/login") || requestURI.equals("/user/register")
				|| requestURI.equals("/admin/login") || requestURI.equals("/news/list")) {
			return true; // 允许访问登录、注册和管理员登录页面
		}

		// 如果用户未登录，重定向到登录页面并添加提示信息
		if (requestURI.equals("/user/manage") || admin == null) {
			request.getSession().setAttribute("loginError", "请先登录"); // 设置提示信息
			response.sendRedirect("/admin/login");
			return false; // 拦截请求
		}

		return true; // 继续处理请求
	}
}