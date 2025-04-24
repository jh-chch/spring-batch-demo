CREATE DATABASE batch_demo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(200) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    last_notified_at DATETIME DEFAULT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (name, email, is_active)
VALUES 
('홍길동', 'hong@example.com', TRUE),
('김개발', 'kim@example.com', TRUE),
('최개발', 'lee@example.com', TRUE);
