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

 Date: 25/12/2023 23:41:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '具体商品的id',
  `product_id` int NOT NULL COMMENT '所属产品id',
  `goods_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '具体商品的详情 因为是账号所以这里就是账号信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int NOT NULL COMMENT '商品是否删除 0 未删除 1 已删除',
  `status` int NOT NULL COMMENT '下架状态 0 未下架 1 已下架',
  `goods_status` int NOT NULL COMMENT '商品状态 0 可用 1 已售 2 锁定',
  PRIMARY KEY (`goods_id`) USING BTREE,
  INDEX `prods`(`product_id` ASC) USING BTREE,
  CONSTRAINT `prods` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `product_id` int NOT NULL COMMENT '购买产品的id',
  `product_name` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '购买产品的名字',
  `goods_price` int NOT NULL COMMENT '商品单价',
  `order_total_price` int NOT NULL COMMENT '订单总价',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户邮箱',
  `goods_detail_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '具体商品的id 可多个 用于解库存',
  `order_status` int NOT NULL COMMENT '订单状态 0 库存已经锁定 1 已完成 2 已取消',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `goods_num` int NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`order_sn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品名称',
  `product_price` int NOT NULL COMMENT '产品价格',
  `product_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品logo',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int NOT NULL COMMENT '产品是否删除 0 未删除 1 已删除',
  `product_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品注意事项 用:隔开',
  `product_warn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品警告 用:隔开',
  `product_stock` int NOT NULL COMMENT '产品库存',
  `product_lock_stock` int NOT NULL COMMENT '产品锁定库存',
  `product_method` int NOT NULL COMMENT '发货方式 0 自动 1 手动',
  `status` int NOT NULL COMMENT '产品是否下架 0 未下架 1 已下架',
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `category_id`(`product_category_id` ASC) USING BTREE,
  CONSTRAINT `category_id` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`product_category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 6, '飞机共享号飞机好东西', 12, 'public/2023-12-25/01955e0d7cd24613b9f376b83b4e2b41.png', '2023-12-25 22:09:13', '2023-12-25 23:28:43', 1, '21', '12', 0, 0, 1, 1);
INSERT INTO `product` VALUES (2, 8, '小火箭共享账号', 16, 'public/2023-12-25/baf7978adf804d73937641b8df2b41be.png', '2023-12-25 22:43:25', '2023-12-25 23:31:56', 0, '没有', '没有:没有没有:没有没有:没有', 0, 0, 1, 0);

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `product_category_id` int NOT NULL AUTO_INCREMENT COMMENT '产品分类id',
  `product_category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品分类名称',
  `product_category_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品分类logo',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int NOT NULL COMMENT '产品分类是否删除 0 未删除 1 已删除',
  `status` int NOT NULL COMMENT '上架状态 0 上架 1 下架',
  PRIMARY KEY (`product_category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (6, '飞机账号', 'public/2023-12-25/5105a96c1b824444b4553b37eced8830.png', '2023-12-25 19:14:30', '2023-12-25 22:45:59', 0, 0);
INSERT INTO `product_category` VALUES (7, '推特账号', 'public/2023-12-25/55819baa087d433f9dfbbe6f5beba1ec.png', '2023-12-25 19:20:08', '2023-12-25 22:45:58', 0, 0);
INSERT INTO `product_category` VALUES (8, '苹果id', 'public/2023-12-25/e0073b89abdc4d7ea40fbf4dcf216601.png', '2023-12-25 19:31:08', '2023-12-25 19:54:18', 0, 0);
INSERT INTO `product_category` VALUES (9, '12', 'public/2023-12-25/93d402ea7fcd4b7eb5cc0084b7629c76.png', '2023-12-25 20:19:21', '2023-12-25 20:19:21', 1, 1);
INSERT INTO `product_category` VALUES (10, '21', 'public/2023-12-25/3b1181c47b5f418cbf35e1446e3e35ef.png', '2023-12-25 20:20:43', '2023-12-25 20:20:43', 1, 0);
INSERT INTO `product_category` VALUES (11, '34', 'public/2023-12-25/a23279aa8c724f4ca9f62d00614fa7bb.png', '2023-12-25 20:21:56', '2023-12-25 20:21:56', 1, 1);
INSERT INTO `product_category` VALUES (12, 'nihao', 'public/2023-12-25/0ad64550490346368ff2d3ad668e416d.png', '2023-12-25 20:24:08', '2023-12-25 20:24:08', 1, 1);
INSERT INTO `product_category` VALUES (13, '45654', 'public/2023-12-25/c616275fc392453188c51ec82977ef97.png', '2023-12-25 20:24:37', '2023-12-25 20:24:37', 1, 1);
INSERT INTO `product_category` VALUES (14, '123121231', 'public/2023-12-25/18e3600aae07498890c7aff6d7c7acad.png', '2023-12-25 20:27:11', '2023-12-25 20:27:11', 1, 1);
INSERT INTO `product_category` VALUES (15, '你好吗', 'public/2023-12-25/d1c0db352e854ba8a1cefb970ecce315.png', '2023-12-25 20:27:23', '2023-12-25 21:04:02', 1, 0);
INSERT INTO `product_category` VALUES (16, 'wer', 'public/2023-12-25/5a0a87c22e374034a613c960a8166709.png', '2023-12-25 20:39:28', '2023-12-25 21:04:08', 1, 0);
INSERT INTO `product_category` VALUES (17, 'fweqef', 'public/2023-12-25/9d3c96e1602447068b75317490bb4759.png', '2023-12-25 22:07:19', '2023-12-25 22:45:55', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
