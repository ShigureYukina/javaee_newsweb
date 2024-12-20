package com.example.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// 全局异常处理类
@ControllerAdvice
public class GlobalExceptionHandler {

	// 处理所有异常
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		// 创建响应体
		Map<String, Object> response = new HashMap<>();
		// 设置错误信息
		response.put("error", e.getMessage());
		// 设置状态为错误
		response.put("status", "error");
		// 返回错误响应
		return ResponseEntity.badRequest().body(response);
	}
}