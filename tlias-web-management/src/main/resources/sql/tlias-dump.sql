-- MySQL dump 10.13  Distrib 8.4.2, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tlias
-- ------------------------------------------------------
-- Server version	8.4.2

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
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(10) NOT NULL COMMENT '部门名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (1,'学工部','2024-08-13 20:04:39','2024-08-13 20:04:39'),(2,'教研部','2024-08-13 20:04:39','2024-08-13 20:04:39'),(4,'就业部','2024-08-13 20:04:39','2024-08-13 20:04:39'),(6,'人事部','2024-08-16 15:51:39','2024-08-16 15:51:39');
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept_log`
--

DROP TABLE IF EXISTS `dept_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `description` varchar(300) DEFAULT NULL COMMENT '操作描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept_log`
--

LOCK TABLES `dept_log` WRITE;
/*!40000 ALTER TABLE `dept_log` DISABLE KEYS */;
INSERT INTO `dept_log` VALUES (5,'2024-08-14 12:52:50','执行了解散部门的操作, 此次解散的是2号部门'),(6,'2024-08-16 15:51:32','执行了解散部门的操作, 此次解散的是5号部门'),(7,'2024-08-16 15:51:58','执行了解散部门的操作, 此次解散的是3号部门');
/*!40000 ALTER TABLE `dept_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT '123456' COMMENT '密码',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `gender` tinyint unsigned NOT NULL COMMENT '性别, 说明: 1 男, 2 女',
  `image` varchar(300) DEFAULT NULL COMMENT '图像',
  `job` tinyint unsigned DEFAULT NULL COMMENT '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管, 5 咨询师',
  `entrydate` date DEFAULT NULL COMMENT '入职时间',
  `dept_id` int unsigned DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
INSERT INTO `emp` VALUES (1,'jinyong','123456','金庸',1,'1.jpg',4,'2000-01-01',2,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(2,'zhangwuji','123456','张无忌',1,'2.jpg',2,'2015-01-01',2,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(3,'yangxiao','123456','杨逍',1,'3.jpg',2,'2008-05-01',2,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(4,'weiyixiao','123456','韦一笑',1,'4.jpg',2,'2007-01-01',2,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(5,'changyuchun','123456','常遇春',1,'5.jpg',2,'2012-12-05',2,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(6,'xiaozhao','123456','小昭',2,'6.jpg',3,'2013-09-05',1,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(7,'jixiaofu','123456','纪晓芙',2,'7.jpg',1,'2005-08-01',1,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(8,'zhouzhiruo','123456','周芷若',2,'8.jpg',1,'2014-11-09',1,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(9,'dingminjun','123456','丁敏君',2,'9.jpg',1,'2011-03-11',1,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(10,'zhaomin','123456','赵敏',2,'10.jpg',1,'2013-09-05',1,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(14,'zhangsanfeng','123456','张三丰',1,'14.jpg',2,'2002-08-01',2,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(15,'yulianzhou','123456','俞莲舟',1,'15.jpg',2,'2011-05-01',2,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(16,'songyuanqiao','123456','宋远桥',1,'16.jpg',2,'2007-01-01',2,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(17,'chenyouliang','123456','陈友谅',1,'17.jpg',NULL,'2015-03-21',NULL,'2024-08-13 20:05:32','2024-08-13 20:05:32'),(18,'linpingzhi','123456','林平之',1,'http://tlias.itheima.com/1.jpg',1,'2015-09-18',1,'2024-08-06 12:02:40','2024-08-06 12:02:40'),(19,'Tom1','123456','汤姆1',1,'https://com-jason-web-tlias.oss-cn-beijing.aliyuncs.com/5a7b899d-f97e-4f39-9ac6-6aed3dd107bd.jpg',1,'2022-11-26',1,'2024-08-06 12:06:32','2024-08-07 22:07:08'),(22,'yaya','123456','丫丫',2,'https://com-jason-web-tlias.oss-cn-beijing.aliyuncs.com/3f7d87c2-d291-4b2e-8a40-e06ae5466bac.jpg',1,'2024-08-07',1,'2024-08-08 00:15:28','2024-08-08 00:15:28');
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operate_log`
--

DROP TABLE IF EXISTS `operate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operate_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `operate_user` int unsigned DEFAULT NULL COMMENT '操作人ID',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `class_name` varchar(100) DEFAULT NULL COMMENT '操作的类名',
  `method_name` varchar(100) DEFAULT NULL COMMENT '操作的方法名',
  `method_params` varchar(1000) DEFAULT NULL COMMENT '方法参数',
  `return_value` varchar(2000) DEFAULT NULL COMMENT '返回值',
  `cost_time` bigint DEFAULT NULL COMMENT '方法执行耗时, 单位:ms',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operate_log`
--

LOCK TABLES `operate_log` WRITE;
/*!40000 ALTER TABLE `operate_log` DISABLE KEYS */;
INSERT INTO `operate_log` VALUES (1,2,'2024-08-16 15:51:32','com.jason.controller.DeptController','delete','[5]','{\"code\":1,\"msg\":\"success\"}',33),(2,2,'2024-08-16 15:51:39','com.jason.controller.DeptController','add','[Dept(id=null, name=人事部, createTime=null, updateTime=null)]','{\"code\":1,\"msg\":\"success\"}',3),(3,2,'2024-08-16 15:51:58','com.jason.controller.DeptController','delete','[3]','{\"code\":1,\"msg\":\"success\"}',16);
/*!40000 ALTER TABLE `operate_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-07  0:49:41
