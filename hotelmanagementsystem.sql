-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2024 at 09:34 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotelmanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `Reservationid` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Mobile` int(11) NOT NULL,
  `Checkin` varchar(255) NOT NULL,
  `Checkout` varchar(255) NOT NULL,
  `Roomcondition` varchar(255) NOT NULL,
  `Roomtype` varchar(255) NOT NULL,
  `Bedtype` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`Reservationid`, `Name`, `Address`, `Mobile`, `Checkin`, `Checkout`, `Roomcondition`, `Roomtype`, `Bedtype`) VALUES
('RE001', 'Admin', 'Colombo', 471452896, '2024-06-04', '2024-06-06', 'AC', 'Single', 'Singel '),
('RE002', 'Charani Subasinghe', 'Colombo', 714529638, '2024-06-04', '2024-06-07', 'AC', 'Single', 'Singel ');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `Roomid` varchar(10) NOT NULL,
  `Roomcondition` varchar(10) NOT NULL,
  `Roomtype` varchar(255) NOT NULL,
  `Bedtype` varchar(255) NOT NULL,
  `price` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`Roomid`, `Roomcondition`, `Roomtype`, `Bedtype`, `price`) VALUES
('R001', 'AC', 'Single', 'Singel ', '5000/='),
('R002', 'AC', 'Double', 'Singel ', '7000/='),
('R003', 'AC', 'Twin', 'Singel ', '9000/='),
('R004', 'AC', 'Triple', 'Singel ', '11000/='),
('R005', 'AC', 'Duluxe', 'Singel ', '13000/='),
('R006', 'AC', 'Cabana', 'Singel ', '15500/='),
('R007', 'Non AC', 'Single', 'Single', '4500/='),
('R008', 'Non AC', 'Double', 'Single', '5500/='),
('R009', 'Non AC', 'Twin', 'Single', '6500/='),
('R010', 'Non AC', 'Triple', 'Single', '8000/='),
('R011', 'Non AC', 'Duluxe', 'Single', '9000/='),
('R012', 'Non AC', 'Cabana', 'Single', '10500/='),
('R013', 'AC', 'Single', 'Double', '5800/='),
('R014', 'AC', 'Double', 'Double', '7800/='),
('R015', 'AC', 'Twin', 'Double', '9800/='),
('R016', 'AC', 'Triple', 'Double', '11800/='),
('R017', 'AC', 'Duluxe', 'Double', '13800/='),
('R018', 'AC', 'Cabana', 'Double', '16300/='),
('R019', 'Non AC', 'Single', 'Double', '5300/='),
('R020', 'Non AC', 'Double', 'Double', '6300/='),
('R021', 'Non AC', 'Twin', 'Double', '7300/='),
('R022', 'Non AC', 'Triple', 'Double', '8800/='),
('R023', 'Non AC', 'Duluxe', 'Double', '9800/='),
('R024', 'Non AC', 'Cabana', 'Double', '11300/='),
('R025', 'AC', 'Single', 'King', '6500/='),
('R026', 'AC', 'Double', 'King', '9500/='),
('R027', 'AC', 'Twin', 'King', '10500/='),
('R028', 'AC', 'Triple', 'King', '12000/=');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Userid` int(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Userid`, `Name`, `Username`, `Password`) VALUES
(1, 'admin account', 'admin', 111),
(6, 'Charani', 'charani', 111);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`Reservationid`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`Roomid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `Userid` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
