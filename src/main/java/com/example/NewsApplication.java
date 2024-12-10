package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 应用程序入口类
@SpringBootApplication
// 扫描Mapper接口所在包
@MapperScan("com.example.dao")
public class NewsApplication {
	// 程序启动入口
	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}
}