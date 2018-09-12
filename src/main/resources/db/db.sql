/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `pln_balance` float NOT NULL DEFAULT '0',
  `usd_balance` float NOT NULL DEFAULT '0',
  `eur_balance` float NOT NULL DEFAULT '0',
  `cny_balance` float NOT NULL DEFAULT '0',
  `gbp_balance` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `add_funds`
--

DROP TABLE IF EXISTS `add_funds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `add_funds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `amount` float NOT NULL,
  `currency` varchar(45) NOT NULL,
  `payment_method` int(2) NOT NULL,
  `status` int(2) NOT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_edit_date` datetime DEFAULT NULL,
  `finish_date` datetime DEFAULT NULL,
  `private_indivdal_code` varchar(45) NOT NULL,
  `transaction_id_from_payment_system` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `private_indivdal_code_UNIQUE` (`private_indivdal_code`),
  KEY `add_funds_account_user_id` (`user_id`),
  CONSTRAINT `add_funds_account_user_id` FOREIGN KEY (`user_id`) REFERENCES `account` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `casino_game_transaction_details`
--

DROP TABLE IF EXISTS `casino_game_transaction_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casino_game_transaction_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `result_number` varchar(45) NOT NULL,
  `used_hash` varchar(500) NOT NULL,
  `private_indivdal_code` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `private_indivdal_code_UNIQUE` (`private_indivdal_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6619 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `casino_transactions`
--

DROP TABLE IF EXISTS `casino_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casino_transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `amount` float DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `kind_of_bet` int(1) DEFAULT NULL,
  `user_number` int(2) DEFAULT NULL,
  `won_amount` float DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_edit_date` datetime DEFAULT NULL,
  `finish_date` datetime DEFAULT NULL,
  `private_indivdal_code` varchar(45) DEFAULT NULL,
  `game_spin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `private_indivdal_code_UNIQUE` (`private_indivdal_code`),
  KEY `casino_transactions_account_user_id` (`user_id`),
  KEY `casino_transactions_casino_game_transaction_details` (`game_spin_id`),
  CONSTRAINT `casino_transactions_account_user_id` FOREIGN KEY (`user_id`) REFERENCES `account` (`user_id`),
  CONSTRAINT `casino_transactions_casino_game_transaction_details` FOREIGN KEY (`game_spin_id`) REFERENCES `casino_game_transaction_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7243 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pair_name` varchar(9) NOT NULL,
  `rate_value` float NOT NULL,
  `from_currency` varchar(3) NOT NULL,
  `to_currency` varchar(3) NOT NULL,
  `add_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `currency_transaction`
--

DROP TABLE IF EXISTS `currency_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency_transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `currency_from` varchar(45) DEFAULT NULL,
  `currency_to` varchar(45) DEFAULT NULL,
  `amount_original_currency` float DEFAULT NULL,
  `amount_bought_currency` float DEFAULT NULL,
  `base_currency_rate` float DEFAULT NULL,
  `currency_rate_with_fee` float DEFAULT NULL,
  `fee_rate` float DEFAULT NULL,
  `fee` float DEFAULT NULL,
  `kind_of_operation` int(1) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `private_indivdal_code` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_edit_date` datetime DEFAULT NULL,
  `finish_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `private_indivdal_code_UNIQUE` (`private_indivdal_code`),
  KEY `currency_transaction_account_user_id` (`user_id`),
  CONSTRAINT `currency_transaction_account_user_id` FOREIGN KEY (`user_id`) REFERENCES `account` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transfer_funds`
--

DROP TABLE IF EXISTS `transfer_funds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transfer_funds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_from` int(11) NOT NULL,
  `user_id_to` int(11) NOT NULL,
  `currency` varchar(45) NOT NULL,
  `amount` float NOT NULL,
  `fee` float NOT NULL,
  `amount_after_fees` double NOT NULL,
  `status` int(2) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_edit_date` datetime DEFAULT NULL,
  `finish_date` datetime DEFAULT NULL,
  `private_indivdal_code` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `private_indivdal_code_UNIQUE` (`private_indivdal_code`),
  KEY `transfer_funds_account_user_id_from` (`user_id_from`),
  KEY `transfer_funds_account_user_id_to` (`user_id_to`),
  CONSTRAINT `transfer_funds_account_user_id_from` FOREIGN KEY (`user_id_from`) REFERENCES `account` (`user_id`),
  CONSTRAINT `transfer_funds_account_user_id_to` FOREIGN KEY (`user_id_to`) REFERENCES `account` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `account` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-12 16:55:45