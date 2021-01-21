CREATE DATABASE  IF NOT EXISTS `cityzendb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cityzendb`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: cityzendb
-- ------------------------------------------------------
-- Server version	8.0.22

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
  `Civico` int NOT NULL,
  `Città` varchar(30) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `numSegnalazioni` int DEFAULT NULL,
  `numSegnAppr` int DEFAULT NULL,
  PRIMARY KEY (`CF`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cittadino`
--

LOCK TABLES `cittadino` WRITE;
/*!40000 ALTER TABLE `cittadino` DISABLE KEYS */;
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
  `Segnalazione` int NOT NULL,
  `Descrizione` varchar(500) NOT NULL,
  `Valutazione` int NOT NULL,
  `DataFeedback` date NOT NULL,
  PRIMARY KEY (`Cittadino`,`Segnalazione`),
  KEY `Segnalazione` (`Segnalazione`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`Cittadino`) REFERENCES `cittadino` (`CF`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`Segnalazione`) REFERENCES `segnalazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
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
  `Segnalazione` int NOT NULL,
  `TipoLavorazione` varchar(20) NOT NULL,
  `DataLavorazione` date NOT NULL,
  PRIMARY KEY (`Impiegato`,`Segnalazione`,`TipoLavorazione`),
  KEY `Segnalazione` (`Segnalazione`),
  CONSTRAINT `gestionesegnalazioni_ibfk_1` FOREIGN KEY (`Impiegato`) REFERENCES `impiegato` (`Matricola`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `gestionesegnalazioni_ibfk_2` FOREIGN KEY (`Segnalazione`) REFERENCES `segnalazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gestionesegnalazioni`
--

LOCK TABLES `gestionesegnalazioni` WRITE;
/*!40000 ALTER TABLE `gestionesegnalazioni` DISABLE KEYS */;
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
  `Civico` int NOT NULL,
  `Città` varchar(30) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `numSegnalazioniApp` int DEFAULT NULL,
  `numSegnalazioniChiuse` int DEFAULT NULL,
  PRIMARY KEY (`Matricola`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impiegato`
--

LOCK TABLES `impiegato` WRITE;
/*!40000 ALTER TABLE `impiegato` DISABLE KEYS */;
INSERT INTO `impiegato` VALUES ('MAT000','RSSMRC78H17H703V','Marco','Rossi','70ccd9007338d6d81dd3b6271621b9cf9a97ea00','Roma',1,'Scafati','mrossi@scafati.it',0,0);
/*!40000 ALTER TABLE `impiegato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `segnalazione`
--

DROP TABLE IF EXISTS `segnalazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `segnalazione` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Via` varchar(30) NOT NULL,
  `Civico` int NOT NULL,
  `Priorità` int NOT NULL,
  `numSolleciti` int DEFAULT NULL,
  `Stato` varchar(20) NOT NULL,
  `DataSegnalazione` date NOT NULL,
  `Oggetto` varchar(40) NOT NULL,
  `Descrizione` varchar(500) NOT NULL,
  `Foto` varchar(50) NOT NULL,
  `Cittadino` varchar(16) NOT NULL,
  `Riaperta` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Cittadino` (`Cittadino`),
  KEY `Riaperta` (`Riaperta`),
  CONSTRAINT `segnalazione_ibfk_1` FOREIGN KEY (`Cittadino`) REFERENCES `cittadino` (`CF`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2682 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `segnalazione`
--

LOCK TABLES `segnalazione` WRITE;
/*!40000 ALTER TABLE `segnalazione` DISABLE KEYS */;
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

-- Dump completed on 2021-01-21 19:18:57
