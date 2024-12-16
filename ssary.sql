DROP DATABASE IF EXISTS running;
CREATE DATABASE running;
USE running;

-- User 테이블
CREATE TABLE `User` (
	`user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(100) NULL,
	`password` VARCHAR(255) NULL,
	`name` VARCHAR(20) NULL,
	`nickname` VARCHAR(20) NULL,
	`address` VARCHAR(255) NULL,
    `user_img` VARCHAR(4096) NULL,
	`created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`private` TINYINT(1) NOT NULL DEFAULT 1,
	PRIMARY KEY (`user_id`)
);

-- Board 테이블
CREATE TABLE `Board` (
	`board_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(20) NOT NULL,
	`content` TEXT NULL,
	`posted_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`board_id`),
	FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE
);

-- Comment 테이블
CREATE TABLE `Comment` (
	`comment_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`board_id` BIGINT(20) NOT NULL,
	`user_id` BIGINT(20) NOT NULL,
	`content` TEXT NULL,
	`posted_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`comment_id`),
	FOREIGN KEY (`board_id`) REFERENCES `Board`(`board_id`) ON DELETE CASCADE,
	FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE
);

-- Party 테이블
CREATE TABLE `Party` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(20) NOT NULL,
	`room_id` VARCHAR(36) NOT NULL,
	`partied_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE
);

-- ChatRoom 테이블
CREATE TABLE `ChatRoom` (
	`room_id` VARCHAR(36) NOT NULL,
	`room_name` VARCHAR(20) NULL,
	`room_type` VARCHAR(20) NULL,
    `created_date` TIMESTAMP NULL DEFAULT current_timestamp,
	PRIMARY KEY (`room_id`)
);

-- ChatMessage 테이블
CREATE TABLE `ChatMessage` (
	`message_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`room_id` VARCHAR(36) NOT NULL,
	`user_id` BIGINT(20) NOT NULL,
	`sended_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`content` VARCHAR(100) NULL,
	`message_type` VARCHAR(20) NULL,
	PRIMARY KEY (`message_id`),
	FOREIGN KEY (`room_id`) REFERENCES `ChatRoom`(`room_id`) ON DELETE CASCADE,
	FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE
);

-- Run 테이블
CREATE TABLE `Run` (
	`run_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(20) NOT NULL,
	`start_time` TIMESTAMP NULL,
	`end_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`distance` FLOAT NULL,
	`run_img` VARCHAR(4096),
	PRIMARY KEY (`run_id`),
	FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE
);

-- Following 테이블
CREATE TABLE `Following` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(20) NOT NULL,
	`following_id` BIGINT(20) NOT NULL,
	`status` TINYINT(1) NULL DEFAULT 0,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE,
	FOREIGN KEY (`following_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE
);

-- Like 테이블
CREATE TABLE `Like` (
	`like_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(20) NOT NULL,
	`board_id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`like_id`),
	FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE,
	FOREIGN KEY (`board_id`) REFERENCES `Board`(`board_id`) ON DELETE CASCADE
);

