-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pos_db
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'No. 1, Galle Road','Col002','Kfc Colombo','0212223447','kfc.colombo@gmail.com',_binary '',1,1),(2,'No. 12, London Road','Lon001','Kfc London','441589522562','kfc.london@gmail.com',_binary '',3,1),(3,'No. 45, Stanley Road','Jan001','Kfc Jaffna','0212223449','kfc.jaffna@gmail.com',_binary '',1,1),(4,'No. 77, Lighthouse Street','Gal001','Kfc Galle','0212223450','kfc.galle@gmail.com',_binary '',1,1),(5,'No. 30, Trinco Road','Bat001','Kfc Batticaloa','0212223451','kfc.batticaloa@gmail.com',_binary '',1,1);
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Srilanka','LKR'),(2,'United States','$'),(3,'United Kingdom','€');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'2025-02-24 19:45:00',_binary '','0776942189','Nivethanan'),(2,'2025-02-24 19:45:24',_binary '','0775480089','Bavithragithan'),(3,'2025-02-24 19:45:37',_binary '','0765894127','Purusothaman'),(4,'2025-02-24 19:46:07',_binary '','0751252005','Rajeeskumar'),(5,'2025-02-24 19:46:20',_binary '','0775868125','Thuvaragan');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,5,_binary ''),(2,10,_binary ''),(3,15,_binary ''),(4,20,_binary ''),(5,25,_binary '');
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,_binary '','Cash'),(2,_binary '','Card'),(3,_binary '','Cheque'),(4,_binary '','Voucher'),(5,_binary '','Digital Wallet');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'48200123456','2025-01-21 18:24:10',_binary '','Coca-Cola',550,1,1,'2025-06-21 00:00:00',10,450,43),(2,'48200198765','2025-03-06 12:47:09',_binary '','Pepsi',550,1,1,'2025-12-31 23:59:59',15,425,0),(3,'5010478544710','2025-03-20 15:34:58',_binary '','orange lic',1200,6,2,'2025-08-25 00:00:00',10,1000,35),(4,'84726583901','2025-01-21 18:26:46',_binary '','Chicken Wings',2000,4,3,'2025-04-18 00:00:00',12,1800,23),(5,'12897634578','2025-01-21 18:27:54',_binary '','Zinger Burger',2400,3,3,'2026-01-17 00:00:00',15,2300,10),(6,'57283669266','2025-03-06 15:59:04',_binary '','Double Cheese Burger',2600,3,3,'2025-12-31 23:59:59',10,2480,13),(7,'98200123856','2025-01-21 18:29:06',_binary '\0','Chocolate Sundae',1100,5,2,'2025-07-21 00:00:00',8,975,32),(8,'4018077000638','2025-03-20 15:01:18',_binary '','Strawberry Sundae',1100,5,2,'2025-12-31 23:59:59',5,950,6),(9,'5000112662429','2025-03-20 15:07:21',_binary '','Spicy Chicken Bucket',5500,4,4,'2025-05-08 00:00:00',10,1000,80),(10,'48392056789','2025-03-21 12:16:00',_binary '','Popcorn Chicken',2400,4,3,'2025-12-31 23:59:59',15,2245,27),(11,'50583841666','2025-03-06 17:43:03',_binary '','Seven Up',2120.25,1,2,'2025-12-31 23:59:59',12,1680,47),(12,'5000112662430','2025-03-20 15:06:49',_binary '','DR Pepper',500,4,3,'2025-12-31 23:59:59',15,450.5,6),(13,'5029913000834','2025-03-20 15:00:23',_binary '','Organic Carrot',1250,7,8,NULL,250,1110,198),(14,'48392056555','2025-03-20 13:33:52',_binary '\0','Organic Carrot',1250,7,8,NULL,5,1110,20),(15,'1','2025-03-20 14:21:36',_binary '','Confectionery-?',100,8,1,NULL,0,100,100),(16,'2','2025-03-20 14:22:15',_binary '','Home Essentials-?',100,8,1,NULL,0,100,100),(17,'3','2025-03-20 14:22:28',_binary '','Magazines & Newspapers-?',100,8,1,NULL,0,100,100),(18,'4','2025-03-20 14:22:42',_binary '','Alcoholic Beverages-?',100,8,1,NULL,0,100,100),(19,'5','2025-03-20 14:22:54',_binary '','Dairy Products-?',100,8,1,NULL,0,100,100),(20,'6','2025-03-20 14:23:08',_binary '','Grocery Items-?',100,8,1,NULL,0,100,100),(21,'7','2025-03-20 14:23:22',_binary '','Soft Drinks-?',100,8,1,NULL,0,100,100),(22,'8','2025-03-20 14:23:35',_binary '','Holy Products-?',100,8,1,NULL,0,100,100),(23,'9','2025-03-20 14:23:51',_binary '','Pet Supplies-?',100,8,1,NULL,0,100,100),(24,'10','2025-03-20 14:24:05',_binary '','Instant Meals-⚡',100,8,1,NULL,0,100,100),(25,'11','2025-03-20 14:24:20',_binary '','Shopping Bags-?',100,8,1,NULL,0,100,100),(26,'12','2025-03-20 14:24:39',_binary '','Eggs-?',100,8,1,NULL,0,100,100),(27,'13','2025-03-20 14:24:56',_binary '','Frozen Foods-❄️',100,8,1,NULL,0,100,100),(28,'14','2025-03-20 14:25:11',_binary '','Ice Cream-?',100,8,1,NULL,0,100,100),(29,'15','2025-03-20 14:27:12',_binary '','Bakery Items-?',100,8,1,NULL,0,100,100);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,_binary '','Soft Drinks'),(2,_binary '','Snacks'),(3,_binary '','Burgers'),(4,_binary '\0','Chicken Items'),(5,_binary '','Desserts'),(6,_binary '','Fruits');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `purchase_list`
--

