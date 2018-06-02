-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 02, 2018 at 12:42 PM
-- Server version: 5.5.60-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `skeleton`
--

-- --------------------------------------------------------

--
-- Table structure for table `access_role`
--

CREATE TABLE IF NOT EXISTS `access_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_credential_id` int(11) NOT NULL,
  `role` enum('ALL') COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `auth_credential_id` (`auth_credential_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `access_role`
--

INSERT INTO `access_role` (`id`, `auth_credential_id`, `role`, `created_at`) VALUES
(1, 1, 'ALL', '2018-05-03 06:19:45');

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formatted_address` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `formatted_address`, `created_at`) VALUES
(1, 'FORMATEED,ADDRESS', '2018-06-02 12:22:42');

-- --------------------------------------------------------

--
-- Table structure for table `auth_credential`
--

CREATE TABLE IF NOT EXISTS `auth_credential` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personal_info_id` int(11) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `status` enum('ACTIVE','DEACTIVE') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK204315vkkjatxsc2egk1ifmlf` (`personal_info_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `auth_credential`
--

INSERT INTO `auth_credential` (`id`, `personal_info_id`, `email`, `password`, `status`, `created_at`) VALUES
(1, 1, 'admin@admin.com', '$2a$10$5aTN9klJNhO3DERcg/j11.VQC2skPV2zz37SG3kraKRms.v5.XYyC', 'ACTIVE', '2018-04-24 04:47:04');

-- --------------------------------------------------------

--
-- Table structure for table `personal_information`
--

CREATE TABLE IF NOT EXISTS `personal_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_id` int(11) DEFAULT NULL,
  `full_name` varchar(200) NOT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `address_id` (`address_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `personal_information`
--

INSERT INTO `personal_information` (`id`, `address_id`, `full_name`, `dob`, `email`, `phone`, `created_at`) VALUES
(1, 1, 'Person 1', '2018-04-04', NULL, NULL, '2018-04-20 13:19:58');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `access_role`
--
ALTER TABLE `access_role`
  ADD CONSTRAINT `FKu5gxdleqtq6tckr8df8durll` FOREIGN KEY (`auth_credential_id`) REFERENCES `auth_credential` (`id`);

--
-- Constraints for table `auth_credential`
--
ALTER TABLE `auth_credential`
  ADD CONSTRAINT `FK204315vkkjatxsc2egk1ifmlf` FOREIGN KEY (`personal_info_id`) REFERENCES `personal_information` (`id`);

--
-- Constraints for table `personal_information`
--
ALTER TABLE `personal_information`
  ADD CONSTRAINT `FKe3ip0abhdip6wyyg4clupqqjn` FOREIGN KEY (`address_id`) REFERENCES `pos`.`address` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
