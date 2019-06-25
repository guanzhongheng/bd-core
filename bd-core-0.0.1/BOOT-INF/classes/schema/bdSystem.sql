/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : bdSystem

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 23/06/2019 10:42:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for T_ACCT_INFO
-- ----------------------------
DROP TABLE IF EXISTS `T_ACCT_INFO`;
CREATE TABLE `T_ACCT_INFO` (
  `user_id` bigint(20) NOT NULL,
  `aval_amount` double(10,2) DEFAULT NULL,
  `total_amount` double(10,2) DEFAULT NULL,
  `rate` double(5,2) DEFAULT NULL COMMENT 'cnst折算比例；默认是5倍',
  `recomm_sum` int(10) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_EXPORT_RECORD
-- ----------------------------
DROP TABLE IF EXISTS `T_EXPORT_RECORD`;
CREATE TABLE `T_EXPORT_RECORD` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `amount` double(10,2) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_usId` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_RECOMM_REL_INFO
-- ----------------------------
DROP TABLE IF EXISTS `T_RECOMM_REL_INFO`;
CREATE TABLE `T_RECOMM_REL_INFO` (
  `user_id` bigint(20) NOT NULL,
  `recomm_user_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idx_recomm_uid` (`recomm_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_USER_INFO
-- ----------------------------
DROP TABLE IF EXISTS `T_USER_INFO`;
CREATE TABLE `T_USER_INFO` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL COMMENT '邮箱地址',
  `password` varchar(20) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL COMMENT '收货地址',
  `inv_code` varchar(30) DEFAULT NULL COMMENT '邀请码',
  `attach_url` varchar(30) DEFAULT NULL COMMENT '交易所充值地址',
  `status` char(1) NOT NULL COMMENT '0-待完善；1-正常',
  `ship_status` char(1) NOT NULL COMMENT '0-未发货；1-已发货',
  `phone` varchar(15) DEFAULT NULL COMMENT '收货人联系电话',
  `recieve_name` varchar(10) DEFAULT NULL COMMENT '收货人姓名',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `idx_status` (`status`),
  KEY `idx_ship_status` (`ship_status`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
