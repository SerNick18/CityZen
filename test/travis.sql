
CREATE USER 'root'@'localhost' IDENTIFIED BY 'admin';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON *.* TO 'root'@'localhost'

CREATE DATABASE  IF NOT EXISTS `cityzendb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cityzendb`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: cityzendb
-- ------------------------------------------------------
-- Server version	5.7.29-log

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
-- Table structure for table `cittadino`
--

DROP TABLE IF EXISTS `cittadino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cittadino` (
  `CF` varchar(16) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Cognome` varchar(30) NOT NULL,
  `Pwd` char(40) NOT NULL,
  `Via` varchar(30) NOT NULL,
  `Civico` int(11) NOT NULL,
  `Città` varchar(30) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `numSegnalazioni` int(11) DEFAULT NULL,
  `numSegnAppr` int(11) DEFAULT NULL,
  PRIMARY KEY (`CF`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cittadino`
--

LOCK TABLES `cittadino` WRITE;
/*!40000 ALTER TABLE `cittadino` DISABLE KEYS */;
INSERT INTO `cittadino` VALUES ('BGLLDA80A01I483C','Aldo','Baglio','fa6bdd241d66911a0f121904b968f19ab3a80dd2','Roma',1,'Scafati','aldo@gmail.com',0,0),('PRTGCM80A01I483F','Giacomo','Poretti','fa6bdd241d66911a0f121904b968f19ab3a80dd2','Roma',1,'Scafati','giacomo@gmail.com',0,0),('SPQNCL91L18B963R','Nicola','Serra','8dc210ed182b4f4e8cfd80c9f621bcfc057f8e44','Roma',5,'Scafati','nicolaserra63@gmail.com',0,0),('SRRNCL91L18B963R','Nicola','Serra','8dc210ed182b4f4e8cfd80c9f621bcfc057f8e44','Roma',5,'Scafati','nicola.serra@hotmail.it',0,0),('SRTNCL91L18B963R','Giovanni','Fresco','dff5b706c0fda08a7a77fb53f743018f0115b00e','Roma',1,'Scafati','fresco@gmailcom',0,0),('STRGNN80A01I483A','Giovanni','Storti','fa6bdd241d66911a0f121904b968f19ab3a80dd2','Roma',2,'Scafati','storti@gmail.com',1,1);
/*!40000 ALTER TABLE `cittadino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `Cittadino` varchar(16) NOT NULL,
  `Segnalazione` int(11) NOT NULL,
  `Descrizione` varchar(500) NOT NULL,
  `Valutazione` int(11) NOT NULL,
  `DataFeedback` date NOT NULL,
  PRIMARY KEY (`Cittadino`,`Segnalazione`),
  KEY `Segnalazione` (`Segnalazione`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`Cittadino`) REFERENCES `cittadino` (`CF`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`Segnalazione`) REFERENCES `segnalazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES ('STRGNN80A01I483A',9,'ciaoneeeeee',3,'2020-12-26'),('STRGNN80A01I483A',11,'Grande lavoro! Bravi',5,'2020-12-26'),('STRGNN80A01I483A',13,'Siete stati pessimi! Problema non risolto',1,'2020-12-26'),('STRGNN80A01I483A',15,'che schifezza di lavoro',5,'2020-12-26'),('STRGNN80A01I483A',16,'ciaoneeeeeeeeeeeee',5,'2020-12-26');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gestionesegnalazioni`
--

DROP TABLE IF EXISTS `gestionesegnalazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gestionesegnalazioni` (
  `Impiegato` varchar(30) NOT NULL,
  `Segnalazione` int(11) NOT NULL,
  `TipoLavorazione` varchar(20) NOT NULL,
  `DataLavorazione` date NOT NULL,
  PRIMARY KEY (`Impiegato`,`Segnalazione`,`TipoLavorazione`),
  KEY `Segnalazione` (`Segnalazione`),
  CONSTRAINT `gestionesegnalazioni_ibfk_1` FOREIGN KEY (`Impiegato`) REFERENCES `impiegato` (`Matricola`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `gestionesegnalazioni_ibfk_2` FOREIGN KEY (`Segnalazione`) REFERENCES `segnalazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gestionesegnalazioni`
--

LOCK TABLES `gestionesegnalazioni` WRITE;
/*!40000 ALTER TABLE `gestionesegnalazioni` DISABLE KEYS */;
INSERT INTO `gestionesegnalazioni` VALUES ('mat4',1,'rifiutata','2020-12-23'),('mat4',9,'approvata','2020-12-26'),('mat4',9,'chiusa','2020-12-26'),('mat4',11,'approvata','2020-12-26'),('mat4',11,'chiusa','2020-12-26'),('mat4',12,'approvata','2020-12-26'),('mat4',13,'approvata','2020-12-26'),('mat4',13,'chiusa','2020-12-26'),('mat4',14,'rifiutata','2020-12-23'),('mat4',15,'approvata','2020-12-23'),('mat4',15,'chiusa','2020-12-26'),('mat4',16,'approvata','2020-12-26'),('mat4',16,'chiusa','2020-12-26'),('mat4',262,'approvata','2021-01-02');
/*!40000 ALTER TABLE `gestionesegnalazioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `impiegato`
--

DROP TABLE IF EXISTS `impiegato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `impiegato` (
  `Matricola` varchar(30) NOT NULL,
  `CF` varchar(16) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Cognome` varchar(30) NOT NULL,
  `Pwd` char(40) NOT NULL,
  `Via` varchar(30) NOT NULL,
  `Civico` int(11) NOT NULL,
  `Città` varchar(30) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `numSegnalazioniApp` int(11) DEFAULT NULL,
  `numSegnalazioniChiuse` int(11) DEFAULT NULL,
  PRIMARY KEY (`Matricola`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impiegato`
--

LOCK TABLES `impiegato` WRITE;
/*!40000 ALTER TABLE `impiegato` DISABLE KEYS */;
INSERT INTO `impiegato` VALUES ('mat1','FRDLSS0011111111','Alessio','Fiordellisi','1e4e888ac66f8dd41e00c5a7ac36a32a9950d271','via roma',3,'baiano','alessio@gmail.com',0,0),('mat2','LCAMEM0011111111','Luca','Memoli','1e4e888ac66f8dd41e00c5a7ac36a32a9950d271','via roma',3,'baiano','luca@gmail.com',0,0),('mat3','FRNSAB0011111111','Francesco','Sabia','1e4e888ac66f8dd41e00c5a7ac36a32a9950d271','via roma',3,'baiano','francesco@gmail.com',0,0),('mat4','NIKSRR0011111111','Nicola','Serra','0be57da28ff9ccd1f4251cdbe74fc17d0ff34d72','via roma',3,'baiano','nicola@scafati.it',1,0),('mat5','ANTRSS0011111111','Antonio','Russo','1e4e888ac66f8dd41e00c5a7ac36a32a9950d271','via roma',3,'baiano','antonio@gmail.com',0,0),('mat6','PAKNAP0011111111','Pasquale','Napolitano','1e4e888ac66f8dd41e00c5a7ac36a32a9950d271','via roma',3,'baiano','pasquale@gmail.com',0,0),('mat7','CPNLLD0011111111','Leo','Capuano','1e4e888ac66f8dd41e00c5a7ac36a32a9950d271','via roma',3,'baiano','leo@gmail.com',0,0);
/*!40000 ALTER TABLE `impiegato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `segnalazione`
--

DROP TABLE IF EXISTS `segnalazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `segnalazione` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Via` varchar(30) NOT NULL,
  `Civico` int(11) NOT NULL,
  `Priorità` int(11) NOT NULL,
  `numSolleciti` int(11) DEFAULT NULL,
  `Stato` varchar(20) NOT NULL,
  `DataSegnalazione` date NOT NULL,
  `Oggetto` varchar(40) NOT NULL,
  `Descrizione` varchar(500) NOT NULL,
  `Foto` varchar(50) NOT NULL,
  `Cittadino` varchar(16) NOT NULL,
  `Riaperta` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Cittadino` (`Cittadino`),
  CONSTRAINT `segnalazione_ibfk_1` FOREIGN KEY (`Cittadino`) REFERENCES `cittadino` (`CF`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=356 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `segnalazione`
--

LOCK TABLES `segnalazione` WRITE;
/*!40000 ALTER TABLE `segnalazione` DISABLE KEYS */;
INSERT INTO `segnalazione` VALUES (1,'Camaleonte',1,0,0,'rifiutata','2020-12-23','Buca in strada','C\'Ã¨ una voragine in strada','buca.jpg','STRGNN80A01I483A',NULL),(2,'aaaaaaa',1,0,0,'inoltrata','2020-12-23','aaaaaaaaaaa','aaaaaaaaaaaaaaaaaaaaaaa','buca.jpg','STRGNN80A01I483A',NULL),(3,'bbbbbbbbbb',3,0,0,'inoltrata','2020-12-23','bbbbbbbbbbbb','bbbbbbbbbbbbbbbbbbbbb','buca.jpg','STRGNN80A01I483A',NULL),(4,'fffffffffff',5,0,0,'inoltrata','2020-12-23','ttttttttttt','tttttttttttttttttt','buca.jpg','STRGNN80A01I483A',NULL),(5,'hhhhhhhhhh',5,0,0,'inoltrata','2020-12-23','hhhhhhhhhhhhhhhhhh','hhhhhhhhhhhhhhhhhhh','buca.jpg','STRGNN80A01I483A',NULL),(6,'asdad',4,0,0,'inoltrata','2020-12-23','hhhhhhhhhhhhhh','sdffffffff','buca.jpg','STRGNN80A01I483A',NULL),(7,'rrrrrrrrrrrr',2,0,0,'inoltrata','2020-12-23','ttttttttttt','hhhhhhhhhhhhhhhh','buca.jpg','STRGNN80A01I483A',NULL),(8,'vvvvvvvvvvvvv',6,0,0,'inoltrata','2020-12-23','vvvvvvvvvvv','vvvvvvvvvvvvvvvv','buca.jpg','STRGNN80A01I483A',NULL),(9,'lllllllllllllllll',5,0,0,'chiusa','2020-12-23','llllllllllllll','llllllllllllllllllllll','buca.jpg','STRGNN80A01I483A',0),(10,'bbbbbbbbbbb',5,0,0,'inoltrata','2020-12-23','bbbbbbbbbbbbbbbbb','bbbbbbbbbbbbbbbbbbbbbb','buca.jpg','STRGNN80A01I483A',NULL),(11,'mmmmmmmmmmmmm',7,0,0,'chiusa','2020-12-23','mmmmmmmmmmmmmmm','mmmmmmmmmmmmmmmmm','buca.jpg','STRGNN80A01I483A',0),(12,'kkkkkkkk',5,0,0,'inoltrata','2020-12-23','kkkkkkkk','kkkkkkkkkkkkkk','buca.jpg','STRGNN80A01I483A',NULL),(13,'ddddddddddddd',4,0,0,'chiusa','2020-12-23','dddddddddddd','ddddddddddddddddd','buca.jpg','STRGNN80A01I483A',NULL),(14,'xxxxxx',2,0,0,'rifiutata','2020-12-23','xxxxxxxxxxxxxx','xxxxxxxxxxxxxxx','buca.jpg','STRGNN80A01I483A',NULL),(15,'zzzzzzzzzz',8,0,0,'chiusa','2020-12-23','zzzzzzzzzzzzz','zzzzzzzzzzzzzzzzz','buca.jpg','STRGNN80A01I483A',0),(16,'marconi',1,0,0,'chiusa','2020-12-26','buca123','bucaaaaaaaaaaaaaaaa','buca.jpg','STRGNN80A01I483A',NULL),(262,'Roma',3,0,0,'approvata','2021-01-02','Perdita d\'acqua','C\'Ã¨ una paerdita d\'acqua in via Roma','buca.jpg','STRGNN80A01I483A',0);
/*!40000 ALTER TABLE `segnalazione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-16 12:51:45
