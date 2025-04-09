-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: tramway.proxy.rlwy.net    Database: pos_main_db
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `banking`
--

DROP TABLE IF EXISTS `banking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7kbi6ovn627v4ycytevkwu4t8` (`user_id`),
  CONSTRAINT `FK7kbi6ovn627v4ycytevkwu4t8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `branch_code` varchar(255) DEFAULT NULL,
  `branch_name` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `country_id` int NOT NULL,
  `shop_details_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3vl5jamdhb1s3p7lgssx2h39h` (`country_id`),
  KEY `FKsu8cl7rnxw2cff1sp6khh8gk9` (`shop_details_id`),
  CONSTRAINT `FK3vl5jamdhb1s3p7lgssx2h39h` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `FKsu8cl7rnxw2cff1sp6khh8gk9` FOREIGN KEY (`shop_details_id`) REFERENCES `shop_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category_totals`
--

DROP TABLE IF EXISTS `category_totals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_totals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  `category_total` double DEFAULT NULL,
  `sales_date_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf4vmbjiqapwyxnowplr6d1hv1` (`sales_date_id`),
  CONSTRAINT `FKf4vmbjiqapwyxnowplr6d1hv1` FOREIGN KEY (`sales_date_id`) REFERENCES `sales_date_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` int NOT NULL AUTO_INCREMENT,
  `country_name` varchar(255) NOT NULL,
  `price_symbol` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discount_percentage` double DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_discount`
--

DROP TABLE IF EXISTS `employee_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_discount` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discount` double DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `user` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK599r9gisbesrv1sr4x0wx877q` (`user`),
  CONSTRAINT `FK599r9gisbesrv1sr4x0wx877q` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `manager_toggle`
--

DROP TABLE IF EXISTS `manager_toggle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager_toggle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `overall_payment_totals`
--

DROP TABLE IF EXISTS `overall_payment_totals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `overall_payment_totals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_total` double DEFAULT NULL,
  `sales_date_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1d79sg77vho0md7hxyxix8cmj` (`sales_date_id`),
  CONSTRAINT `FK1d79sg77vho0md7hxyxix8cmj` FOREIGN KEY (`sales_date_id`) REFERENCES `sales_date_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiry_token_time` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g0guo4k8krgpwuagos61oc06j` (`token`),
  KEY `FK5lwtbncug84d4ero33v3cfxvl` (`user_id`),
  CONSTRAINT `FK5lwtbncug84d4ero33v3cfxvl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payout`
--

DROP TABLE IF EXISTS `payout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payout` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmta521wp61e6omlicj2daxt5c` (`user_id`),
  CONSTRAINT `FKmta521wp61e6omlicj2daxt5c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `barcode` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price_per_unit` double DEFAULT NULL,
  `product_category` int NOT NULL,
  `tax` int NOT NULL,
  `expiry_date` datetime DEFAULT NULL,
  `low_stock` int DEFAULT NULL,
  `purchase_price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `discount_validation` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbvdd7owwhvkamc6unq83xdcrj` (`product_category`),
  KEY `FKsq4yh5m5uqhyfuoj7wsxjjq7t` (`tax`),
  CONSTRAINT `FKbvdd7owwhvkamc6unq83xdcrj` FOREIGN KEY (`product_category`) REFERENCES `product_category` (`id`),
  CONSTRAINT `FKsq4yh5m5uqhyfuoj7wsxjjq7t` FOREIGN KEY (`tax`) REFERENCES `tax` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `product_category_name` varchar(255) DEFAULT NULL,
  `agevalidation` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchase_list`
--

DROP TABLE IF EXISTS `purchase_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `barcode` varchar(255) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_date_details`
--

DROP TABLE IF EXISTS `sales_date_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_date_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sales_date` varchar(255) DEFAULT NULL,
  `total_sales` double DEFAULT NULL,
  `total_transactions` int DEFAULT NULL,
  `report_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk01sklq0v1ls76i6q7fwwvawh` (`report_id`),
  CONSTRAINT `FKk01sklq0v1ls76i6q7fwwvawh` FOREIGN KEY (`report_id`) REFERENCES `sales_report` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_report`
--

DROP TABLE IF EXISTS `sales_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `end_date` datetime DEFAULT NULL,
  `fully_total_sales` double DEFAULT NULL,
  `report_generated_by` varchar(255) DEFAULT NULL,
  `report_type` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shifts`
--

