-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: capcal
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `foodlog`
--

DROP TABLE IF EXISTS `foodlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foodlog` (
  `id` bigint NOT NULL,
  `calories` int NOT NULL,
  `food_name` varchar(255) DEFAULT NULL,
  `food_date` datetime(6) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  CONSTRAINT `foodlog_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `foodlog_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodlog`
--

LOCK TABLES `foodlog` WRITE;
/*!40000 ALTER TABLE `foodlog` DISABLE KEYS */;
INSERT INTO `foodlog` VALUES (1002,3000,'Butter','2023-05-11 23:45:15.000000','aboli'),(1003,0,'Butter','2023-05-11 23:45:15.000000','aboli'),(1203,3000,'Banana','2023-05-12 23:45:15.000000','aboli'),(1204,3000,'Banana','2023-05-13 23:45:15.000000','aboli'),(1205,3000,'Banana','2023-05-14 23:45:15.000000','aboli'),(1206,3000,'Banana','2023-05-15 23:45:15.000000','aboli'),(1207,3000,'Banana','2023-05-16 23:45:15.000000','aboli'),(1212,500,'Burger','2023-05-12 23:45:15.000000','aboli'),(1213,500,'Burger','2023-05-13 23:45:15.000000','aboli'),(1214,500,'Burger','2023-05-14 23:45:15.000000','aboli'),(1215,500,'Burger','2023-05-15 23:45:15.000000','aboli'),(1216,500,'Burger','2023-05-16 23:45:15.000000','aboli'),(1219,400,'Milkshake','2023-05-11 23:45:15.000000','aboli'),(1220,400,'Milkshake','2023-05-12 23:45:15.000000','aboli'),(1221,400,'Milkshake','2023-05-13 23:45:15.000000','aboli'),(1222,400,'Milkshake','2023-05-14 23:45:15.000000','aboli'),(1223,400,'Milkshake','2023-05-15 23:45:15.000000','aboli'),(1224,400,'Milkshake','2023-05-16 23:45:15.000000','aboli'),(1225,400,'Milkshake','2023-05-17 23:45:15.000000','aboli'),(1227,900,'Chicken fried rice','2023-05-13 23:45:15.000000','aboli'),(1228,900,'Chicken fried rice','2023-05-16 23:45:15.000000','aboli'),(1229,900,'Chicken fried rice','2023-05-17 23:45:15.000000','aboli'),(1230,900,'Chicken fried rice','2023-05-11 23:45:15.000000','aboli'),(1232,300,'Cafe mocha','2023-05-13 23:45:15.000000','aboli'),(1233,300,'Cafe mocha','2023-05-15 23:45:15.000000','aboli'),(1234,300,'Cafe mocha','2023-05-17 23:45:15.000000','aboli'),(1239,4000,'Hot dog','2023-03-08 08:01:00.000000','aboli'),(1252,2000,'Ice Cream','2023-05-08 11:44:00.000000','aboli'),(1253,2000,'Ice Cream','2023-03-08 02:45:00.000000','aboli'),(1354,3000,'Butter','2023-05-11 23:45:15.000000','james'),(1355,0,'Butter','2023-05-11 23:45:15.000000','james'),(1356,3000,'Banana','2023-05-12 23:45:15.000000','james'),(1357,3000,'Banana','2023-05-13 23:45:15.000000','james'),(1358,3000,'Banana','2023-05-14 23:45:15.000000','james'),(1359,3000,'Banana','2023-05-15 23:45:15.000000','james'),(1360,3000,'Banana','2023-05-16 23:45:15.000000','james'),(1361,3000,'Banana','2023-05-17 23:45:15.000000','james'),(1365,500,'Burger','2023-05-12 23:45:15.000000','james'),(1366,500,'Burger','2023-05-13 23:45:15.000000','james'),(1367,500,'Burger','2023-05-14 23:45:15.000000','james'),(1368,500,'Burger','2023-05-15 23:45:15.000000','james'),(1369,500,'Burger','2023-05-16 23:45:15.000000','james'),(1370,500,'Burger','2023-05-17 23:45:15.000000','james'),(1372,400,'Milkshake','2023-05-11 23:45:15.000000','james'),(1373,400,'Milkshake','2023-05-12 23:45:15.000000','james'),(1374,400,'Milkshake','2023-05-13 23:45:15.000000','james'),(1375,400,'Milkshake','2023-05-14 23:45:15.000000','james'),(1376,400,'Milkshake','2023-05-15 23:45:15.000000','james'),(1377,400,'Milkshake','2023-05-16 23:45:15.000000','james'),(1378,400,'Milkshake','2023-05-17 23:45:15.000000','james'),(1380,900,'Chicken fried rice','2023-05-13 23:45:15.000000','james'),(1381,900,'Chicken fried rice','2023-05-16 23:45:15.000000','james'),(1382,900,'Chicken fried rice','2023-05-17 23:45:15.000000','james'),(1383,900,'Chicken fried rice','2023-05-11 23:45:15.000000','james'),(1385,300,'Cafe mocha','2023-05-13 23:45:15.000000','james'),(1386,300,'Cafe mocha','2023-05-15 23:45:15.000000','james'),(1387,300,'Cafe mocha','2023-05-17 23:45:15.000000','james'),(1388,300,'Cafe mocha','2023-05-18 23:45:15.000000','james'),(1390,4000,'Hot dog','2023-03-08 08:01:00.000000','james'),(1391,2000,'Ice Cream','2023-05-08 11:44:00.000000','james'),(1392,2000,'Ice Cream','2023-03-08 02:45:00.000000','james'),(1393,3000,'Butter','2023-05-11 23:45:15.000000','taewan'),(1394,0,'Butter','2023-05-11 23:45:15.000000','taewan'),(1395,3000,'Banana','2023-05-12 23:45:15.000000','taewan'),(1396,3000,'Banana','2023-05-13 23:45:15.000000','taewan'),(1397,3000,'Banana','2023-05-14 23:45:15.000000','taewan'),(1398,3000,'Banana','2023-05-15 23:45:15.000000','taewan'),(1399,3000,'Banana','2023-05-16 23:45:15.000000','taewan'),(1400,3000,'Banana','2023-05-17 23:45:15.000000','taewan'),(1404,500,'Burger','2023-05-12 23:45:15.000000','taewan'),(1405,500,'Burger','2023-05-13 23:45:15.000000','taewan'),(1406,500,'Burger','2023-05-14 23:45:15.000000','taewan'),(1407,500,'Burger','2023-05-15 23:45:15.000000','taewan'),(1408,500,'Burger','2023-05-16 23:45:15.000000','taewan'),(1409,500,'Burger','2023-05-17 23:45:15.000000','taewan'),(1411,400,'Milkshake','2023-05-11 23:45:15.000000','taewan'),(1412,400,'Milkshake','2023-05-12 23:45:15.000000','taewan'),(1413,400,'Milkshake','2023-05-13 23:45:15.000000','taewan'),(1414,400,'Milkshake','2023-05-14 23:45:15.000000','taewan'),(1415,400,'Milkshake','2023-05-15 23:45:15.000000','taewan'),(1416,400,'Milkshake','2023-05-16 23:45:15.000000','taewan'),(1417,400,'Milkshake','2023-05-17 23:45:15.000000','taewan'),(1418,400,'Milkshake','2023-05-18 23:45:15.000000','taewan'),(1419,900,'Chicken fried rice','2023-05-13 23:45:15.000000','taewan'),(1420,900,'Chicken fried rice','2023-05-16 23:45:15.000000','taewan'),(1421,900,'Chicken fried rice','2023-05-17 23:45:15.000000','taewan'),(1422,900,'Chicken fried rice','2023-05-11 23:45:15.000000','taewan'),(1423,900,'Chicken fried rice','2023-05-18 23:45:15.000000','taewan'),(1424,300,'Cafe mocha','2023-05-13 23:45:15.000000','taewan'),(1425,300,'Cafe mocha','2023-05-15 23:45:15.000000','taewan'),(1426,300,'Cafe mocha','2023-05-17 23:45:15.000000','taewan'),(1427,300,'Cafe mocha','2023-05-18 23:45:15.000000','taewan'),(1428,1,'222@@@@','2023-05-18 09:09:00.000000','taewan'),(1429,4000,'Hot dog','2023-03-08 08:01:00.000000','taewan'),(1430,2000,'Ice Cream','2023-05-08 11:44:00.000000','taewan'),(1431,2000,'Ice Cream','2023-03-08 02:45:00.000000','taewan'),(1483,400,'Banan','2023-05-16 20:58:00.000000','aboli'),(1582,200,'Chocolate','0009-09-09 11:18:00.000000','aboli'),(1685,333,'burger ','2023-05-18 15:34:00.000000','aboli'),(1689,222,'Chocolate','2023-05-18 16:09:00.000000','aboli'),(1782,222,'Burger','2023-05-18 17:57:00.000000','aboli'),(1834,300,'Burger','2023-05-17 22:25:00.000000','aboli');
/*!40000 ALTER TABLE `foodlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodlog_seq`
--

DROP TABLE IF EXISTS `foodlog_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foodlog_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodlog_seq`
--

LOCK TABLES `foodlog_seq` WRITE;
/*!40000 ALTER TABLE `foodlog_seq` DISABLE KEYS */;
INSERT INTO `foodlog_seq` VALUES (1931);
/*!40000 ALTER TABLE `foodlog_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `callimit` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('aboli',2100,'aboli@gmail.com','$2a$10$mEFuYuS.7grq0aJbYYijmuZ/NNDcKBSAf2go.bP/iUvChL.WQrTN2','ROLE_USER','K','Aboli'),('admin',2100,'admin@gmail.com','$2a$10$xYGnBBsft5h2Uu2OjCihu.83Bmhwin.SYEzG.rDgSCBWQp9vlghZa','ROLE_ADMIN','Wadia','Neetu'),('hiralal',2100,'kiran.khamitkar283@gmail.com','$2a$10$Atm.Ac4An3G/J9Ok2eQ9E.W0q85rQMBEJJhheSfEtmG.qwhbhgoGu','ROLE_USER','Khamitkar','Hiralal'),('james',2100,'james@gmail.com','$2a$10$9Cpdpcw1I32UQOvafXjGS.ICfedBgD7TLOtoV2hrv75YBd0ThB6hG','ROLE_USER','Paul','James'),('taewan',2100,'taewan@gmail.com','$2a$10$qVT22rntCvqF4Fblo2gqY.CRTsaxlJk5i1qpux3svxRuyMAE006He','ROLE_USER','Chung','Taewan');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-08 14:02:10
