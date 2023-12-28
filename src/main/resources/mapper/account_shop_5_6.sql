/*
 Navicat Premium Data Transfer

 Source Server         : vps
 Source Server Type    : MySQL
 Source Server Version : 50650 (5.6.50-log)
 Source Host           : 172.245.10.200:3306
 Source Schema         : account_shop

 Target Server Type    : MySQL
 Target Server Version : 50650 (5.6.50-log)
 File Encoding         : 65001

 Date: 28/12/2023 21:53:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '具体商品的id',
  `product_id` int(11) NOT NULL COMMENT '所属产品id',
  `goods_detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '具体商品的详情 因为是账号所以这里就是账号信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int(11) NOT NULL COMMENT '商品是否删除 0 未删除 1 已删除',
  `status` int(11) NOT NULL COMMENT '下架状态 0 未下架 1 已下架',
  `goods_status` int(11) NOT NULL COMMENT '商品状态 0 可用 1 已售 2 锁定',
  PRIMARY KEY (`goods_id`) USING BTREE,
  INDEX `prods`(`product_id`) USING BTREE,
  CONSTRAINT `prods` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 92 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (56, 11, '我就得哦房价我二姐夫', '2023-12-27 22:54:43', '2023-12-28 15:24:20', 0, 1, 1);
INSERT INTO `goods` VALUES (57, 11, 'f俄外交飞机平稳发我i就', '2023-12-27 22:54:55', '2023-12-28 16:30:33', 0, 1, 1);
INSERT INTO `goods` VALUES (58, 11, '访问覅欧维去哦披肩发琼配', '2023-12-27 22:55:06', '2023-12-28 15:18:51', 0, 0, 0);
INSERT INTO `goods` VALUES (59, 11, '服务器节目解耦起飞我去舰炮', '2023-12-27 22:55:17', '2023-12-28 15:18:51', 0, 0, 0);
INSERT INTO `goods` VALUES (61, 11, '测试测试', '2023-12-28 00:32:52', '2023-12-28 15:11:57', 0, 0, 0);
INSERT INTO `goods` VALUES (62, 11, '测试测试1', '2023-12-28 00:33:08', '2023-12-28 15:13:01', 0, 0, 0);
INSERT INTO `goods` VALUES (63, 11, '测试测试2', '2023-12-28 00:33:20', '2023-12-28 15:13:01', 0, 0, 0);
INSERT INTO `goods` VALUES (64, 11, '测试测试wre', '2023-12-28 00:34:21', '2023-12-28 15:13:01', 0, 0, 0);
INSERT INTO `goods` VALUES (65, 11, '测试测试e', '2023-12-28 00:34:33', '2023-12-28 15:14:32', 0, 0, 0);
INSERT INTO `goods` VALUES (66, 11, '测试测试v', '2023-12-28 00:34:47', '2023-12-28 15:17:10', 0, 0, 0);
INSERT INTO `goods` VALUES (67, 11, '测试测试eee', '2023-12-28 00:34:58', '2023-12-28 00:36:26', 0, 0, 0);
INSERT INTO `goods` VALUES (68, 11, '测试测试wewwer', '2023-12-28 00:35:11', '2023-12-28 00:36:24', 0, 0, 0);
INSERT INTO `goods` VALUES (69, 11, '测试测试fsdfd', '2023-12-28 00:35:24', '2023-12-28 00:36:23', 0, 0, 0);
INSERT INTO `goods` VALUES (70, 11, '测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:35:35', '2023-12-28 00:36:21', 0, 0, 0);
INSERT INTO `goods` VALUES (71, 11, '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:35:48', '2023-12-28 00:36:20', 0, 0, 0);
INSERT INTO `goods` VALUES (72, 11, '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:35:59', '2023-12-28 00:36:18', 0, 0, 0);
INSERT INTO `goods` VALUES (73, 11, '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:36:11', '2023-12-28 00:36:17', 0, 0, 0);
INSERT INTO `goods` VALUES (74, 11, '测试测试测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:37:16', '2023-12-28 00:39:58', 0, 0, 0);
INSERT INTO `goods` VALUES (75, 11, '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:37:26', '2023-12-28 00:40:00', 0, 0, 0);
INSERT INTO `goods` VALUES (76, 11, 'testetesteteste', '2023-12-28 00:37:42', '2023-12-28 00:40:00', 0, 0, 0);
INSERT INTO `goods` VALUES (77, 11, 'testetestetestetestetestetestetestetesteteste', '2023-12-28 00:37:51', '2023-12-28 00:40:02', 0, 0, 0);
INSERT INTO `goods` VALUES (78, 11, 'testetestetestetestetestetestetestetestetestetestetestetestetestetestetesteteste', '2023-12-28 00:38:00', '2023-12-28 00:39:54', 0, 0, 0);
INSERT INTO `goods` VALUES (79, 11, 'testetestetestetestetestetestetestetestetestetestetestetestetestetestetestetesteteste', '2023-12-28 00:38:09', '2023-12-28 00:39:52', 0, 0, 0);
INSERT INTO `goods` VALUES (80, 11, 'testetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetesteteste', '2023-12-28 00:38:29', '2023-12-28 00:39:51', 0, 0, 0);
INSERT INTO `goods` VALUES (81, 11, 'testtesttesttesttesttesttesttesttesttest', '2023-12-28 00:38:41', '2023-12-28 00:39:50', 0, 0, 0);
INSERT INTO `goods` VALUES (82, 11, 'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestv', '2023-12-28 00:38:51', '2023-12-28 00:39:48', 0, 0, 0);
INSERT INTO `goods` VALUES (83, 11, 'testtesttesttesttesttesttesttesttesttesttesttest', '2023-12-28 00:38:59', '2023-12-28 00:39:47', 0, 0, 0);
INSERT INTO `goods` VALUES (84, 11, 'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest', '2023-12-28 00:39:09', '2023-12-28 00:39:46', 0, 0, 0);
INSERT INTO `goods` VALUES (85, 11, 'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest', '2023-12-28 00:39:18', '2023-12-28 00:39:45', 0, 0, 0);
INSERT INTO `goods` VALUES (86, 11, 'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest', '2023-12-28 00:39:28', '2023-12-28 00:39:42', 0, 0, 0);
INSERT INTO `goods` VALUES (87, 11, 'testtesttesttesttesttesttesttest', '2023-12-28 00:39:38', '2023-12-28 00:39:41', 0, 0, 0);
INSERT INTO `goods` VALUES (88, 11, 'testtesttest', '2023-12-28 00:40:21', '2023-12-28 00:41:01', 0, 0, 0);
INSERT INTO `goods` VALUES (89, 11, 'cececcececcececcececcececcececcececcececcecec', '2023-12-28 00:40:44', '2023-12-28 00:41:00', 0, 0, 0);
INSERT INTO `goods` VALUES (90, 11, 'cececcececcececcececcececcececcececcecec', '2023-12-28 00:40:55', '2023-12-28 02:04:46', 0, 0, 0);
INSERT INTO `goods` VALUES (91, 12, '+16396527855Ｉhttps://www.mysms.one/api/send?token=8df3ee00-7625-11ee-bfce-bf3655981d2a', '2023-12-28 16:47:47', '2023-12-28 20:40:00', 0, 0, 0);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `product_id` int(11) NOT NULL COMMENT '购买产品的id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '购买产品的名字',
  `goods_price` int(11) NOT NULL COMMENT '商品单价',
  `order_total_price` int(11) NOT NULL COMMENT '订单总价',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户邮箱',
  `goods_detail_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '具体商品的id 可多个 用于解库存 : 分割',
  `order_status` int(11) NOT NULL COMMENT '订单状态 0 库存已经锁定 1 已完成 2 已取消',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `goods_num` int(11) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`order_sn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('20658d14bb182a93bcbd44c15b8cd583', 12, 'Telegram美国账号', 15, 15, '749062870@qq.com', '91', 2, '2023-12-28 16:48:26', '2023-12-28 16:54:26', 1);
INSERT INTO `order` VALUES ('a9fc96119f4c11e0e5d07280f6f1279a', 11, '测试产品', 1, 1, '749062870@qq.com', '56', 1, '2023-12-28 15:23:58', '2023-12-28 15:24:20', 1);
INSERT INTO `order` VALUES ('ad23a9fca44c16089a12dcee40ed3250', 12, 'Telegram美国账号', 15, 15, '749062870@qq.com', '91', 2, '2023-12-28 20:34:00', '2023-12-28 20:40:00', 1);
INSERT INTO `order` VALUES ('ddc13be6074f475a96982a175ee2ef63', 11, '测试产品', 1, 1, '749062870@qq.com', '57', 1, '2023-12-28 16:30:12', '2023-12-28 16:30:33', 1);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `product_category_id` int(11) NOT NULL COMMENT '产品分类id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `product_price` int(11) NOT NULL COMMENT '产品价格',
  `product_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品logo',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int(11) NOT NULL COMMENT '产品是否删除 0 未删除 1 已删除',
  `product_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品注意事项 用:隔开',
  `product_warn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品警告 用:隔开',
  `product_stock` int(11) NOT NULL COMMENT '产品库存',
  `product_lock_stock` int(11) NOT NULL COMMENT '产品锁定库存',
  `product_method` int(11) NOT NULL COMMENT '发货方式 0 自动 1 手动',
  `status` int(11) NOT NULL COMMENT '产品是否下架 0 未下架 1 已下架',
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '产品类型 0 有库存 1 有库存',
  `product_detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '无库存产品的具体商品 无库存的商品不用进商品库',
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `category_id`(`product_category_id`) USING BTREE,
  CONSTRAINT `category_id` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`product_category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (11, 33, '测试产品', 1, 'public/2023-12-27/8d0834182d954e2284bbf6d1cdeb7fad.png', '2023-12-27 22:54:18', '2023-12-28 16:30:33', 0, '本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿:本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿:本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿', '本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿:本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿', 32, 0, 0, 0, 0, NULL);
INSERT INTO `product` VALUES (12, 28, 'Telegram美国账号', 15, 'public/2023-12-28/947b70ca24b54b8781c81c524fc65100.jpg', '2023-12-28 16:46:03', '2023-12-28 20:40:00', 0, '如果登录不上或者出问题 直接联系客服', '如果登录不上或者出问题 直接联系客服', 1, 0, 0, 0, 0, NULL);

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品分类id',
  `product_category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品分类名称',
  `product_category_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品分类logo',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int(11) NOT NULL COMMENT '产品分类是否删除 0 未删除 1 已删除',
  `status` int(11) NOT NULL COMMENT '上架状态 0 上架 1 下架',
  PRIMARY KEY (`product_category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (28, 'Telegram账号', 'public/2023-12-26/9415bbb0610c478f8c997c1714110b7b.png', '2023-12-26 14:40:31', '2023-12-28 00:41:41', 0, 0);
INSERT INTO `product_category` VALUES (29, 'Twitter账号', 'public/2023-12-26/e25bf9855f0541578af25be649c442a8.png', '2023-12-26 14:40:56', '2023-12-28 00:41:39', 0, 0);
INSERT INTO `product_category` VALUES (30, 'AppleId', 'public/2023-12-26/a58d2ef193bd4cd3ac9662979ce7868a.png', '2023-12-26 14:41:08', '2023-12-28 01:59:15', 0, 0);
INSERT INTO `product_category` VALUES (31, 'TIktok账号', 'public/2023-12-26/cc64af97181346a5b4bfb9493dbee1fa.png', '2023-12-26 14:41:23', '2023-12-28 00:41:36', 0, 0);
INSERT INTO `product_category` VALUES (33, '测试分类', 'public/2023-12-27/06eda6d2c23542a9b425dd05cb8963ac.png', '2023-12-27 22:52:54', '2023-12-27 22:53:35', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
