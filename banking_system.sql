-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 01, 2024 at 08:40 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banking_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `account_type` varchar(50) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `customer_id`, `account_type`, `balance`) VALUES
(1, 1, 'Savings', 5000.00),
(2, 1, 'Checking', 2500.00),
(3, 2, 'Savings', 10000.00),
(4, 3, 'Checking', 3500.00),
(5, 4, 'Savings', 7500.00),
(6, 5, 'Checking', 4612.00),
(7, 6, 'Savings', 6000.00),
(9, 1, 'SAVINGS', 0.00),
(10, 1, 'CHECKING', 0.00),
(11, 1, 'SAVINGS', 0.00),
(12, 1, 'CHECKING', 0.00),
(13, 1, 'SAVINGS', 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `name`, `email`) VALUES
(1, 'John Doe', 'john.doe@example.com'),
(2, 'Alice Smith', 'alice.smith@example.com'),
(3, 'Bob Johnson', 'bob.johnson@example.com'),
(4, 'Eva Anderson', 'eva.anderson@example.com'),
(5, 'Michael Clark', 'michael.clark@example.com'),
(6, 'Sophia White', 'sophia.white@example.com'),
(7, 'hehe', 'boi'),
(8, 'hi', 'bio'),
(10, 'hehe boi', 'lmao@lol.hehe');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `transaction_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `account_id`, `type`, `amount`, `transaction_date`) VALUES
(1, 1, 'Deposit', 1000.00, '2024-01-01 18:37:44'),
(2, 1, 'Withdrawal', 500.00, '2024-01-01 18:37:44'),
(3, 2, 'Deposit', 1500.00, '2024-01-01 18:37:44'),
(4, 3, 'Withdrawal', 200.00, '2024-01-01 18:37:44'),
(5, 4, 'Deposit', 2000.00, '2024-01-01 18:37:44'),
(6, 4, 'Withdrawal', 1000.00, '2024-01-01 18:37:44'),
(7, 5, 'Deposit', 2000.00, '2024-01-01 18:37:44'),
(8, 4, 'Withdrawal', 800.00, '2024-01-01 18:37:44'),
(9, 6, 'Deposit', 1200.00, '2024-01-01 18:37:44'),
(10, 2, 'Deposit', 300.00, '2024-01-01 18:37:44'),
(11, 3, 'Withdrawal', 600.00, '2024-01-01 18:37:44');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
