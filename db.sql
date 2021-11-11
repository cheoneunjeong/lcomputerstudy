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
  `b_writer` varchar(255) NOT NULL,
  PRIMARY KEY (`b_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- 테이블 데이터 jung.test:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`b_idx`, `b_title`, `b_content`, `b_date`, `b_writer`) VALUES
	(18, '수정1', 'ㅅㅈㅅㅈ', '2000-10-10 00:00:00', 'fg'),
	(19, 'sdddd', 'dfdddddh', '2000-10-01 00:00:00', 'fasd'),
	(21, 'qqqqqqqqqqqqq', 'qqqqqqqqqqqqqqq', '2000-10-10 00:00:00', ''),
	(23, 'qqqqqqqqq', 'qqqqq', '2000-10-10 00:00:00', ''),
	(24, 'aaaqqq', 'qqqqqqqqq', '2000-10-10 00:00:00', ''),
	(25, 'xxxx', 'xxxxxxx', '2000-10-10 00:00:00', ''),
	(26, 'ccc', 'cccccccc', '2000-10-10 00:00:00', ''),
	(27, 'xxxxxccccccc', 'xxx', '2000-10-10 00:00:00', ''),
	(28, 'qqq', 'qqq', '2000-10-10 00:00:00', '');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
