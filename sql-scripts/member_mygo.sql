CREATE DATABASE  IF NOT EXISTS `member_mygo`;
USE `member_mygo`;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `member` VALUES 
	(1,'Sakiko','Togawa','sakiko@crychic.com'),
	(2,'Tomori','Takamatsu','penguin@mygo.com'),
	(3,'Soyo','Nagasaki','soyo@mygo.com'),
	(4,'Mutsumi','Wakaba','mutusmi@crychic.com'),
	(5,'Taki','Shiina','Taki@mygo.com');

