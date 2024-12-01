package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * 数据库配置类
 * 用于初始化数据库架构和数据
 */
@Configuration
public class DatabaseConfig {

	/**
	 * 数据库架构SQL脚本文件路径
	 */
	@Value("classpath:db/schema.sql")
	private Resource schemaScript;

	/**
	 * 初始数据SQL脚本文件路径
	 */
	@Value("classpath:db/data.sql")
	private Resource dataScript;

	/**
	 * 数据库初始化模式配置
	 * 默认值为"never"，表示不自动初始化
	 */
	@Value("${spring.sql.init.mode:never}")
	private String initMode;

	/**
	 * 配置数据库初始化器
	 * @param dataSource 数据源
	 * @return 数据源初始化器
	 */
	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		return initializer;
	}
}