LOCK TABLES `purchase_list` WRITE;
/*!40000 ALTER TABLE `purchase_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shifts`
--

LOCK TABLES `shifts` WRITE;
/*!40000 ALTER TABLE `shifts` DISABLE KEYS */;
INSERT INTO `shifts` VALUES (1,'2025-02-21 00:59:59',_binary '','2025-02-19 20:07:59',1),(2,'2025-03-21 00:59:59',_binary '','2025-03-19 20:07:59',2),(3,'2025-04-21 00:59:59',_binary '','2025-04-19 20:07:59',3);
/*!40000 ALTER TABLE `shifts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shop_details`
--

LOCK TABLES `shop_details` WRITE;
/*!40000 ALTER TABLE `shop_details` DISABLE KEYS */;
INSERT INTO `shop_details` VALUES (1,'123 Main Street Colombo Sri Lanka','0771234567','shop@kfc.com',_binary '','KFC','0777654321');
/*!40000 ALTER TABLE `shop_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,_binary '',100,1,2),(2,_binary '',100,2,3),(3,_binary '',100,3,4),(4,_binary '',100,4,5),(5,_binary '',100,5,6);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'2025-01-21 18:44:57','chickenco@gmail.com',_binary '','0761234567','Fresh Chicken Co.','0751234567'),(2,'2025-01-21 18:48:17','packaging@gmail.com',_binary '','0784567890','Packaging Masters','0774567890'),(3,'2025-01-21 18:46:22','beveragesup@gmail.com',_binary '','0779876543','Beverage Suppliers','0769876543'),(4,'2025-03-08 00:34:46','spicespremium@gmail.com',_binary '','0712345677','Premium Spices Ltd','0702345600'),(5,'2025-03-07 21:00:19','dairydelights@gmail.com',_binary '','0725678901','Dairy Delights','0715678901'),(6,'2025-03-07 21:00:15','uthaman@gmail.com',_binary '\0','0745755525','Prusoth','0774858965');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tax`
--

LOCK TABLES `tax` WRITE;
/*!40000 ALTER TABLE `tax` DISABLE KEYS */;
INSERT INTO `tax` VALUES (1,_binary '',5),(2,_binary '',10),(3,_binary '',15),(4,_binary '\0',20),(5,_binary '',25),(6,_binary '',30),(7,_binary '\0',7);
/*!40000 ALTER TABLE `tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'2025-02-11 22:58:11',_binary '','Completed',5000,1,3,1,1),(2,'2025-02-11 23:02:54',_binary '','Completed',5175,3,1,1,2),(3,'2025-02-12 16:46:15',_binary '','Completed',7575,5,5,1,1),(4,'2025-02-16 18:25:08',_binary '','Completed',9200,3,2,1,1);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transaction_details_list`
--

LOCK TABLES `transaction_details_list` WRITE;
/*!40000 ALTER TABLE `transaction_details_list` DISABLE KEYS */;
INSERT INTO `transaction_details_list` VALUES (1,50,2,1000,1,1),(2,100,2,1500,3,1),(3,75,3,1200,3,2),(4,100,1,1750,2,2),(5,75,3,2400,5,3),(6,100,1,550,2,3),(7,50,2,550,1,4),(8,100,2,550,2,4),(9,50,3,2400,5,4);
/*!40000 ALTER TABLE `transaction_details_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transaction_payment_method`
--

LOCK TABLES `transaction_payment_method` WRITE;
/*!40000 ALTER TABLE `transaction_payment_method` DISABLE KEYS */;
INSERT INTO `transaction_payment_method` VALUES (1,3000,_binary '',1,1),(2,2000,_binary '',2,1),(3,3000,_binary '',1,2),(4,2175,_binary '',2,2),(5,2975,_binary '',1,3),(6,2000,_binary '',3,3),(7,4000,_binary '',1,4);
/*!40000 ALTER TABLE `transaction_payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Jaffna','2025-01-21 19:29:19','lathusanthurairajah@codearson.com','Lathusan',_binary '','Thurairajah','076121266','$2a$10$fje4amNbEEQfTvzYNMSPBOo3/fuz4eMIOZHJaLF4sknnwSa6yyNRO',1,1,NULL),(2,'Colombo','2025-01-21 19:29:19','svinothini96s@gmail.com','Vinothini',_binary '','Sathithyaseelan','076261862','$2a$10$AG71q.p3jWxX5YmhPUCzEOZmT6Kbfbw3MOLxnlbw4kknqv99WojCu',2,2,NULL),(3,'Colombo','2025-01-21 19:29:19','saravanan.g@codearson.com','Saravanan',_binary '','Guganathan','0765639528','$2a$10$o38YxJEgLvFOADxsHIQ1fu9NEoOC01IdjHU51cjFXTEaGWlzYGd4W',5,3,NULL),(4,'Jaffna','2025-02-20 16:36:24','skbavi61@gmail.com','Bavithragithan',_binary '','Kuganesan','076261862','$2a$10$PNny.kLUlMkwAf2e9wAVJ.cAiEM9hAGlKVN/Q32Fq6GpsU7q/c7R6',1,2,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_logs`
--

LOCK TABLES `user_logs` WRITE;
/*!40000 ALTER TABLE `user_logs` DISABLE KEYS */;
INSERT INTO `user_logs` VALUES (1,'Login','2025-02-20 23:50:06',NULL,_binary '',1),(2,'Login','2025-02-20 23:50:10',NULL,_binary '',2),(3,'Login','2025-02-20 23:50:14',NULL,_binary '',3),(4,'Password reset successfully','2025-02-21 00:36:12',NULL,_binary '\0',4);
/*!40000 ALTER TABLE `user_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,_binary '','ADMIN'),(2,_binary '','MANAGER'),(3,_binary '','USER');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-21 20:41:30
