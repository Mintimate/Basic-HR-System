/*
 Navicat Premium Data Transfer

 Source Server         : MintimateBS
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Host           : www.mintimate.cn:3306
 Source Schema         : intimate

 Target Server Type    : MySQL
 Target Server Version : 50646
 File Encoding         : 65001

 Date: 10/01/2020 08:29:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dep
-- ----------------------------
DROP TABLE IF EXISTS `dep`;
CREATE TABLE `dep` (
  `depNo` varchar(4) NOT NULL DEFAULT '',
  `depName` varchar(10) DEFAULT NULL,
  `depManager` varchar(10) DEFAULT NULL,
  `empNo` varchar(4) NOT NULL,
  PRIMARY KEY (`depNo`,`empNo`) USING BTREE,
  KEY `empName` (`depName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dep
-- ----------------------------
BEGIN;
INSERT INTO `dep` VALUES ('1001', '行政部', '李美', '2202');
INSERT INTO `dep` VALUES ('1002', '后勤部', '李四', '2303');
INSERT INTO `dep` VALUES ('1121', '生产部', '王五', '2304');
INSERT INTO `dep` VALUES ('1122', '销售部', '王无', '2305');
INSERT INTO `dep` VALUES ('1123', '???', '梅吹花', '2306');
INSERT INTO `dep` VALUES ('1131', '财务部', '翠花', '2307');
INSERT INTO `dep` VALUES ('1132', '保安部', '燕双鹰', '3001');
COMMIT;

-- ----------------------------
-- Table structure for dep_copy1
-- ----------------------------
DROP TABLE IF EXISTS `dep_copy1`;
CREATE TABLE `dep_copy1` (
  `depNo` varchar(4) NOT NULL DEFAULT '',
  `depName` varchar(10) DEFAULT NULL,
  `depManager` varchar(10) DEFAULT NULL,
  `empNo` varchar(4) NOT NULL,
  PRIMARY KEY (`depNo`,`empNo`) USING BTREE,
  KEY `empName` (`depName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dep_copy1
-- ----------------------------
BEGIN;
INSERT INTO `dep_copy1` VALUES ('1001', '行政部', '李美', '2202');
INSERT INTO `dep_copy1` VALUES ('1002', '后勤部', '李四', '2303');
INSERT INTO `dep_copy1` VALUES ('1121', '生产部', '王五', '2304');
INSERT INTO `dep_copy1` VALUES ('1122', '销售部', '王无', '2305');
INSERT INTO `dep_copy1` VALUES ('1123', '售后部', '梅吹花', '2306');
INSERT INTO `dep_copy1` VALUES ('1131', '财务部', '翠花', '2307');
INSERT INTO `dep_copy1` VALUES ('1132', '保安部', '燕双鹰', '3001');
COMMIT;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `empNo` varchar(11) NOT NULL DEFAULT '',
  `empName` varchar(10) NOT NULL,
  `empSex` varchar(3) DEFAULT NULL,
  `empEdu` varchar(10) DEFAULT NULL,
  `empAge` varchar(3) DEFAULT NULL,
  `depName` varchar(20) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `salary` varchar(10) DEFAULT '0',
  PRIMARY KEY (`empNo`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of emp
-- ----------------------------
BEGIN;
INSERT INTO `emp` VALUES ('201', '乔布斯', '女', '本科', '25', '生产部', '职员', '8000');
INSERT INTO `emp` VALUES ('202', '库克', '男', '本科', '26', '销售部', '副部长', '10000');
INSERT INTO `emp` VALUES ('203', '马云', '男', '博士', '31', '外联部', '顾问', '12000');
INSERT INTO `emp` VALUES ('204', '朱棣', '男', '本科', '25', '销售部', '部长', '12000');
INSERT INTO `emp` VALUES ('205', '朱元璋', '男', '博士', '30', '行政部', '太祖', '20000');
INSERT INTO `emp` VALUES ('206', '徐阶', '男', '硕士', '32', '行政部', '副部长', '200');
INSERT INTO `emp` VALUES ('207', '张居正', '男', '本科', '24', '内阁部', '内阁首辅', '30000');
INSERT INTO `emp` VALUES ('208', '陈旭强', '男', '本科', '23', '开发部', '总工程师', '15000');
INSERT INTO `emp` VALUES ('209', '王守仁', '男', '本科', '32', '后勤部', '部长', '12000');
INSERT INTO `emp` VALUES ('210', '袁崇焕', '男', '本科', '35', '生产部', '部长', '12000');
INSERT INTO `emp` VALUES ('211', '朱祁镇', '男', '硕士', '31', '销售部', '部长', '12000');
INSERT INTO `emp` VALUES ('212', '魏忠贤', '男', '本科', '25', '售后部', '部长', '12000');
INSERT INTO `emp` VALUES ('213', '胡宗宪', '男', '本科', '27', '财务部', '部长', '12000');
INSERT INTO `emp` VALUES ('214', '也先', '男', '本科', '22', '保安部', '部长', '12000');
INSERT INTO `emp` VALUES ('215', '高拱', '男', '本科', '26', '财务部', '职员', '8000');
INSERT INTO `emp` VALUES ('216', '朱厚照', '男', '本科', '27', '行政部', '武宗', '18000');
INSERT INTO `emp` VALUES ('217', '陈友谅', '男', '本科', '28', '军机部', '职员', '12000');
INSERT INTO `emp` VALUES ('218', '戚继光', '男', '本科', '29', '军机部', '部长', '12000');
INSERT INTO `emp` VALUES ('219', '夏言', '男', '本科', '30', '保安部', '职员', '8000');
INSERT INTO `emp` VALUES ('220', '于谦', '男', '本科', '31', '内阁部', '职员', '8000');
INSERT INTO `emp` VALUES ('221', '李如松', '男', '本科', '32', '军机部', '副部长', '10000');
INSERT INTO `emp` VALUES ('222', '方法', '男', '本科', '33', '售后部', '部长', '12000');
COMMIT;

-- ----------------------------
-- Table structure for sal
-- ----------------------------
DROP TABLE IF EXISTS `sal`;
CREATE TABLE `sal` (
  `empNo` varchar(4) NOT NULL,
  `empName` varchar(10) NOT NULL,
  `empSex` varchar(3) DEFAULT NULL,
  `depName` varchar(20) DEFAULT NULL,
  `prize` decimal(10,2) DEFAULT NULL,
  `overtime` decimal(10,2) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`empNo`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=gbk ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sal
-- ----------------------------
BEGIN;
INSERT INTO `sal` VALUES ('2001', '月巧英', '女', '生产部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('2009', '李白', '男', '保安部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('2202', '李美', '女', '行政部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('2203', '钱多多', '男', '行政部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('2211', '张巧', '男', '	销售部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('2241', '王翰', '男', '售后部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('2303', '李四', '男', '后勤部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('2304', '王五', '男', '生产部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('2305', '王无', '女', '销售部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('2306', '梅吹花', '女', '售后部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('2307', '翠花', '女', '财务部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('3001', '燕双鹰', '男', '保安部', NULL, NULL, NULL);
INSERT INTO `sal` VALUES ('e310', '张三', '男', '财务部', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `limit` int(255) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=gbk ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (NULL, '2333', 'f7e0b956540676a129760a3eae309294', 0, '');
INSERT INTO `user` VALUES (NULL, '666', 'fae0b27c451c728867a567e8c1bb4e53', 1, '');
INSERT INTO `user` VALUES (NULL, 'a', '900150983cd24fb0d6963f7d28e17f72', 0, 'a');
INSERT INTO `user` VALUES (NULL, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, 'admin');
INSERT INTO `user` VALUES (NULL, 'b', '92eb5ffee6ae2fec3ad71c777531578f', 1, 'b');
INSERT INTO `user` VALUES (NULL, 'd', '8277e0910d750195b448797616e091ad', 0, 'd');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
