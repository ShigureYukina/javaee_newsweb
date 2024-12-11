package com.example.controller;

import com.example.model.Admin;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	// 管理员登录页面
	@GetMapping("/login")
	public String loginPage() {
		return "admin/login";
	}

	// 处理管理员登录请求
	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestParam String username,
			@RequestParam String password,
			HttpSession session) {
		Admin admin = adminService.login(username, password);
		if (admin != null) {
			session.setAttribute("admin", admin);
			return "success";
		}
		return "用户名或密码错误";
	}

	// 退出登录
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/admin/login";
	}
	
}