-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.6.4-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- jung 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `jung` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `jung`;

-- 테이블 jung.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `b_idx` int(10) NOT NULL AUTO_INCREMENT,
  `b_title` varchar(255) NOT NULL,
  `b_content` varchar(255) NOT NULL,
  `b_date` varchar(255) NOT NULL,
  `b_writer` varchar(255) NOT NULL,
  `u_idx` int(10) DEFAULT NULL,
  PRIMARY KEY (`b_idx`),
  KEY `u_idx` (`u_idx`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 jung.board:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` (`b_idx`, `b_title`, `b_content`, `b_date`, `b_writer`, `u_idx`) VALUES
	(1, '제목', '내용입니다.', '2021-11-09', '작성자', 1);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 jung.test 구조 내보내기
CREATE TABLE IF NOT EXISTS `test` (
  `b_idx` int(10) NOT NULL AUTO_INCREMENT,
  `b_title` varchar(255) NOT NULL,
  `b_content` varchar(255) NOT NULL,
  `b_date` datetime NOT NULL,
  `u_idx` int(10) NOT NULL,
  `b_hit` int(10) NOT NULL DEFAULT 0,
  `groups` int(11) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `depth` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`b_idx`),
  KEY `u_idx` (`u_idx`),
  CONSTRAINT `test_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 jung.test:~19 rows (대략적) 내보내기
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`b_idx`, `b_title`, `b_content`, `b_date`, `u_idx`, `b_hit`, `groups`, `orders`, `depth`) VALUES
	(99, '1', 'l', '2021-11-19 09:59:04', 11, 3, 99, 1, 0),
	(100, '3', 'ㅇㅇㅇ', '2021-11-19 05:22:50', 11, 44, 100, 1, 0),
	(101, '1-1', ';', '2021-11-19 09:59:15', 11, 3, 99, 5, 1),
	(102, '1-2', 'p0', '2021-11-19 09:59:22', 11, 2, 99, 3, 1),
	(103, '1-1 /1', 'jnj', '2021-11-19 10:06:22', 11, 0, 99, 9, 2),
	(104, '1-1 / 2', '[[', '2021-11-19 10:06:32', 11, 3, 99, 6, 2),
	(105, '112 -1', '[[', '2021-11-19 10:06:55', 11, 0, 99, 8, 3),
	(106, '112 -2', '[[', '2021-11-19 10:07:09', 11, 0, 99, 7, 3),
	(107, '2-1', '[', '2021-11-19 10:07:23', 11, 1, 100, 2, 1),
	(108, '1-3', '[[', '2021-11-19 10:07:34', 11, 2, 99, 2, 1),
	(109, '1-2 /1', '[[', '2021-11-19 10:07:57', 11, 0, 99, 4, 2),
	(113, '글', 'ㄹㄹㄹ', '2021-11-19 05:21:23', 11, 1, 113, 1, 0),
	(114, '글2', 'ㅇㅇㅇ', '2021-11-19 05:21:32', 11, 4, 114, 1, 0),
	(115, '답글', 'ㄴㄴㄴ', '2021-11-19 05:21:44', 11, 1, 113, 2, 1),
	(116, '수정', 'ㅇㅇ', '2021-11-19 05:22:16', 11, 3, 113, 3, 2),
	(117, 'tt', 'tt', '2021-11-19 07:45:40', 11, 3, 117, 1, 0),
	(118, 'dd', 'dd', '2021-11-20 10:34:31', 11, 1, 118, 1, 0),
	(119, 'ff', 'ff', '2021-11-20 10:38:15', 11, 429, 119, 1, 0),
	(120, 'fff', 'fff', '2021-11-20 10:38:23', 11, 1, 119, 2, 1);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;

