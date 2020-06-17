-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2020 at 05:38 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `doctz_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment_tb`
--

CREATE TABLE IF NOT EXISTS `appointment_tb` (
  `appointmentId` int(10) NOT NULL AUTO_INCREMENT,
  `doctorId` int(10) NOT NULL,
  `patientId` int(10) NOT NULL,
  `hospitalId` int(10) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `invoice` text,
  `status` varchar(100) NOT NULL DEFAULT 'pending',
  `isActive` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`appointmentId`),
  KEY `doctorId` (`doctorId`),
  KEY `patientId` (`patientId`),
  KEY `hospitalId` (`hospitalId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `appointment_tb`
--

INSERT INTO `appointment_tb` (`appointmentId`, `doctorId`, `patientId`, `hospitalId`, `date`, `time`, `invoice`, `status`, `isActive`) VALUES
(1, 5, 12, 5, '2020-06-15', '02:00:00', NULL, 'complete', 1),
(5, 5, 12, 8, '2020-07-01', '10:00:00', NULL, 'complete', 1),
(6, 4, 12, 7, '2020-04-08', '10:00:00', NULL, 'complete', 1),
(7, 10, 12, 5, '2020-05-20', '02:00:00', NULL, 'complete', 1),
(8, 4, 12, 6, '2020-06-25', '02:00:00', NULL, 'pending', 1),
(9, 5, 12, 5, '2020-06-17', '10:00:00', NULL, 'pending', 1);

-- --------------------------------------------------------

--
-- Table structure for table `area_tb`
--

CREATE TABLE IF NOT EXISTS `area_tb` (
  `areaId` int(10) NOT NULL AUTO_INCREMENT,
  `areaName` varchar(100) NOT NULL,
  `cityId` int(10) NOT NULL,
  `pincode` int(10) NOT NULL,
  `isActive` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`areaId`),
  KEY `cityId` (`cityId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `area_tb`
--

INSERT INTO `area_tb` (`areaId`, `areaName`, `cityId`, `pincode`, `isActive`) VALUES
(1, 'Adajan', 1, 395009, 1),
(2, 'Pal', 1, 394510, 1),
(3, 'CityLight', 1, 395007, 1),
(4, 'Althan', 1, 395017, 1),
(5, 'Bhagal', 1, 395003, 1),
(6, 'Varachha', 1, 395006, 1),
(7, 'Ring Road', 1, 395002, 1);

-- --------------------------------------------------------

--
-- Table structure for table `city_tb`
--

CREATE TABLE IF NOT EXISTS `city_tb` (
  `cityId` int(10) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(100) NOT NULL,
  `stateId` int(10) NOT NULL,
  `isActive` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cityId`),
  KEY `stateId` (`stateId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `city_tb`
--

INSERT INTO `city_tb` (`cityId`, `cityName`, `stateId`, `isActive`) VALUES
(1, 'Surat', 1, 1),
(2, 'Baroda', 1, 1),
(8, 'Ahemdabad', 1, 1),
(9, 'Bhavnagar', 1, 1),
(10, 'Kutchh', 1, 1),
(11, 'Vadodara', 1, 1),
(12, 'Mumbai', 2, 1),
(13, 'Pune', 2, 1),
(14, 'Udaipur', 3, 1),
(15, 'Jaipur', 3, 1),
(16, 'Raighad', 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `doctor_attachment_tb`
--

CREATE TABLE IF NOT EXISTS `doctor_attachment_tb` (
  `attachmentId` int(10) NOT NULL AUTO_INCREMENT,
  `doctorId` int(10) NOT NULL,
  `patientId` int(10) NOT NULL,
  `attachment` text NOT NULL,
  PRIMARY KEY (`attachmentId`),
  KEY `doctorId` (`doctorId`),
  KEY `patientId` (`patientId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `doctor_attachment_tb`
--

INSERT INTO `doctor_attachment_tb` (`attachmentId`, `doctorId`, `patientId`, `attachment`) VALUES
(1, 5, 12, 'resources/img/docAttachment/Sem_6_Syllabus.pdf'),
(2, 4, 12, 'resources/img/docAttachment/unit3_part1.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_schedule_tb`
--

CREATE TABLE IF NOT EXISTS `doctor_schedule_tb` (
  `scheduleId` int(10) NOT NULL AUTO_INCREMENT,
  `hospitalId` int(10) NOT NULL,
  `doctorId` int(10) NOT NULL,
  `date` date NOT NULL,
  `fromTime` time NOT NULL,
  `toTime` time NOT NULL,
  `numberOfPatient` int(10) NOT NULL,
  `isActive` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`scheduleId`),
  KEY `hospitalId` (`hospitalId`),
  KEY `doctorId` (`doctorId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `doctor_schedule_tb`
--

INSERT INTO `doctor_schedule_tb` (`scheduleId`, `hospitalId`, `doctorId`, `date`, `fromTime`, `toTime`, `numberOfPatient`, `isActive`) VALUES
(1, 5, 4, '2020-06-20', '09:00:00', '10:00:00', 10, 1),
(2, 5, 4, '2020-06-20', '02:00:00', '04:00:00', 10, 1),
(4, 5, 4, '2020-06-20', '08:00:00', '09:00:00', 15, 1),
(5, 5, 4, '2020-06-20', '04:00:00', '05:00:00', 6, 1),
(6, 5, 4, '2020-06-15', '02:00:00', '04:00:00', 9, 1),
(7, 5, 5, '2020-06-20', '09:00:00', '10:00:00', 10, 1),
(8, 5, 9, '2020-06-16', '02:00:00', '04:00:00', 25, 1);

-- --------------------------------------------------------

--
-- Table structure for table `doctor_tb`
--

CREATE TABLE IF NOT EXISTS `doctor_tb` (
  `doctorId` int(10) NOT NULL AUTO_INCREMENT,
  `doctorName` varchar(100) NOT NULL,
  `specializationId` int(11) NOT NULL,
  `experience` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `userId` int(10) NOT NULL,
  `certificates` text NOT NULL,
  `education` varchar(100) NOT NULL,
  `profile` text NOT NULL,
  `documents` text NOT NULL,
  `isActive` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`doctorId`),
  KEY `specializationId` (`specializationId`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `doctor_tb`
--

INSERT INTO `doctor_tb` (`doctorId`, `doctorName`, `specializationId`, `experience`, `gender`, `userId`, `certificates`, `education`, `profile`, `documents`, `isActive`) VALUES
(4, 'Dr. Bhumik Patel\r\n', 7, '11 Years', 'Male', 26, 'Medical Registration Verified,\r\nDentist, Dental Surgeon, Implantologist', 'BDS', 'resources/img/doctors/bhumik.jpg', '', 1),
(5, 'Dr. Kaushika Anant Patel', 8, '21 Years', 'Female', 27, 'Gynecologist, Obstetrician, Infertility Specialist', 'MBBS,DGO', 'resources/img/doctors/kaushika.jpeg', '', 1),
(6, 'Dr. Kajal Mangukiya', 8, '13 Years', 'Female', 28, 'Gynecologist, Infertility Specialist, Obstetrician', ']MBBS , MD', 'resources/img/doctors/kajal.jpg', '', 1),
(7, 'Dr. Ushma K Kakkad', 7, '10 Years', 'Female', 29, 'Dentist, Implantologist, Cosmetic/Aesthetic Dentist, Dental Surgeon', 'BDS ', 'resources/img/doctors/ushma.jpg', '', 1),
(8, 'Dr. Dhawal Naik', 1, '29 Years', 'Male', 30, 'DNB - Urology/Genito - Urinary Surgery, MNAMS - Urology,\r\nUrological Surgeon', 'MBBS, MS ', 'resources/img/doctors/dhawal.jpeg', '', 1),
(9, 'Dr. Chetan Sheladia', 1, '11 Years', 'Male', 31, 'Urological Surgeon, Urologist, Laparoscopic Surgeon', 'DNB, MBBS', 'resources/img/doctors/chetan.jpg', '', 1),
(10, 'Dr. Bhaumik P Thakor', 2, '10 Years', 'Male', 32, 'Neurosurgeon, Neurologist', 'MCh, MBBS', 'resources/img/doctors/bhaumik.jpg', '', 1),
(11, 'Dr. Rajiv Raj Choudhry', 3, '33 Years ', 'Male', 33, 'Orthopedist, Spine And Pain Specialist, Joint Replacement Surgeon', 'MBBS, MS - Orthopaedics', 'resources/img/doctors/rajivraj.jpg', '', 1),
(12, 'Dr. Krunal Shah', 3, '14 Years', 'Male', 34, 'Orthopedist, Joint Replacement Surgeon', 'MBBS, MS - Orthopaedics\r\n', 'resources/img/doctors/krunal.png', '', 1),
(13, 'Dr. Sulok Desai', 6, '10 Years', 'Male', 35, 'Cardiologist', 'MBBS', 'resources/img/doctors/sulok.jpg', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `fees_tb`
--

CREATE TABLE IF NOT EXISTS `fees_tb` (
  `feesId` int(10) NOT NULL AUTO_INCREMENT,
  `hospitalId` int(10) NOT NULL,
  `specializationId` int(10) NOT NULL,
  `fees` int(10) NOT NULL,
  PRIMARY KEY (`feesId`),
  KEY `hospitalId` (`hospitalId`),
  KEY `specializationId` (`specializationId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `fees_tb`
--

INSERT INTO `fees_tb` (`feesId`, `hospitalId`, `specializationId`, `fees`) VALUES
(1, 5, 1, 300),
(2, 5, 2, 300),
(3, 5, 3, 400),
(5, 6, 1, 350),
(6, 6, 2, 400),
(7, 6, 3, 400),
(8, 6, 6, 450),
(9, 7, 1, 200),
(10, 7, 2, 200),
(11, 7, 3, 200),
(12, 7, 6, 200),
(13, 8, 1, 400),
(14, 8, 2, 450),
(15, 8, 3, 500),
(16, 8, 6, 550),
(17, 8, 7, 300),
(18, 10, 1, 500),
(19, 10, 2, 500),
(20, 10, 3, 500),
(21, 10, 7, 500),
(22, 10, 8, 500),
(23, 5, 8, 1050),
(26, 5, 6, 500);

-- --------------------------------------------------------

--
-- Table structure for table `group_tb`
--

CREATE TABLE IF NOT EXISTS `group_tb` (
  `groupId` int(11) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(100) NOT NULL,
  PRIMARY KEY (`groupId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `group_tb`
--

INSERT INTO `group_tb` (`groupId`, `groupName`) VALUES
(1, 'admin'),
(2, 'hospital'),
(3, 'doctor'),
(4, 'patient');

-- --------------------------------------------------------

--
-- Table structure for table `hospital_facility_tb`
--

CREATE TABLE IF NOT EXISTS `hospital_facility_tb` (
  `facilityId` int(10) NOT NULL AUTO_INCREMENT,
  `hospitalId` int(10) NOT NULL,
  `specializationId` int(10) NOT NULL,
  PRIMARY KEY (`facilityId`),
  KEY `hospitalId` (`hospitalId`),
  KEY `specializationId` (`specializationId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `hospital_facility_tb`
--

INSERT INTO `hospital_facility_tb` (`facilityId`, `hospitalId`, `specializationId`) VALUES
(1, 5, 1),
(2, 5, 2),
(3, 5, 3),
(4, 5, 6),
(5, 6, 1),
(6, 6, 2),
(7, 6, 3),
(8, 6, 6),
(9, 7, 1),
(10, 7, 2),
(11, 7, 3),
(12, 7, 6),
(13, 8, 1),
(14, 8, 2),
(15, 8, 3),
(16, 8, 6),
(17, 8, 7),
(18, 10, 1),
(19, 10, 2),
(20, 10, 3),
(21, 10, 7),
(22, 10, 8);

-- --------------------------------------------------------

--
-- Table structure for table `hospital_tb`
--

CREATE TABLE IF NOT EXISTS `hospital_tb` (
  `hospitalId` int(10) NOT NULL AUTO_INCREMENT,
  `hospitalName` varchar(100) NOT NULL,
  `address` text NOT NULL,
  `areaId` int(10) NOT NULL,
  `cityId` int(10) NOT NULL,
  `pincode` int(10) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `maplink` text NOT NULL,
  `openingTime` time NOT NULL,
  `closingTime` time NOT NULL,
  `logo` text NOT NULL,
  `documents` text NOT NULL,
  `userId` int(10) NOT NULL,
  `isActive` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`hospitalId`),
  KEY `areaId` (`areaId`),
  KEY `cityId` (`cityId`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `hospital_tb`
--

INSERT INTO `hospital_tb` (`hospitalId`, `hospitalName`, `address`, `areaId`, `cityId`, `pincode`, `latitude`, `longitude`, `maplink`, `openingTime`, `closingTime`, `logo`, `documents`, `userId`, `isActive`) VALUES
(5, 'Shalby Hospital', 'Nr. Navyug College, Rander Road, \r\n\r\n', 1, 1, 395009, 21.1968399, 72.7789305, 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3719.744379513282!2d72.79880851398555!3d21.202311185904588!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3be04c2bd38b220d%3A0xfd16cdc3f794f73b!2sShalby%20Hospital%2C%20Surat!5e0!3m2!1sen!2sin!4v1590676954938!5m2!1sen!2sin', '08:00:00', '11:45:00', 'resources/img/hospital/h3.jpg', 'resources/img/hospital/h3.jpg', 21, 1),
(6, 'Malvia Hospital', 'Aditya Complex, Anand Mahal Rd', 1, 1, 395009, 21.2012498, 72.7925257, 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3719.712479898504!2d72.79204231398562!3d21.203577785903892!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3be04c2e416c01ab%3A0x332fb4a506074c14!2sMalvia%20Hospital!5e0!3m2!1sen!2sin!4v1590775020053!5m2!1sen!2sin', '07:00:00', '11:30:00', 'resources/img/hospital/h4.jpg', 'resources/img/hospital/h4.jpg', 22, 1),
(7, 'New City Hospital', '101 bhagwati ashish appartment 2, City Light Rd', 3, 1, 395007, 21.1888568, 72.7734796, 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3720.562287827078!2d72.78711011398481!3d21.169810785922124!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3be04de9ab07e3ef%3A0xb4baf36339271b87!2sNew%20City%20Hospital!5e0!3m2!1sen!2sin!4v1590775099206!5m2!1sen!2sin', '11:00:00', '08:00:00', 'resources/img/hospital/h5.jpg', 'resources/img/hospital/h5.jpg', 23, 1),
(8, 'Mahavir Hospital', 'Opposite Jivan Bharti School', 7, 1, 395001, 21.184108, 72.8115983, 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3720.2026305273985!2d72.81159831398513!3d21.184107985914437!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3be04e705192d03f%3A0xe4fc1330863e2f25!2sMahavir%20Hospital!5e0!3m2!1sen!2sin!4v1590775150931!5m2!1sen!2sin', '06:00:00', '12:00:00', 'resources/img/hospital/h6.jpg', 'resources/img/hospital/h6.jpg', 24, 1),
(10, 'Kiran Hospital', 'Nr Sumul DairyTunki, Katargam', 6, 1, 395004, 21.2190018, 72.8366558, 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3719.3238770368093!2d72.834467113986!3d21.21900178589565!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3be04f393048f2ad%3A0xfd28539e974e241a!2sKiran%20Hospital!5e0!3m2!1sen!2sin!4v1590775197174!5m2!1sen!2sin', '06:00:00', '12:00:00', 'resources/img/hospital/h7.jpg', 'resources/img/hospital/h7.jpg', 25, 1),
(15, 'Baps Hospital ', 'Shri Pramukh Swami Maharaj Marg,, Adajan Char Rasta', 1, 1, 395009, 21.1895239, 72.798148, 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d18959.881083200355!2d72.78748602007362!3d21.200080219728648!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3be04dd14b0af37d%3A0x230d1d02a04d45bd!2sBAPS%20Pramukh%20Swami%20Hospital!5e0!3m2!1sen!2sin!4v1591120580485!5m2!1sen!2sin', '10:00:00', '08:00:00', 'resources/img/hospital/h8.jpg', 'resources/img/hospitalDoc/Sem_6_Syllabus.pdf', 67, 0);

-- --------------------------------------------------------

--
-- Table structure for table `patient_tb`
--

CREATE TABLE IF NOT EXISTS `patient_tb` (
  `patientId` int(11) NOT NULL AUTO_INCREMENT,
  `patientName` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `userId` int(11) NOT NULL,
  `address` text NOT NULL,
  `age` int(3) NOT NULL,
  `isActive` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`patientId`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `patient_tb`
--

INSERT INTO `patient_tb` (`patientId`, `patientName`, `gender`, `userId`, `address`, `age`, `isActive`) VALUES
(12, 'Nancy', 'Female', 51, 'B-19,Ganesh Park,Adajan', 20, 1);

-- --------------------------------------------------------

--
-- Table structure for table `review_tb`
--

CREATE TABLE IF NOT EXISTS `review_tb` (
  `reviewId` int(10) NOT NULL AUTO_INCREMENT,
  `patientId` int(10) NOT NULL,
  `doctorId` int(10) DEFAULT NULL,
  `hospitalId` int(10) DEFAULT NULL,
  `review` varchar(100) NOT NULL,
  PRIMARY KEY (`reviewId`),
  KEY `patientId` (`patientId`),
  KEY `doctorId` (`doctorId`),
  KEY `hospitalId` (`hospitalId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `review_tb`
--

INSERT INTO `review_tb` (`reviewId`, `patientId`, `doctorId`, `hospitalId`, `review`) VALUES
(1, 12, 4, NULL, 'Great Work'),
(2, 12, 4, NULL, 'nancy'),
(3, 12, 9, NULL, 'Great Work'),
(4, 12, 5, NULL, 'Great Work'),
(5, 12, NULL, 5, 'Facilities provided by hospital are good'),
(6, 12, 4, NULL, 'bad'),
(7, 12, NULL, 5, 'Great Work'),
(8, 12, NULL, 6, 'Great');

-- --------------------------------------------------------

--
-- Table structure for table `specialization_tb`
--

CREATE TABLE IF NOT EXISTS `specialization_tb` (
  `specializationId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `image` text NOT NULL,
  `isActive` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`specializationId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `specialization_tb`
--

INSERT INTO `specialization_tb` (`specializationId`, `name`, `description`, `image`, `isActive`) VALUES
(1, 'Urology', 'Urology is a part of health care that deals with diseases of urinary tract.', 'resources/img/specialities/specialities-01.png', 1),
(2, 'Neurologist', 'The neurologist treats disorders that affect the brain.', 'resources/img/specialities/specialities-02.png', 1),
(3, 'Orthopaedic', 'Orthopaedic surgeons are devoted to the  treatment of disorders of the bon.', 'resources/img/specialities/specialities-03.png', 1),
(6, 'Cardiologist', 'A cardiologist specializes in diagnosing and treating diseases of the cardiovascular system.', 'resources/img/specialities/specialities-04.png', 1),
(7, 'Dentist', 'Dentists diagnose and treat dental issues.', 'resources/img/specialities/specialities-05.png', 1),
(8, 'Gynecologists', 'Gynecologists are doctors who specialize in women''s health, with female reproductive system.', 'resources/img/specialities/specialities-06.png', 1);

-- --------------------------------------------------------

--
-- Table structure for table `state_tb`
--

CREATE TABLE IF NOT EXISTS `state_tb` (
  `stateId` int(10) NOT NULL AUTO_INCREMENT,
  `stateName` varchar(100) NOT NULL,
  `isActive` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`stateId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `state_tb`
--

INSERT INTO `state_tb` (`stateId`, `stateName`, `isActive`) VALUES
(1, 'Gujarat', 1),
(2, 'Maharashtra', 1),
(3, 'Rajasthan', 1),
(4, 'Himachal Prades', 1),
(5, 'punjab', 1),
(6, 'Goa', 1),
(8, 'Kerela', 1),
(9, 'TamilNadu', 1),
(10, 'punjab', 0);

-- --------------------------------------------------------

--
-- Table structure for table `usergroup_tb`
--

CREATE TABLE IF NOT EXISTS `usergroup_tb` (
  `usergroupId` int(10) NOT NULL AUTO_INCREMENT,
  `userId` int(10) NOT NULL,
  `groupId` int(10) NOT NULL,
  PRIMARY KEY (`usergroupId`),
  KEY `userId` (`userId`),
  KEY `groupId` (`groupId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=43 ;

--
-- Dumping data for table `usergroup_tb`
--

INSERT INTO `usergroup_tb` (`usergroupId`, `userId`, `groupId`) VALUES
(1, 21, 2),
(2, 22, 2),
(3, 23, 2),
(4, 24, 2),
(5, 25, 2),
(6, 26, 3),
(7, 27, 3),
(8, 28, 3),
(9, 29, 3),
(10, 30, 3),
(11, 31, 3),
(12, 32, 3),
(13, 33, 3),
(14, 34, 3),
(15, 35, 3),
(16, 36, 1),
(28, 51, 4),
(33, 57, 4),
(34, 58, 4),
(42, 67, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user_tb`
--

CREATE TABLE IF NOT EXISTS `user_tb` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) NOT NULL,
  `password` varchar(500) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contact` bigint(10) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=68 ;

--
-- Dumping data for table `user_tb`
--

INSERT INTO `user_tb` (`userId`, `userName`, `password`, `email`, `contact`) VALUES
(21, 'shalby ', 'PBKDF2WithHmacSHA256:2048:MvLcmtVB5L7ZZkl3hr7oKBFFJo3RGjzlY/OQusG+EUo=:vj1g/Fj/4Fgg7kFc1DyOQldKqeKVTU5Gb7A9STAxYic=', 'info.surat@shalby.in', 9898725144),
(22, 'malvia', 'PBKDF2WithHmacSHA256:2048:hBjD7zQVpslhgGj7vLewcyXWUj9aEBsBKnz7bqtDBag=:nJn1LI2eKqLxv+40g+bfX4X28RJScTsToeBbiNxjYw0=', 'malvia@gmail.com', 2612767668),
(23, 'newcity', 'newcity', 'newcity@gmail.com', 2612257100),
(24, 'mahavir', 'mahavir', 'mahavirhospital@gmail.com', 2612292000),
(25, 'kiran', 'kiran', 'info@kiranhospital.com\r\n', 2617161111),
(26, 'drbhumik', 'PBKDF2WithHmacSHA256:2048:B7OOns5PsR9f6F9O7kV/h5QknnIZSlLZhAVFC4FLJAk=:eCjmAEUEFf0rXc1yZXpywlgfFA39ksQaXhYlas42Jjc=', 'bhumikpatel@gmail.com', 7854616120),
(27, 'drkaushika', 'PBKDF2WithHmacSHA256:2048:B7OOns5PsR9f6F9O7kV/h5QknnIZSlLZhAVFC4FLJAk=:eCjmAEUEFf0rXc1yZXpywlgfFA39ksQaXhYlas42Jjc=', 'drkaushikapatel@gmail.com', 9868686875),
(28, 'Dr. Kajal Mangukiya', 'PBKDF2WithHmacSHA256:2048:NA1TR4XLAcNhGjXGtYO8Iu3YNhDSmIn2BhLRPAReU28=:TS0OexEODXVywlUgaKRN8MKbuNRPMA/s8Zss2KojWVM=', 'ushamehta1574@gmail.com', 8788456123),
(29, 'drushma', 'drushma', 'drushma@gmail.com', 8866592424),
(30, 'drdhawal', 'drdhawal', 'dhawalnaik@gmail.com', 9797565622),
(31, 'drchetan', 'drchetan', 'chetansheladia@gmail.com', 8787124650),
(32, 'drbhaumik', 'drbhaumik', 'bhaumikthakor@gmail.com', 7014989822),
(33, 'drrajivraj', 'drrajivraj', 'rajivrajchoudhry@gmail.com', 8885664400),
(34, 'drkrunal', 'drkrunal', 'krunalshah@gmail.com', 8787224458),
(35, 'drsulok', 'drsulok', 'sulokdesai@gmail.com', 9898747566),
(36, 'nidhi', 'PBKDF2WithHmacSHA256:2048:hBjD7zQVpslhgGj7vLewcyXWUj9aEBsBKnz7bqtDBag=:nJn1LI2eKqLxv+40g+bfX4X28RJScTsToeBbiNxjYw0=', 'nidhimehta9399@gmail.com', 7016955943),
(51, 'nancy', 'PBKDF2WithHmacSHA256:2048:6act3PzzEYdg2rVBa+FE15Qd5YcWjOfQ5HwqVSLbYvI=:LdMKtlaaPjKggt0NraGwFIBbYh2qv1B0qUV46tUfzlM=', 'kapadianancy21@gmail.com', 9426543700),
(57, 'roshan', 'PBKDF2WithHmacSHA256:2048:R786XOpPm0chLtmz6kCgXJT20ydUmgx4KljDuTJmTbM=:19fr8L7CCpRGJe9UfGDJct2WIQ4muNy7fvLyDyuHvng=', 'roshan@gmail.com', 1234567890),
(58, 'john', 'PBKDF2WithHmacSHA256:2048:/MvfyBB3iQTFXGH+ar99a60EQy1uoJmdFWPcGhU1op0=:77pcu65GUW5xzUVqD4xIS+35N0PNDlQi+VBui5FNiCM=', 'john@gmail.com', 9887654321),
(67, 'Baps Hospital ', 'PBKDF2WithHmacSHA256:2048:d+kV7XkYPZuuJCTkrLF6T7i/Ynr2d1yiu2FAoXqRhlU=:9C/9ZfSuQMT/NwB2OBb9yHy+LIX32ii/VpjtpcnYX1E=', 'binalmehta4034@gmail.com', 9898725144);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment_tb`
--
ALTER TABLE `appointment_tb`
  ADD CONSTRAINT `appointment_tb_ibfk_1` FOREIGN KEY (`doctorId`) REFERENCES `doctor_tb` (`doctorId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `appointment_tb_ibfk_2` FOREIGN KEY (`hospitalId`) REFERENCES `hospital_tb` (`hospitalId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `appointment_tb_ibfk_3` FOREIGN KEY (`patientId`) REFERENCES `patient_tb` (`patientId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `area_tb`
--
ALTER TABLE `area_tb`
  ADD CONSTRAINT `area_tb_ibfk_1` FOREIGN KEY (`cityId`) REFERENCES `city_tb` (`cityId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `city_tb`
--
ALTER TABLE `city_tb`
  ADD CONSTRAINT `city_tb_ibfk_1` FOREIGN KEY (`stateId`) REFERENCES `state_tb` (`stateId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `doctor_attachment_tb`
--
ALTER TABLE `doctor_attachment_tb`
  ADD CONSTRAINT `doctor_attachment_tb_ibfk_1` FOREIGN KEY (`doctorId`) REFERENCES `doctor_tb` (`doctorId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `doctor_attachment_tb_ibfk_2` FOREIGN KEY (`patientId`) REFERENCES `patient_tb` (`patientId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `doctor_schedule_tb`
--
ALTER TABLE `doctor_schedule_tb`
  ADD CONSTRAINT `doctor_schedule_tb_ibfk_1` FOREIGN KEY (`hospitalId`) REFERENCES `hospital_tb` (`hospitalId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `doctor_schedule_tb_ibfk_2` FOREIGN KEY (`doctorId`) REFERENCES `doctor_tb` (`doctorId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `doctor_tb`
--
ALTER TABLE `doctor_tb`
  ADD CONSTRAINT `doctor_tb_ibfk_1` FOREIGN KEY (`specializationId`) REFERENCES `specialization_tb` (`specializationId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `doctor_tb_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user_tb` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `fees_tb`
--
ALTER TABLE `fees_tb`
  ADD CONSTRAINT `fees_tb_ibfk_1` FOREIGN KEY (`hospitalId`) REFERENCES `hospital_tb` (`hospitalId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fees_tb_ibfk_2` FOREIGN KEY (`specializationId`) REFERENCES `specialization_tb` (`specializationId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hospital_facility_tb`
--
ALTER TABLE `hospital_facility_tb`
  ADD CONSTRAINT `hospital_facility_tb_ibfk_1` FOREIGN KEY (`hospitalId`) REFERENCES `hospital_tb` (`hospitalId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hospital_facility_tb_ibfk_2` FOREIGN KEY (`specializationId`) REFERENCES `specialization_tb` (`specializationId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hospital_tb`
--
ALTER TABLE `hospital_tb`
  ADD CONSTRAINT `hospital_tb_ibfk_1` FOREIGN KEY (`areaId`) REFERENCES `area_tb` (`areaId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hospital_tb_ibfk_2` FOREIGN KEY (`cityId`) REFERENCES `city_tb` (`cityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hospital_tb_ibfk_3` FOREIGN KEY (`userId`) REFERENCES `user_tb` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `patient_tb`
--
ALTER TABLE `patient_tb`
  ADD CONSTRAINT `patient_tb_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user_tb` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `review_tb`
--
ALTER TABLE `review_tb`
  ADD CONSTRAINT `review_tb_ibfk_1` FOREIGN KEY (`hospitalId`) REFERENCES `hospital_tb` (`hospitalId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `review_tb_ibfk_2` FOREIGN KEY (`patientId`) REFERENCES `patient_tb` (`patientId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `review_tb_ibfk_3` FOREIGN KEY (`doctorId`) REFERENCES `doctor_tb` (`doctorId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `usergroup_tb`
--
ALTER TABLE `usergroup_tb`
  ADD CONSTRAINT `usergroup_tb_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user_tb` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usergroup_tb_ibfk_2` FOREIGN KEY (`groupId`) REFERENCES `group_tb` (`groupId`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
