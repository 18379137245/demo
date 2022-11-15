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

 Date: 15/11/2022 16:53:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_details
-- ----------------------------
DROP TABLE IF EXISTS `account_details`;
CREATE TABLE `account_details`  (
  `details_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '明细id',
  `merchant_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家名称',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `creation_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `payment` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
  `trasaction` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易单号',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额',
  PRIMARY KEY (`details_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_details
-- ----------------------------
INSERT INTO `account_details` VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1001', 4501.00);
INSERT INTO `account_details` VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1001', 5001.00);
INSERT INTO `account_details` VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1001', 4969.00);
INSERT INTO `account_details` VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1001', 0.00);
INSERT INTO `account_details` VALUES (5, '1', '1', '1', '1', '2022-11-15 02:55:51', '1', '1', '1001', 500.00);
INSERT INTO `account_details` VALUES (6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1001', 1000.00);
INSERT INTO `account_details` VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1001', 1500.00);
INSERT INTO `account_details` VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1001', 1468.00);
INSERT INTO `account_details` VALUES (15, '周黑鸭店铺', 'null', 'null', '周黑鸭', '2022-11-15 03:24:01', 'null', 'null', 'null', NULL);
INSERT INTO `account_details` VALUES (16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1001', 968.00);
INSERT INTO `account_details` VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1001', 468.00);
INSERT INTO `account_details` VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1001', 436.00);
INSERT INTO `account_details` VALUES (19, '周黑鸭店铺', '支付', 'null', '周黑鸭', '2022-11-15 16:37:05', 'null', 'null', '1001', NULL);
INSERT INTO `account_details` VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1001', 468.00);
INSERT INTO `account_details` VALUES (21, '周黑鸭店铺', '退款', 'null', '周黑鸭', '2022-11-15 16:37:14', 'null', 'null', '1001', NULL);

SET FOREIGN_KEY_CHECKS = 1;
