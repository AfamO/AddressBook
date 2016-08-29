-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 29, 2016 at 04:23 PM
-- Server version: 5.1.36
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `phonebook`
--

-- --------------------------------------------------------

--
-- Table structure for table `phonebook`
--

CREATE TABLE IF NOT EXISTS `phonebook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(15) NOT NULL,
  `lastName` varchar(15) NOT NULL,
  `email` varchar(25) NOT NULL,
  `address` text NOT NULL,
  `city` varchar(12) NOT NULL,
  `zip` int(6) NOT NULL,
  `state` varchar(14) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `user_id` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `phonebook`
--

INSERT INTO `phonebook` (`id`, `firstName`, `lastName`, `email`, `address`, `city`, `zip`, `state`, `phone`, `user_id`) VALUES
(1, 'Clinton', 'Dan', 'clin@gmail.com', '6a and 6b Sule Abuka Crescent Off Opebi Road\r\nIkeja', 'Texas', 100281, 'Lagos', '08064500095', '2'),
(2, 'Clinton', 'Dan', 'clin@gmail.com', '6a and 6b Sule Abuka Crescent Off Opebi Road\r\nIkeja', 'Texas', 100281, 'Lagos', '08064500095', '2'),
(3, 'Clinton', 'Hillary', 'hill@yahoo.com', '9a and 6b Sule Abuka Crescent Off Opebi Road\r\nIkeja', 'NewYork', 100283, 'Enugu', '8064511195', '1'),
(4, 'Clinton', 'Hillary', 'hill@yahoo.com', '9a and 6b Sule Abuka Crescent Off Opebi Road\r\nIkeja', 'NewYork', 100283, 'Enugu', '8064511195', '1');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `password` varchar(34) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`) VALUES
(1, 'Ciga Igbo ', 'ciga@phonebook.com', 'c48652551683566039edc921b38cf70a'),
(2, 'George Bush ', 'bush@phonebook.com', 'dbbc546e357040b4a83a65f387d5c06a');
