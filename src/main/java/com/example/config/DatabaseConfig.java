package com.example.config;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("com.example.dao")
public class DatabaseConfig {

	/**
	 * 数据库架构SQL脚本文件路径
	 */
	@Value("classpath:db/schema.sql")
	private Resource schemaScript;

	/**
	 * 配置数据库初始化器
	 * @param dataSource 数据源
	 * @return 数据源初始化器
	 */
	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(schemaScript);
		populator.setContinueOnError(true);
		populator.setSeparator(";");

		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(populator);
		return initializer;
	}
}