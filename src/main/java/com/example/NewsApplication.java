package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class NewsApplication implements ErrorController {

	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}

	@RequestMapping("/error")
	public String handleError() {
		return "error";
	}
}