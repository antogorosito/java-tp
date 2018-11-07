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
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplares`
--

LOCK TABLES `ejemplares` WRITE;
/*!40000 ALTER TABLE `ejemplares` DISABLE KEYS */;
INSERT INTO `ejemplares` VALUES (1001,1),(1002,1),(1003,1),(2001,2),(2002,2),(2003,2),(3001,3),(3002,3),(4001,4),(4002,4),(4003,4),(5001,5),(6001,6),(6002,6),(7001,7),(7002,7),(8001,8),(8002,8),(8003,8),(8004,8),(9001,9),(10001,10),(10002,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,'9-456-987-05','Harry Potter y la pierda filosofal',456,'1997-06-26',12),(2,'9-123-541-10','Harry Potter y la camara secreta',123,'1998-07-02',14),(3,'9-985-612-25','Harry Potter y el prisionero de azkaban',985,'1999-07-08',6),(4,'9-665-145-03','Harry Potter y el caliz de fuego',665,'2000-07-08',7),(5,'9-124-784-05','Harry Potter y la orden del fenix',124,'2003-06-21',7),(6,'9-951-368-27','Harry Potter y el misterio del principe',951,'2005-07-16',7),(7,'9-778-247-38','Harry Potter y las reliquias de la muerte',778,'2007-07-21',6),(8,'9-357-410-21','It',357,'1986-09-15',14),(9,'9-002-689-04','El resplandor',2,'1977-01-28',10),(10,'9-963-114-54','El principito',963,'1943-04-06',10);
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
  `idPrestamo` int(11) NOT NULL,
  `idEjemplar` int(11) NOT NULL,
  PRIMARY KEY (`idLineaPrestamo`),
  KEY `idEjemplar_fk` (`idEjemplar`),
  KEY `idPrestamo_fk` (`idPrestamo`),
  KEY `idSancion_fk` (`idSancion`),
  CONSTRAINT `idEjemplar_fk` FOREIGN KEY (`idEjemplar`) REFERENCES `ejemplares` (`idejemplar`) ON UPDATE CASCADE,
  CONSTRAINT `idPrestamo_fk` FOREIGN KEY (`idPrestamo`) REFERENCES `prestamos` (`idprestamo`) ON UPDATE CASCADE,
  CONSTRAINT `idSancion_fk` FOREIGN KEY (`idSancion`) REFERENCES `sanciones` (`idsancion`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineas_de_prestamos`
--

LOCK TABLES `lineas_de_prestamos` WRITE;
/*!40000 ALTER TABLE `lineas_de_prestamos` DISABLE KEYS */;
INSERT INTO `lineas_de_prestamos` VALUES (1,'2018-09-10',1,2,1,10001),(2,'2018-09-10',1,2,1,6002),(3,'2018-10-28',1,1,2,5001),(4,'2018-10-28',1,1,2,4003),(5,'2018-10-28',1,1,2,2001),(6,'2018-10-08',1,NULL,3,1003),(7,NULL,0,NULL,4,10001),(8,NULL,0,NULL,4,1003),(9,NULL,0,NULL,4,2002),(10,NULL,0,NULL,5,5001),(11,NULL,0,NULL,5,3002),(12,NULL,0,NULL,5,7002),(13,'2018-10-30',1,3,6,9001),(14,NULL,0,NULL,7,6002),(15,NULL,0,NULL,7,8004),(16,NULL,0,NULL,7,2003),(17,NULL,0,NULL,7,4001),(18,NULL,0,NULL,7,10002);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `politica_sanciones`
--

LOCK TABLES `politica_sanciones` WRITE;
/*!40000 ALTER TABLE `politica_sanciones` DISABLE KEYS */;
INSERT INTO `politica_sanciones` VALUES (1,1,3,7),(2,4,7,10),(3,8,11,14),(4,12,15,21);
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
INSERT INTO `prestamos` VALUES (1,'2018-09-03','15:00:01',6,'2018-09-09',2),(2,'2018-10-23','12:20:11',4,'2018-10-27',1),(3,'2018-11-03','09:15:10',6,'2018-10-09',3),(4,'2018-10-30','11:23:00',10,'2018-11-08',6),(5,'2018-10-31','12:45:53',6,'2018-11-05',5),(6,'2018-10-19','11:00:10',10,'2018-10-29',4),(7,'2018-11-05','10:34:05',7,'2018-11-12',2);
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
   `activo`  tinyint(1) NOT NULL,
  PRIMARY KEY (`idSancion`),
  KEY `idSocioSancion_fk` (`idSocio`),
  CONSTRAINT `idSocioSancion_fk` FOREIGN KEY (`idSocio`) REFERENCES `socios` (`idsocio`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanciones`
--

LOCK TABLES `sanciones` WRITE;
/*!40000 ALTER TABLE `sanciones` DISABLE KEYS */;
INSERT INTO `sanciones` VALUES (1,'2018-10-28','2018-11-03',1,0),(2,'2018-09-10','2018-09-13',2,0),(3,'2018-10-30','2018-11-05',4,1);
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
  `email` varchar(30) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `domicilio` varchar(20) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idSocio`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socios`
--

LOCK TABLES `socios` WRITE;
/*!40000 ALTER TABLE `socios` DISABLE KEYS */;
INSERT INTO `socios` VALUES (1,'Alejandra','Perez','al4@hotmail.com','4258952','Ecuador 4567','25689754',0),(2,'Hernan','Romeno','antoo.-bj@hotmail.com','4567891','San Martin 455','33556210',1),(3,'Mauricio','Knazovic','isabri91@gmail.com','4110258','Pueryrredon 2487','24895001',1),(4,'Raul','Sanchez','raul_2018@hotmail.com','4158756','Salta 498','21456321',0),(5,'Marina','Rodriguez','antonellabj21@gmail.com','Moreno 758','4982245','37894201',1),(6,'Sebastian','Romero','sabri64_5@hotmail.com','Santiago 104','4581004','31663895',0);
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
  `idSocio` int(11) DEFAULT NULL,
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
INSERT INTO `usuarios` VALUES ('21456321','Sanchez',1,4),('24895001','Knazovic',1,3),('25689754','Perez',1,1),('31663895','Romero',1,6),('33556210','Romeno',1,2),('37894201','Rodriguez',1,5),('antonella','anto123',0,NULL),('sabrina','sabri123',0,NULL);
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

-- Dump completed on 2018-11-05 19:54:00
