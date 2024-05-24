-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.0.45-community-nt - MySQL Community Edition (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2018-03-31 13:20:16
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for resume_extraction
DROP DATABASE IF EXISTS `resume_extraction`;
CREATE DATABASE IF NOT EXISTS `resume_extraction` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `resume_extraction`;


-- Dumping structure for table resume_extraction.jobseeker
DROP TABLE IF EXISTS `jobseeker`;
CREATE TABLE IF NOT EXISTS `jobseeker` (
  `id` int(11) NOT NULL auto_increment,
  `JobSeeker_id` int(20) NOT NULL,
  `Name` text,
  `Resume_Name` text,
  `Resume_Location` text,
  `Email_ID` text,
  PRIMARY KEY  (`id`),
  KEY `FK_jobseeker_jobseeker_registration` (`JobSeeker_id`),
  CONSTRAINT `FK_jobseeker_jobseeker_registration` FOREIGN KEY (`JobSeeker_id`) REFERENCES `jobseeker_registration` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.jobseeker: ~7 rows (approximately)
DELETE FROM `jobseeker`;
/*!40000 ALTER TABLE `jobseeker` DISABLE KEYS */;
INSERT INTO `jobseeker` (`id`, `JobSeeker_id`, `Name`, `Resume_Name`, `Resume_Location`, `Email_ID`) VALUES
	(2, 2, 'aniket', 'Amruta_Mohite.doc', 'F:\\Vishwa-Project\\Complete Project Data\\Resume\\Amruta_Mohite.doc', 'aniket389sutar@gmail.com'),
	(3, 3, 'nilesh', 'Archana_Bansode.docx', 'F:\\Vishwa-Project\\Complete Project Data\\Resume\\Archana_Bansode.docx', 'nil.mistary@gmail.com'),
	(4, 4, 'tanvi', 'chhabriya_sohil.doc', 'F:\\Vishwa-Project\\Complete Project Data\\Resume\\chhabriya_sohil.doc', 'tdhewade@gmail.com'),
	(5, 5, 'snehal', 'parth_Vaghela.doc', 'F:\\Vishwa-Project\\Complete Project Data\\Resume\\parth_Vaghela.doc', 'malisnehal024@gmail.com'),
	(6, 6, 'vicky', 'Suhas_Vishwanath.doc', 'F:\\Vishwa-Project\\Complete Project Data\\Resume\\Suhas_Vishwanath.doc', 'vickykadam786.vk@gmail.com'),
	(7, 9, 'Anjali', 'Anjali.doc', 'F:\\Vishwa-Project\\Complete Project Data\\Resume\\Anjali.doc', 'dipalijgtap24@gmail.com'),
	(8, 10, 'charul', 'bhadke.docx', 'F:\\Vishwa-Project\\Complete Project Data\\Resume\\bhadke.docx', 'bhadkecharul222@gmail.com'),
	(9, 11, 'Tinku', 'vicky.docx', 'F:\\Vishwa-Project\\Complete Project Data\\Resume\\vicky.docx', 'aniket398sutar@gmail.com');
/*!40000 ALTER TABLE `jobseeker` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.jobseeker_registration
DROP TABLE IF EXISTS `jobseeker_registration`;
CREATE TABLE IF NOT EXISTS `jobseeker_registration` (
  `id` int(40) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `address` varchar(50) default NULL,
  `contact_number` mediumtext,
  `email_id` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `confirmpassword` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.jobseeker_registration: ~8 rows (approximately)
DELETE FROM `jobseeker_registration`;
/*!40000 ALTER TABLE `jobseeker_registration` DISABLE KEYS */;
INSERT INTO `jobseeker_registration` (`id`, `name`, `address`, `contact_number`, `email_id`, `password`, `confirmpassword`) VALUES
	(2, 'aniket', 'chinchwad', '8446980118', 'aniket389sutar@gmail.com', '97660874', '976605874'),
	(3, 'nilesh', 'pune', '9623845046', 'nil.mistary@gmail.com', '66626662', '66626662'),
	(4, 'tanvi', 'bhosari', '9823673836', 'tdhewade@gmail.com', '19991997', '19991997'),
	(5, 'snehal', 'pune', '7378553975', 'malisnehal024@gmail.com', '987654321snehal', '987654321snehal'),
	(6, 'vicky', 'pune', '9595410304', 'vickykadam786.vk@gmail.com', '12341234', '12341234'),
	(9, 'Anjali', 'pune', '8985641562', 'dipalijgtap24@gmail.com', '5050', '5050'),
	(10, 'charul', 'chinchwad', '8956485694', 'bhadkecharul222@gmail.com', '5050', '5050'),
	(11, 'Tinku', 'pune', '852641296241', 'aniket398sutar@gmail.com', '5050', '5050');
/*!40000 ALTER TABLE `jobseeker_registration` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.keywords
DROP TABLE IF EXISTS `keywords`;
CREATE TABLE IF NOT EXISTS `keywords` (
  `Keyword_id` int(20) NOT NULL,
  `Keyword_type` text,
  `Keyword_desc` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.keywords: ~106 rows (approximately)
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
	(45, 'Skill', 'Microsoft Access'),
	(143, 'Location', 'Karad'),
	(144, 'Location', 'kolhapur'),
	(145, 'Location', 'kopargaon'),
	(146, 'Skill', 'SWIFT'),
	(147, 'Location', 'nigdi');
/*!40000 ALTER TABLE `keywords` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.matching_percent
DROP TABLE IF EXISTS `matching_percent`;
CREATE TABLE IF NOT EXISTS `matching_percent` (
  `per_id` int(20) NOT NULL auto_increment,
  `JobSeeker_id` int(20) default NULL,
  `matching_percent` int(20) default NULL,
  PRIMARY KEY  (`per_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.matching_percent: ~0 rows (approximately)
DELETE FROM `matching_percent`;
/*!40000 ALTER TABLE `matching_percent` DISABLE KEYS */;
/*!40000 ALTER TABLE `matching_percent` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.recruter
DROP TABLE IF EXISTS `recruter`;
CREATE TABLE IF NOT EXISTS `recruter` (
  `Recruter_Id` int(20) NOT NULL auto_increment,
  `Recruter_Name` text,
  `Password` text,
  PRIMARY KEY  (`Recruter_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.recruter: ~1 rows (approximately)
DELETE FROM `recruter`;
/*!40000 ALTER TABLE `recruter` DISABLE KEYS */;
INSERT INTO `recruter` (`Recruter_Id`, `Recruter_Name`, `Password`) VALUES
	(1, 'morevishwa9909@gmail.com', '99099909');
/*!40000 ALTER TABLE `recruter` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.registration
DROP TABLE IF EXISTS `registration`;
CREATE TABLE IF NOT EXISTS `registration` (
  `registration_id` int(40) default NULL,
  `name` varchar(50) default NULL,
  `address` varchar(50) default NULL,
  `contact_number` mediumtext,
  `email_id` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `confirmpassword` varchar(50) default NULL,
  `Birthdate` varchar(50) default NULL,
  `gender` varchar(50) default NULL,
  `companyname` varchar(50) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.registration: ~1 rows (approximately)
DELETE FROM `registration`;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` (`registration_id`, `name`, `address`, `contact_number`, `email_id`, `password`, `confirmpassword`, `Birthdate`, `gender`, `companyname`) VALUES
	(1, 'Vishwa', 'Karad', '7485961230', 'morevishwa9909@gmail.com', '99099909', '99099909', '01/28/1998', 'Male', 'Vishwa Soft pvt.ltd.');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;


-- Dumping structure for table resume_extraction.resume
DROP TABLE IF EXISTS `resume`;
CREATE TABLE IF NOT EXISTS `resume` (
  `extract_id` int(20) NOT NULL auto_increment,
  `JobSeeker_id` int(20) default NULL,
  `Keyword_id` int(20) default NULL,
  `Description` text,
  `Appearence` int(20) default '0',
  PRIMARY KEY  (`extract_id`),
  KEY `JobSeeker_id` (`JobSeeker_id`),
  CONSTRAINT `JobSeeker_id` FOREIGN KEY (`JobSeeker_id`) REFERENCES `jobseeker` (`JobSeeker_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=latin1;

-- Dumping data for table resume_extraction.resume: ~95 rows (approximately)
DELETE FROM `resume`;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` (`extract_id`, `JobSeeker_id`, `Keyword_id`, `Description`, `Appearence`) VALUES
	(60, 2, 2, '', 7),
	(61, 2, 19, '', 94),
	(62, 2, 20, '', 3),
	(63, 2, 22, '', 5),
	(64, 2, 26, '', 1),
	(65, 2, 30, '', 2),
	(66, 2, 31, '', 3),
	(67, 2, 32, '', 1),
	(68, 2, 33, '', 3),
	(69, 2, 34, '', 3),
	(70, 2, 38, '', 3),
	(71, 2, 39, '', 2),
	(72, 2, 40, '', 2),
	(73, 2, 41, '', 3),
	(74, 2, 43, '', 7),
	(75, 2, 47, '', 2),
	(76, 2, 51, '', 1),
	(77, 2, 66, '', 1),
	(78, 2, 69, '', 1),
	(79, 2, 73, '', 2),
	(80, 2, 113, '', 3),
	(81, 2, 123, '', 3),
	(82, 3, 19, '', 1),
	(83, 3, 20, '', 1),
	(84, 3, 22, '', 1),
	(85, 3, 40, '', 1),
	(86, 3, 66, '', 1),
	(87, 3, 69, '', 1),
	(88, 3, 123, '', 1),
	(89, 4, 4, '', 4),
	(90, 4, 9, '', 1),
	(91, 4, 19, '', 48),
	(92, 4, 20, '', 3),
	(93, 4, 22, '', 4),
	(94, 4, 23, '', 4),
	(95, 4, 25, '', 6),
	(96, 4, 31, '', 1),
	(97, 4, 32, '', 1),
	(98, 4, 33, '', 4),
	(99, 4, 37, '', 3),
	(100, 4, 38, '', 2),
	(101, 4, 40, '', 1),
	(102, 4, 41, '', 6),
	(103, 4, 43, '', 5),
	(104, 4, 47, '', 3),
	(105, 4, 53, '', 1),
	(106, 4, 60, '', 1),
	(107, 4, 65, '', 1),
	(108, 4, 117, '', 1),
	(109, 4, 123, '', 2),
	(110, 4, 35, '', 1),
	(111, 5, 19, '', 34),
	(112, 5, 66, '', 1),
	(113, 5, 73, '', 1),
	(121, 6, 19, '', 19),
	(122, 6, 20, '', 1),
	(123, 6, 26, '', 4),
	(124, 6, 27, '', 4),
	(125, 6, 28, '', 2),
	(126, 6, 40, '', 2),
	(127, 6, 44, '', 1),
	(128, 6, 121, '', 4),
	(129, 6, 123, '', 1),
	(130, 9, 19, '', 35),
	(131, 9, 22, '', 3),
	(132, 9, 26, '', 2),
	(133, 9, 27, '', 1),
	(134, 9, 28, '', 2),
	(135, 9, 30, '', 2),
	(136, 9, 31, '', 3),
	(137, 9, 32, '', 1),
	(138, 9, 33, '', 3),
	(139, 9, 44, '', 3),
	(140, 9, 47, '', 1),
	(141, 9, 63, '', 1),
	(142, 9, 113, '', 4),
	(143, 9, 123, '', 4),
	(144, 10, 19, '', 1),
	(145, 10, 22, '', 1),
	(146, 10, 26, '', 1),
	(147, 10, 28, '', 1),
	(148, 10, 30, '', 1),
	(149, 10, 31, '', 1),
	(150, 10, 33, '', 1),
	(151, 10, 40, '', 1),
	(152, 10, 41, '', 1),
	(153, 10, 43, '', 1),
	(154, 10, 44, '', 1),
	(155, 10, 58, '', 1),
	(156, 10, 59, '', 1),
	(157, 10, 66, '', 1),
	(158, 10, 69, '', 1),
	(159, 10, 73, '', 1),
	(160, 10, 113, '', 1),
	(161, 10, 123, '', 1),
	(170, 11, 19, '', 1),
	(171, 11, 20, '', 1),
	(172, 11, 22, '', 1),
	(173, 11, 34, '', 1),
	(174, 11, 54, '', 1),
	(175, 11, 65, '', 1),
	(176, 11, 73, '', 1),
	(177, 11, 123, '', 1);
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
