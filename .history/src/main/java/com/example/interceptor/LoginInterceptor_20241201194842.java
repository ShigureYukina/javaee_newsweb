package com.example.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		// 检查是否已登录
		if (session.getAttribute("admin") == null) {
			// 如果是AJAX请求，返回401状态码
			String xhr = request.getHeader("X-Requested-With");
			if ("XMLHttpRequest".equals(xhr)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
			// 普通请求重定向到登录页
			response.sendRedirect("/admin/login");
			return false;
		}
		return true;
	}
}