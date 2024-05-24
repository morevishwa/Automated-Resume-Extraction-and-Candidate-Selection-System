-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.73-community - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for resume_extraction
CREATE DATABASE IF NOT EXISTS `resume_extraction` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `resume_extraction`;


-- Dumping structure for table resume_extraction.jobseeker
CREATE TABLE IF NOT EXISTS `jobseeker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `JobSeeker_id` int(20) NOT NULL,
  `Name` text,
  `Resume_Name` text,
  `Resume_Location` text,
  `Email_ID` text,
  PRIMARY KEY (`id`),
  KEY `FK_jobseeker_jobseeker_registration` (`JobSeeker_id`),
  CONSTRAINT `FK_jobseeker_jobseeker_registration` FOREIGN KEY (`JobSeeker_id`) REFERENCES `jobseeker_registration` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.jobseeker: ~0 rows (approximately)
DELETE FROM `jobseeker`;
/*!40000 ALTER TABLE `jobseeker` DISABLE KEYS */;
INSERT INTO `jobseeker` (`id`, `JobSeeker_id`, `Name`, `Resume_Name`, `Resume_Location`, `Email_ID`) VALUES
	(1, 1, 'Amruta Mohite', 'Amruta_Mohite.doc', 'E:\\Sangram\\My_Doc\\Software\\Diploma\\Resume\\Amruta_Mohite.doc', 'sangrammore11@gmail.com'),
	(2, 2, 'chhabriya sohil', 'chhabriya sohil.doc', 'E:\\Sangram\\My_Doc\\Software\\Diploma\\Resume\\chhabriya sohil.doc', 'sohil8chhabriya@gmail.com');
/*!40000 ALTER TABLE `jobseeker` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.jobseeker_registration
CREATE TABLE IF NOT EXISTS `jobseeker_registration` (
  `id` int(40) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `contact_number` mediumtext,
  `email_id` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `confirmpassword` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.jobseeker_registration: ~1 rows (approximately)
DELETE FROM `jobseeker_registration`;
/*!40000 ALTER TABLE `jobseeker_registration` DISABLE KEYS */;
INSERT INTO `jobseeker_registration` (`id`, `name`, `address`, `contact_number`, `email_id`, `password`, `confirmpassword`) VALUES
	(1, 'Amruta Mohite', 'Pune', '7410258963', 'sangrammore11@gmail.com', '1234', '1234'),
	(2, 'chhabriya sohil', 'Pune', '7894563210', 'sangramsinh.more@flexur.com', '123', '123');
/*!40000 ALTER TABLE `jobseeker_registration` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.keywords
CREATE TABLE IF NOT EXISTS `keywords` (
  `Keyword_id` int(20) NOT NULL,
  `Keyword_type` text,
  `Keyword_desc` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.keywords: ~101 rows (approximately)
DELETE FROM `keywords`;
/*!40000 ALTER TABLE `keywords` DISABLE KEYS */;
INSERT INTO `keywords` (`Keyword_id`, `Keyword_type`, `Keyword_desc`) VALUES
	(1, 'Designation', 'Software Trainee'),
	(2, 'Designation', 'Software Engineer'),
	(3, 'Designation', 'Sr Software Engineer'),
	(4, 'Designation', 'Software Architect'),
	(5, 'Designation', 'Design Engineer'),
	(6, 'Designation', 'Sr Design Engineer'),
	(7, 'Designation', 'Engineer Software Testing'),
	(8, 'Designation', 'Sr Engineer Software Testing'),
	(9, 'Designation', 'Team Lead'),
	(10, 'Designation', 'Test Lead'),
	(11, 'Designation', 'Assistant Project Manager'),
	(12, 'Designation', 'Project Manager'),
	(13, 'Designation', 'Application Developer'),
	(14, 'Designation', 'Sr Application Developer'),
	(15, 'Designation', 'Product Head'),
	(16, 'Designation', 'Software Analyst'),
	(17, 'Designation', 'Lead Analyst'),
	(18, 'Designation', 'Sr Analyst'),
	(19, 'Skill', 'C'),
	(20, 'Skill', 'C++'),
	(21, 'Skill', 'CPP'),
	(22, 'Skill', 'Java'),
	(23, 'Skill', 'J2SE'),
	(24, 'Skill', 'J2ME'),
	(25, 'Skill', 'J2EE'),
	(26, 'Skill', '.NET'),
	(27, 'Skill', 'ASP.NET'),
	(28, 'Skill', 'C#'),
	(29, 'Skill', 'Cognos'),
	(30, 'Skill', 'CSS'),
	(31, 'Skill', 'HTML'),
	(32, 'Skill', 'XML'),
	(33, 'Skill', 'JavaScript'),
	(34, 'Skill', 'Visual Basic'),
	(35, 'Skill', 'Hibernet'),
	(36, 'Skill', 'JSF'),
	(37, 'Skill', 'AJAX'),
	(38, 'Skill', 'LINUX'),
	(39, 'Skill', 'UNIX'),
	(40, 'Skill', 'Windows'),
	(41, 'Skill', 'Oracle'),
	(42, 'Skill', 'PL/SQL'),
	(43, 'Skill', 'MYSQL'),
	(44, 'Skill', 'SQL Server'),
	(45, 'Skill', 'MS Access'),
	(46, 'Skill', 'PostgreSQL'),
	(47, 'Skill', 'jQuery'),
	(48, 'Skill', 'python'),
	(49, 'Skill', 'cobol'),
	(50, 'Skill', 'pascal'),
	(51, 'Skill', 'ubuntu'),
	(52, 'Skill', 'mac os'),
	(53, 'Skill', 'Apache Tomcat'),
	(54, 'Skill', 'Android'),
	(55, 'Skill', 'Iphone'),
	(56, 'Skill', 'Netbeans'),
	(57, 'Skill', 'QTP'),
	(58, 'Skill', 'Automation Testing'),
	(59, 'Skill', 'Manual Testing'),
	(60, 'Skill', 'Struts'),
	(63, 'Skill', 'Perl'),
	(64, 'Skill', 'Spring'),
	(65, 'Skill', 'Eclipse'),
	(66, 'percentage criteria', 'Distinction'),
	(69, 'percentage criteria', 'First Class'),
	(70, 'percentage criteria', 'Higher Second Class'),
	(71, 'percentage criteria', 'Second Class'),
	(72, 'percentage criteria', 'Third Class'),
	(73, 'percentage criteria', 'Pass'),
	(113, 'Keywords', 'Experience'),
	(114, 'Location', 'Ahmedabad'),
	(115, 'Location', 'Bangalore'),
	(116, 'Location', 'Chennai'),
	(117, 'Location', 'Delhi'),
	(118, 'Location', 'Gurgaon'),
	(119, 'Location', 'Hyderabad'),
	(120, 'Location', 'Kolkata'),
	(121, 'Location', 'Mumbai'),
	(122, 'Location', 'Noida'),
	(123, 'Location', 'Pune'),
	(124, 'Location', 'Ahmednagar'),
	(125, 'Location', 'Ajmer'),
	(126, 'Location', 'Aligarh'),
	(127, 'Location', 'Allahabad'),
	(128, 'Location', 'Bhagalpur'),
	(129, 'Location', 'Baroda '),
	(130, 'Location', 'Bhopal '),
	(131, 'Location', 'Bhubaneshwar'),
	(132, 'Location', 'Chandigarh'),
	(133, 'Location', 'Coimbatore'),
	(134, 'Location', 'Goa'),
	(135, 'Location', 'Gwalior'),
	(136, 'Location', 'Indore'),
	(137, 'Location', 'Jabalpur'),
	(138, 'Location', 'Jaipur'),
	(139, 'Location', 'Jammu'),
	(140, 'Location', 'Jamshedpur'),
	(141, 'Location', 'Kolhapur'),
	(142, 'Location', 'Kharagpur'),
	(35, 'Skill', 'Hybernet'),
	(45, 'Skill', 'Microsoft Access');
/*!40000 ALTER TABLE `keywords` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.matching_percent
CREATE TABLE IF NOT EXISTS `matching_percent` (
  `per_id` int(20) NOT NULL AUTO_INCREMENT,
  `JobSeeker_id` int(20) DEFAULT NULL,
  `matching_percent` int(20) DEFAULT NULL,
  PRIMARY KEY (`per_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.matching_percent: ~0 rows (approximately)