DROP TABLE IF EXISTS `shifts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shifts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `end_time` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `user` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK10fvmxns4m4c28r432xhcndu6` (`user`),
  CONSTRAINT `FK10fvmxns4m4c28r432xhcndu6` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shop_details`
--

DROP TABLE IF EXISTS `shop_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `whatsapp_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `staff_leave`
--

DROP TABLE IF EXISTS `staff_leave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_leave` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_on` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7ghes70hvqloc9s4182tb0rqm` (`user_id`),
  CONSTRAINT `FK7ghes70hvqloc9s4182tb0rqm` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `branch_id` int NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8wkpxnja0ikk6t0xp3ju8aoar` (`branch_id`),
  KEY `FKjghkvw2snnsr5gpct0of7xfcf` (`product_id`),
  CONSTRAINT `FK8wkpxnja0ikk6t0xp3ju8aoar` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
  CONSTRAINT `FKjghkvw2snnsr5gpct0of7xfcf` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `whatsapp_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tax`
--

DROP TABLE IF EXISTS `tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tax` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `taxpercentage` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_time` datetime NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `total_amount` double NOT NULL,
  `branch_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `shopdetails_id` int NOT NULL,
  `user_id` int NOT NULL,
  `generate_date_time` datetime DEFAULT NULL,
  `manual_discount` double DEFAULT NULL,
  `employee_discount` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK67x0jrs2q3l7neymsrgys54ip` (`branch_id`),
  KEY `FKnbpjofb5abhjg5hiovi0t3k57` (`customer_id`),
  KEY `FK7w4ufnt4kd8gl8l74kruoaeil` (`shopdetails_id`),
  KEY `FKsg7jp0aj6qipr50856wf6vbw1` (`user_id`),
  CONSTRAINT `FK67x0jrs2q3l7neymsrgys54ip` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
  CONSTRAINT `FK7w4ufnt4kd8gl8l74kruoaeil` FOREIGN KEY (`shopdetails_id`) REFERENCES `shop_details` (`id`),
  CONSTRAINT `FKnbpjofb5abhjg5hiovi0t3k57` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKsg7jp0aj6qipr50856wf6vbw1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction_details_list`
--

DROP TABLE IF EXISTS `transaction_details_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_details_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discount` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `product_id` int NOT NULL,
  `transaction_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmi2ec2ydi8vwne4mb4fb1bg8q` (`product_id`),
  KEY `FKfi2ym21hkejmt01reshumrsrv` (`transaction_id`),
  CONSTRAINT `FKfi2ym21hkejmt01reshumrsrv` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`),
  CONSTRAINT `FKmi2ec2ydi8vwne4mb4fb1bg8q` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction_payment_method`
--

DROP TABLE IF EXISTS `transaction_payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_payment_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `payment_method_id` int NOT NULL,
  `transaction_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm0j5glmfckvu0fcuexuc5h3bk` (`payment_method_id`),
  KEY `FKa42hox34kjfcy8sjd1f1ohef3` (`transaction_id`),
  CONSTRAINT `FKa42hox34kjfcy8sjd1f1ohef3` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`),
  CONSTRAINT `FKm0j5glmfckvu0fcuexuc5h3bk` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `email_address` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `branch_id` int NOT NULL,
  `user_role_id` int NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9yy0ya980j002yvtxi9r7kv6b` (`branch_id`),
  KEY `FKh2wc2dtfdo8maylne7mgubowq` (`user_role_id`),
  CONSTRAINT `FK9yy0ya980j002yvtxi9r7kv6b` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
  CONSTRAINT `FKh2wc2dtfdo8maylne7mgubowq` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_logs`
--

DROP TABLE IF EXISTS `user_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_logs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `log_in` datetime DEFAULT NULL,
  `log_out` datetime DEFAULT NULL,
  `sign_off` bit(1) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKka8typye3hp79yncc7x1rwpw6` (`user_id`),
  CONSTRAINT `FKka8typye3hp79yncc7x1rwpw6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_payment_details`
--

DROP TABLE IF EXISTS `user_payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_payment_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_total` double DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `sales_date_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsr8oebf51dnhd7qvbeu7ticvu` (`sales_date_id`),
  CONSTRAINT `FKsr8oebf51dnhd7qvbeu7ticvu` FOREIGN KEY (`sales_date_id`) REFERENCES `sales_date_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=309 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-08 11:54:21