-- 테이블 jung.test_reply 구조 내보내기
CREATE TABLE IF NOT EXISTS `test_reply` (
  `c_num` int(10) NOT NULL AUTO_INCREMENT,
  `b_idx` int(11) NOT NULL DEFAULT 0,
  `u_idx` int(11) NOT NULL DEFAULT 0,
  `c_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `c_content` varchar(255) NOT NULL,
  `groups` int(11) NOT NULL DEFAULT 0,
  `orders` int(11) NOT NULL DEFAULT 0,
  `depth` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`c_num`),
  KEY `u_idx` (`u_idx`),
  KEY `b_idx` (`b_idx`),
  CONSTRAINT `test_reply_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`),
  CONSTRAINT `test_reply_ibfk_2` FOREIGN KEY (`b_idx`) REFERENCES `test` (`b_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 jung.test_reply:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `test_reply` DISABLE KEYS */;
INSERT INTO `test_reply` (`c_num`, `b_idx`, `u_idx`, `c_date`, `c_content`, `groups`, `orders`, `depth`) VALUES
	(125, 119, 11, '2021-11-24 02:54:26', '1', 125, 1, 0),
	(126, 119, 11, '2021-11-24 02:54:30', '1-1', 125, 9, 1),
	(127, 119, 11, '2021-11-24 02:54:35', '1-2', 125, 3, 1),
	(128, 119, 11, '2021-11-24 02:54:38', '1-3', 125, 2, 1),
	(129, 119, 11, '2021-11-24 02:54:42', '12-1', 125, 8, 2),
	(130, 119, 11, '2021-11-24 02:54:51', '12-2', 125, 4, 2),
	(131, 119, 11, '2021-11-24 02:54:58', '122-1', 125, 7, 3),
	(132, 119, 11, '2021-11-24 02:55:03', '122-2', 125, 6, 3),
	(133, 119, 11, '2021-11-24 03:13:31', '122-3', 125, 5, 3);
/*!40000 ALTER TABLE `test_reply` ENABLE KEYS */;

-- 테이블 jung.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `u_idx` int(10) NOT NULL AUTO_INCREMENT,
  `u_id` varchar(255) NOT NULL,
  `u_pw` varchar(255) NOT NULL,
  `u_name` varchar(255) NOT NULL,
  `u_tel` varchar(255) NOT NULL,
  `u_age` varchar(255) NOT NULL,
  `manager` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`u_idx`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 jung.user:~19 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`u_idx`, `u_id`, `u_pw`, `u_name`, `u_tel`, `u_age`, `manager`) VALUES
	(1, 'abc', '1234', 'NAME1', '010-0000-0000', '50', 1),
	(3, 'rlaEodEOd', '111', '김땡땡', '010-1111-1111', '20', 1),
	(4, 'b', '222', '이모씨', '010-2222-2222', '37', 1),
	(5, 'ccc', '34343', '강하다', '010-2121-3232', '58', 0),
	(6, 'dfdf', '545', '홍길동', '010-2898-4767', '44', 0),
	(7, 'zyzy', '252d', '대한민국', '010-4747-3634', '88', 0),
	(8, 'a1234', 'bbbb', '만세', '010-8487-7978', '69', 0),
	(9, 'baba', 'cfdfd', '바비', '010-8787-1111', '56', 0),
	(10, 'wew', 'qqqq', '박찬호', '010-7777-3232', '50', 0),
	(11, 'a1', '5555', '강호동', '090-4343-4444', '32', 0),
	(12, 'b1', '1111', '박길동', '090-3333-7777', '49', 0),
	(13, 'bb1', '2222', '고길돌', '090-4444-6666', '61', 0),
	(16, 'gbhuj', '1234', 'ok', '1-1-1', '11', 0),
	(18, 'abcd', 'efg', 'abcd', '010-1234-1234', '30', 0),
	(20, 'ttt', 'zxczxc', 'ttt', '010-7894-1412', '30', 0),
	(21, 'zzxx', 'zzxx', 'zzxx', '010-1111-2222', '11', 0),
	(22, 'gg', 'aa', 'aa', 'a-a-a', 'aa', 0),
	(23, 'zxc', 'zxc', 'zxc', 'zxc-zxc-zxc', 'zxc', 0),
	(24, 'ㅇㅇㅇ', '123123', 'ddd', '1231-1231-1231', '12', 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
