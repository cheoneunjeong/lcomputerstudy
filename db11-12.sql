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

-- 테이블 jung.test 구조 내보내기
CREATE TABLE IF NOT EXISTS `test` (
  `b_idx` int(10) NOT NULL AUTO_INCREMENT,
  `b_title` varchar(255) NOT NULL,
  `b_content` varchar(255) NOT NULL,
  `b_date` datetime NOT NULL,
  `u_idx` int(10) NOT NULL,
  `b_hit` int(10) NOT NULL DEFAULT 0,
  PRIMARY KEY (`b_idx`),
  KEY `u_idx` (`u_idx`),
  CONSTRAINT `test_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- 테이블 데이터 jung.test:~12 rows (대략적) 내보내기
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`b_idx`, `b_title`, `b_content`, `b_date`, `u_idx`, `b_hit`) VALUES
	(21, 'qqqqqqqqqqqqq', 'qqqqqqqqqqqqqqq', '2000-10-10 00:00:00', 8, 0),
	(23, 'qqqqqqqqq', 'qqqqq', '2000-10-10 00:00:00', 4, 0),
	(24, 'aaaqqq', 'qqqqqqqqq', '2000-10-10 00:00:00', 11, 0),
	(25, 'xxxx', 'xxxxxxx', '2000-10-10 00:00:00', 1, 0),
	(26, 'ccc', 'cccccccc', '2000-10-10 00:00:00', 1, 0),
	(27, 'xxxxxccccccc', 'xxx', '2000-10-10 00:00:00', 9, 0),
	(28, 'qqq', 'qqq', '2000-10-10 00:00:00', 32, 0),
	(31, 'rtrrt', 'rtrtre', '2021-11-12 09:03:55', 16, 0),
	(32, 'ㅍㅍ', 'ㅍㅌㅇㅊ', '2021-11-12 09:14:23', 35, 0),
	(33, 'ftyu', 'fty', '2021-11-12 10:26:42', 11, 0),
	(38, 'rr', 'rr', '2021-11-12 11:02:49', 11, 0),
	(39, 'aa', 'aaa', '2021-11-12 11:05:02', 11, 0);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
