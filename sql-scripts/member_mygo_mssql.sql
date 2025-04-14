-- 創建資料庫
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'member_mygo')
BEGIN
    CREATE DATABASE member_mygo;
END;
GO

-- 使用資料庫
USE member_mygo;
GO

-- 刪除表（如果存在）
IF OBJECT_ID('dbo.member', 'U') IS NOT NULL
    DROP TABLE dbo.member;
GO

-- 建立表
CREATE TABLE dbo.member (
    id INT IDENTITY(1,1) PRIMARY KEY,
    first_name NVARCHAR(45) NULL,
    last_name NVARCHAR(45) NULL,
    email NVARCHAR(45) NULL
);
GO

-- 插入資料
INSERT INTO dbo.member (first_name, last_name, email) VALUES
    ('Sakiko', 'Togawa', 'sakiko@crychic.com'),
    ('Tomori', 'Takamatsu', 'penguin@mygo.com'),
    ('Soyo', 'Nagasaki', 'soyo@mygo.com'),
    ('Mutsumi', 'Wakaba', 'mutusmi@crychic.com'),
    ('Taki', 'Shiina', 'Taki@mygo.com');
GO
