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

 Date: 01/01/2024 18:43:02
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
  `goods_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '具体商品的详情 因为是账号所以这里就是账号信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int NOT NULL COMMENT '商品是否删除 0 未删除 1 已删除',
  `status` int NOT NULL COMMENT '下架状态 0 未下架 1 已下架',
  `goods_status` int NOT NULL COMMENT '商品状态 0 可用 1 已售 2 锁定',
  PRIMARY KEY (`goods_id`) USING BTREE,
  INDEX `prods`(`product_id` ASC) USING BTREE,
  CONSTRAINT `prods` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (51, 8, '+1234234234234   https://wejfwiofjwiefjweoi.com/werfjiowejfwejfiowejfowejfwoejfowpeijf', '2023-12-26 14:56:25', '2023-12-29 15:50:21', 0, 1, 1);
INSERT INTO `goods` VALUES (52, 8, '+213213124233432v https:///wfwefwef', '2023-12-26 14:57:50', '2023-12-29 15:50:21', 0, 1, 1);
INSERT INTO `goods` VALUES (53, 8, '为人父个体户脚后跟范德萨', '2023-12-26 16:29:44', '2023-12-28 21:38:04', 0, 0, 0);
INSERT INTO `goods` VALUES (54, 9, '微软给海关法的实施', '2023-12-26 17:06:23', '2023-12-27 11:17:43', 0, 0, 0);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `product_id` int NOT NULL COMMENT '购买产品的id',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '购买产品的名字',
  `goods_price` int NOT NULL COMMENT '商品单价',
  `order_total_price` int NOT NULL COMMENT '订单总价',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户邮箱',
  `goods_detail_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '具体商品的id 可多个 用于解库存 : 分割',
  `order_status` int NOT NULL COMMENT '订单状态 0 库存已经锁定 1 已完成 2 已取消',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `goods_num` int NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`order_sn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('0591906c762d02a3af0ad4c0405c3cba', 12, '无库存产品第二个', 1, 1, '749062870@qq.com', NULL, 2, '2023-12-28 21:33:01', '2023-12-28 21:39:01', 1);
INSERT INTO `order` VALUES ('05ee65900eec52de5548a501ca63a93c', 12, '无库存产品第二个', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-29 15:30:12', '2023-12-29 15:30:42', 1);
INSERT INTO `order` VALUES ('112060ee13a0c4f5e4dab6f68be1b40a', 8, 'Telegram美国成品号', 1, 2, '749062870@qq.com', '51:52', 2, '2023-12-28 21:21:10', '2023-12-28 21:27:10', 2);
INSERT INTO `order` VALUES ('1c8358bf9e0842cac98950f900e4e87e', 11, '无库存产品第一个', 1, 1, '749062870@qq.com', NULL, 2, '2023-12-29 14:18:24', '2023-12-29 14:24:24', 1);
INSERT INTO `order` VALUES ('2dafff99219b2e9aad4b0937beecd96c', 12, '无库存产品第二个', 1, 1, '749062870@qq.com', NULL, 2, '2023-12-28 21:19:35', '2023-12-28 21:25:34', 1);
INSERT INTO `order` VALUES ('3c6739198693c6315021fe6f5ca601af', 11, '无库存产品第一个', 1, 1, '749062870@qq.com', NULL, 2, '2023-12-29 14:01:49', '2023-12-29 14:07:50', 1);
INSERT INTO `order` VALUES ('4223c8d743d298b9cc2f7839f00e4d65', 8, 'Telegram美国成品号', 1, 2, '749062870@qq.com', '52:53', 2, '2023-12-28 21:32:04', '2023-12-28 21:38:04', 2);
INSERT INTO `order` VALUES ('70705ed6c93e2e15b79ef1a344f8bba2', 12, '无库存产品第二个', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-29 17:46:12', '2023-12-29 17:48:25', 1);
INSERT INTO `order` VALUES ('7a65263d8dfded2f24c389e99fd2f51e', 11, '无库存产品第一个', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-29 14:55:16', '2023-12-29 15:01:54', 1);
INSERT INTO `order` VALUES ('85d5185178fff380bf04ee08e61857ec', 11, '无库存产品第一个', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-29 17:43:08', '2023-12-29 17:43:39', 1);
INSERT INTO `order` VALUES ('8ef17a884e002c8556d3164ffd7cbb3c', 12, '无库存产品第二个', 1, 1, '749062870@qq.com', NULL, 2, '2023-12-28 21:33:25', '2023-12-28 21:39:26', 1);
INSERT INTO `order` VALUES ('9b2a6d44bd577c0c1c2559a4c5c5ff26', 11, '无库存产品第一个', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-29 15:14:58', '2023-12-29 15:18:26', 1);
INSERT INTO `order` VALUES ('abeafdf2efd9a21ef2356b992e202ece', 11, '无库存产品第一个', 1, 1, '3603225769@qq.com', NULL, 1, '2023-12-29 15:35:33', '2023-12-29 15:36:09', 1);
INSERT INTO `order` VALUES ('cd45c4efdd211e0586f3e2c25b38675f', 11, '无库存产品第一个', 1, 1, '749062870@qq.com', NULL, 2, '2023-12-29 14:06:11', '2023-12-29 14:12:12', 1);
INSERT INTO `order` VALUES ('d075aceb3a49d9d083b9eb9c38ce5024', 8, 'Telegram美国成品号', 1, 2, '749062870@qq.com', '51:52', 1, '2023-12-29 15:41:07', '2023-12-29 15:52:06', 2);
INSERT INTO `order` VALUES ('d88f520629a5645cf59fcbadec4dc438', 11, '无库存产品第一个', 1, 1, '749062870@qq.com', NULL, 2, '2023-12-29 14:11:48', '2023-12-29 14:17:50', 1);
INSERT INTO `order` VALUES ('faa36d12945a1c3dc3791a4d707f86e4', 8, 'Telegram美国成品号', 1, 1, '749062870@qq.com', '51', 2, '2023-12-28 21:31:30', '2023-12-28 21:37:30', 1);

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
  `type` int NOT NULL DEFAULT 0 COMMENT '产品类型 0 有库存 1 有库存',
  `product_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '无库存产品的具体商品 无库存的商品不用进商品库',
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `category_id`(`product_category_id` ASC) USING BTREE,
  CONSTRAINT `category_id` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`product_category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (8, 28, 'Telegram美国成品号', 1, 'public/2023-12-26/10fee6b2c19e47fe88af573494055f4d.png', '2023-12-26 14:42:40', '2023-12-29 15:50:21', 0, '需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用:需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用', '切勿违规使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用需要自己使用', -1, -2, 0, 0, 0, NULL);
INSERT INTO `product` VALUES (9, 28, 'Telegram协议号', 20, 'public/2023-12-26/0e19b8e8d762423d93c189976d7e3f04.png', '2023-12-26 14:43:31', '2023-12-27 20:16:18', 0, '本店在技术范围内会尽力保障商品的可用性，所有商品如无单独标注，售后期为商品发货24小时内；批发售后期为商品发货5天内:建议只在app store里面登录账号，请勿在【设置】里登录账号', '每天数量有限，找TG客服预定，优先发货！', 1, 0, 1, 0, 0, NULL);
INSERT INTO `product` VALUES (10, 32, '678', 20, 'public/2023-12-28/5ebeac4feeb944b2b93f6bdf5b3a9917.PNG', '2023-12-28 19:59:15', '2023-12-28 20:02:42', 0, '345678', '34567890', 0, 0, 1, 0, 1, '4567890');
INSERT INTO `product` VALUES (11, 33, '无库存产品第一个', 1, 'public/2023-12-28/159bf7c8c06142f697f2f9ab64c29cb1.PNG', '2023-12-28 21:12:40', '2023-12-29 14:01:27', 0, '无库存产品第一个无库存产品第一个', '无库存产品第一个无库存产品第一个', 0, 0, 1, 0, 1, '无库存产品第一个无库存产品第一个');
INSERT INTO `product` VALUES (12, 33, '无库存产品第二个', 1, 'public/2023-12-28/cccb7a01ca364efe8fd609bf4fe2394c.png', '2023-12-28 21:13:25', '2023-12-28 21:13:32', 0, '无库存产品第二个', '无库存产品第二个', 0, 0, 0, 0, 1, '无库存产品第二个无库存产品第二个无库存产品第二个');

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
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (28, 'Telegram账号', 'public/2023-12-26/9415bbb0610c478f8c997c1714110b7b.png', '2023-12-26 14:40:31', '2023-12-27 20:12:04', 0, 0);
INSERT INTO `product_category` VALUES (29, 'Twitter账号', 'public/2023-12-26/e25bf9855f0541578af25be649c442a8.png', '2023-12-26 14:40:56', '2023-12-27 20:15:36', 0, 0);
INSERT INTO `product_category` VALUES (30, 'AppliId', 'public/2023-12-26/a58d2ef193bd4cd3ac9662979ce7868a.png', '2023-12-26 14:41:08', '2023-12-27 20:15:34', 0, 0);
INSERT INTO `product_category` VALUES (31, 'TIktok账号', 'public/2023-12-26/cc64af97181346a5b4bfb9493dbee1fa.png', '2023-12-26 14:41:23', '2023-12-27 20:15:33', 0, 0);
INSERT INTO `product_category` VALUES (32, '科学上网', 'public/2023-12-28/d4013d14142a490295c40f599c3fa63d.png', '2023-12-28 19:56:09', '2023-12-28 19:57:36', 0, 0);
INSERT INTO `product_category` VALUES (33, '无库存分类', 'public/2023-12-28/cbd20b4614d840bb8192fa58e667edea.png', '2023-12-28 21:07:59', '2023-12-28 21:08:13', 0, 0);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '字典id',
  `dict_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典编码',
  `parent_id` int NULL DEFAULT NULL COMMENT '父字典id',
  `dict_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典描述',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (97, 'account_shop_dict', NULL, '账号商城字典', NULL);
INSERT INTO `sys_dict` VALUES (98, 'huaxianren_telegram', 97, '客服tg', 'https://t.me/lee7s_7s');
INSERT INTO `sys_dict` VALUES (99, 'huaxianren_email', 97, '客服邮箱', 'liqisong2002@gmail.com');
INSERT INTO `sys_dict` VALUES (100, 'huaxianren_recommend_channel_url', 97, '推荐频道链接', 'https://t.me/av_share_channel');
INSERT INTO `sys_dict` VALUES (101, 'huaxianren_recommend_channel_name', 97, '推荐频道名字', '闲人AV频道');
INSERT INTO `sys_dict` VALUES (102, 'telegram_copy_dict', NULL, 'telegram群消息复制字典', NULL);
INSERT INTO `sys_dict` VALUES (103, 'order_ids', 102, '可听命的账号id', '5060527090:6833588245');
INSERT INTO `sys_dict` VALUES (104, 'resource_account', 102, '被复制的群或者频道', 'JPYPorn');
INSERT INTO `sys_dict` VALUES (105, 'target_account', 102, '被复制到的目标账号', 'huaxianrenshare');
INSERT INTO `sys_dict` VALUES (106, 'redis_index_key_word', 102, 'redis中存放的消息起始id的key名字', 'min_id');
INSERT INTO `sys_dict` VALUES (107, 'contact', 102, '客服账号', 'https://t.me/shnajkzl');
INSERT INTO `sys_dict` VALUES (108, 'account_shop_url', 102, '商店地址', 'https://shop.somg.xyz');

SET FOREIGN_KEY_CHECKS = 1;
