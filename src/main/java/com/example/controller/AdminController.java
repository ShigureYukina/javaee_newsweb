package com.example.controller;

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

	@GetMapping("/login")
	public String loginPage() {
		return "admin/login";
	}

	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestParam String username,
			@RequestParam String password,
			HttpSession session) {
		if (adminService.validateAdmin(username, password)) {
			session.setAttribute("admin", username);
			return "登录成功";
		}
		return "用户名或密码错误";
	}

	@GetMapping("/news/manage")
	public String newsManage(HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		return "admin/news-manage";
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/admin/login";
	}
}