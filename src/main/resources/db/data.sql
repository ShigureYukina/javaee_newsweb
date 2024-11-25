-- 清空现有数据
TRUNCATE TABLE news;

-- 插入测试数据
INSERT INTO news (title, content, image_url, create_time) VALUES 
('测试新闻1', '这是测试新闻1的详细内容', '/images/news1.jpg', NOW()),
('测试新闻2', '这是测试新闻2的详细内容', '/images/news2.jpg', NOW()),
('测试新闻3', '这是测试新闻3的详细内容', '/images/news3.jpg', NOW()),
('测试新闻4', '这是测试新闻4的详细内容', '/images/news4.jpg', NOW()),
('测试新闻5', '这是测试新闻5的详细内容', '/images/news5.jpg', NOW()); 