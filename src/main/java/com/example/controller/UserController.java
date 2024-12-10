package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/login")
	public String loginPage() {
		return "user/login";
	}

	@PostMapping("/doLogin")
	@ResponseBody
	public String doLogin(@RequestParam String username,
			@RequestParam String password,
			HttpSession session) {
		try {
			// 从users表中查询用户
			String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
			Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
			
			if (count != null && count > 0) {
				session.setAttribute("loginUser", username);
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@GetMapping("/")
	public String index() {
		return "redirect:/news/list";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:/login";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "user/register";
	}

	@PostMapping("/doRegister")
	@ResponseBody
	public String doRegister(@RequestParam String username,
			@RequestParam String password,
			@RequestParam String confirmPassword) {
		// 验证密码是否一致
		if (!password.equals(confirmPassword)) {
			return "passwords_not_match";
		}

		try {
			// 检查用户名是否已存在
			String checkSql = "SELECT COUNT(*) FROM users WHERE username = ?";
			Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, username);

			if (count != null && count > 0) {
				return "username_exists";
			}

			// 插入新用户
			String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";
			jdbcTemplate.update(insertSql, username, password);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}