-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.8-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


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
  `u_idx` int(10) NOT NULL,
  PRIMARY KEY (`b_idx`),
  KEY `u_idx` (`u_idx`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 테이블 데이터 jung.board:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` (`b_idx`, `b_title`, `b_content`, `b_date`, `b_writer`, `u_idx`) VALUES
	(1, '제목', '내용입니다.', '2021-11-09', '작성자', 1);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 jung.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `u_idx` int(10) NOT NULL AUTO_INCREMENT,
  `u_id` varchar(255) NOT NULL,
  `u_pw` varchar(255) NOT NULL,
  `u_name` varchar(255) NOT NULL,
  `u_tel` varchar(255) NOT NULL,
  `u_age` varchar(255) NOT NULL,
  PRIMARY KEY (`u_idx`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- 테이블 데이터 jung.user:~31 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`u_idx`, `u_id`, `u_pw`, `u_name`, `u_tel`, `u_age`) VALUES
	(1, 'abc', '1234', 'NAME1', '010-0000-0000', '50'),
	(3, 'rlaEodEOd', '111', '김땡땡', '010-1111-1111', '20'),
	(4, 'b', '222', '이모씨', '010-2222-2222', '37'),
	(5, 'ccc', '34343', '강하다', '010-2121-3232', '58'),
	(6, 'dfdf', '545', '홍길동', '010-2898-4767', '44'),
	(7, 'zyzy', '252d', '대한민국', '010-4747-3634', '88'),
	(8, 'a1234', 'bbbb', '만세', '010-8487-7978', '69'),
	(9, 'baba', 'cfdfd', '바비', '010-8787-1111', '56'),
	(10, 'wew', 'qqqq', '박찬호', '010-7777-3232', '50'),
	(11, 'a1', '5555', '강호동', '090-4343-4444', '32'),
	(12, 'b1', '1111', '박길동', '090-3333-7777', '49'),
	(13, 'bb1', '2222', '고길돌', '090-4444-6666', '61'),
	(16, 'gbhuj', '1234', 'ok', '1-1-1', '11'),
	(18, 'abcd', 'efg', 'abcd', '010-1234-1234', '30'),
	(20, 'ttt', 'zxczxc', 'ttt', '010-7894-1412', '30'),
	(21, 'zzxx', 'zzxx', 'zzxx', '010-1111-2222', '11'),
	(22, 'gg', 'aa', 'aa', 'a-a-a', 'aa'),
	(23, 'zxc', 'zxc', 'zxc', 'zxc-zxc-zxc', 'zxc'),
	(24, 'vvv', 'dsf', 'awsr', '010-1233-1233', '22'),
	(25, 'mmm', 'zxczxdc', 'qqaa', '010-3333-1233', '14'),
	(26, 'mmm11', 'zxczxdcasd', 'qqaa1', '010-3333-4444', '50'),
	(27, 'mmm11', 'zxczxdcasd', 'qqaa1', '010-3333-4444', '2'),
	(28, 'ggff', 'zxczxdcasd', 'fff', '010-3333-4444', '22'),
	(29, 'ggff', 'zxczxdcasd', 'fffz', '010-3333-4444', '22'),
	(30, 'ggffzx', 'zxczxdcassd', 'fffz', '010-3333-4444', '26'),
	(31, 'dfhdf', 'zxczxdcassd', 'dfh', '010-9009-7777', '26'),
	(32, 'eryuy', 'dftr', 'rtui', '010-8888-7777', '44'),
	(33, 'nnn', 'dftr', 'nnn', '010-8883-7222', '55'),
	(34, 'asd', 'dftr', 'zxc', '010-1312-1233', '13'),
	(35, 'yy5', '5678', 'ccc', '010-1312-1233', '9'),
	(36, 'www1', '5678', 'wwxx', '010-4444-3333', '40');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
