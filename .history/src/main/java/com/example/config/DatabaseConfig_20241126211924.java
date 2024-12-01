package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    
    @Value("classpath:db/schema.sql")
    private Resource schemaScript;
    
    @Value("classpath:db/data.sql")
    private Resource dataScript;
    
    @Value("${spring.sql.init.mode:never}")
    private String initMode;
    
    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        
        if ("always".equalsIgnoreCase(initMode)) {
            ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
            resourceDatabasePopulator.addScript(schemaScript);
            resourceDatabasePopulator.addScript(dataScript);
            resourceDatabasePopulator.setContinueOnError(true);
            initializer.setDatabasePopulator(resourceDatabasePopulator);
        }
        
        return initializer;
    }
} 