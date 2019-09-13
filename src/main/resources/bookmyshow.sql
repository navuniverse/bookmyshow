CREATE DATABASE IF NOT EXISTS `bookmyshow`;

USE `bookmyshow`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;

CREATE TABLE `movies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `certificate_type` varchar(255) NOT NULL,
  `language` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `release_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `theaters`
--

DROP TABLE IF EXISTS `theaters`;

CREATE TABLE `theaters` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `theater_seats`
--

DROP TABLE IF EXISTS `theater_seats`;

CREATE TABLE `theater_seats` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rate` int(11) NOT NULL,
  `seat_number` varchar(255) NOT NULL,
  `seat_type` varchar(255) NOT NULL,
  `theater_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1y5u2vlcimt7yh0g93ruptxh6` (`theater_id`),
  CONSTRAINT `FK1y5u2vlcimt7yh0g93ruptxh6` FOREIGN KEY (`theater_id`) REFERENCES `theaters` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `shows`
--

DROP TABLE IF EXISTS `shows`;

CREATE TABLE `shows` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `rate_multiplier` float(2,1) NOT NULL DEFAULT '1.0',
  `show_date` date NOT NULL,
  `show_time` time NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `movie_id` bigint(20) DEFAULT NULL,
  `theater_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqdpwhiv5r3lx844pct0eudapk` (`movie_id`),
  KEY `FK2jn1xrqhda6lv56dpegfb3gvn` (`theater_id`),
  CONSTRAINT `FK2jn1xrqhda6lv56dpegfb3gvn` FOREIGN KEY (`theater_id`) REFERENCES `theaters` (`id`),
  CONSTRAINT `FKqdpwhiv5r3lx844pct0eudapk` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;

CREATE TABLE `tickets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alloted_seats` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `booked_at` datetime NOT NULL,
  `show_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKosj8dc2tn2tcidsfimopidq13` (`show_id`),
  KEY `FK4eqsebpimnjen0q46ja6fl2hl` (`user_id`),
  CONSTRAINT `FK4eqsebpimnjen0q46ja6fl2hl` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKosj8dc2tn2tcidsfimopidq13` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `show_seats`
--

DROP TABLE IF EXISTS `show_seats`;

CREATE TABLE `show_seats` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_booked` bit(1) NOT NULL DEFAULT b'0',
  `booked_at` datetime DEFAULT NULL,
  `rate` int(11) NOT NULL,
  `seat_number` varchar(255) NOT NULL,
  `seat_type` varchar(255) NOT NULL,
  `show_id` bigint(20) DEFAULT NULL,
  `ticket_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKldtrq74q8syptlbgqag9cw9w1` (`show_id`),
  KEY `FKpmjwdp0tmam4e5b53rb043jqk` (`ticket_id`),
  CONSTRAINT `FKldtrq74q8syptlbgqag9cw9w1` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`),
  CONSTRAINT `FKpmjwdp0tmam4e5b53rb043jqk` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
