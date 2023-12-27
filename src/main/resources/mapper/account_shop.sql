/*
 Navicat Premium Data Transfer

 Source Server         : lee7s-local
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : account_shop

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 27/12/2023 21:40:43
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '具体商品的id',
  `product_id` int NOT NULL COMMENT '所属产品id',
  `goods_detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '具体商品的详情 因为是账号所以这里就是账号信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int NOT NULL COMMENT '商品是否删除 0 未删除 1 已删除',
  `status` int NOT NULL COMMENT '下架状态 0 未下架 1 已下架',
  `goods_status` int NOT NULL COMMENT '商品状态 0 可用 1 已售 2 锁定',
  PRIMARY KEY (`goods_id`) USING BTREE,
  INDEX `prods`(`product_id` ASC) USING BTREE,
  CONSTRAINT `prods` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (51, 8, '+1234234234234   https://wejfwiofjwiefjweoi.com/werfjiowejfwejfiowejfowejfwoejfowpeijf', '2023-12-26 14:56:25', '2023-12-27 19:43:41', 0, 0, 0);
INSERT INTO `goods` VALUES (52, 8, '+213213124233432v https:///wfwefwef', '2023-12-26 14:57:50', '2023-12-27 19:43:41', 0, 0, 0);
INSERT INTO `goods` VALUES (53, 8, '为人父个体户脚后跟范德萨', '2023-12-26 16:29:44', '2023-12-27 20:59:41', 0, 0, 0);
INSERT INTO `goods` VALUES (54, 9, '微软给海关法的实施', '2023-12-26 17:06:23', '2023-12-27 11:17:43', 0, 0, 0);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `product_id` int NOT NULL COMMENT '购买产品的id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '购买产品的名字',
  `goods_price` int NOT NULL COMMENT '商品单价',
  `order_total_price` int NOT NULL COMMENT '订单总价',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户邮箱',
  `goods_detail_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '具体商品的id 可多个 用于解库存 : 分割',
  `order_status` int NOT NULL COMMENT '订单状态 0 库存已经锁定 1 已完成 2 已取消',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `goods_num` int NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`order_sn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `product_category_id` int NOT NULL COMMENT '产品分类id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `product_price` int NOT NULL COMMENT '产品价格',
  `product_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品logo',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int NOT NULL COMMENT '产品是否删除 0 未删除 1 已删除',
  `product_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品注意事项 用:隔开',
  `product_warn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品警告 用:隔开',
  `product_stock` int NOT NULL COMMENT '产品库存',
  `product_lock_stock` int NOT NULL COMMENT '产品锁定库存',
  `product_method` int NOT NULL COMMENT '发货方式 0 自动 1 手动',
  `status` int NOT NULL COMMENT '产品是否下架 0 未下架 1 已下架',
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `category_id`(`product_category_id` ASC) USING BTREE,
  CONSTRAINT `category_id` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`product_category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (8, 28, 'Telegram美国成品号', 1, 'public/2023-12-26/10fee6b2c19e47fe88af573494055f4d.png', '2023-12-26 14:42:40', '2023-12-27 20:59:41', 0, '需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用:需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用', '切勿违规使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用', 3, 0, 0, 0);
INSERT INTO `product` VALUES (9, 28, 'Telegram协议号', 20, 'public/2023-12-26/0e19b8e8d762423d93c189976d7e3f04.png', '2023-12-26 14:43:31', '2023-12-27 20:16:18', 0, '本店在技术范围内会尽力保障商品的可用性，所有商品如无单独标注，售后期为商品发货24小时内；批发售后期为商品发货5天内:建议只在app store里面登录账号，请勿在【设置】里登录账号', '每天数量有限，找TG客服预定，优先发货！', 1, 0, 1, 0);

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `product_category_id` int NOT NULL AUTO_INCREMENT COMMENT '产品分类id',
  `product_category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品分类名称',
  `product_category_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品分类logo',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int NOT NULL COMMENT '产品分类是否删除 0 未删除 1 已删除',
  `status` int NOT NULL COMMENT '上架状态 0 上架 1 下架',
  PRIMARY KEY (`product_category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (28, 'Telegram账号', 'public/2023-12-26/9415bbb0610c478f8c997c1714110b7b.png', '2023-12-26 14:40:31', '2023-12-27 20:12:04', 0, 0);
INSERT INTO `product_category` VALUES (29, 'Twitter账号', 'public/2023-12-26/e25bf9855f0541578af25be649c442a8.png', '2023-12-26 14:40:56', '2023-12-27 20:15:36', 0, 0);
INSERT INTO `product_category` VALUES (30, 'AppliId', 'public/2023-12-26/a58d2ef193bd4cd3ac9662979ce7868a.png', '2023-12-26 14:41:08', '2023-12-27 20:15:34', 0, 0);
INSERT INTO `product_category` VALUES (31, 'TIktok账号', 'public/2023-12-26/cc64af97181346a5b4bfb9493dbee1fa.png', '2023-12-26 14:41:23', '2023-12-27 20:15:33', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
