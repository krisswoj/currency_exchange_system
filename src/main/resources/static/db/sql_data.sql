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
-- Table structure for table `user`
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
  PRIMARY KEY (`user_id`)
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

INSERT INTO `role` VALUES (1,'USER');

--
-- Table structure for table `rod`
--

DROP TABLE IF EXISTS `rod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rod` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `optional_information` varchar(255) NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `ready_meal_details`
--

DROP TABLE IF EXISTS `food_ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_ingredient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `category` int(11) DEFAULT NULL,
  `subcategory` int(11) DEFAULT NULL,
  `amount_protins` INT(11) DEFAULT NULL,
  `amount_carbs` INT(11) DEFAULT NULL,
  `amount_fats` INT(11) DEFAULT NULL,
  `created_date` TIMESTAMP NOT NULL,
  `update_date` TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ready_meal`
--

DROP TABLE IF EXISTS `ready_meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ready_meal` (
  `meal_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `title` varchar(255) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`meal_id`),
  KEY `FK_user_id_ready_meal_account_34twegah` (`user_id`),
  CONSTRAINT `FK_user_id_ready_meal_account_34twegah` FOREIGN KEY (`user_id`) REFERENCES `account` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ready_meal_details`
--

DROP TABLE IF EXISTS `ready_meal_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ready_meal_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meal_id` int(11) DEFAULT NULL,
  `food_ingredient_id` int(11) DEFAULT NULL,
  `grams_portion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ready_meal_id_ready_meal_details_34twegah` (`meal_id`),
  CONSTRAINT `FK_ready_meal_id_ready_meal_details_34twegah` FOREIGN KEY (`meal_id`) REFERENCES `ready_meal` (`meal_id`),
  CONSTRAINT `FK_calorie_id_food_ingredient` FOREIGN KEY (`food_ingredient_id`) REFERENCES `food_ingredient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `ready_meal_details`
--

DROP TABLE IF EXISTS `calorie_calculator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calorie_calculator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sex` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `planned_effort` int(11) DEFAULT NULL,
  `body_goal` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `body_type` int(11) DEFAULT NULL,
  `calories_demand` int(11) DEFAULT NULL,
  `created_date` TIMESTAMP NOT NULL,
  `update_date` TIMESTAMP,
  KEY `FK_user_id_calorie_calculator` (`user_id`),
  CONSTRAINT `FK_user_id_calorie_calculator` FOREIGN KEY (`user_id`) REFERENCES `account` (`user_id`),

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

