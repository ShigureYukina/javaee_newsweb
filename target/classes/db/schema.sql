-- 创建数据库
CREATE DATABASE IF NOT EXISTS news_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE news_db;

-- 创建新闻表
CREATE TABLE IF NOT EXISTS news (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    image_url VARCHAR(255),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 插入测试数据
INSERT INTO news (title, content, create_time) 
SELECT '测试新闻1', '这是测试新闻1的内容', NOW()
WHERE NOT EXISTS (SELECT 1 FROM news WHERE title = '测试新闻1');

INSERT INTO news (title, content, create_time) 
SELECT '测试新闻2', '这是测试新闻2的内容', NOW()
WHERE NOT EXISTS (SELECT 1 FROM news WHERE title = '测试新闻2');

CREATE TABLE IF NOT EXISTS admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '管理员ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '管理员表';

-- 插入默认管理员账号（只在没有管理员账号时插入）
INSERT INTO admin (username, password) 
SELECT 'admin', '123456'
WHERE NOT EXISTS (SELECT 1 FROM admin WHERE username = 'admin');