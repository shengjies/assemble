-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: jg2
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tab_company`
--

DROP TABLE IF EXISTS `tab_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tab_company` (
  `id` int(11) NOT NULL,
  `cname` varchar(45) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `iso_path` varchar(45) DEFAULT NULL,
  `iso` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `spwd` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_company`
--

LOCK TABLES `tab_company` WRITE;
/*!40000 ALTER TABLE `tab_company` DISABLE KEYS */;
INSERT INTO `tab_company` VALUES (9,'公司9',2,NULL,'iso25','2019-06-02 12:38:39','1308b28d90ec4b71808985e45180a0c0'),(10,'公司10',2,NULL,'iso25','2019-06-02 12:40:09','1308b28d90ec4b71808985e45180a0c0'),(11,'公司11',2,NULL,'iso26','2019-06-02 12:42:01','1308b28d90ec4b71808985e45180a0c0'),(12,'公司12',2,NULL,'iso27','2019-06-02 12:42:59','1308b28d90ec4b71808985e45180a0c0'),(13,'公司13',2,NULL,'iso28','2019-06-02 12:45:26','1308b28d90ec4b71808985e45180a0c0');
/*!40000 ALTER TABLE `tab_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_user`
--

DROP TABLE IF EXISTS `tab_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tab_user` (
  `id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  `sign` int(11) DEFAULT '0',
  `company_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_user`
--

LOCK TABLES `tab_user` WRITE;
/*!40000 ALTER TABLE `tab_user` DISABLE KEYS */;
INSERT INTO `tab_user` VALUES (8,'ajie2','123',0,10,'2019-06-02 12:40:10'),(9,'ajie3','123',0,11,'2019-06-02 12:42:01'),(10,'ajie4','1234',0,12,'2019-06-02 12:42:59'),(11,'ajie5','123',0,13,'2019-06-02 12:45:27');
/*!40000 ALTER TABLE `tab_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'jg2'
--

--
-- Dumping routines for database 'jg2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-02 21:42:56
