// package com.example.interceptor;

// import org.springframework.web.servlet.HandlerInterceptor;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// // 登录拦截器：拦截需要登录才能访问的请求
// public class LoginInterceptor implements HandlerInterceptor {

// 	@Override
// 	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
// 			throws Exception {
// 		HttpSession session = request.getSession();

// 		// 检查是否已登录，未登录则重定向到登录页
// 		if (session.getAttribute("admin") == null) {
// 			response.sendRedirect("/admin/login");
// 			return false;
// 		}
// 		return true;
// 	}
// }