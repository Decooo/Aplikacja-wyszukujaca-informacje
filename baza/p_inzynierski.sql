-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: p_inzynierski
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `forma_zatrudniena`
--

DROP TABLE IF EXISTS `forma_zatrudniena`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forma_zatrudniena` (
  `id_forma_zatrudnienia` int(11) NOT NULL,
  `nazwa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_forma_zatrudnienia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_zatrudniena`
--

LOCK TABLES `forma_zatrudniena` WRITE;
/*!40000 ALTER TABLE `forma_zatrudniena` DISABLE KEYS */;
/*!40000 ALTER TABLE `forma_zatrudniena` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forma_zatrudnienia`
--

DROP TABLE IF EXISTS `forma_zatrudnienia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forma_zatrudnienia` (
  `id_forma_zatrudnienia` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(20) COLLATE utf32_polish_ci NOT NULL,
  PRIMARY KEY (`id_forma_zatrudnienia`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_zatrudnienia`
--

LOCK TABLES `forma_zatrudnienia` WRITE;
/*!40000 ALTER TABLE `forma_zatrudnienia` DISABLE KEYS */;
INSERT INTO `forma_zatrudnienia` VALUES (1,'Dowolne'),(2,'Pełny etat'),(3,'Część etatu'),(4,'Praca czasowa'),(5,'Kontrakt');
/*!40000 ALTER TABLE `forma_zatrudnienia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategoria`
--

DROP TABLE IF EXISTS `kategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kategoria` (
  `id_kategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(40) COLLATE utf32_polish_ci NOT NULL,
  PRIMARY KEY (`id_kategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategoria`
--

LOCK TABLES `kategoria` WRITE;
/*!40000 ALTER TABLE `kategoria` DISABLE KEYS */;
INSERT INTO `kategoria` VALUES (1,'Administracja biurowa'),(2,'Doradztwo/Konsulting'),(3,'Badania i rozwój'),(4,'Bankowość'),(5,'BHP / Ochrona środowiska'),(6,'Budownictwo'),(7,'Call Center'),(8,'Edukacja / Szkolenia'),(9,'Finanse /Ekonomia'),(10,'Franczyzna / Własny biznes'),(11,'Hotelarstwo / Turystyka / Gastronomia'),(12,'Zasoby  ludzkie'),(13,'Nowe media'),(14,'Inżynieria'),(15,'IT- Administracja'),(16,'IT-Rozwoj oprogramowania'),(17,'Łancuch dostaw'),(18,'Marketing'),(19,'Media / Sztuka / Rozrywka'),(20,'Nieruchomości'),(21,'Prawo'),(22,'Produkcja'),(23,'Reklama / Grafika / Kreacja / Fotografia'),(24,'Sektor publiczny'),(25,'Sprzedaż'),(26,'Transport /Spedycja'),(27,'Ubezpieczenia'),(28,'Zakupy'),(29,'Kontrola jakości'),(30,'Zdrowie / Uroda / Rekreacja'),(31,'Energetyka'),(32,'Inne');
/*!40000 ALTER TABLE `kategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ogloszenie`
--

DROP TABLE IF EXISTS `ogloszenie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ogloszenie` (
  `id_ogloszenie` int(11) NOT NULL AUTO_INCREMENT,
  `id_uzytkownik` int(11) NOT NULL,
  `id_kategoria` int(11) NOT NULL,
  `id_forma_zatrudnienia` int(11) NOT NULL,
  `id_stanowisko` int(11) NOT NULL,
  `tytul` varchar(50) COLLATE utf32_polish_ci NOT NULL,
  `lokalizacja` varchar(50) COLLATE utf32_polish_ci NOT NULL,
  `zarobki` int(11) NOT NULL,
  `opis` text COLLATE utf32_polish_ci NOT NULL,
  PRIMARY KEY (`id_ogloszenie`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ogloszenie`
--

LOCK TABLES `ogloszenie` WRITE;
/*!40000 ALTER TABLE `ogloszenie` DISABLE KEYS */;
INSERT INTO `ogloszenie` VALUES (2,1,6,2,6,'Zatrudnię murarza','Rzeszów',3400,'Zatrudnię murarza.  Praca na terenie podkarpacia. Na okres próbny umowa zlecenie, a następnie umowa o pracę. '),(3,1,6,2,6,'Zatrudnię tynkarza','Łódź',2900,'Zatrudnię na stanowisku tynkarza maszynowego. Praca głownie w woj. łódzkim');
/*!40000 ALTER TABLE `ogloszenie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stanowisko`
--

DROP TABLE IF EXISTS `stanowisko`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stanowisko` (
  `id_stanowisko` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(30) COLLATE utf32_polish_ci NOT NULL,
  PRIMARY KEY (`id_stanowisko`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stanowisko`
--

LOCK TABLES `stanowisko` WRITE;
/*!40000 ALTER TABLE `stanowisko` DISABLE KEYS */;
INSERT INTO `stanowisko` VALUES (1,'Dyrektor / Prezes'),(2,'Kierownik'),(3,'Specjalista'),(4,'Asystent'),(5,'Praktykant / Stażysta'),(6,'Pracownik fizyczny');
/*!40000 ALTER TABLE `stanowisko` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uzytkownik`
--

DROP TABLE IF EXISTS `uzytkownik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uzytkownik` (
  `id_uzytkownik` int(11) NOT NULL AUTO_INCREMENT,
  `login` text COLLATE utf32_polish_ci NOT NULL,
  `haslo` text COLLATE utf32_polish_ci NOT NULL,
  PRIMARY KEY (`id_uzytkownik`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uzytkownik`
--

LOCK TABLES `uzytkownik` WRITE;
/*!40000 ALTER TABLE `uzytkownik` DISABLE KEYS */;
INSERT INTO `uzytkownik` VALUES (1,'admin','admin'),(2,'patryk','patryk'),(3,'adam','adam'),(5,'andrzej','andrzej');
/*!40000 ALTER TABLE `uzytkownik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'p_inzynierski'
--

--
-- Dumping routines for database 'p_inzynierski'
--
/*!50003 DROP PROCEDURE IF EXISTS `addAdverts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addAdverts`(in idUser int(11), in idKategoria int(11), in formaZatrudnienia int(11) , in stanowisko int(11), in tytul varchar(150),
in lokalizacja varchar(100), in zarobki int(11), in opis varchar(5000))
BEGIN
insert into ogloszenie(id_uzytkownik,id_kategoria,id_forma_zatrudnienia,id_stanowisko,tytul,lokalizacja,zarobki,opis) values(idUser,idKategoria,formaZatrudnienia,stanowisko,tytul,lokalizacja,zarobki,opis);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addusers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addusers`(in in_login varchar(45), in in_haslo varchar(45))
BEGIN
insert into uzytkownik(login,haslo) values(in_login,in_haslo);
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

-- Dump completed on 2017-11-23  0:43:25
