package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// 登录页面
	@GetMapping("/login")
	public String loginPage() {
		return "user/login"; // 返回用户登录页面
	}

	// 注册页面
	@GetMapping("/register")
	public String registerPage() {
		return "user/register"; // 返回用户注册页面
	}

	// 处理注册请求
	@PostMapping("/register")
	public String register(@RequestParam String username,
			@RequestParam String password,
			@RequestParam String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password); // 请确保密码在存储前进行加密
		user.setEmail(email);
		userService.register(user); // 假设您在 UserService 中有 register 方法
		return "redirect:/user/login"; // 注册成功后重定向到登录页面
	}

	// 处理登录请求
	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestParam String username,
			@RequestParam String password,
			HttpSession session) {
		User user = userService.login(username, password);
		if (user != null) {
			session.setAttribute("user", user);
			return "success"; // 登录成功
		}
		return "用户名或密码错误"; // 登录失败
	}

	// 退出登录
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/user/login"; // 重定向到登录页面
	}

	// 用户管理页面
	@GetMapping("/manage")
	public String managePage(Model model, HttpSession session) {
		// 检查管理员身份
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login"; // 如果未登录，重定向到管理员登录页面
		}
		
		List<User> userList = userService.getAllUsers(); // 获取所有用户
		model.addAttribute("userList", userList); // 将用户列表添加到模型中
		return "user/manage"; // 返回用户管理页面
	}

	// 删除用户
	@PostMapping("/delete/{id}")
	@ResponseBody
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id); // 调用服务层删除用户
		return "删除成功"; // 返回删除成功的消息
	}

	// 编辑用户页面
	@GetMapping("/edit/{id}")
	public String editPage(@PathVariable Long id, Model model) {
		User user = userService.getUserById(id); // 根据ID获取用户
		model.addAttribute("user", user); // 将用户信息添加到模型中
		return "user/edit"; // 返回编辑用户页面
	}

	// 处理编辑用户请求
	@PostMapping("/edit")
	public String editUser(@ModelAttribute User user) {
		userService.updateUser(user); // 更新用户信息
		return "redirect:/user/manage"; // 重定向到用户管理页面
	}
}