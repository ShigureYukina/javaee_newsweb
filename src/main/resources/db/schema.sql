-- 创建数据库（如果不存在）
-- 使用UTF8编码以支持中文
CREATE DATABASE IF NOT EXISTS news_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 切换到新闻数据库
USE news_db;

-- 创建用户表
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建新闻表
CREATE TABLE IF NOT EXISTS news (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    image_url VARCHAR(255),
    author VARCHAR(50) NOT NULL DEFAULT 'admin',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 创建管理员表
CREATE TABLE IF NOT EXISTS admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 插入默认管理员账号（如果不存在）
INSERT INTO admin (username, password) 
SELECT 'admin', '123456'
WHERE NOT EXISTS (
    SELECT 1 FROM admin 
    WHERE username = 'admin'
);

-- 插入测试新闻数据（只在新闻表为空时插入）
INSERT INTO
    news (title, content, author, create_time)
SELECT '测试新闻1', '这是测试新闻1的详细内容', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM news WHERE title = '测试新闻1');

INSERT INTO
    news (title, content, author, create_time)
SELECT '测试新闻2', '这是测试新闻2的详细内容', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM news WHERE title = '测试新闻2');

INSERT INTO
    news (title, content, author, create_time)
SELECT '测试新闻3', '这是测试新闻3的详细内容', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM news WHERE title = '测试新闻3');

INSERT INTO
    news (title, content, author, create_time)
SELECT '测试新闻4', '这是测试新闻4的详细内容', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM news WHERE title = '测试新闻4');

INSERT INTO
    news (title, content, author, create_time)
SELECT '测试新闻5', '这是测试新闻5的详细内容', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM news WHERE title = '测试新闻5');

-- 插入测试用户数据
INSERT INTO users (username, password) VALUES 
('test', '123456'),
('admin', '123456');