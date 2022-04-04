/*
 Navicat Premium Data Transfer

 Source Server         : 123
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : jiudianmanage

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 04/04/2022 12:31:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` int(0) NOT NULL,
  `managesalary` double(10, 2) DEFAULT NULL,
  `staffsalary` double(10, 2) DEFAULT NULL,
  `cleanerssalary` double(10, 2) DEFAULT NULL,
  `manage` double(10, 2) DEFAULT NULL,
  `staff` double(10, 2) DEFAULT NULL,
  `cleaner` double(10, 2) DEFAULT NULL,
  `totalmoney` double(10, 2) DEFAULT NULL,
  `totalroom` double(10, 2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lend
-- ----------------------------
DROP TABLE IF EXISTS `lend`;
CREATE TABLE `lend`  (
  `userid` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lend
-- ----------------------------
INSERT INTO `lend` VALUES (1, '全齐', '1', '15217191707', '已经与老师联系');
INSERT INTO `lend` VALUES (1, '周林', '1', '15217191707', 'SAADSADSAD');
INSERT INTO `lend` VALUES (1, 'hzh', '72', '136333', '好大好大的设备');
INSERT INTO `lend` VALUES (1, 'hzh', '67', '666', '了解美女');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `news_id` int(0) NOT NULL,
  `news_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `news_anthor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `news_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `news_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `news_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`news_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (111, '111', '666', '2020-4-1', 'www', 'File\\c30eaf7b-ddfe-4f11-bcf6-f7a425fe0747蕾姆1.jpg');
INSERT INTO `news` VALUES (321, '123', 'hhh', '2019-1-1', 'sport', 'File\\c30eaf7b-ddfe-4f11-bcf6-f7a425fe0747蕾姆1.jpg');

-- ----------------------------
-- Table structure for op_application
-- ----------------------------
DROP TABLE IF EXISTS `op_application`;
CREATE TABLE `op_application`  (
  `application_id` int(0) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `your_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `your_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `p_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `t_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`application_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of op_application
-- ----------------------------
INSERT INTO `op_application` VALUES (3, '研究王者算法', 'zl', '1901010056', '666', '信息技术类', '山上优雅', '1');
INSERT INTO `op_application` VALUES (4, 'AI人工智能', '何哲豪', '1901010056', '123', '信息技术类', 'Isbell', '0');
INSERT INTO `op_application` VALUES (5, '制作黄片', '全棋', '1901010102', '9300', '信息技术类', '苍井空', '0');
INSERT INTO `op_application` VALUES (6, '游戏开发', '占铭耀', '1901010056', '7777', '信息技术类', 'uzi', '1');
INSERT INTO `op_application` VALUES (7, '炒股票', 'fhw', '1901010050', '123', '数学金融类', '宋浩', '1');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `orderid` int(0) NOT NULL,
  `householdname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `starttime` datetime(0) DEFAULT NULL,
  `endtime` datetime(0) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `state` int(0) DEFAULT NULL,
  `roomid` int(0) DEFAULT NULL,
  `userid` int(0) DEFAULT NULL,
  PRIMARY KEY (`orderid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `roomid` int(0) NOT NULL,
  `local` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `money` double DEFAULT NULL,
  `state` int(0) DEFAULT NULL,
  `type` int(0) DEFAULT NULL,
  PRIMARY KEY (`roomid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for survey_tool
-- ----------------------------
DROP TABLE IF EXISTS `survey_tool`;
CREATE TABLE `survey_tool`  (
  `tool` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tool_id` int(0) NOT NULL AUTO_INCREMENT,
  `amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`tool_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of survey_tool
-- ----------------------------
INSERT INTO `survey_tool` VALUES ('电脑', 1, '10');
INSERT INTO `survey_tool` VALUES ('教室', 2, '20');
INSERT INTO `survey_tool` VALUES ('显微镜', 3, '100');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` int(0) NOT NULL,
  `useraccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(0) DEFAULT NULL,
  `power` int(0) DEFAULT NULL,
  `IDnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `money` double DEFAULT NULL,
  `photoUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phonenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1', '1', '111', 1, 0, '1', 111, 'File\\c30eaf7b-ddfe-4f11-bcf6-f7a425fe0747蕾姆1.jpg', '111');

SET FOREIGN_KEY_CHECKS = 1;
