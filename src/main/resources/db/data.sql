-- 清空现有数据
TRUNCATE TABLE news;

-- 插入测试数据
INSERT INTO news (title, content, author, create_time) VALUES 
('测试新闻1', '这是测试新闻1的详细内容', 'admin', NOW()),
('测试新闻2', '这是测试新闻2的详细内容', 'admin', NOW()),
('测试新闻3', '这是测试新闻3的详细内容', 'admin', NOW()),
('测试新闻4', '这是测试新闻4的详细内容', 'admin', NOW()),
('测试新闻5', '这是测试新闻5的详细内容', 'admin', NOW()); 

-- 添加用户登录功能
INSERT INTO users (username, password) VALUES 
('admin', '123456'),
('test', '123456'); 