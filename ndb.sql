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
  `u_idx` int(10) DEFAULT NULL,
  PRIMARY KEY (`b_idx`),
  KEY `u_idx` (`u_idx`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- 테이블 데이터 jung.test:~11 rows (대략적) 내보내기
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`b_idx`, `b_title`, `b_content`, `b_date`, `u_idx`, `b_hit`, `groups`, `orders`, `depth`) VALUES
	(23, 'qqqqqqqqq', 'qqqqq', '2000-10-10 00:00:00', 4, 0, 1, 2, 0),
	(25, 'xxxx', 'xxxxxxx', '2000-10-10 00:00:00', 1, 2, 1, 2, 0),
	(26, 'ccc', 'cccccccc', '2000-10-10 00:00:00', 1, 1, 1, 2, 0),
	(43, '수정', '수정', '2021-11-17 07:41:36', 11, 6, 1, 2, 0),
	(44, 'zssa', 'asaaf', '2021-11-18 08:57:55', 4, 0, 1, 2, 0),
	(45, 'gdsg', 'sdgsdg', '2021-11-18 08:57:59', 4, 2, 1, 2, 0),
	(46, 'wetw34t', 'ttwewet', '2021-11-18 08:58:05', 4, 2, 11, 2, 0),
	(47, '수정', 'ㅁㅁ', '2021-11-18 08:58:54', 11, 8, 1, 2, 0),
	(48, '확인', '확인', '2021-11-18 10:18:06', 11, 5, 48, 2, 0),
	(49, 'ee', 'ee', '2021-11-18 10:37:48', 11, 1, 49, 2, 0),
	(50, 'adf', 'af', '2021-11-18 10:38:19', 11, 0, 50, 2, 0),
	(53, '글쓰기', '글', '2021-11-18 11:18:50', 11, 3, 53, 1, 0),
	(55, '답글', '답', '2021-11-18 11:21:46', 11, 0, 53, 1, 1),
	(57, '방금글', 'dfg', '2021-11-18 00:00:00', 1, 0, 22, 1, 0),
	(58, '새글', 'ㄴ', '2021-11-18 11:39:39', 11, 2, 58, 58, 0),
	(59, '새댓글', 'ㄷ', '2021-11-18 11:39:58', 11, 2, 58, 61, 1),
	(60, '두번째 댓글', 'ㅇㅇ', '2021-11-18 11:44:26', 11, 0, 58, 60, 1),
	(61, '새댓글의댓글', 'ㅇㅇ', '2021-11-18 11:45:13', 11, 0, 58, 61, 2),
	(62, '그냥 글', 'ㅇㅇ', '2021-11-18 11:59:42', 11, 0, 62, 62, 0),
	(63, '1글', 'ㄴㄴ', '2021-11-18 12:03:14', 11, 5, 63, 63, 0),
	(67, '1글의 댓1', 'ㅇㅇ', '2021-11-18 12:08:54', 11, 0, 63, 65, 1),
	(68, '1글의 댓2', 'ㅇㅇ', '2021-11-18 12:09:05', 11, 0, 63, 64, 1),
	(69, '2글', 'ㄴㄴ', '2021-11-18 12:09:45', 11, 0, 69, 69, 0);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

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
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
