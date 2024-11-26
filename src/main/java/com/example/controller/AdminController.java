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

	@GetMapping("/login")
	public String loginPage() {
		return "admin/login";
	}

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

	@GetMapping("/news-manage")
	public String newsManage(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/admin/login";
		}
		return "redirect:/news/manage";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/admin/login";
	}
}