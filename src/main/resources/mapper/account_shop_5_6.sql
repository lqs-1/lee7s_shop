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

 Date: 01/01/2024 18:42:36
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
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (56, 11, '我就得哦房价我二姐夫', '2023-12-27 22:54:43', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (57, 11, 'f俄外交飞机平稳发我i就', '2023-12-27 22:54:55', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (58, 11, '访问覅欧维去哦披肩发琼配', '2023-12-27 22:55:06', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (59, 11, '服务器节目解耦起飞我去舰炮', '2023-12-27 22:55:17', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (61, 11, '测试测试', '2023-12-28 00:32:52', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (62, 11, '测试测试1', '2023-12-28 00:33:08', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (63, 11, '测试测试2', '2023-12-28 00:33:20', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (64, 11, '测试测试wre', '2023-12-28 00:34:21', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (65, 11, '测试测试e', '2023-12-28 00:34:33', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (66, 11, '测试测试v', '2023-12-28 00:34:47', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (67, 11, '测试测试eee', '2023-12-28 00:34:58', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (68, 11, '测试测试wewwer', '2023-12-28 00:35:11', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (69, 11, '测试测试fsdfd', '2023-12-28 00:35:24', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (70, 11, '测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:35:35', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (71, 11, '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:35:48', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (72, 11, '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:35:59', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (73, 11, '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:36:11', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (74, 11, '测试测试测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:37:16', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (75, 11, '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2023-12-28 00:37:26', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (76, 11, 'testetesteteste', '2023-12-28 00:37:42', '2023-12-28 23:11:31', 0, 1, 1);
INSERT INTO `goods` VALUES (77, 11, 'testetestetestetestetestetestetestetesteteste', '2023-12-28 00:37:51', '2023-12-29 00:16:45', 0, 1, 1);
INSERT INTO `goods` VALUES (78, 11, 'testetestetestetestetestetestetestetestetestetestetestetestetestetestetesteteste', '2023-12-28 00:38:00', '2023-12-29 01:14:38', 0, 1, 1);
INSERT INTO `goods` VALUES (79, 11, 'testetestetestetestetestetestetestetestetestetestetestetestetestetestetestetesteteste', '2023-12-28 00:38:09', '2023-12-29 20:28:31', 0, 1, 1);
INSERT INTO `goods` VALUES (80, 11, 'testetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetestetesteteste', '2023-12-28 00:38:29', '2023-12-29 20:28:31', 0, 1, 1);
INSERT INTO `goods` VALUES (81, 11, 'testtesttesttesttesttesttesttesttesttest', '2023-12-28 00:38:41', '2023-12-29 22:27:26', 0, 1, 1);
INSERT INTO `goods` VALUES (82, 11, 'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestv', '2023-12-28 00:38:51', '2023-12-29 18:08:03', 0, 1, 1);
INSERT INTO `goods` VALUES (83, 11, 'testtesttesttesttesttesttesttesttesttesttesttest', '2023-12-28 00:38:59', '2023-12-29 18:08:03', 0, 1, 1);
INSERT INTO `goods` VALUES (84, 11, 'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest', '2023-12-28 00:39:09', '2023-12-29 18:08:03', 0, 1, 1);
INSERT INTO `goods` VALUES (85, 11, 'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest', '2023-12-28 00:39:18', '2023-12-31 00:08:47', 0, 1, 1);
INSERT INTO `goods` VALUES (86, 11, 'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest', '2023-12-28 00:39:28', '2023-12-31 16:58:07', 0, 1, 1);
INSERT INTO `goods` VALUES (87, 11, 'testtesttesttesttesttesttesttest', '2023-12-28 00:39:38', '2023-12-28 23:12:21', 0, 1, 1);
INSERT INTO `goods` VALUES (88, 11, 'testtesttest', '2023-12-28 00:40:21', '2023-12-28 23:12:21', 0, 1, 1);
INSERT INTO `goods` VALUES (89, 11, 'cececcececcececcececcececcececcececcececcecec', '2023-12-28 00:40:44', '2023-12-28 23:12:21', 0, 1, 1);
INSERT INTO `goods` VALUES (90, 11, 'cececcececcececcececcececcececcececcecec', '2023-12-28 00:40:55', '2023-12-28 23:12:21', 0, 1, 1);
INSERT INTO `goods` VALUES (91, 12, '+12013618665|https://www.mysms.one/api/send?token=5ad2d120-a7dd-11ee-b483-b929cc61b0b1\n输入前面的号码点击获取验证码，然后把后面的超链接复制到浏览器就可以获取到验证码，如果出现问题请联系客服', '2023-12-28 16:47:47', '2024-01-01 08:50:06', 0, 1, 0);
INSERT INTO `goods` VALUES (92, 15, '卡密格式:登录用户名---登录密码---辅助邮箱地址---邮箱密码 如果登录不上就用辅助邮箱\n卡密:fwaewe1202---inithuaxianren---fjewioafewi@proton.me---inithuaxianren', '2023-12-29 19:12:24', '2023-12-29 21:27:31', 0, 0, 0);
INSERT INTO `goods` VALUES (93, 15, '卡密格式:登录用户名---登录密码---辅助邮箱地址---邮箱密码 如果登录不上就用辅助邮箱\n卡密:ewfw5285---inithuaxianren---fwefwefwefwfew@proton.me---inithuaxianren', '2023-12-29 19:18:07', '2023-12-29 21:27:31', 0, 0, 0);
INSERT INTO `goods` VALUES (94, 19, 'kangmi37516153@163.com----Ys123456----Ys123456----330702198607036047', '2023-12-30 02:46:21', '2023-12-31 01:38:29', 0, 1, 0);
INSERT INTO `goods` VALUES (95, 24, ' dansdavis59@gmail.com---inithuaxianren，登录地址 https://mail.google.com', '2023-12-30 04:31:48', '2023-12-30 11:34:46', 0, 0, 0);
INSERT INTO `goods` VALUES (96, 26, '+12013618665|https://www.mysms.one/api/send?token=5ad2d120-a7dd-11ee-b483-b929cc61b0b1\n输入前面的号码点击获取验证码，然后把后面的超链接复制到浏览器就可以获取到验证码，如果出现问题请联系客服', '2024-01-01 08:50:20', '2024-01-01 08:50:42', 0, 0, 0);

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
INSERT INTO `order` VALUES ('060df612549fa902e83d043dac809f3b', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2023-12-31 16:47:50', '2023-12-31 16:48:16', 1);
INSERT INTO `order` VALUES ('1815b9b9f574d234a5ff7c7bd1aa3069', 11, '测试产品', 1, 1, '749062870@qq.com', '86', 2, '2023-12-31 16:42:03', '2023-12-31 16:49:39', 1);
INSERT INTO `order` VALUES ('19cd8178e0d3f852008961c229c89ee7', 15, 'Twitter全新号', 10, 20, '749062870@qq.com', '92:93', 2, '2023-12-29 21:21:31', '2023-12-29 21:27:31', 2);
INSERT INTO `order` VALUES ('213225da0f1a5f48629f6d9ac989fb37', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2023-12-31 20:07:30', '2023-12-31 20:17:30', 1);
INSERT INTO `order` VALUES ('260cf83a877222d3721f4eaf774cb4d4', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2023-12-31 16:44:55', '2023-12-31 16:45:17', 1);
INSERT INTO `order` VALUES ('279b87daddf4d53d955455c641b70cd3', 13, '无限1', 1, 1, '749062870@qq.com', NULL, 2, '2023-12-28 23:13:03', '2023-12-28 23:19:03', 1);
INSERT INTO `order` VALUES ('29d1f251d6242302a92468263c3a31fe', 11, '测试产品', 1, 2, '749062870@qq.com', '79:80', 1, '2023-12-29 20:28:17', '2023-12-29 20:28:32', 2);
INSERT INTO `order` VALUES ('2d5c7efbd1739e8aba15b57c4a40a82f', 14, '无限2', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-29 18:05:15', '2023-12-29 18:05:42', 1);
INSERT INTO `order` VALUES ('306d8a917320f21e4e81d77712c02fde', 12, 'Telegram美国号', 10, 10, 'ghostdemog@gmail.com', '91', 2, '2023-12-31 22:19:20', '2023-12-31 22:29:21', 1);
INSERT INTO `order` VALUES ('411619d8769440b12bd34afa78876233', 24, '全新谷歌邮箱', 5, 5, '749062870@qq.com', '95', 2, '2023-12-30 11:28:46', '2023-12-30 11:34:46', 1);
INSERT INTO `order` VALUES ('4289657e6df8ea90b1c0178caec354ce', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2023-12-31 17:42:56', '2023-12-31 17:52:56', 1);
INSERT INTO `order` VALUES ('42fbcd8792fed0c17996e18122e09927', 17, '200部短文爽剧', 4, 4, '3379582030@qq.com', NULL, 1, '2023-12-29 20:51:13', '2023-12-29 20:52:14', 1);
INSERT INTO `order` VALUES ('47bc48accfd2bcfec8cb9f1c5f09e7c3', 12, 'Telegram美国账号', 15, 15, '749062870@qq.com', '91', 2, '2023-12-28 23:30:29', '2023-12-28 23:36:29', 1);
INSERT INTO `order` VALUES ('4916179c26ab4cb75a55b3ce06ddbb12', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2023-12-31 19:44:51', '2023-12-31 19:54:51', 1);
INSERT INTO `order` VALUES ('4c617f5f48d501fa42c7bcde66eadc29', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2023-12-30 14:40:54', '2023-12-30 14:43:36', 1);
INSERT INTO `order` VALUES ('4d7b9b2b40dd53bccde4538581f05ef6', 11, '测试产品', 1, 7, '749062870@qq.com', '79:80:81:82:83:84:85', 2, '2023-12-29 11:47:47', '2023-12-29 11:53:48', 7);
INSERT INTO `order` VALUES ('575321b8bac97169386098dc1a461363', 21, '188G高速流量', 30, 30, '749062870@qq.com', NULL, 2, '2023-12-31 19:46:48', '2023-12-31 19:56:48', 1);
INSERT INTO `order` VALUES ('5845640f01a35128bc3d6112f80b2dd6', 11, '测试产品', 1, 1, '4455', '85', 2, '2023-12-29 18:11:58', '2023-12-29 18:17:59', 1);
INSERT INTO `order` VALUES ('615c3cdc243e010b1fc5a817819e87a7', 11, '测试产品', 1, 1, '3228949325@qq.com', '78', 1, '2023-12-29 01:14:06', '2023-12-29 01:14:39', 1);
INSERT INTO `order` VALUES ('62c5de8e35ae0d06b355424cc8f50f95', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2023-12-31 18:19:37', '2023-12-31 18:29:37', 1);
INSERT INTO `order` VALUES ('64db510cf8b03b6bfb9f9244f972e327', 11, '测试产品', 1, 1, '749062870@qq.com', '86', 1, '2023-12-31 16:57:43', '2023-12-31 16:58:09', 1);
INSERT INTO `order` VALUES ('66676fb9090365af8ed3ca8b88a08581', 11, '测试产品', 1, 1, '3228949325@ qq.com', '77', 1, '2023-12-29 00:16:08', '2023-12-29 00:16:45', 1);
INSERT INTO `order` VALUES ('67dd97e39f1fb4c0e0332f177a571d83', 12, 'Telegram美国号', 10, 10, '749062870@qq.com', '91', 2, '2024-01-01 08:21:08', '2024-01-01 08:31:09', 1);
INSERT INTO `order` VALUES ('6a0163eb0b92cf3027d629ac917493b2', 11, '测试产品', 1, 3, '749062870@qq.com', '82:83:84', 1, '2023-12-29 18:07:46', '2023-12-29 18:08:04', 3);
INSERT INTO `order` VALUES ('6bbf589b11ba9d6a6057ec313cadffd0', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2023-12-31 16:42:41', '2023-12-31 16:43:07', 1);
INSERT INTO `order` VALUES ('6df2366010ef3147659a4e30aa4e092e', 11, '测试产品', 1, 20, '749062870@qq.com', '56:57:58:59:61:62:63:64:65:66:67:68:69:70:71:72:73:74:75:76', 1, '2023-12-28 23:11:13', '2023-12-28 23:11:32', 20);
INSERT INTO `order` VALUES ('884d889768b52a535ab00084117ed04c', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2023-12-31 17:18:59', '2023-12-31 17:28:59', 1);
INSERT INTO `order` VALUES ('8a630f77f1fce490a0e89e59e53b9df6', 11, '测试产品', 1, 1, '749062870@qq.com', '85', 1, '2023-12-31 00:08:10', '2023-12-31 00:08:48', 1);
INSERT INTO `order` VALUES ('8ca2dba5f9f3af208130e0eb8a251365', 13, '无限1', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-29 14:31:47', '2023-12-29 14:32:05', 1);
INSERT INTO `order` VALUES ('906cd6a75a7b361840c2690e579251d9', 11, '测试产品', 1, 1, '749062870@qq.com', '85', 2, '2023-12-29 22:55:21', '2023-12-29 23:01:21', 1);
INSERT INTO `order` VALUES ('907b7db91ed240906ff80a89560fd00b', 16, '300部短文爽剧合集', 5, 5, '302576360', NULL, 2, '2023-12-29 21:42:42', '2023-12-29 21:48:42', 1);
INSERT INTO `order` VALUES ('9219aff0a08a7bc2e4860ac8c5c78e76', 13, '无限1', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-28 23:13:22', '2023-12-28 23:13:36', 1);
INSERT INTO `order` VALUES ('9606e015c478ae293b324ddf6bd3cd13', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2023-12-31 20:06:39', '2023-12-31 20:16:39', 1);
INSERT INTO `order` VALUES ('af1695c40d2455b9ff45a21356d2bd1f', 13, '无限1', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-28 23:12:34', '2023-12-28 23:12:51', 1);
INSERT INTO `order` VALUES ('bbcd1930d7dfc4cd46ff8d96ca13a748', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2023-12-31 17:18:01', '2023-12-31 17:18:21', 1);
INSERT INTO `order` VALUES ('c7675ca78318117d7cd4f8c309ef82aa', 21, '188G高速流量', 30, 30, '749062870@qq.com', NULL, 2, '2023-12-31 19:45:12', '2023-12-31 19:55:12', 1);
INSERT INTO `order` VALUES ('c89bf71d3ada2dac0e8f07d57ea69e0a', 11, '测试产品', 1, 1, 'ghostdemog@gmail.com', '81', 1, '2023-12-29 22:26:47', '2023-12-29 22:27:27', 1);
INSERT INTO `order` VALUES ('d4501b087c7b8feeb4fcdf6dbdb4aa1d', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2024-01-01 08:24:58', '2024-01-01 08:34:58', 1);
INSERT INTO `order` VALUES ('da07954ddd7c87b85585c10aa1014f13', 16, '300部短文爽剧合集', 5, 5, '34575376@qq.com', NULL, 2, '2023-12-29 22:40:28', '2023-12-29 22:46:29', 1);
INSERT INTO `order` VALUES ('e2eb1ff2af5a62b1ff04d631bdf21e5d', 11, '测试产品', 1, 1, '2606816118@qq.com', '81', 2, '2023-12-29 21:31:50', '2023-12-29 21:37:50', 1);
INSERT INTO `order` VALUES ('e92e2fc4114508a236d0a2e188a78936', 11, '测试产品', 1, 4, '749062870@qq.com', '87:88:89:90', 1, '2023-12-28 23:12:10', '2023-12-28 23:12:22', 4);
INSERT INTO `order` VALUES ('ec2f9d13d2eddea5e15817d64da0832f', 21, '188G高速流量', 30, 30, '749062870@qq.com', NULL, 2, '2023-12-31 19:46:02', '2023-12-31 19:56:02', 1);
INSERT INTO `order` VALUES ('efa1e1ef0ed3109f2d9911e62aa199dc', 17, '200部短文爽剧', 4, 4, '749062870@qq.com', NULL, 2, '2023-12-31 20:00:28', '2023-12-31 20:10:29', 1);
INSERT INTO `order` VALUES ('f2040fa1a07da5b50b806c943188e9b5', 25, 'Android免费VPN', 20, 20, '749062870@qq.com', NULL, 2, '2023-12-30 18:03:27', '2023-12-30 18:09:28', 1);
INSERT INTO `order` VALUES ('f4e2c1dce427d1f6d7a1c78848a598a0', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2023-12-31 16:46:14', '2023-12-31 16:46:40', 1);
INSERT INTO `order` VALUES ('f580c6eb65f992e192c1c92548e7f8b0', 18, 'Tiktok破解版', 2, 2, '777', NULL, 2, '2023-12-30 01:18:18', '2023-12-30 01:24:19', 1);
INSERT INTO `order` VALUES ('f62fb51164a08c53f6735bd26ed0d1aa', 13, '无限1', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-29 18:04:04', '2023-12-29 18:04:35', 1);
INSERT INTO `order` VALUES ('f9eaf0992bc2e202103c0ba2129b5929', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2023-12-31 19:49:25', '2023-12-31 19:59:26', 1);
INSERT INTO `order` VALUES ('fa403f3310fb2a10d623577bda372e36', 11, '测试产品', 1, 3, '749062870@qq.com', '79:80:81', 2, '2023-12-29 18:07:19', '2023-12-29 18:13:19', 3);
INSERT INTO `order` VALUES ('fd805bd54292d74ff032990543e2929c', 11, '测试产品', 1, 10, '749062870@qq.com', '77:78:79:80:81:82:83:84:85:86', 2, '2023-12-28 23:11:47', '2023-12-28 23:17:47', 10);

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
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (11, 33, '测试产品', 1, 'public/2023-12-27/8d0834182d954e2284bbf6d1cdeb7fad.png', '2023-12-27 22:54:18', '2023-12-31 16:58:07', 0, '本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿:本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿:本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿', '本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿:本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿', 0, 0, 0, 0, 0, '无库存');
INSERT INTO `product` VALUES (12, 28, 'Telegram美国号', 10, 'public/2023-12-28/e290a36ae1b34bdb89b0fd4467f22593.png', '2023-12-28 16:46:03', '2024-01-01 08:51:12', 0, '如果登录不上或者出问题 直接联系客服 具体登录会发到你邮箱:网站量少 可以私信客服订购批量', '如果登录不上或者出问题 直接联系客服:网站量少 有需要可以私信客服批量订购', 0, 0, 0, 1, 0, NULL);
INSERT INTO `product` VALUES (13, 35, '无限1', 1, 'public/2023-12-28/2207a6af03a04635be7578cadc8c6da4.png', '2023-12-28 21:56:37', '2023-12-28 21:56:48', 0, '无限1无限1', '无限1无限1', 0, 0, 0, 0, 1, '无限1无限1无限1无限1无限1');
INSERT INTO `product` VALUES (14, 35, '无限2', 1, 'public/2023-12-28/31b8a53a95e04c61bdcff24b43a1191e.png', '2023-12-28 21:57:27', '2023-12-29 18:03:34', 0, '无限2无限2', '无限2无限2', 0, 0, 1, 0, 1, '无限2无限2无限2无限2无限24');
INSERT INTO `product` VALUES (15, 29, 'Twitter全新号', 10, 'public/2023-12-29/7f21e38cd29c49e98fb35b64ac8de429.png', '2023-12-29 19:03:10', '2023-12-29 21:27:31', 0, '卡密格式:登录用户名---登录密码---辅助邮箱地址---邮箱密码', '购买本商品 不用绑定手机号码 更加安全', 2, 0, 0, 0, 0, NULL);
INSERT INTO `product` VALUES (16, 37, '300部短文爽剧合集', 5, 'public/2023-12-29/b98de68807e24b58b1d60fdb7f8370cc.png', '2023-12-29 19:52:52', '2023-12-29 19:55:15', 0, '此产品为夸克网盘资源 购买后会发送链接 可以在线观看', '此产品为夸克网盘资源 购买后会发送链接 可以在线观看', 0, 0, 0, 0, 1, '夸克网盘分享了链接：https://pan.quark.cn/s/0bc3f70fc50f');
INSERT INTO `product` VALUES (17, 37, '200部短文爽剧', 4, 'public/2023-12-29/51184ae92cea4750990187d7d215c46c.png', '2023-12-29 20:00:21', '2023-12-29 20:00:42', 0, '此产品为夸克网盘资源 购买后会发送链接 可以在线观看', '此产品为夸克网盘资源 购买后会发送链接 可以在线观看', 0, 0, 0, 0, 1, '夸克网盘分享链接：https://pan.quark.cn/s/300824404e55');
INSERT INTO `product` VALUES (18, 40, 'Tiktok破解版', 2, 'public/2023-12-29/140346948fc64a8f88c1fefee8563012.jpg', '2023-12-29 22:21:09', '2023-12-29 22:21:46', 0, '本产品为夸克网盘资源 下载你想要的版本安装即可', '本产品为夸克网盘资源 下载你想要的版本安装即可', 0, 0, 0, 0, 1, '夸克网盘分享链接: https://pan.quark.cn/s/dd2eceeac541');
INSERT INTO `product` VALUES (19, 36, '超值双改号', 120, 'public/2023-12-29/f12f43e3919447ebae503691c9cb036a.png', '2023-12-30 02:41:49', '2023-12-31 01:38:52', 0, '卡密格式: 账号----密码----邮箱密码', '卡密格式: 账号----密码----邮箱密码', 0, 0, 0, 1, 0, '');
INSERT INTO `product` VALUES (20, 41, '100G高速流量', 20, 'public/2023-12-29/f66bdbbc92394abfae6b66cdabe4b52a.jpeg', '2023-12-30 03:19:42', '2023-12-30 03:19:45', 0, '20块钱100G高速流量 看什么都快 做什么都顺 不担心流量不够', '此产品为手动发货 下单后 客服会将产品发到您填写的邮箱地址', 0, 0, 1, 0, 1, NULL);
INSERT INTO `product` VALUES (21, 41, '188G高速流量', 30, 'public/2023-12-29/de6d321e7c054e53b1ef282b6498d6ba.jpeg', '2023-12-30 03:21:25', '2023-12-30 03:21:28', 0, '30块钱188G高速流量 看什么都快 做什么都顺 不担心流量不够', '此产品为手动发货 下单后 客服会将产品发到您填写的邮箱地址', 0, 0, 1, 0, 1, NULL);
INSERT INTO `product` VALUES (22, 40, 'Android翻墙软件', 20, 'public/2023-12-29/31b4529c1f1a48cc84e93b6f5d6831de.jpeg', '2023-12-30 03:32:01', '2023-12-30 03:33:02', 0, '此产品为安卓翻墙工具 购买产品后会发送下载链接到您邮箱 您只需要下载安装即可:软件使用教程在 梯子-科学上网-翻墙 分类下', '此产品切勿传播', 0, 0, 0, 0, 1, '下载地址: https://pub-pce.oss-cn-chengdu.aliyuncs.com/public/tools/ClashForAndroid.apk');
INSERT INTO `product` VALUES (23, 40, 'Windows电脑翻墙软件', 10, 'public/2023-12-29/0f0b2d280a044f38b953d2edf710db53.jpeg', '2023-12-30 03:35:52', '2023-12-30 03:35:55', 0, '此产品为windows电脑翻墙工具 购买产品后会发送下载链接到您邮箱 您只需要下载解压即可:软件使用教程在 梯子-科学上网-翻墙 分类下', '此产品切勿传播', 0, 0, 0, 0, 1, '下载链接: https://pub-pce.oss-cn-chengdu.aliyuncs.com/public/tools/ClashForWindows.7z');
INSERT INTO `product` VALUES (24, 42, '全新谷歌邮箱', 5, 'public/2023-12-29/8a45cf4a078446d683fa71d4be21ecd7.png', '2023-12-30 04:31:02', '2023-12-30 11:34:46', 0, '卡密格式: 谷歌邮箱---邮箱密码', '自己使用 不要交给别人', 1, 0, 0, 0, 0, NULL);
INSERT INTO `product` VALUES (25, 40, 'Android免费VPN', 20, 'public/2023-12-30/b60460f148fa41fca320717d049b09cc.png', '2023-12-30 18:02:28', '2023-12-30 18:02:31', 0, '此商品为永久免费vpn软件 购买后会邮件给你下载地址', '此商品为永久免费vpn软件 购买后会邮件给你下载地址', 0, 0, 0, 0, 1, '免费VPN软件下载链接: \nhttps://pub-pce.oss-cn-chengdu.aliyuncs.com/public/application/V2VPN%20-%20A%20Fast%20VPN%20Proxy_5.4.17_Apkpure.apk');
INSERT INTO `product` VALUES (26, 28, 'Telegram特价号', 2, 'public/2023-12-31/b9b88a23456b46cab4453daf81f14cba.jpg', '2023-12-31 22:23:46', '2024-01-01 08:50:42', 0, '此产品为特价号 效果和普通号一模一样 新店做活动', '此产品为特价号 效果和普通号一模一样 新店做活动', 1, 0, 0, 0, 0, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (28, 'Telegram账号', 'public/2023-12-26/9415bbb0610c478f8c997c1714110b7b.png', '2023-12-26 14:40:31', '2023-12-31 21:06:49', 0, 0);
INSERT INTO `product_category` VALUES (29, 'Twitter账号', 'public/2023-12-26/e25bf9855f0541578af25be649c442a8.png', '2023-12-26 14:40:56', '2023-12-29 18:53:17', 0, 0);
INSERT INTO `product_category` VALUES (30, 'AppleId', 'public/2023-12-26/a58d2ef193bd4cd3ac9662979ce7868a.png', '2023-12-26 14:41:08', '2023-12-30 04:42:30', 0, 1);
INSERT INTO `product_category` VALUES (31, 'TIktok账号', 'public/2023-12-29/6281b809e78142e5a574caac34ab84e3.jpg', '2023-12-26 14:41:23', '2023-12-30 04:42:22', 0, 1);
INSERT INTO `product_category` VALUES (33, '测试分类', 'public/2023-12-29/6edb9d96aecd4884928fa052533993c2.jpg', '2023-12-27 22:52:54', '2023-12-31 19:39:19', 0, 1);
INSERT INTO `product_category` VALUES (35, '无限库存商品测试分类', 'public/2023-12-28/08f51d8b30414b918db60e67c94551e6.png', '2023-12-28 21:55:44', '2023-12-29 21:19:25', 0, 1);
INSERT INTO `product_category` VALUES (36, '原神', 'public/2023-12-28/ab5ce4430461430d8969a31ef301cb65.png', '2023-12-29 01:44:25', '2023-12-31 01:39:00', 0, 1);
INSERT INTO `product_category` VALUES (37, '抖音短文爽剧', 'public/2023-12-29/081c393f10de41f2810f63b2216efe18.png', '2023-12-29 19:50:22', '2023-12-29 20:10:03', 0, 0);
INSERT INTO `product_category` VALUES (38, 'Instagram账号', 'public/2023-12-29/b95af7dc18fa42cbad260c40869a5e3a.png', '2023-12-29 20:13:46', '2023-12-30 04:42:10', 0, 1);
INSERT INTO `product_category` VALUES (39, '梯子-科学上网-翻墙', 'public/2023-12-29/431494ed07f542f8925be1dfaf13f311.jpg', '2023-12-29 20:22:53', '2023-12-30 21:17:26', 0, 1);
INSERT INTO `product_category` VALUES (40, '软件类', 'public/2023-12-29/12824535d47649cdab5e4b37981df1f7.png', '2023-12-29 20:34:49', '2023-12-29 20:34:59', 0, 0);
INSERT INTO `product_category` VALUES (41, '流量套餐', 'public/2023-12-29/9ad693b4fee64fcf8d5a74683b11539e.jpeg', '2023-12-30 03:15:49', '2023-12-30 03:15:54', 0, 0);
INSERT INTO `product_category` VALUES (42, '谷歌邮箱账号', 'public/2023-12-29/6fb3fa59408d451bad16ca3d9a63fa24.png', '2023-12-30 04:26:44', '2023-12-30 04:26:47', 0, 0);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典id',
  `dict_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父字典id',
  `dict_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典描述',
  `dict_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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
