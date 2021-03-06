/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : cheng

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2015-05-16 22:14:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `arc`
-- ----------------------------
DROP TABLE IF EXISTS `arc`;
CREATE TABLE `arc` (
  `id` varchar(255) NOT NULL,
  `netId` int(11) NOT NULL,
  `source` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `inscription` varchar(255) DEFAULT NULL,
  `tagged` varchar(255) DEFAULT NULL,
  `isDel` bit(1) NOT NULL COMMENT '模拟删除',
  `destroyer` varchar(255) DEFAULT NULL,
  `destroyTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`,`netId`,`isDel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of arc
-- ----------------------------

-- ----------------------------
-- Table structure for `arcpath`
-- ----------------------------
DROP TABLE IF EXISTS `arcpath`;
CREATE TABLE `arcpath` (
  `id` tinyint(11) NOT NULL,
  `arcId` varchar(255) NOT NULL,
  `netId` tinyint(11) NOT NULL,
  `x` varchar(255) DEFAULT NULL,
  `y` varchar(255) DEFAULT NULL,
  `curvePoint` varchar(255) DEFAULT NULL,
  `isDel` bit(1) NOT NULL COMMENT '模拟删除',
  `destroyer` varchar(255) DEFAULT NULL,
  `destroyTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`,`arcId`,`netId`,`isDel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of arcpath
-- ----------------------------

-- ----------------------------
-- Table structure for `loginrecord`
-- ----------------------------
DROP TABLE IF EXISTS `loginrecord`;
CREATE TABLE `loginrecord` (
  `流水号` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `操作年度` varchar(20) DEFAULT NULL,
  `操作月份` varchar(20) DEFAULT NULL,
  `操作日期` varchar(20) DEFAULT NULL,
  `操作时间` varchar(20) DEFAULT NULL,
  `操作人` varchar(20) DEFAULT NULL,
  `操作状态` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`流水号`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of loginrecord
-- ----------------------------
INSERT INTO `loginrecord` VALUES ('1', '2015', '5', '8', '20 : 26', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('2', '2015', '5', '8', '20 : 34', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('3', '2015', '5', '8', '20 : 47', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('4', '2015', '5', '8', '20 : 48', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('5', '2015', '5', '8', '20 : 55', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('6', '2015', '5', '8', '21 : 4', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('7', '2015', '5', '8', '21 : 7', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('8', '2015', '5', '8', '21 : 10', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('9', '2015', '5', '8', '21 : 12', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('10', '2015', '5', '8', '21 : 18', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('11', '2015', '5', '8', '21 : 19', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('12', '2015', '5', '8', '21 : 21', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('13', '2015', '5', '8', '21 : 24', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('14', '2015', '5', '8', '21 : 27', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('15', '2015', '5', '8', '21 : 48', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('16', '2015', '5', '8', '21 : 50', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('17', '2015', '5', '8', '22 : 4', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('18', '2015', '5', '8', '22 : 5', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('19', '2015', '5', '8', '22 : 5', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('20', '2015', '5', '8', '22 : 5', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('21', '2015', '5', '8', '22 : 5', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('22', '2015', '5', '8', '22 : 5', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('23', '2015', '5', '8', '22 : 7', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('24', '2015', '5', '8', '22 : 13', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('25', '2015', '5', '8', '22 : 13', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('26', '2015', '5', '8', '22 : 13', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('27', '2015', '5', '8', '22 : 13', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('28', '2015', '5', '8', '22 : 13', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('29', '2015', '5', '8', '22 : 14', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('30', '2015', '5', '8', '22 : 21', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('31', '2015', '5', '8', '22 : 22', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('32', '2015', '5', '8', '22 : 30', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('33', '2015', '5', '8', '22 : 31', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('34', '2015', '5', '9', '17 : 50', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('35', '2015', '5', '9', '17 : 51', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('36', '2015', '5', '9', '17 : 54', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('37', '2015', '5', '9', '17 : 56', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('38', '2015', '5', '9', '18 : 1', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('39', '2015', '5', '9', '18 : 6', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('40', '2015', '5', '10', '11 : 28', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('41', '2015', '5', '10', '11 : 50', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('42', '2015', '5', '10', '11 : 54', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('43', '2015', '5', '10', '12 : 1', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('44', '2015', '5', '10', '12 : 23', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('45', '2015', '5', '10', '12 : 24', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('46', '2015', '5', '10', '12 : 42', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('47', '2015', '5', '10', '12 : 44', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('48', '2015', '5', '10', '12 : 47', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('49', '2015', '5', '10', '12 : 47', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('50', '2015', '5', '10', '12 : 47', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('51', '2015', '5', '10', '12 : 47', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('52', '2015', '5', '10', '12 : 48', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('53', '2015', '5', '10', '12 : 56', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('54', '2015', '5', '10', '13 : 10', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('55', '2015', '5', '10', '13 : 13', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('56', '2015', '5', '10', '13 : 15', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('57', '2015', '5', '10', '13 : 27', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('58', '2015', '5', '10', '13 : 29', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('59', '2015', '5', '10', '13 : 55', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('60', '2015', '5', '10', '13 : 55', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('61', '2015', '5', '13', '13 : 41', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('62', '2015', '5', '13', '13 : 51', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('63', '2015', '5', '16', '10 : 28', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('64', '2015', '5', '16', '10 : 32', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('65', '2015', '5', '16', '10 : 32', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('66', '2015', '5', '16', '10 : 44', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('67', '2015', '5', '16', '10 : 44', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('68', '2015', '5', '16', '10 : 44', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('69', '2015', '5', '16', '11 : 0', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('70', '2015', '5', '16', '11 : 20', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('71', '2015', '5', '16', '11 : 20', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('72', '2015', '5', '16', '11 : 20', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('73', '2015', '5', '16', '11 : 23', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('74', '2015', '5', '16', '11 : 24', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('75', '2015', '5', '16', '11 : 32', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('76', '2015', '5', '16', '12 : 24', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('77', '2015', '5', '16', '12 : 27', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('78', '2015', '5', '16', '12 : 32', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('79', '2015', '5', '16', '12 : 38', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('80', '2015', '5', '16', '12 : 58', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('81', '2015', '5', '16', '13 : 0', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('82', '2015', '5', '16', '13 : 4', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('83', '2015', '5', '16', '13 : 5', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('84', '2015', '5', '16', '13 : 6', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('85', '2015', '5', '16', '13 : 19', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('86', '2015', '5', '16', '13 : 23', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('87', '2015', '5', '16', '13 : 27', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('88', '2015', '5', '16', '13 : 31', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('89', '2015', '5', '16', '13 : 34', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('90', '2015', '5', '16', '13 : 39', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('91', '2015', '5', '16', '13 : 49', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('92', '2015', '5', '16', '13 : 50', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('93', '2015', '5', '16', '13 : 53', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('94', '2015', '5', '16', '14 : 15', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('95', '2015', '5', '16', '14 : 16', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('96', '2015', '5', '16', '14 : 17', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('97', '2015', '5', '16', '14 : 18', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('98', '2015', '5', '16', '14 : 19', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('99', '2015', '5', '16', '14 : 20', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('100', '2015', '5', '16', '14 : 23', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('101', '2015', '5', '16', '14 : 27', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('102', '2015', '5', '16', '14 : 28', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('103', '2015', '5', '16', '14 : 28', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('104', '2015', '5', '16', '14 : 29', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('105', '2015', '5', '16', '14 : 30', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('106', '2015', '5', '16', '14 : 31', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('107', '2015', '5', '16', '14 : 31', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('108', '2015', '5', '16', '21 : 0', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('109', '2015', '5', '16', '21 : 1', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('110', '2015', '5', '16', '21 : 7', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('111', '2015', '5', '16', '21 : 8', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('112', '2015', '5', '16', '21 : 20', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('113', '2015', '5', '16', '21 : 21', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('114', '2015', '5', '16', '21 : 36', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('115', '2015', '5', '16', '21 : 39', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('116', '2015', '5', '16', '21 : 40', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('117', '2015', '5', '16', '21 : 41', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('118', '2015', '5', '16', '21 : 42', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('119', '2015', '5', '16', '21 : 42', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('120', '2015', '5', '16', '21 : 46', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('121', '2015', '5', '16', '21 : 46', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('122', '2015', '5', '16', '21 : 50', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('123', '2015', '5', '16', '21 : 52', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('124', '2015', '5', '16', '21 : 54', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('125', '2015', '5', '16', '21 : 55', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('126', '2015', '5', '16', '21 : 55', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('127', '2015', '5', '16', '21 : 55', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('128', '2015', '5', '16', '21 : 56', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('129', '2015', '5', '16', '21 : 56', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('130', '2015', '5', '16', '21 : 57', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('131', '2015', '5', '16', '21 : 57', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('132', '2015', '5', '16', '21 : 58', 'root', '退出');
INSERT INTO `loginrecord` VALUES ('133', '2015', '5', '16', '22 : 8', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('134', '2015', '5', '16', '22 : 9', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('135', '2015', '5', '16', '22 : 11', 'root', '进入');
INSERT INTO `loginrecord` VALUES ('136', '2015', '5', '16', '22 : 11', 'root', '退出');

-- ----------------------------
-- Table structure for `loginsystem`
-- ----------------------------
DROP TABLE IF EXISTS `loginsystem`;
CREATE TABLE `loginsystem` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `Department` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `IsSystemManager` varchar(255) NOT NULL,
  `IsFinancialManager` varchar(255) NOT NULL,
  `EmailAddress` varchar(255) DEFAULT NULL,
  `Birthday` varchar(255) DEFAULT NULL,
  `HomeAddress` varchar(255) DEFAULT NULL,
  `Remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of loginsystem
-- ----------------------------
INSERT INTO `loginsystem` VALUES ('001', 'root', '男', 'root', 'root', '财务', '15297300008', '是', '是', '15297300001@163.com', '12345000900', '北京市朝阳区', '我是系统管1112222222222222');

-- ----------------------------
-- Table structure for `net`
-- ----------------------------
DROP TABLE IF EXISTS `net`;
CREATE TABLE `net` (
  `id` tinyint(11) unsigned NOT NULL AUTO_INCREMENT,
  `netId` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `creater` varchar(255) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `isDel` bit(1) NOT NULL COMMENT '模拟删除',
  `destroyer` varchar(255) DEFAULT NULL,
  `destroyTime` timestamp NULL DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of net
-- ----------------------------

-- ----------------------------
-- Table structure for `place`
-- ----------------------------
DROP TABLE IF EXISTS `place`;
CREATE TABLE `place` (
  `id` varchar(255) NOT NULL,
  `netId` int(11) NOT NULL,
  `positionX` varchar(255) DEFAULT NULL,
  `positionY` varchar(255) DEFAULT NULL,
  `nameValue` varchar(255) DEFAULT NULL,
  `nameOffsetX` varchar(255) DEFAULT NULL,
  `nameOffsetY` varchar(255) DEFAULT NULL,
  `initMarkValue` varchar(255) DEFAULT NULL,
  `initMarkOffsetX` varchar(255) DEFAULT NULL,
  `initMarkOffsetY` varchar(255) DEFAULT NULL,
  `capacity` varchar(255) DEFAULT NULL,
  `isDel` bit(1) NOT NULL COMMENT '模拟删除',
  `destroyer` varchar(255) DEFAULT NULL,
  `destroyTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`,`netId`,`isDel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of place
-- ----------------------------

-- ----------------------------
-- Table structure for `transition`
-- ----------------------------
DROP TABLE IF EXISTS `transition`;
CREATE TABLE `transition` (
  `id` varchar(255) NOT NULL,
  `netId` int(11) NOT NULL,
  `positionX` varchar(255) DEFAULT NULL,
  `positionY` varchar(255) DEFAULT NULL,
  `nameValue` varchar(255) DEFAULT NULL,
  `nameOffsetX` varchar(255) DEFAULT NULL,
  `nameOffsetY` varchar(255) DEFAULT NULL,
  `orientation` varchar(255) DEFAULT NULL,
  `rate` varchar(255) DEFAULT NULL,
  `timed` varchar(255) DEFAULT NULL,
  `infiniteServer` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `startTime` varchar(255) DEFAULT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `isDel` bit(1) NOT NULL COMMENT '模拟删除',
  `destroyer` varchar(255) DEFAULT NULL,
  `destroyTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`,`netId`,`isDel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transition
-- ----------------------------
