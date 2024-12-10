-- 创建数据库（如果不存在）
-- 使用UTF8编码以支持中文
CREATE DATABASE IF NOT EXISTS news_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 切换到新闻数据库
USE news_db;

-- 创建新闻表
-- id: 自增主键
-- title: 新闻标题
-- content: 新闻内容
-- image_url: 图片路径
-- author: 作者名称
-- create_time: 创建时间（自动设置）
-- update_time: 更新时间（自动更新）
CREATE TABLE IF NOT EXISTS news
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    content     TEXT,
    image_url   VARCHAR(255),
    author      VARCHAR(50)  NOT NULL DEFAULT 'admin',
    create_time DATETIME              DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 创建管理员表
-- id: 自增主键
-- username: 用户名（唯一）
-- password: 密码
-- create_time: 创建时间
CREATE TABLE IF NOT EXISTS admin
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    username    VARCHAR(50)  NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 插入默认管理员账号
-- 仅在管理员表为空时插入
INSERT INTO admin (username, password)
SELECT 'admin', '123456'
WHERE NOT EXISTS (SELECT 1
                  FROM admin
                  WHERE username = 'admin');

-- 创建用户表
CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 插入示例用户
INSERT INTO users (username, password, email)
VALUES ('user',
        '123456',
        '123456@example.com');