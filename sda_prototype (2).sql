-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2024 at 04:23 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sda_prototype`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `account_type` varchar(50) DEFAULT NULL,
  `balance` decimal(15,2) DEFAULT 0.00,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `password_hash` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_id`, `customer_id`, `account_type`, `balance`, `created_at`, `password_hash`) VALUES
(1, 1, 'Checking', '848.00', '2023-12-30 11:06:01', '123'),
(2, 2, 'Savings', '1500.00', '2023-12-30 11:06:01', '000'),
(3, 3, 'Checking', '522.00', '2023-12-30 11:06:01', '123'),
(4, 4, 'Savings', '2530.00', '2023-12-30 11:06:01', '000'),
(5, 5, 'Checking', '3000.00', '2023-12-30 11:06:01', '123'),
(6, 6, 'Savings', '2100.00', '2023-12-30 11:06:01', '000');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `address` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `first_name`, `last_name`, `email`, `phone_number`, `address`, `created_at`) VALUES
(1, 'John', 'Doe', 'john.doe@example.com', '123-456-7890', '123 Maple Street, Anytown', '2023-12-30 11:02:17'),
(2, 'Jane', 'Doe', 'jane.doe@example.com', '987-654-3210', '456 Oak Avenue, Somewhere', '2023-12-30 11:02:17'),
(3, 'Alice', 'Smith', 'alice.smith@example.com', '555-123-4567', '789 Pine Road, Thisplace', '2023-12-30 11:02:17'),
(4, 'Bob', 'Johnson', 'bob.johnson@example.com', '555-987-6543', '321 Birch Boulevard, Thatplace', '2023-12-30 11:02:17'),
(5, 'Charlie', 'Brown', 'charlie.brown@example.com', '555-666-7777', '654 Willow Way, Otherplace', '2023-12-30 11:02:17'),
(6, 'Eve', 'Williams', 'eve.williams@example.com', '555-111-2222', '987 Elm Street, Elsewhere', '2023-12-30 11:02:17');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `hire_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `type` enum('deposit','withdrawal','transfer') DEFAULT NULL,
  `amount` decimal(15,2) DEFAULT NULL,
  `transaction_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `account_id`, `type`, `amount`, `transaction_date`) VALUES
(1, 1, 'deposit', '500.00', '2023-12-30 11:07:33'),
(2, 1, 'withdrawal', '200.00', '2023-12-30 11:07:33'),
(3, 2, 'deposit', '300.00', '2023-12-30 11:07:33'),
(4, 2, 'transfer', '150.00', '2023-12-30 11:07:33'),
(5, 3, 'withdrawal', '50.00', '2023-12-30 11:07:33'),
(6, 3, 'deposit', '450.00', '2023-12-30 11:07:33'),
(7, 4, 'transfer', '200.00', '2023-12-30 11:07:33'),
(8, 4, 'deposit', '600.00', '2023-12-30 11:07:33'),
(9, 5, 'withdrawal', '100.00', '2023-12-30 11:07:33'),
(10, 6, 'deposit', '250.00', '2023-12-30 11:07:33'),
(11, 1, 'withdrawal', '12.00', '2023-12-31 01:12:10'),
(12, 3, 'deposit', '12.00', '2023-12-31 01:12:10'),
(13, 1, 'withdrawal', '10.00', '2023-12-31 04:07:15'),
(14, 4, 'deposit', '10.00', '2023-12-31 04:07:15'),
(17, 1, 'withdrawal', '20.00', '2023-12-31 07:05:00'),
(18, 4, 'deposit', '20.00', '2023-12-31 07:05:00'),
(19, 1, 'withdrawal', '100.00', '2023-12-31 07:06:01'),
(20, 6, 'deposit', '100.00', '2023-12-31 07:06:01');

-- --------------------------------------------------------

--
-- Table structure for table `transfer`
--

CREATE TABLE `transfer` (
  `transfer_id` int(11) NOT NULL,
  `source_account_id` int(11) DEFAULT NULL,
  `destination_account_id` int(11) DEFAULT NULL,
  `amount` decimal(15,2) DEFAULT NULL,
  `transfer_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `transfer`
--
ALTER TABLE `transfer`
  ADD PRIMARY KEY (`transfer_id`),
  ADD KEY `source_account_id` (`source_account_id`),
  ADD KEY `destination_account_id` (`destination_account_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `transfer`
--
ALTER TABLE `transfer`
  MODIFY `transfer_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);

--
-- Constraints for table `transfer`
--
ALTER TABLE `transfer`
  ADD CONSTRAINT `transfer_ibfk_1` FOREIGN KEY (`source_account_id`) REFERENCES `account` (`account_id`),
  ADD CONSTRAINT `transfer_ibfk_2` FOREIGN KEY (`destination_account_id`) REFERENCES `account` (`account_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
