package com.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration // 配置类注解
@MapperScan("com.example.dao") // 指定Mapper接口所在的包
public class DatabaseConfig {

	@Value("classpath:db/schema.sql") // 指定数据库模式脚本的位置
	private Resource schemaScript;

	@Value("classpath:db/data.sql") // 指定数据库数据脚本的位置
	private Resource dataScript;

	@Value("${spring.sql.init.mode:never}") // 获取数据库初始化模式
	private String initMode;
	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource); // 设置数据源

		if ("always".equalsIgnoreCase(initMode)) { // 如果初始化模式为always
			ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
			resourceDatabasePopulator.addScript(schemaScript); // 添加模式脚本
			resourceDatabasePopulator.addScript(dataScript); // 添加数据脚本
			resourceDatabasePopulator.setContinueOnError(true); // 设置继续执行脚本
			initializer.setDatabasePopulator(resourceDatabasePopulator); // 设置数据库填充器
		}

		return initializer; // 返回数据源初始化器
	}
}