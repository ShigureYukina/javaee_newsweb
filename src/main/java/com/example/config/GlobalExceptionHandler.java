package com.example.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 * 用于统一处理应用中抛出的异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 处理所有未被特定处理器捕获的异常
	 * @param e 捕获到的异常
	 * @return 包含错误信息的ResponseEntity对象
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		// 创建响应Map对象
		Map<String, Object> response = new HashMap<>();
		// 设置错误信息
		response.put("error", e.getMessage());
		// 设置状态标识
		response.put("status", "error");
		// 返回400错误响应
		return ResponseEntity.badRequest().body(response);
	}
}