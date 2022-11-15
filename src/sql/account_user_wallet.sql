/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : ding

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 15/11/2022 16:52:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_user_wallet
-- ----------------------------
DROP TABLE IF EXISTS `account_user_wallet`;
CREATE TABLE `account_user_wallet`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `balance_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '钱包id',
  `balance` decimal(64, 2) NOT NULL COMMENT '余额',
  `deposits` decimal(64, 2) NOT NULL COMMENT '银行金额',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_user_wallet
-- ----------------------------
INSERT INTO `account_user_wallet` VALUES ('1001', '1', 468.00, 1000.00);

SET FOREIGN_KEY_CHECKS = 1;
