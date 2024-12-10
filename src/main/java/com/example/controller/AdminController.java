package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/login")
	public String loginPage() {
		return "admin/login";
	}

	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestParam String username,
			@RequestParam String password,
			HttpSession session) {
		try {
			// 从admin表中查询管理员
			String sql = "SELECT id FROM admin WHERE username = ? AND password = ?";
			List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, username, password);

			if (!results.isEmpty()) {
				// 登录成功，设置session
				session.setAttribute("admin", username);
				session.setAttribute("adminId", results.get(0).get("id"));
				System.out.println("管理员登录成功: " + username);
				return "success";
			}
			System.out.println("管理员登录失败: 用户名或密码错误");
			return "failed";
		} catch (Exception e) {
			System.out.println("管理员登录异常: " + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	@GetMapping("/manage")
	public String managePage(Model model, HttpSession session) {
		// 检查是否已登录
		Object admin = session.getAttribute("admin");
		if (admin == null) {
			System.out.println("未登录，重定向到登录页面");
			return "redirect:/admin/login";
		}

		try {
			System.out.println("当前管理员: " + admin);
			// 查询所有新闻
			String sql = "SELECT * FROM news ORDER BY create_time DESC";
			List<Map<String, Object>> newsList = jdbcTemplate.queryForList(sql);
			model.addAttribute("newsList", newsList);
			return "redirect:/news/manage"; // 修改这里，重定向到新闻管理页面
		} catch (Exception e) {
			System.out.println("查询新闻列表异常: " + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		session.removeAttribute("adminId");
		return "redirect:/admin/login";
	}
}