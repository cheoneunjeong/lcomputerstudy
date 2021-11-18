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
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 jung.test:~23 rows (대략적) 내보내기
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
	(69, '2글', 'ㄴㄴ', '2021-11-18 12:09:45', 11, 2, 69, 69, 0),
	(70, '2글의 답글', 'ㄷ', '2021-11-18 05:22:06', 11, 0, 69, 70, 1),
	(71, 'ㅇㅇㅇ', 'ㅇㅇㅇ', '2021-11-18 05:44:49', 4, 2, 71, 71, 0),
	(72, 'ㅇㄱㄱ', 'ㄱㄱㄱ', '2021-11-18 05:45:01', 4, 1, 71, 74, 1),
	(73, 'ㅇㄱㄱㄱㄷㄷ', 'ㅇㅇ', '2021-11-18 05:45:13', 4, 0, 71, 74, 2),
	(74, 'ㅇㄴ', 'ㅇㄴ', '2021-11-18 05:45:22', 4, 0, 71, 72, 1);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
