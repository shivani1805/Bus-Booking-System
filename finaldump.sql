CREATE DATABASE  IF NOT EXISTS `bus_ticket_booking` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bus_ticket_booking`;
-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: bus_ticket_booking
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `first_name` varchar(64) DEFAULT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `phone_no` bigint DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  KEY `username` (`username`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('Jane','Doe','janedoe@gmail.com',8576473827,'admin2'),('daisy','flower','df31@hotmail.com',9085647856,'daisy31');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_journey_booking`
--

DROP TABLE IF EXISTS `bus_journey_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_journey_booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `payment_method` enum('Credit Card','Debit Card','Online Wallet') DEFAULT NULL,
  `amount_paid` int DEFAULT NULL,
  `seats_booked` int DEFAULT NULL,
  `route_id` int DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `route_id` (`route_id`),
  KEY `username` (`username`),
  CONSTRAINT `bus_journey_booking_ibfk_1` FOREIGN KEY (`route_id`) REFERENCES `bus_route` (`route_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bus_journey_booking_ibfk_2` FOREIGN KEY (`username`) REFERENCES `customer` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_journey_booking`
--

LOCK TABLES `bus_journey_booking` WRITE;
/*!40000 ALTER TABLE `bus_journey_booking` DISABLE KEYS */;
INSERT INTO `bus_journey_booking` VALUES (2,'Online Wallet',50,3,3,'cust3'),(3,'Credit Card',80,4,4,'cust3'),(4,'Debit Card',55,2,5,'cust3'),(5,'Online Wallet',70,3,6,'cust3'),(6,'Credit Card',90,1,7,'cust3'),(7,'Debit Card',65,2,3,'lemo'),(8,'Online Wallet',60,3,4,'lemo'),(9,'Credit Card',85,4,5,'lemo'),(10,'Debit Card',75,1,6,'lemo'),(11,'Online Wallet',100,2,7,'lemo'),(12,'Credit Card',60,2,3,'cust3'),(13,'Debit Card',45,3,4,'cust3'),(14,'Online Wallet',30,2,5,'cust3'),(15,'Credit Card',70,1,6,'cust3'),(16,'Debit Card',35,2,7,'cust3'),(17,'Credit Card',65,2,3,'lemo'),(18,'Debit Card',50,3,4,'lemo'),(19,'Online Wallet',35,2,5,'lemo'),(20,'Credit Card',75,1,6,'lemo'),(21,'Debit Card',40,2,7,'lemo'),(22,'Credit Card',70,2,3,'aden@123'),(23,'Debit Card',55,3,4,'aden@123'),(24,'Online Wallet',40,2,5,'aden@123'),(25,'Credit Card',80,1,6,'aden@123'),(26,'Debit Card',45,2,7,'aden@123'),(28,'Credit Card',60,2,18,'aden@123'),(29,'Debit Card',45,3,19,'aden@123'),(30,'Online Wallet',30,2,20,'aden@123'),(31,'Credit Card',70,1,21,'aden@123'),(32,'Debit Card',35,2,22,'aden@123'),(33,'Online Wallet',50,3,18,'aden@123'),(34,'Credit Card',75,1,19,'aden@123'),(35,'Debit Card',40,2,20,'aden@123'),(36,'Online Wallet',55,3,21,'aden@123'),(37,'Credit Card',80,4,22,'aden@123'),(38,'Credit Card',65,2,23,'lemo'),(39,'Debit Card',50,3,24,'lemo'),(40,'Online Wallet',35,2,25,'lemo'),(41,'Credit Card',75,1,26,'lemo'),(42,'Debit Card',40,2,27,'lemo'),(43,'Credit Card',70,2,23,'lemo'),(44,'Debit Card',55,3,24,'lemo'),(45,'Online Wallet',40,2,25,'lemo'),(46,'Credit Card',80,1,26,'lemo'),(47,'Debit Card',45,2,27,'lemo'),(48,'Online Wallet',50,3,23,'cust3'),(49,'Credit Card',80,4,24,'cust3'),(50,'Debit Card',55,2,25,'cust3'),(51,'Online Wallet',70,3,26,'cust3'),(52,'Credit Card',90,1,27,'cust3'),(53,'Online Wallet',50,3,23,'cust3'),(54,'Credit Card',75,1,24,'cust3'),(55,'Debit Card',40,2,25,'cust3'),(56,'Online Wallet',55,3,26,'cust3'),(57,'Credit Card',80,4,27,'cust3');
/*!40000 ALTER TABLE `bus_journey_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_route`
--

DROP TABLE IF EXISTS `bus_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_route` (
  `route_id` int NOT NULL AUTO_INCREMENT,
  `total_fare` int DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `start_date_time` datetime DEFAULT NULL,
  `end_date_time` datetime DEFAULT NULL,
  `origin_city_name` varchar(64) DEFAULT NULL,
  `destination_city_name` varchar(64) DEFAULT NULL,
  `driver_id` int DEFAULT NULL,
  `bus_id` int DEFAULT NULL,
  PRIMARY KEY (`route_id`),
  KEY `origin_city_name` (`origin_city_name`),
  KEY `destination_city_name` (`destination_city_name`),
  KEY `driver_id` (`driver_id`),
  KEY `bus_id` (`bus_id`),
  CONSTRAINT `bus_route_ibfk_1` FOREIGN KEY (`origin_city_name`) REFERENCES `city` (`city_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bus_route_ibfk_2` FOREIGN KEY (`destination_city_name`) REFERENCES `city` (`city_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bus_route_ibfk_3` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bus_route_ibfk_4` FOREIGN KEY (`bus_id`) REFERENCES `bus_type` (`bus_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_route`
--

LOCK TABLES `bus_route` WRITE;
/*!40000 ALTER TABLE `bus_route` DISABLE KEYS */;
INSERT INTO `bus_route` VALUES (3,100,4,'2023-12-10 08:00:00','2023-12-10 12:00:00','New York','Boston',1,1),(4,80,3,'2023-12-11 10:00:00','2023-12-11 13:00:00','Chicago','Houston',2,2),(5,120,5,'2023-12-12 12:00:00','2023-12-12 17:00:00','Houston','Miami',3,3),(6,150,6,'2023-12-13 14:00:00','2023-12-13 20:00:00','Miami','New York',4,4),(7,90,3,'2023-12-14 09:00:00','2023-12-14 12:00:00','Boston','Chicago',5,5),(8,50,2,'2023-12-15 08:00:00','2023-12-15 10:00:00','Boston','Houston',3,51),(9,40,1,'2023-12-16 10:00:00','2023-12-16 11:00:00','New York','Miami',4,52),(10,60,3,'2023-12-17 12:00:00','2023-12-17 15:00:00','Chicago','Boston',5,53),(11,70,4,'2023-12-18 14:00:00','2023-12-18 18:00:00','New York','Miami',4,54),(12,45,2,'2023-12-19 09:00:00','2023-12-19 11:00:00','Miami','Houston',5,55),(13,80,4,'2023-12-15 08:00:00','2023-12-15 12:00:00','New York','Boston',6,64),(14,90,5,'2023-12-16 10:00:00','2023-12-16 15:00:00','Boston','Miami',7,62),(15,100,6,'2023-12-17 12:00:00','2023-12-17 18:00:00','Chicago','New York',8,63),(16,85,4,'2023-12-18 14:00:00','2023-12-18 18:00:00','Houston','Miami',6,62),(17,75,3,'2023-12-19 09:00:00','2023-12-19 12:00:00','Miami','Chicago',9,61),(18,50,2,'2023-12-15 08:00:00','2023-12-15 10:00:00','Boston','Houston',3,51),(19,40,1,'2023-12-16 10:00:00','2023-12-16 11:00:00','New York','Miami',4,52),(20,60,3,'2023-12-17 12:00:00','2023-12-17 15:00:00','Chicago','Boston',5,53),(21,70,4,'2023-12-18 14:00:00','2023-12-18 18:00:00','New York','Miami',4,54),(22,45,2,'2023-12-19 09:00:00','2023-12-19 11:00:00','Miami','Houston',5,55),(23,80,4,'2023-12-15 08:00:00','2023-12-15 12:00:00','New York','Boston',6,64),(24,90,5,'2023-12-16 10:00:00','2023-12-16 15:00:00','Boston','Miami',7,62),(25,100,6,'2023-12-17 12:00:00','2023-12-17 18:00:00','Chicago','New York',8,63),(26,85,4,'2023-12-18 14:00:00','2023-12-18 18:00:00','Houston','Miami',6,62),(27,75,3,'2023-12-19 09:00:00','2023-12-19 12:00:00','Miami','Chicago',9,61);
/*!40000 ALTER TABLE `bus_route` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `duration_update` BEFORE UPDATE ON `bus_route` FOR EACH ROW BEGIN
    SET NEW.duration = TIMESTAMPDIFF(HOUR, NEW.start_date_time, NEW.end_date_time);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `bus_type`
--

DROP TABLE IF EXISTS `bus_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_type` (
  `bus_id` int NOT NULL AUTO_INCREMENT,
  `type` enum('sleeper','seater','Double Decker','Shuttle') DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `model` varchar(64) DEFAULT NULL,
  `color` varchar(64) DEFAULT NULL,
  `license_plate` varchar(64) NOT NULL,
  PRIMARY KEY (`bus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_type`
--

LOCK TABLES `bus_type` WRITE;
/*!40000 ALTER TABLE `bus_type` DISABLE KEYS */;
INSERT INTO `bus_type` VALUES (1,'sleeper',50,'Volvo XYZ','Blue','XYZ123'),(2,'seater',30,'Mercedes ABC','Red','ABC456'),(3,'sleeper',40,'Scania DEF','Green','DEF789'),(4,'seater',35,'Volvo GHI','Yellow','GHI012'),(5,'sleeper',45,'Mercedes JKL','Purple','JKL345'),(6,'seater',25,'Scania MNO','Orange','MNO678'),(7,'sleeper',55,'Volvo PQR','Pink','PQR901'),(8,'seater',32,'Mercedes STU','Brown','STU234'),(9,'sleeper',38,'Scania VWX','Black','VWX567'),(10,'seater',28,'Volvo YZA','White','YZA890'),(11,'sleeper',48,'Mercedes BCD','Gray','BCD123'),(12,'seater',33,'Scania EFG','Silver','EFG456'),(13,'sleeper',42,'Volvo HIJ','Gold','HIJ789'),(14,'seater',27,'Mercedes KLM','Cyan','KLM012'),(15,'sleeper',52,'Scania NOP','Magenta','NOP345'),(16,'seater',29,'Volvo QRS','Indigo','QRS678'),(17,'sleeper',43,'Mercedes TUV','Lime','TUV901'),(18,'seater',31,'Scania WXY','Maroon','WXY234'),(19,'sleeper',47,'Volvo ZAB','Olive','ZAB567'),(20,'seater',26,'Mercedes CDE','Turquoise','CDE890'),(21,'sleeper',53,'Scania FGH','Violet','FGH123'),(22,'seater',34,'Volvo IJK','Teal','IJK456'),(23,'sleeper',39,'Mercedes LMN','Beige','LMN789'),(24,'seater',36,'Scania OPQ','Salmon','OPQ012'),(25,'sleeper',44,'Volvo RST','Khaki','RST345'),(26,'seater',23,'Mercedes UVW','Coral','UVW678'),(27,'sleeper',49,'Scania XYZ','Aquamarine','XYZ901'),(28,'seater',37,'Volvo ABC','Plum','ABC234'),(29,'sleeper',41,'Mercedes DEF','NavajoWhite','DEF567'),(30,'sleeper',40,'DreamSleeper','Blue','ABC123'),(31,'seater',50,'TravelMax','Red','XYZ789'),(32,'Double Decker',80,'DoubleDeluxe','White','DEF456'),(33,'Shuttle',20,'CityHopper','Green','GHI789'),(34,'sleeper',45,'CozyNap','Purple','JKL012'),(35,'seater',55,'EconomyExpress','Yellow','MNO345'),(36,'Double Decker',75,'LuxuryLiner','Silver','PQR678'),(37,'Shuttle',25,'QuickShuttle','Orange','STU901'),(38,'sleeper',42,'SlumberRide','Green','VWX234'),(39,'seater',48,'CityComfort','Blue','YZA567'),(40,'Double Decker',82,'SuperDeck','Red','BCD890'),(41,'Shuttle',22,'MetroShuttle','Yellow','EFG123'),(42,'sleeper',38,'RestfulRide','Gray','HIJ456'),(43,'seater',60,'RoyalTransit','Black','KLM789'),(44,'Double Decker',85,'MegaDeck','Purple','NOP012'),(45,'Shuttle',18,'SpeedyShuttle','Orange','QRS345'),(46,'sleeper',44,'SweetDreams','Blue','TUV678'),(47,'seater',53,'ExpressWay','Green','WXY901'),(48,'Double Decker',78,'HighDeck','Red','ZAB234'),(49,'Shuttle',30,'CityMover','Silver','BCD567'),(50,'Double Decker',80,'DD101','Red','ABC123'),(51,'Double Decker',80,'DD102','Blue','DEF456'),(52,'Double Decker',80,'DD103','Green','GHI789'),(53,'Double Decker',80,'DD104','Yellow','JKL012'),(54,'Double Decker',80,'DD105','Purple','MNO345'),(55,'Double Decker',80,'DD106','Orange','PQR678'),(56,'Double Decker',80,'DD107','Pink','STU901'),(57,'Double Decker',80,'DD108','Brown','VWX234'),(58,'Double Decker',80,'DD109','Gray','YZA567'),(59,'Double Decker',80,'DD110','Black','BCD890'),(60,'Shuttle',30,'SH101','White','EFG123'),(61,'Shuttle',30,'SH102','Silver','HIJ456'),(62,'Shuttle',30,'SH103','Gold','KLM789'),(63,'Shuttle',30,'SH104','Cyan','NOP012'),(64,'Shuttle',30,'SH105','Magenta','QRS345'),(65,'Shuttle',30,'SH106','Bronze','TUV678'),(66,'Shuttle',30,'SH107','Indigo','WXY901'),(67,'Shuttle',30,'SH108','Lime','ZAB234'),(68,'Shuttle',30,'SH109','Turquoise','BCD567'),(69,'Shuttle',30,'SH110','Maroon','EFG890');
/*!40000 ALTER TABLE `bus_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `city_id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(64) DEFAULT NULL,
  `state` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `city_name` (`city_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'New York','NY'),(2,'Boston','MA'),(3,'Chicago','IL'),(4,'Houston','TX'),(5,'Miami','FL');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `first_name` varchar(64) DEFAULT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `phone_no` bigint DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  KEY `username` (`username`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('jay','z','jayz@yahoo.com',8657465734,'cust3'),('lemo','nade','lemonade@gmail.com',7453675898,'lemo'),('aden','pitt','adenp@gmail.com',7463456898,'aden@123');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver` (
  `driver_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(64) DEFAULT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `phone_no` bigint DEFAULT NULL,
  `license_no` varchar(10) NOT NULL,
  PRIMARY KEY (`driver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'John','Doe',1234567890,'DL12345'),(2,'Alice','Smith',9876543210,'DL67890'),(3,'Bob','Johnson',8765432109,'DL54321'),(4,'Emma','Wilson',7654321098,'DL09876'),(5,'David','Lee',6543210987,'DL23456'),(6,'Sarah','Brown',5432109876,'DL65432'),(7,'Ryan','Miller',4321098765,'DL78901'),(8,'Olivia','Wong',3210987654,'DL32109'),(9,'Michael','Chen',2109876543,'DL56789'),(10,'Bob','Johnson',8765432109,'');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(64) NOT NULL,
  `user_role` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('aden@123','customer'),('admin2','admin'),('cust3','customer'),('daisy31','admin'),('lemo','customer');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bus_ticket_booking'
--

--
-- Dumping routines for database 'bus_ticket_booking'
--
/*!50003 DROP FUNCTION IF EXISTS `getCountOfBus` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getCountOfBus`(busId INT) RETURNS int
    NO SQL
BEGIN
    DECLARE countResult INT;

SELECT COUNT(*) INTO countResult
FROM bus_type
WHERE bus_id = busId;

RETURN countResult ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getCountOfCity` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getCountOfCity`(cityName varchar(64)) RETURNS int
    NO SQL
BEGIN
    DECLARE countResult INT;

SELECT COUNT(*) INTO countResult
FROM city
WHERE city_name = cityName;

RETURN countResult ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getCountOfDriver` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getCountOfDriver`(driverId INT) RETURNS int
    NO SQL
BEGIN
    DECLARE countResult INT;

SELECT COUNT(*) INTO countResult
FROM driver
WHERE driver_id = driverId;

RETURN countResult;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getCountOfRoute` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getCountOfRoute`(routeId INT) RETURNS int
    NO SQL
BEGIN
    DECLARE countResult INT;

SELECT COUNT(*) INTO countResult
FROM bus_route
WHERE route_id = routeId;

RETURN countResult ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `bus_booking_chart` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `bus_booking_chart`()
BEGIN
SELECT bt.type AS bus_type, COUNT(bjb.booking_id) AS total_bookings
FROM bus_journey_booking bjb
         JOIN bus_route br ON bjb.route_id = br.route_id
         JOIN bus_type bt ON br.bus_id = bt.bus_id
GROUP BY bt.type;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `check_booking_exists` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_booking_exists`(booking_id int)
BEGIN
SELECT *
FROM bus_journey_booking as bjb
WHERE bjb.booking_id=booking_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `check_booking_ids` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_booking_ids`(username varchar(64))
BEGIN
SELECT bjb.booking_id
FROM bus_journey_booking as bjb
WHERE bjb.username=username;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_booking` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_booking`(
    IN route_id INT,
    IN total_fare INT,
    IN payment_method enum ("Credit Card", "Debit Card", "Online Wallet"),
    IN seats INT,
    username VARCHAR(64)
        )
BEGIN
INSERT INTO bus_journey_booking (payment_method, amount_paid, seats_booked, route_id, username)
VALUES (payment_method, total_fare, seats, route_id, username);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_booking` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_booking`(id int)
BEGIN
DELETE FROM bus_journey_booking WHERE bus_journey_booking.booking_id = id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `edit_route` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `edit_route`(booking_Id INT, route_id_new INT, new_fare INT)
BEGIN
UPDATE bus_journey_booking SET route_id = route_id_new WHERE bus_journey_booking.booking_id = booking_Id;
UPDATE bus_journey_booking SET amount_paid = new_fare WHERE bus_journey_booking.booking_id = booking_Id;
SELECT 'Route updated successfully' AS result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_bus` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_bus`(
    IN bus_type enum("sleeper","seater","Double Decker","Shuttle"),
    IN bus_cap int,
    IN bus_model varchar(64),
    IN bus_color varchar(64),
    IN bus_plate varchar(64)
        )
BEGIN
INSERT INTO bus_type (type, capacity,model,color,license_plate)
VALUES (bus_Type, bus_cap,bus_model,bus_color,bus_plate);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_city` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_city`(
    IN cityName varchar(64),
    IN stateName varchar(64)
)
BEGIN
INSERT INTO city (city_name, state)
VALUES (cityName, stateName);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_driver` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_driver`(
    IN firstName varchar(64),
    IN lastName varchar(64),
    IN phone BIGINT,
    IN license varchar(10)
)
BEGIN
INSERT INTO driver (first_name, last_name, phone_no, license_no)
VALUES (firstName, lastName, phone, license);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_route` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_route`(
    IN totalFare INT,
    IN startTime DATETIME,
    IN endTime DATETIME,
    IN origin VARCHAR(64),
    IN dest VARCHAR(64),
    IN driver INT,
    IN bus INT
)
BEGIN
    DECLARE calculatedDuration INT;


    SET calculatedDuration = TIMESTAMPDIFF(HOUR, startTime, endTime);


INSERT INTO bus_route (
    total_fare,
    start_date_time,
    end_date_time,
    origin_city_name,
    destination_city_name,
    driver_id,
    bus_id,
    duration
)
VALUES (
           totalFare,
           startTime,
           endTime,
           origin,
           dest,
           driver,
           bus,
           calculatedDuration
       );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_admine` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_admine`(username VARCHAR(64), new_email VARCHAR(64))
BEGIN
UPDATE admin SET email = new_email WHERE admin.username = username;
SELECT 'Email ID update successfully' AS result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_adminfn` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_adminfn`(username VARCHAR(64), new_fn VARCHAR(64))
BEGIN
UPDATE admin SET first_name = new_fn WHERE admin.username = username;
SELECT 'First name update successfully' AS result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_adminln` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_adminln`(username VARCHAR(64), new_ln VARCHAR(64))
BEGIN
UPDATE admin SET last_name = new_ln WHERE admin.username = username;
SELECT 'Last name update successfully' AS result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_adminpn` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_adminpn`(username VARCHAR(64), new_pn bigint)
BEGIN
UPDATE admin SET phone_no = new_pn WHERE admin.username = username;
SELECT 'Phone number update successfully' AS result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_e` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_e`(username VARCHAR(64), new_email VARCHAR(64))
BEGIN
UPDATE customer SET email = new_email WHERE customer.username = username;
SELECT 'Email ID update successfully' AS result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_fn` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_fn`(username VARCHAR(64), new_fn VARCHAR(64))
BEGIN
UPDATE customer SET first_name = new_fn WHERE customer.username = username;
SELECT 'First name update successfully' AS result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_ln` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_ln`(username VARCHAR(64), new_ln VARCHAR(64))
BEGIN
UPDATE customer SET last_name = new_ln WHERE customer.username = username;
SELECT 'Last name update successfully' AS result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_pn` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_pn`(username VARCHAR(64), new_pn bigint)
BEGIN
UPDATE customer SET phone_no = new_pn WHERE customer.username = username;
SELECT 'Phone number update successfully' AS result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_seats` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_seats`(p_bookingId INT, p_newSeatCount INT)
BEGIN
UPDATE bus_journey_booking SET seats_booked = p_newSeatCount WHERE bus_journey_booking.booking_id = p_bookingId;
SELECT 'Seat count update successfully' AS result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `view_admin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `view_admin`(username varchar(64))
BEGIN
SELECT a.first_name, a.last_name, a.email, a.phone_no, a.username, u.user_role
FROM admin as a
         INNER JOIN user as u ON u.username = a.username
WHERE a.username=username;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `view_booking` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `view_booking`(booking_id int)
BEGIN
SELECT bjb.booking_id, br.origin_city_name, br.destination_city_name, br.start_date_time, br.end_date_time, br.duration, bjb.seats_booked, bjb.amount_paid, bjb.payment_method, bt.type, bt.capacity, bt.model, bt.color, d.first_name, d.last_name
FROM bus_journey_booking AS bjb
         INNER JOIN bus_route AS br ON bjb.route_id = br.route_id
         INNER JOIN bus_type AS bt ON br.bus_id = bt.bus_id
         INNER JOIN driver AS d ON br.driver_id = d.driver_id
WHERE bjb.booking_id=booking_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `view_bus_route` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `view_bus_route`()
BEGIN
SELECT route.route_id, route.total_fare, route.duration, route.start_date_time, route.end_date_time
        ,route.origin_city_name, route.destination_city_name, bt.license_plate,
       CONCAT(d.first_name,' ',d.last_name) AS driver_name, d.license_no
FROM bus_route AS route
         INNER JOIN bus_type AS bt ON route.bus_id = bt.bus_id
         INNER JOIN driver AS d ON route.driver_id = d.driver_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `view_cust_booking` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `view_cust_booking`()
BEGIN
SELECT bjb.booking_id, br.origin_city_name, br.destination_city_name, br.start_date_time, br.end_date_time, br.duration, bjb.seats_booked, bjb.amount_paid, bjb.payment_method, bt.type, bt.capacity, bt.model, bt.color, d.first_name, d.last_name
FROM bus_journey_booking AS bjb
         INNER JOIN bus_route AS br ON bjb.route_id = br.route_id
         INNER JOIN bus_type AS bt ON br.bus_id = bt.bus_id
         INNER JOIN driver AS d ON br.driver_id = d.driver_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `view_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `view_user`(username varchar(64))
BEGIN
SELECT c.first_name, c.last_name, c.email, c.phone_no, c.username, u.user_role
FROM customer as c
         INNER JOIN user as u ON u.username = c.username
WHERE c.username=username;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-08 21:49:13
