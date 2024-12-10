package com.example.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorMessage", e.getMessage());
		modelAndView.setViewName("error");
		return modelAndView;
	}
}