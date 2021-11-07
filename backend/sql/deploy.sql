-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Oct 02, 2021 at 10:00 AM
-- Server version: 5.7.26
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `cs203`
--

-- --------------------------------------------------------

--
-- Table structure for table `cluster`
--

CREATE TABLE `cluster` (
  `id` bigint(20) NOT NULL,
  `cluster` varchar(255) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `covid_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cluster`
--

INSERT INTO `cluster` (`id`, `cluster`, `number`, `covid_id`) VALUES
(60, 'Westlite Toh Guan', 110, 59),
(61, 'Grand Hyatt Hotel', 3, 59),
(62, 'Yong Thai Hang (Lavender)', 9, 59),
(63, 'Seletar Aerospace Heights', 5, 59),
(64, 'Grace Assembly of God', 23, 59),
(65, 'boulder+ Gym (12 Kallang Avenue)', 3, 59),
(66, 'Wizlearn Technologies Pte Ltd', 14, 59),
(67, 'The Life Church and Missions Singapore', 10, 59),
(68, 'Private dinner function at SAFRA Jurong', 47, 59),
(69, 'Dover Court International School', 11, 59),
(70, 'Church of Singapore (Bukit Timah)', 4, 59),
(71, 'Mass Religious Gathering In Malaysia', 5, 59),
(72, 'Shaw Lodge', 24, 59),
(73, 'Cochrane Lodge I', 53, 59),
(74, '31 Sungei Kadut Avenue', 31, 59),
(75, 'Black Tap', 8, 59),
(76, 'S11 Dormitory', 797, 59),
(77, 'Cassia @ Penjuru', 15, 59),
(78, 'North Coast Lodge', 18, 59),
(79, 'Kranji Lodge I', 20, 59),
(80, 'Sungei Tengah Lodge', 279, 59),
(81, 'Tuas View Dormitory', 43, 59),
(82, 'Acacia Lodge', 56, 59),
(83, '85 Kallang Dormitory', 5, 59),
(84, '36 Woodlands Industrial Park E1', 27, 59),
(85, 'McDonald’s', 5, 59),
(86, 'Kenyon/UBS construction site (9 Penang Road)', 15, 59),
(87, 'Tampines Dormitory', 61, 59),
(88, 'CitiWall', 6, 59),
(89, 'Westlite Woodlands dormitory', 10, 59),
(90, 'Project Glory', 49, 59),
(91, 'Keppel Shipyard', 22, 59),
(92, 'NUH renovation site', 26, 59),
(93, '10 Kian Teck Crescent dormitory', 4, 59),
(94, 'Kian Teck Dormitory', 3, 59),
(95, 'ABC Hostel', 7, 59),
(96, '234 Balestier Road', 8, 59),
(97, 'Tech Park Crescent dormitory', 19, 59),
(98, 'PPT Lodge 1A', 18, 59),
(99, 'Cochrane Lodge II', 46, 59),
(100, 'Orange Ballroom', 5, 59),
(101, 'Toh Guan Dormitory', 83, 59),
(102, 'The Wedding Brocade', 4, 59),
(103, 'Mandai Lodge', 7, 59),
(104, '21B Senoko Loop', 20, 59),
(105, 'Mustafa Centre', 87, 59),
(106, 'Kranji Dormitory', 9, 59);

-- --------------------------------------------------------

--
-- Table structure for table `covid`
--

CREATE TABLE `covid` (
  `covid_id` bigint(20) NOT NULL,
  `active_cases` bigint(20) NOT NULL,
  `average_age` bigint(20) NOT NULL,
  `critical` bigint(20) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `deceased` bigint(20) NOT NULL,
  `discharged` bigint(20) NOT NULL,
  `female_cases` bigint(20) NOT NULL,
  `gender_unidentified_cases` bigint(20) NOT NULL,
  `imported_cases` bigint(20) NOT NULL,
  `imported_or_local_unreported_cases` bigint(20) NOT NULL,
  `local_transmissions` bigint(20) NOT NULL,
  `male_cases` bigint(20) NOT NULL,
  `total_cases` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `community` bigint(20) NOT NULL,
  `deaths` bigint(20) NOT NULL,
  `dormitory` bigint(20) NOT NULL,
  `hospitalised` bigint(20) NOT NULL,
  `imported` bigint(20) NOT NULL,
  `new_cases` bigint(20) NOT NULL,
  `recovered` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `covid`
--

INSERT INTO `covid` (`covid_id`, `active_cases`, `average_age`, `critical`, `date`, `deceased`, `discharged`, `female_cases`, `gender_unidentified_cases`, `imported_cases`, `imported_or_local_unreported_cases`, `local_transmissions`, `male_cases`, `total_cases`, `id`, `community`, `deaths`, `dormitory`, `hospitalised`, `imported`, `new_cases`, `recovered`) VALUES
(59, 20203, 35, 34, '2021-10-02 14:30:04.658000', 103, 79124, 723, 92900, 562, 92842, 6026, 5807, 99430, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(251);

-- --------------------------------------------------------

--
-- Table structure for table `infection_source`
--

CREATE TABLE `infection_source` (
  `id` bigint(20) NOT NULL,
  `infection_source` varchar(255) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `covid_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `infection_source`
--

INSERT INTO `infection_source` (`id`, `infection_source`, `number`, `covid_id`) VALUES
(107, 'United Kingdom', 178, 59),
(108, 'United States', 63, 59),
(109, 'Indonesia', 27, 59),
(110, 'China', 24, 59),
(111, 'Malaysia', 22, 59),
(112, 'Philippines', 17, 59),
(113, 'France', 11, 59),
(114, 'Others', 80, 59);

-- --------------------------------------------------------

--
-- Table structure for table `nationality`
--

CREATE TABLE `nationality` (
  `id` bigint(20) NOT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `covid_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nationality`
--

INSERT INTO `nationality` (`id`, `nationality`, `number`, `covid_id`) VALUES
(115, 'Bangladeshi', 2922, 59),
(116, 'Indian', 1608, 59),
(117, 'Singaporean', 1286, 59),
(118, 'Chinese', 233, 59),
(119, 'Myanmarian', 136, 59),
(120, 'Malaysian', 95, 59),
(121, 'Unidentified', 62, 59),
(122, 'Others', 246, 59);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cluster`
--
ALTER TABLE `cluster`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkwvjdj50sqsqf2ksh73haxrcf` (`covid_id`);

--
-- Indexes for table `covid`
--
ALTER TABLE `covid`
  ADD PRIMARY KEY (`covid_id`);

--
-- Indexes for table `infection_source`
--
ALTER TABLE `infection_source`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKikm3qa4xp9skyfhr7sashe2eu` (`covid_id`);

--
-- Indexes for table `nationality`
--
ALTER TABLE `nationality`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9nc30oy30ercwia25q337vjfy` (`covid_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cluster`
--
ALTER TABLE `cluster`
  ADD CONSTRAINT `FKkwvjdj50sqsqf2ksh73haxrcf` FOREIGN KEY (`covid_id`) REFERENCES `covid` (`covid_id`);

--
-- Constraints for table `infection_source`
--
ALTER TABLE `infection_source`
  ADD CONSTRAINT `FKikm3qa4xp9skyfhr7sashe2eu` FOREIGN KEY (`covid_id`) REFERENCES `covid` (`covid_id`);

--
-- Constraints for table `nationality`
--
ALTER TABLE `nationality`
  ADD CONSTRAINT `FK9nc30oy30ercwia25q337vjfy` FOREIGN KEY (`covid_id`) REFERENCES `covid` (`covid_id`);