DELETE FROM `matching_percent`;
/*!40000 ALTER TABLE `matching_percent` DISABLE KEYS */;
/*!40000 ALTER TABLE `matching_percent` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.recruter
CREATE TABLE IF NOT EXISTS `recruter` (
  `Recruter_Id` int(20) NOT NULL AUTO_INCREMENT,
  `Recruter_Name` text,
  `Password` text,
  PRIMARY KEY (`Recruter_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.recruter: ~3 rows (approximately)
DELETE FROM `recruter`;
/*!40000 ALTER TABLE `recruter` DISABLE KEYS */;
INSERT INTO `recruter` (`Recruter_Id`, `Recruter_Name`, `Password`) VALUES
	(9, 'shivtrivedi1992@gmail.com', '12345678'),
	(10, 'sangrammore11@gmail.com', 'Blindc$11'),
	(11, 'sangrammore11@gmail.com', 'qwerty');
/*!40000 ALTER TABLE `recruter` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.registration
CREATE TABLE IF NOT EXISTS `registration` (
  `registration_id` int(40) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `contact_number` mediumtext,
  `email_id` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `confirmpassword` varchar(50) DEFAULT NULL,
  `Birthdate` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `companyname` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.registration: ~3 rows (approximately)
DELETE FROM `registration`;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` (`registration_id`, `name`, `address`, `contact_number`, `email_id`, `password`, `confirmpassword`, `Birthdate`, `gender`, `companyname`) VALUES
	(9, 'Shiv', 'ABC', '9970987886', 'shivtrivedi1992@gmail.com', '12345678', '12345678', '9/6/1986', 'Male', 'ABC'),
	(10, 'Vishwa', 'Pune', '7709706690', 'sangrammore11@gmail.com', 'Blindc$11', 'Blindc$11', '12/01/1990', 'Male', 'MYWorld'),
	(11, 'a', 'b', '9874563210', 'sangrammore11@gmail.com', 'qwerty', 'qwerty', 'null', 'null', 'null');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.resume
CREATE TABLE IF NOT EXISTS `resume` (
  `extract_id` int(20) NOT NULL AUTO_INCREMENT,
  `JobSeeker_id` int(20) DEFAULT NULL,
  `Keyword_id` int(20) DEFAULT NULL,
  `Description` text,
  `Appearence` int(20) DEFAULT '0',
  PRIMARY KEY (`extract_id`),
  KEY `JobSeeker_id` (`JobSeeker_id`),
  CONSTRAINT `JobSeeker_id` FOREIGN KEY (`JobSeeker_id`) REFERENCES `jobseeker` (`JobSeeker_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.resume: ~0 rows (approximately)
DELETE FROM `resume`;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` (`extract_id`, `JobSeeker_id`, `Keyword_id`, `Description`, `Appearence`) VALUES
	(1, 1, 2, '', 7),
	(2, 1, 19, '', 94),
	(3, 1, 20, '', 3),
	(4, 1, 22, '', 5),
	(5, 1, 26, '', 1),
	(6, 1, 30, '', 2),
	(7, 1, 31, '', 3),
	(8, 1, 32, '', 1),
	(9, 1, 33, '', 3),
	(10, 1, 34, '', 3),
	(11, 1, 38, '', 3),
	(12, 1, 39, '', 2),
	(13, 1, 40, '', 2),
	(14, 1, 41, '', 3),
	(15, 1, 43, '', 7),
	(16, 1, 47, '', 2),
	(17, 1, 51, '', 1),
	(18, 1, 66, '', 1),
	(19, 1, 69, '', 1),
	(20, 1, 73, '', 2),
	(21, 1, 113, '', 3),
	(22, 1, 123, '', 3),
	(23, 2, 4, '', 4),
	(24, 2, 9, '', 1),
	(25, 2, 19, '', 48),
	(26, 2, 20, '', 3),
	(27, 2, 22, '', 4),
	(28, 2, 23, '', 4),
	(29, 2, 25, '', 6),
	(30, 2, 31, '', 1),
	(31, 2, 32, '', 1),
	(32, 2, 33, '', 4),
	(33, 2, 37, '', 3),
	(34, 2, 38, '', 2),
	(35, 2, 40, '', 1),
	(36, 2, 41, '', 6),
	(37, 2, 43, '', 5),
	(38, 2, 47, '', 3),
	(39, 2, 53, '', 1),
	(40, 2, 60, '', 1),
	(41, 2, 65, '', 1),
	(42, 2, 117, '', 1),
	(43, 2, 123, '', 2),
	(44, 2, 35, '', 1);
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
