-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `ejemplares`
--

DROP TABLE IF EXISTS `ejemplares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ejemplares` (
  `idEjemplar` int(11) NOT NULL AUTO_INCREMENT,
  `idLibro` int(11) NOT NULL,
  PRIMARY KEY (`idEjemplar`),
  KEY `idLibro_fk` (`idLibro`),
  CONSTRAINT `idLibro_fk` FOREIGN KEY (`idLibro`) REFERENCES `libros` (`idlibro`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=411 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplares`
--

LOCK TABLES `ejemplares` WRITE;
/*!40000 ALTER TABLE `ejemplares` DISABLE KEYS */;
INSERT INTO `ejemplares` VALUES (101,1),(102,1),(103,1),(104,2),(105,2),(210,3),(211,4),(212,4),(213,4),(214,4),(321,5),(322,5),(323,6),(401,7),(402,7),(403,7),(404,7),(405,8),(409,9),(410,9);
/*!40000 ALTER TABLE `ejemplares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `libros` (
  `idLibro` int(11) NOT NULL AUTO_INCREMENT,
  `ISBN` varchar(20) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `nroEdicion` int(11) NOT NULL,
  `fechaEdicion` date NOT NULL,
  `cantDiasMaxPrestamo` int(11) NOT NULL,
  PRIMARY KEY (`idLibro`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,'9-226-154-05','Harry Potter y la piedra filosofal',226,'1997-06-26',5),(2,'9-227-784-07','Harry Potter y la camara secreta',227,'1998-07-02',4),(3,'9-425-450-11','Harry Potter y el prisionero de azkaban',425,'1999-07-08',7),(4,'9-223-784-14','It',223,'1990-11-18',4),(5,'9-332-456-22','El resplandor',332,'1977-01-28',3),(6,'9-754-895-21','Misery',754,'1987-06-08',5),(7,'9-478-125-89','Mr. Mercedes',478,'2014-06-03',6),(8,'9-652-852-41','The Maze Runner',652,'2009-10-21',4),(9,'9-995-526-04','El principito',995,'1943-04-06',3);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineas_de_prestamos`
--

DROP TABLE IF EXISTS `lineas_de_prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lineas_de_prestamos` (
  `idLineaPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `fechaDevolucion` date DEFAULT NULL,
  `devuelto` tinyint(1) NOT NULL,
  `idSancion` int(11) DEFAULT NULL,
  `idSocio` int(11) NOT NULL,
  `idPrestamo` int(11) NOT NULL,
  `idEjemplar` int(11) NOT NULL,
  PRIMARY KEY (`idLineaPrestamo`),
  KEY `idSocioLinea_fk` (`idSocio`),
  KEY `idEjemplar_fk` (`idEjemplar`),
  KEY `idPrestamo_fk` (`idPrestamo`),
  KEY `idSancion_fk` (`idSancion`),
  CONSTRAINT `idEjemplar_fk` FOREIGN KEY (`idEjemplar`) REFERENCES `ejemplares` (`idejemplar`) ON UPDATE CASCADE,
  CONSTRAINT `idPrestamo_fk` FOREIGN KEY (`idPrestamo`) REFERENCES `prestamos` (`idprestamo`) ON UPDATE CASCADE,
  CONSTRAINT `idSancion_fk` FOREIGN KEY (`idSancion`) REFERENCES `sanciones` (`idsancion`) ON UPDATE CASCADE,
  CONSTRAINT `idSocioLinea_fk` FOREIGN KEY (`idSocio`) REFERENCES `socios` (`idsocio`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineas_de_prestamos`
--

LOCK TABLES `lineas_de_prestamos` WRITE;
/*!40000 ALTER TABLE `lineas_de_prestamos` DISABLE KEYS */;
INSERT INTO `lineas_de_prestamos` VALUES (1,'2018-08-05',1,NULL,1,1,101),(2,'2018-08-05',1,NULL,1,1,210),(3,'2018-07-28',1,2,2,2,210),(4,'2018-07-28',1,2,2,2,212),(5,'2018-07-22',1,NULL,3,3,410),(6,'2018-07-22',1,NULL,3,3,105),(7,NULL,0,NULL,4,4,405),(8,NULL,0,NULL,4,4,323),(9,'2018-08-24',1,NULL,3,5,322),(10,'2018-08-28',1,1,3,6,322),(11,NULL,0,NULL,1,7,101),(12,NULL,0,NULL,1,7,104),(13,NULL,0,NULL,2,8,103),(14,NULL,0,NULL,2,8,402);
/*!40000 ALTER TABLE `lineas_de_prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `politica_prestamos`
--

DROP TABLE IF EXISTS `politica_prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `politica_prestamos` (
  `idPoliticaPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `cantMaxLibrosPend` int(11) NOT NULL,
  `fechaVigenciaPolPrestamo` date NOT NULL,
  PRIMARY KEY (`idPoliticaPrestamo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `politica_prestamos`
--

LOCK TABLES `politica_prestamos` WRITE;
/*!40000 ALTER TABLE `politica_prestamos` DISABLE KEYS */;
INSERT INTO `politica_prestamos` VALUES (1,3,'2017-12-31'),(2,5,'2018-12-31');
/*!40000 ALTER TABLE `politica_prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `politica_sanciones`
--

DROP TABLE IF EXISTS `politica_sanciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `politica_sanciones` (
  `idPoliticaSancion` int(11) NOT NULL AUTO_INCREMENT,
  `diasDeAtrasoDesde` int(11) NOT NULL,
  `diasDeAtrasoHasta` int(11) NOT NULL,
  `diasDeSancion` int(11) NOT NULL,
  PRIMARY KEY (`idPoliticaSancion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `politica_sanciones`
--

LOCK TABLES `politica_sanciones` WRITE;
/*!40000 ALTER TABLE `politica_sanciones` DISABLE KEYS */;
INSERT INTO `politica_sanciones` VALUES (1,1,3,7),(2,4,7,10),(3,8,11,14);
/*!40000 ALTER TABLE `politica_sanciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `prestamos` (
  `idPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `fechaPrestamo` date NOT NULL,
  `horaPrestamo` time NOT NULL,
  `diasPrestamo` int(11) DEFAULT NULL,
  `fechaADevolver` date DEFAULT NULL,
  `idSocio` int(11) NOT NULL,
  PRIMARY KEY (`idPrestamo`),
  KEY `idSocio_fk` (`idSocio`),
  CONSTRAINT `idSocio_fk` FOREIGN KEY (`idSocio`) REFERENCES `socios` (`idsocio`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (1,'2018-08-01','14:05:00',5,'2018-04-06',1),(2,'2018-07-18','11:45:21',4,'2018-06-22',2),(3,'2018-07-18','13:32:12',3,'2018-06-21',3),(4,'2018-08-20','16:55:45',4,'2018-08-24',4),(5,'2018-08-21','10:05:06',3,'2018-08-24',3),(6,'2018-08-24','11:04:05',3,'2018-08-27',3),(7,'2018-08-30','19:08:04',4,'2018-09-03',1),(8,'2018-09-04','11:57:00',5,'2018-09-09',2);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanciones`
--

DROP TABLE IF EXISTS `sanciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sanciones` (
  `idSancion` int(11) NOT NULL AUTO_INCREMENT,
  `fechaSancion` date NOT NULL,
  `fechaSancionHasta` date NOT NULL,
  `idSocio` int(11) NOT NULL,
  PRIMARY KEY (`idSancion`),
  KEY `idSocioSancion_fk` (`idSocio`),
  CONSTRAINT `idSocioSancion_fk` FOREIGN KEY (`idSocio`) REFERENCES `socios` (`idsocio`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanciones`
--

LOCK TABLES `sanciones` WRITE;
/*!40000 ALTER TABLE `sanciones` DISABLE KEYS */;
INSERT INTO `sanciones` VALUES (1,'2018-07-28','2018-08-07',2),(2,'2018-08-28','2018-08-31',3);
/*!40000 ALTER TABLE `sanciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socios`
--

DROP TABLE IF EXISTS `socios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `socios` (
  `idSocio` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `domicilio` varchar(20) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idSocio`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socios`
--

LOCK TABLES `socios` WRITE;
/*!40000 ALTER TABLE `socios` DISABLE KEYS */;
INSERT INTO `socios` VALUES (1,'Hernan','Romeno','hernan@gmail.com','4235689','San Martin 456','35456789',0),(2,'Mauricio','Knazovic','mauri@gmail.com','4551203','Alvear 1204','36587977',1),(3,'Ana','Lopez','ana@hotmail.com','4785698','Eva Peron 6985','26894120',1),(4,'Ezequiel','Sanchez','eze@gmail.com','4668956','Laprida 5142','39562140',0);
/*!40000 ALTER TABLE `socios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuarios` (
  `nombreUsuario` varchar(20) NOT NULL,
  `clave` varchar(20) NOT NULL,
   `tipo` int(1) NOT NULL,
    `idSocio` int(11)  NULL,
  PRIMARY KEY (`nombreUsuario`),
    KEY `idSocioUsuario_fk` (`idSocio`),
  CONSTRAINT `idSocioUsuario_fk` FOREIGN KEY (`idSocio`) REFERENCES `socios` (`idsocio`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('antonella','anto123',0,null),('sabrina','sabri123',0,null),('35456789','Romeno',1,1),('36587977','Knazovic',1,2),('26894120','Lopez',1,3),('39562140','Sanchez',1,4);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'biblioteca'
--

--
-- Dumping routines for database 'biblioteca'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-05 20:03:47
