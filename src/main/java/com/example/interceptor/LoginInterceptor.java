package com.example.interceptor;
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
		// 获取用户 session
		Object user = request.getSession().getAttribute("user");

		// 允许访问的路径
		String requestURI = request.getRequestURI();
		// 允许访问新闻页面、登录和注册页面
		if (requestURI.startsWith("/news") || requestURI.equals("/user/login") || requestURI.equals("/news/list")
				|| requestURI.equals("/user/register")) {
			return true; // 允许访问新闻页面和登录、注册页面
		}

		// 如果用户未登录，重定向新闻界面
		if (user == null) {
			response.sendRedirect("/news/list");
			return false; // 拦截请求
		}

		return true; // 继续处理请求
	}
}