-- BoardImg 테이블
CREATE TABLE `BoardImg` (
	`img_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`board_id` BIGINT(20) NOT NULL,
	`board_url` VARCHAR(4096) NULL,
	PRIMARY KEY (`img_id`),
	FOREIGN KEY (`board_id`) REFERENCES `Board`(`board_id`) ON DELETE CASCADE
);

-- User 테이블 더미 데이터
INSERT INTO `User` (`email`, `password`, `name`, `nickname`, `address`) VALUES
('tlgmdtl1118@gmail.com', 'pwd1', '홍길동', 'seoul1', '서울특별시 강남구'),
('user2@example.com', 'pwd2', '김영희', 'seoul2', '서울특별시 서초구'),
('user3@example.com', 'pwd3', '이철수', 'seoul3', '서울특별시 송파구'),
('user4@example.com', 'pwd4', '박민수', 'seoul4', '서울특별시 마포구'),
('user5@example.com', 'pwd5', '정수진', 'seoul5', '서울특별시 성동구'),
('user6@example.com', 'pwd6', '최현준', 'seoul6', '서울특별시 영등포구'),
('user7@example.com', 'pwd7', '강미영', 'seoul7', '서울특별시 중구'),
('user8@example.com', 'pwd8', '윤상혁', 'seoul8', '서울특별시 용산구'),
('user9@example.com', 'pwd9', '배수빈', 'seoul9', '서울특별시 은평구'),
('user10@example.com', 'pwd10', '김하늘', 'seoul10', '서울특별시 종로구');

-- ChatRoom 테이블 더미 데이터
INSERT INTO `ChatRoom` (`room_id`, `room_name`, `room_type`, `created_date`) VALUES
('room1', '등산모임1', 'OPEN', '2024-11-01 10:02:33'),
('room2', '등산모임2', 'PRIVATE', '2024-11-01 10:02:33'),
('room3', '등산모임3', 'OPEN', '2024-11-01 10:02:33'),
('room4', '등산모임4', 'PRIVATE', '2024-11-01 10:02:33'),
('room5', '등산모임5', 'OPEN', '2024-11-01 10:02:33'),
('room6', '등산모임6', 'PRIVATE', '2024-11-01 10:02:33'),
('room7', '등산모임7', 'OPEN', '2024-11-01 10:02:33'),
('room8', '등산모임8', 'PRIVATE', '2024-11-01 10:02:33'),
('room9', '등산모임9', 'OPEN', '2024-11-01 10:02:33'),
('room10', '등산모임10', 'PRIVATE', '2024-11-01 10:02:33');

-- Board 테이블 더미 데이터
INSERT INTO `Board` (`user_id`, `content`, `posted_date`) VALUES
(1, '첫 번째 게시글입니다.', '2024-11-01 10:00:00'),
(2, '두 번째 게시글입니다.', '2024-11-02 11:00:00'),
(3, '세 번째 게시글입니다.', '2024-11-03 12:00:00'),
(4, '네 번째 게시글입니다.', '2024-11-04 13:00:00'),
(5, '다섯 번째 게시글입니다.', '2024-11-05 14:00:00'),
(6, '여섯 번째 게시글입니다.', '2024-11-06 15:00:00'),
(7, '일곱 번째 게시글입니다.', '2024-11-07 16:00:00'),
(8, '여덟 번째 게시글입니다.', '2024-11-08 17:00:00'),
(9, '아홉 번째 게시글입니다.', '2024-11-09 18:00:00'),
(10, '열 번째 게시글입니다.', '2024-11-10 19:00:00');

-- Comment 테이블 더미 데이터
INSERT INTO `Comment` (`board_id`, `user_id`, `content`, `posted_date`) VALUES
(1, 2, '첫 번째 댓글입니다.', '2024-11-01 11:00:00'),
(2, 3, '두 번째 댓글입니다.', '2024-11-02 12:00:00'),
(3, 4, '세 번째 댓글입니다.', '2024-11-03 13:00:00'),
(4, 5, '네 번째 댓글입니다.', '2024-11-04 14:00:00'),
(5, 6, '다섯 번째 댓글입니다.', '2024-11-05 15:00:00'),
(6, 7, '여섯 번째 댓글입니다.', '2024-11-06 16:00:00'),
(7, 8, '일곱 번째 댓글입니다.', '2024-11-07 17:00:00'),
(8, 9, '여덟 번째 댓글입니다.', '2024-11-08 18:00:00'),
(9, 10, '아홉 번째 댓글입니다.', '2024-11-09 19:00:00'),
(10, 1, '열 번째 댓글입니다.', '2024-11-10 20:00:00');

-- Party 테이블 더미 데이터
INSERT INTO `Party` (`user_id`, `room_id`, `partied_date`) VALUES
(1, 'room1', '2024-11-01 10:00:00'),
(2, 'room2', '2024-11-02 11:00:00'),
(3, 'room3', '2024-11-03 12:00:00'),
(4, 'room4', '2024-11-04 13:00:00'),
(5, 'room5', '2024-11-05 14:00:00'),
(6, 'room6', '2024-11-06 15:00:00'),
(7, 'room7', '2024-11-07 16:00:00'),
(8, 'room8', '2024-11-08 17:00:00'),
(9, 'room9', '2024-11-09 18:00:00'),
(10, 'room10', '2024-11-10 19:00:00');

-- ChatMessage 테이블 더미 데이터
INSERT INTO `ChatMessage` (`room_id`, `user_id`, `message_type`, `sended_date`, `content`) VALUES
('room1', 1, 'ENTER', '2024-11-01 10:00:00', ''),
('room1', 2, 'ENTER', '2024-11-01 10:01:00', ''),
('room1', 1, 'TALK', '2024-11-01 10:02:00', '안녕하세요.'),
('room2', 2, 'ENTER', '2024-11-02 11:00:00', ''),
('room2', 3, 'TALK', '2024-11-02 11:05:00', '반갑습니다.'),
('room3', 3, 'ENTER', '2024-11-03 12:00:00', ''),
('room3', 4, 'TALK', '2024-11-03 12:05:00', '좋은 날씨네요.'),
('room4', 4, 'ENTER', '2024-11-04 13:00:00', ''),
('room4', 5, 'TALK', '2024-11-04 13:10:00', '모임 기대됩니다.'),
('room5', 6, 'TALK', '2024-11-05 14:00:00', '좋은 하루 되세요.');

-- Like 테이블 더미 데이터
INSERT INTO `Like` (`user_id`, `board_id`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(6, 3),
(7, 4),
(8, 4),
(9, 5),
(10, 5);

-- Following 테이블 더미 데이터
INSERT INTO `Following` (`user_id`, `following_id`, `status`) VALUES
(1, 2, 1),
(1, 3, 1),
(2, 4, 1),
(2, 5, 1),
(3, 6, 1),
(3, 7, 1),
(4, 8, 1),
(4, 9, 1),
(5, 10, 1),
(6, 1, 1);

-- Run 테이블 더미 데이터
INSERT INTO `Run` (`user_id`, `start_time`, `end_time`, `distance`, `run_img`) VALUES
(1, '2024-11-01 06:00:00', '2024-11-01 07:00:00', 5.0, 'https://example.com/run1.jpg'),
(2, '2024-11-02 06:30:00', '2024-11-02 07:15:00', 6.5, 'https://example.com/run2.jpg'),
(3, '2024-11-03 07:00:00', '2024-11-03 08:00:00', 7.2, 'https://example.com/run3.jpg'),
(4, '2024-11-04 07:15:00', '2024-11-04 08:05:00', 8.1, 'https://example.com/run4.jpg'),
(5, '2024-11-05 06:50:00', '2024-11-05 07:40:00', 5.8, 'https://example.com/run5.jpg'),
(6, '2024-11-06 06:00:00', '2024-11-06 06:50:00', 4.6, 'https://example.com/run6.jpg'),
(7, '2024-11-07 06:30:00', '2024-11-07 07:10:00', 6.0, 'https://example.com/run7.jpg'),
(8, '2024-11-08 07:00:00', '2024-11-08 07:45:00', 7.0, 'https://example.com/run8.jpg'),
(9, '2024-11-09 07:15:00', '2024-11-09 08:10:00', 8.5, 'https://example.com/run9.jpg'),
(10, '2024-11-10 06:40:00', '2024-11-10 07:30:00', 5.9, 'https://example.com/run10.jpg');

-- BoardImg 테이블 더미 데이터
INSERT INTO `BoardImg` (`board_id`, `board_url`) VALUES
(1, 'https://example.com/board1_img1.jpg'),
(1, 'https://example.com/board1_img2.jpg'),
(2, 'https://example.com/board2_img1.jpg'),
(2, 'https://example.com/board2_img2.jpg'),
(3, 'https://example.com/board3_img1.jpg'),
(3, 'https://example.com/board3_img2.jpg'),
(4, 'https://example.com/board4_img1.jpg'),
(4, 'https://example.com/board4_img2.jpg'),
(5, 'https://example.com/board5_img1.jpg'),
(5, 'https://example.com/board5_img2.jpg');

-- 데이터 확인
SELECT * FROM `Like`;
SELECT * FROM `Following`;
SELECT * FROM `Run`;
SELECT * FROM `BoardImg`;


-- 데이터 확인
SELECT * FROM `User`;
SELECT * FROM `ChatRoom`;
SELECT * FROM `Party`;
SELECT * FROM `ChatMessage`;
SELECT * FROM `Board`;
SELECT * FROM `Comment`;

