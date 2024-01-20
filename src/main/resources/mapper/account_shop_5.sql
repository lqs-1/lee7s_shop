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

 Date: 20/01/2024 21:31:16
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
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `goods` VALUES (92, 15, '卡密格式:登录用户名---登录密码---辅助邮箱地址---邮箱密码 如果登录不上就用辅助邮箱\n卡密:fwaewe1202---inithuaxianren---fjewioafewi@proton.me---inithuaxianren', '2023-12-29 19:12:24', '2024-01-16 09:53:21', 0, 1, 1);
INSERT INTO `goods` VALUES (93, 15, '卡密格式:登录用户名---登录密码---辅助邮箱地址---邮箱密码 如果登录不上就用辅助邮箱 邮箱登录地址 https://proton.me/\n卡密:ewfw5285---inithuaxianren---fwefwefwefwfew@proton.me---inithuaxianren', '2023-12-29 19:18:07', '2024-01-18 19:58:04', 0, 0, 0);
INSERT INTO `goods` VALUES (94, 19, 'kangmi37516153@163.com----Ys123456----Ys123456----330702198607036047', '2023-12-30 02:46:21', '2023-12-31 01:38:29', 0, 1, 0);
INSERT INTO `goods` VALUES (95, 24, ' dansdavis59@gmail.com---inithuaxianren，登录地址 https://mail.google.com', '2023-12-30 04:31:48', '2023-12-30 11:34:46', 0, 0, 0);
INSERT INTO `goods` VALUES (96, 26, '+12013618665|https://www.mysms.one/api/send?token=5ad2d120-a7dd-11ee-b483-b929cc61b0b1\n输入前面的号码点击获取验证码，然后把后面的超链接复制到浏览器就可以获取到验证码，如果出现问题请联系客服', '2024-01-01 08:50:20', '2024-01-02 14:58:14', 1, 1, 0);
INSERT INTO `goods` VALUES (97, 27, 'jjjjjiiiiillllll9----inithuaxianren----qiso.mg@gmail.com', '2024-01-03 23:40:00', '2024-01-03 23:47:27', 0, 0, 0);
INSERT INTO `goods` VALUES (98, 26, '+13802613912|https://www.mysms.one/api/send?token=e5ee5420-ae2b-11ee-982a-e77175dcf160', '2024-01-08 21:45:05', '2024-01-08 21:53:47', 0, 1, 0);
INSERT INTO `goods` VALUES (99, 26, '+14199552419|https://www.mysms.one/api/send?token=7ed6ee60-b36e-11ee-8773-8bca4e5ba234', '2024-01-15 14:23:38', '2024-01-15 19:24:11', 0, 1, 0);
INSERT INTO `goods` VALUES (100, 26, '+17162123958|https://www.mysms.one/api/send?token=7ed6f570-b36e-11ee-9b1f-83e38f7f18fb', '2024-01-15 14:24:21', '2024-01-15 19:24:12', 0, 1, 0);
INSERT INTO `goods` VALUES (101, 26, '+12232981920|https://www.mysms.one/api/send?token=7ed6fa50-b36e-11ee-9d95-5d4375ef538d', '2024-01-15 14:24:47', '2024-01-15 19:24:13', 0, 1, 0);
INSERT INTO `goods` VALUES (102, 26, '+14199552472|https://www.mysms.one/api/send?token=7ed6ff00-b36e-11ee-9012-8faebaf2b6ff', '2024-01-15 14:25:14', '2024-01-15 19:24:14', 0, 1, 0);
INSERT INTO `goods` VALUES (103, 26, '+12232982916|https://www.mysms.one/api/send?token=7ed703b0-b36e-11ee-8b37-ab140bdc19e5', '2024-01-15 14:25:43', '2024-01-20 21:24:02', 0, 1, 2);
INSERT INTO `goods` VALUES (104, 26, '+16672877322|https://www.mysms.one/api/send?token=7ed70860-b36e-11ee-8342-3926fe53cd38', '2024-01-15 14:26:05', '2024-01-18 22:30:59', 0, 0, 0);
INSERT INTO `goods` VALUES (105, 26, '+16672877389|https://www.mysms.one/api/send?token=7ed70d00-b36e-11ee-8f78-57b4586d8ce9', '2024-01-15 14:26:31', '2024-01-15 14:30:48', 0, 0, 0);
INSERT INTO `goods` VALUES (106, 26, '+19085399435|https://www.mysms.one/api/send?token=7ed711b0-b36e-11ee-be4c-cf32d10e3ef5', '2024-01-15 14:26:58', '2024-01-15 14:30:40', 0, 0, 0);
INSERT INTO `goods` VALUES (107, 26, '+18149983957|https://www.mysms.one/api/send?token=7ed716c0-b36e-11ee-af0f-97d4d7e864a0', '2024-01-15 14:27:56', '2024-01-15 14:30:39', 0, 0, 0);
INSERT INTO `goods` VALUES (108, 26, '+18149983690|https://www.mysms.one/api/send?token=7ed71b80-b36e-11ee-97e6-89a076213f8d', '2024-01-15 14:28:13', '2024-01-15 14:30:38', 0, 0, 0);
INSERT INTO `goods` VALUES (109, 26, '+18149983776|https://www.mysms.one/api/send?token=7ed72020-b36e-11ee-a019-cb597a3ab343', '2024-01-15 14:28:33', '2024-01-15 14:30:37', 0, 0, 0);
INSERT INTO `goods` VALUES (110, 26, '+18149983729|https://www.mysms.one/api/send?token=7ed724c0-b36e-11ee-adab-9fb259f4d363', '2024-01-15 14:28:54', '2024-01-15 14:30:36', 0, 0, 0);
INSERT INTO `goods` VALUES (111, 26, '+16672877053|https://www.mysms.one/api/send?token=7ed72960-b36e-11ee-934d-73bc3a123c55', '2024-01-15 14:29:12', '2024-01-15 14:30:34', 0, 0, 0);
INSERT INTO `goods` VALUES (112, 26, '+13204574472|https://www.mysms.one/api/send?token=7ed72e10-b36e-11ee-ad9c-3da3249b9dff', '2024-01-15 14:29:31', '2024-01-18 23:19:06', 0, 1, 0);
INSERT INTO `goods` VALUES (113, 26, '+15303713999|https://www.mysms.one/api/send?token=7ed73360-b36e-11ee-af95-89adcc983b35', '2024-01-15 14:29:57', '2024-01-18 23:06:31', 0, 1, 0);
INSERT INTO `goods` VALUES (114, 26, '+14199551900|https://www.mysms.one/api/send?token=7ed73820-b36e-11ee-85df-390044c52333', '2024-01-15 14:30:12', '2024-01-18 23:09:07', 0, 1, 0);
INSERT INTO `goods` VALUES (115, 26, '+18149983582|https://www.mysms.one/api/send?token=7ed73d30-b36e-11ee-8bd4-6b7891fbb512', '2024-01-15 14:30:28', '2024-01-16 08:14:27', 0, 1, 0);
INSERT INTO `goods` VALUES (116, 15, '卡密格式:登录用户名---登录密码---辅助邮箱地址---邮箱密码 如果登录不上就用辅助邮箱 邮箱登录地址 https://mail.google.com/\n卡密:moseng1030773---inithuaxianren---dans.davis.5.9@gmail.com---inithuaxianren', '2024-01-16 11:49:58', '2024-01-16 11:50:02', 0, 0, 0);
INSERT INTO `goods` VALUES (117, 15, '卡密格式:登录用户名---登录密码---辅助邮箱地址---邮箱密码 如果登录不上就用辅助邮箱 邮箱登录地址 https://mail.google.com/\n卡密:gdpyier---inithuaxianren---dans.davis.59@gmail.com---inithuaxianren', '2024-01-16 11:50:27', '2024-01-16 12:34:13', 0, 0, 0);

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
INSERT INTO `order` VALUES ('09ee95c56131889f9db46c1ddd3d23d4', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2024-01-08 16:18:13', '2024-01-08 16:28:14', 1);
INSERT INTO `order` VALUES ('0b4da30a5dfe12f116de32407fbcb5d6', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-12 12:58:23', '2024-01-12 13:08:23', 1);
INSERT INTO `order` VALUES ('0f400540f9be7456b5b5a8c7dea0b074', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2024-01-02 16:37:35', '2024-01-02 16:47:36', 1);
INSERT INTO `order` VALUES ('14a6b1e6cf7cd123136b98bf382c9ab9', 20, '100G高速流量', 20, 20, 'yty', NULL, 2, '2024-01-08 22:47:41', '2024-01-08 22:57:41', 1);
INSERT INTO `order` VALUES ('157f645b5b124f1d93acd2ad0590f5d6', 26, '美国号', 5, 5, '749062870@qq.com', '103', 2, '2024-01-17 23:51:55', '2024-01-18 00:01:55', 1);
INSERT INTO `order` VALUES ('1815b9b9f574d234a5ff7c7bd1aa3069', 11, '测试产品', 1, 1, '749062870@qq.com', '86', 2, '2023-12-31 16:42:03', '2023-12-31 16:49:39', 1);
INSERT INTO `order` VALUES ('19cd8178e0d3f852008961c229c89ee7', 15, 'Twitter全新号', 10, 20, '749062870@qq.com', '92:93', 2, '2023-12-29 21:21:31', '2023-12-29 21:27:31', 2);
INSERT INTO `order` VALUES ('1b7c3fc68c6e774716dbc4a0d1d5a123', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2024-01-10 12:28:43', '2024-01-10 12:38:44', 1);
INSERT INTO `order` VALUES ('213225da0f1a5f48629f6d9ac989fb37', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2023-12-31 20:07:30', '2023-12-31 20:17:30', 1);
INSERT INTO `order` VALUES ('25057ae955e10d9191bffa12358fa517', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-08 14:24:19', '2024-01-08 14:34:19', 1);
INSERT INTO `order` VALUES ('260cf83a877222d3721f4eaf774cb4d4', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2023-12-31 16:44:55', '2023-12-31 16:45:17', 1);
INSERT INTO `order` VALUES ('26e3176055433c99688b9d0236c906aa', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2024-01-12 12:56:58', '2024-01-12 13:06:58', 1);
INSERT INTO `order` VALUES ('2708691c00b4386c7d8e39e68cfe49e5', 26, '美国号', 5, 5, '749062870@qq.com', '103', 0, '2024-01-20 21:24:02', '2024-01-20 21:24:02', 1);
INSERT INTO `order` VALUES ('279b87daddf4d53d955455c641b70cd3', 13, '无限1', 1, 1, '749062870@qq.com', NULL, 2, '2023-12-28 23:13:03', '2023-12-28 23:19:03', 1);
INSERT INTO `order` VALUES ('29d1f251d6242302a92468263c3a31fe', 11, '测试产品', 1, 2, '749062870@qq.com', '79:80', 1, '2023-12-29 20:28:17', '2023-12-29 20:28:32', 2);
INSERT INTO `order` VALUES ('2c2f3fda50ed6caa1a07fb8392dcdd1b', 31, '高质量网站–需翻墙', 5, 5, '2909761388@qq.com', NULL, 2, '2024-01-20 19:56:17', '2024-01-20 20:06:17', 1);
INSERT INTO `order` VALUES ('2cbbedce36815b43059afda5ebd87ca6', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-01 19:58:45', '2024-01-01 20:08:45', 1);
INSERT INTO `order` VALUES ('2d5c7efbd1739e8aba15b57c4a40a82f', 14, '无限2', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-29 18:05:15', '2023-12-29 18:05:42', 1);
INSERT INTO `order` VALUES ('2ef51a639cd06bdb78aacba229e2e2b7', 31, '高质量网站–需翻墙', 5, 5, '2909761388@qq.com', NULL, 2, '2024-01-20 19:56:50', '2024-01-20 20:06:51', 1);
INSERT INTO `order` VALUES ('2f6931746b020027d96a337c5e119f9a', 18, 'Tiktok破解版', 2, 2, '749862870@qq.com', NULL, 2, '2024-01-03 14:31:56', '2024-01-03 14:41:56', 1);
INSERT INTO `order` VALUES ('306d8a917320f21e4e81d77712c02fde', 12, 'Telegram美国号', 10, 10, 'ghostdemog@gmail.com', '91', 2, '2023-12-31 22:19:20', '2023-12-31 22:29:21', 1);
INSERT INTO `order` VALUES ('3085da600ef2d9395b187e495942c4b4', 25, 'Android免费VPN', 10, 10, '3818378591@qq.com', NULL, 1, '2024-01-17 20:23:04', '2024-01-17 20:23:48', 1);
INSERT INTO `order` VALUES ('363a3e507ae792f7195d312d35bddfdc', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-05 00:23:08', '2024-01-05 00:33:08', 1);
INSERT INTO `order` VALUES ('411619d8769440b12bd34afa78876233', 24, '全新谷歌邮箱', 5, 5, '749062870@qq.com', '95', 2, '2023-12-30 11:28:46', '2023-12-30 11:34:46', 1);
INSERT INTO `order` VALUES ('4289657e6df8ea90b1c0178caec354ce', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2023-12-31 17:42:56', '2023-12-31 17:52:56', 1);
INSERT INTO `order` VALUES ('42b0c91703ca05abb22846a4db879258', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2024-01-03 00:13:27', '2024-01-03 00:23:28', 1);
INSERT INTO `order` VALUES ('42fbcd8792fed0c17996e18122e09927', 17, '200部短文爽剧', 4, 4, '3379582030@qq.com', NULL, 1, '2023-12-29 20:51:13', '2023-12-29 20:52:14', 1);
INSERT INTO `order` VALUES ('47bc48accfd2bcfec8cb9f1c5f09e7c3', 12, 'Telegram美国账号', 15, 15, '749062870@qq.com', '91', 2, '2023-12-28 23:30:29', '2023-12-28 23:36:29', 1);
INSERT INTO `order` VALUES ('4880615a41dccd2547b49d298cea777c', 23, 'Windows电脑翻墙软件', 10, 10, '749062870@qq.com', NULL, 2, '2024-01-03 00:14:09', '2024-01-03 00:24:09', 1);
INSERT INTO `order` VALUES ('4916179c26ab4cb75a55b3ce06ddbb12', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2023-12-31 19:44:51', '2023-12-31 19:54:51', 1);
INSERT INTO `order` VALUES ('4c617f5f48d501fa42c7bcde66eadc29', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2023-12-30 14:40:54', '2023-12-30 14:43:36', 1);
INSERT INTO `order` VALUES ('4d7b9b2b40dd53bccde4538581f05ef6', 11, '测试产品', 1, 7, '749062870@qq.com', '79:80:81:82:83:84:85', 2, '2023-12-29 11:47:47', '2023-12-29 11:53:48', 7);
INSERT INTO `order` VALUES ('56d735faa62d10a0f513e3f7068919a3', 31, '高质量网站–需翻墙', 5, 5, '2909761388@qq.com', NULL, 2, '2024-01-20 19:53:21', '2024-01-20 20:03:21', 1);
INSERT INTO `order` VALUES ('575321b8bac97169386098dc1a461363', 21, '188G高速流量', 30, 30, '749062870@qq.com', NULL, 2, '2023-12-31 19:46:48', '2023-12-31 19:56:48', 1);
INSERT INTO `order` VALUES ('5845640f01a35128bc3d6112f80b2dd6', 11, '测试产品', 1, 1, '4455', '85', 2, '2023-12-29 18:11:58', '2023-12-29 18:17:59', 1);
INSERT INTO `order` VALUES ('59eb8f6ac624a341c46f05822457a804', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-03 23:41:00', '2024-01-03 23:51:01', 1);
INSERT INTO `order` VALUES ('5cac767fcaeee93d0312ac0651a1bb44', 26, '美国号', 5, 5, '749062870@qq.com', '104', 2, '2024-01-17 23:59:46', '2024-01-18 00:09:46', 1);
INSERT INTO `order` VALUES ('615c3cdc243e010b1fc5a817819e87a7', 11, '测试产品', 1, 1, '3228949325@qq.com', '78', 1, '2023-12-29 01:14:06', '2023-12-29 01:14:39', 1);
INSERT INTO `order` VALUES ('61e87716ac5fd4641dcac9ad1a58d413', 31, '高质量网站–需翻墙', 5, 5, '2909761388@qq.com', NULL, 2, '2024-01-20 19:52:47', '2024-01-20 20:02:47', 1);
INSERT INTO `order` VALUES ('623a251edb7ac90d4b2a5e2bd42ef3fa', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-04 08:01:53', '2024-01-04 08:11:53', 1);
INSERT INTO `order` VALUES ('62c5de8e35ae0d06b355424cc8f50f95', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2023-12-31 18:19:37', '2023-12-31 18:29:37', 1);
INSERT INTO `order` VALUES ('64db510cf8b03b6bfb9f9244f972e327', 11, '测试产品', 1, 1, '749062870@qq.com', '86', 1, '2023-12-31 16:57:43', '2023-12-31 16:58:09', 1);
INSERT INTO `order` VALUES ('65da111100da6450cb38292899470d81', 26, 'Telegram特价号', 2, 2, '749062870@qq.com', '96', 2, '2024-01-02 07:34:22', '2024-01-02 07:44:23', 1);
INSERT INTO `order` VALUES ('66676fb9090365af8ed3ca8b88a08581', 11, '测试产品', 1, 1, '3228949325@ qq.com', '77', 1, '2023-12-29 00:16:08', '2023-12-29 00:16:45', 1);
INSERT INTO `order` VALUES ('67dd97e39f1fb4c0e0332f177a571d83', 12, 'Telegram美国号', 10, 10, '749062870@qq.com', '91', 2, '2024-01-01 08:21:08', '2024-01-01 08:31:09', 1);
INSERT INTO `order` VALUES ('686b380621f00d8949b01adac7280824', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-05 00:23:25', '2024-01-05 00:33:25', 1);
INSERT INTO `order` VALUES ('6a0163eb0b92cf3027d629ac917493b2', 11, '测试产品', 1, 3, '749062870@qq.com', '82:83:84', 1, '2023-12-29 18:07:46', '2023-12-29 18:08:04', 3);
INSERT INTO `order` VALUES ('6bbf589b11ba9d6a6057ec313cadffd0', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2023-12-31 16:42:41', '2023-12-31 16:43:07', 1);
INSERT INTO `order` VALUES ('6df2366010ef3147659a4e30aa4e092e', 11, '测试产品', 1, 20, '749062870@qq.com', '56:57:58:59:61:62:63:64:65:66:67:68:69:70:71:72:73:74:75:76', 1, '2023-12-28 23:11:13', '2023-12-28 23:11:32', 20);
INSERT INTO `order` VALUES ('70c26a49cb64a6982ebd8d75a82cca51', 28, '长期高速代理', 10, 10, '1280737118@qq.com', NULL, 2, '2024-01-16 22:28:38', '2024-01-16 22:38:39', 1);
INSERT INTO `order` VALUES ('725719e061f093c09c252383449cd3f3', 25, 'Android免费VPN', 10, 10, '421850241@qq.com', NULL, 1, '2024-01-16 01:52:03', '2024-01-16 01:52:47', 1);
INSERT INTO `order` VALUES ('7647f2dfdce5194871e8017cd8dac85f', 31, '高质量网站–需翻墙', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-16 06:54:15', '2024-01-16 07:04:15', 1);
INSERT INTO `order` VALUES ('796b099ce361aca48ed852f154ff9975', 15, 'Twitter全新号', 10, 10, '@qq.com577818309', '93', 2, '2024-01-18 19:48:04', '2024-01-18 19:58:04', 1);
INSERT INTO `order` VALUES ('7ebb100c1e70eb5fec7736b931aba6ac', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2024-01-05 22:03:55', '2024-01-05 22:13:55', 1);
INSERT INTO `order` VALUES ('829e974564580602af3a6bca8513f6c7', 28, '每日更新', 1, 1, '749062870@qq.com', NULL, 2, '2024-01-08 18:37:57', '2024-01-08 18:47:57', 1);
INSERT INTO `order` VALUES ('884d889768b52a535ab00084117ed04c', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2023-12-31 17:18:59', '2023-12-31 17:28:59', 1);
INSERT INTO `order` VALUES ('8a630f77f1fce490a0e89e59e53b9df6', 11, '测试产品', 1, 1, '749062870@qq.com', '85', 1, '2023-12-31 00:08:10', '2023-12-31 00:08:48', 1);
INSERT INTO `order` VALUES ('8ad27e2341b27cbea171378098d0c805', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2024-01-17 02:59:04', '2024-01-17 03:09:05', 1);
INSERT INTO `order` VALUES ('8c38080092d7bec9209dacd7d3c1bb31', 31, '高质量网站–需翻墙', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-13 23:15:45', '2024-01-13 23:25:45', 1);
INSERT INTO `order` VALUES ('8ca2dba5f9f3af208130e0eb8a251365', 13, '无限1', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-29 14:31:47', '2023-12-29 14:32:05', 1);
INSERT INTO `order` VALUES ('906cd6a75a7b361840c2690e579251d9', 11, '测试产品', 1, 1, '749062870@qq.com', '85', 2, '2023-12-29 22:55:21', '2023-12-29 23:01:21', 1);
INSERT INTO `order` VALUES ('907b7db91ed240906ff80a89560fd00b', 16, '300部短文爽剧合集', 5, 5, '302576360', NULL, 2, '2023-12-29 21:42:42', '2023-12-29 21:48:42', 1);
INSERT INTO `order` VALUES ('91df6a33c66a835f7eb8a44e68c88da1', 15, 'Twitter全新号', 10, 10, 'liuyi15145924580@126.com', '92', 1, '2024-01-16 09:52:45', '2024-01-16 09:53:22', 1);
INSERT INTO `order` VALUES ('9219aff0a08a7bc2e4860ac8c5c78e76', 13, '无限1', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-28 23:13:22', '2023-12-28 23:13:36', 1);
INSERT INTO `order` VALUES ('934ed71c5ade627c3fa91b80bf94b796', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 0, '2024-01-20 21:25:08', '2024-01-20 21:25:08', 1);
INSERT INTO `order` VALUES ('9606e015c478ae293b324ddf6bd3cd13', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2023-12-31 20:06:39', '2023-12-31 20:16:39', 1);
INSERT INTO `order` VALUES ('967f7470a01be484b03d38256ec15b85', 15, 'Twitter全新号', 10, 20, '749062870@qq.com', '92:93', 2, '2024-01-01 19:57:22', '2024-01-01 20:07:23', 2);
INSERT INTO `order` VALUES ('98a7d231614278a4428f5be34f5a4c19', 28, '每日更新', 1, 1, '749062870@qq.com', NULL, 1, '2024-01-08 18:44:33', '2024-01-08 18:45:00', 1);
INSERT INTO `order` VALUES ('9b30a5336ab467d48a5a96933bac935e', 26, '美国号', 5, 5, '1272988122@qq.com', '103', 2, '2024-01-18 22:20:10', '2024-01-18 22:30:10', 1);
INSERT INTO `order` VALUES ('a0b630db625d69761b7a2cd28fce798e', 25, 'Android免费VPN', 20, 20, '749062870@qq.com', NULL, 2, '2024-01-03 00:15:36', '2024-01-03 00:25:36', 1);
INSERT INTO `order` VALUES ('a66b5e713b8023bfac693b25e38eeed1', 31, '高质量网站–需翻墙', 5, 5, '2909761388@qq.com', NULL, 2, '2024-01-20 19:51:58', '2024-01-20 20:01:59', 1);
INSERT INTO `order` VALUES ('a6dec7023489dea684764f35ddfa12c7', 25, 'Android免费VPN', 10, 10, '3286984015@qq.com', NULL, 2, '2024-01-18 10:15:50', '2024-01-18 10:25:50', 1);
INSERT INTO `order` VALUES ('a6fcda551f3d09bf487241f84fa290d8', 31, '高质量网站–需翻墙', 5, 5, 'zt18613385590@yeah.net', NULL, 2, '2024-01-20 13:59:28', '2024-01-20 14:09:28', 1);
INSERT INTO `order` VALUES ('aa07a43b585510d3ca2ab97f5f64faa0', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2024-01-13 09:07:25', '2024-01-13 09:17:26', 1);
INSERT INTO `order` VALUES ('abb4ed81a815f396f6398f7a2806fd8f', 26, 'Telegram特价号', 2, 2, '749062870@qq.com', '96', 2, '2024-01-01 19:56:48', '2024-01-01 20:06:49', 1);
INSERT INTO `order` VALUES ('af1695c40d2455b9ff45a21356d2bd1f', 13, '无限1', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-28 23:12:34', '2023-12-28 23:12:51', 1);
INSERT INTO `order` VALUES ('b61a725d0dc99b07a277a49b4f7fee75', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-15 14:11:15', '2024-01-15 14:21:15', 1);
INSERT INTO `order` VALUES ('bb80c24f3175593f8f0276f819eedac9', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2024-01-12 17:15:52', '2024-01-12 17:25:53', 1);
INSERT INTO `order` VALUES ('bbcd1930d7dfc4cd46ff8d96ca13a748', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2023-12-31 17:18:01', '2023-12-31 17:18:21', 1);
INSERT INTO `order` VALUES ('c4da1c0bec31896ad496940c6c043239', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-12 12:58:39', '2024-01-12 13:08:39', 1);
INSERT INTO `order` VALUES ('c7675ca78318117d7cd4f8c309ef82aa', 21, '188G高速流量', 30, 30, '749062870@qq.com', NULL, 2, '2023-12-31 19:45:12', '2023-12-31 19:55:12', 1);
INSERT INTO `order` VALUES ('c79c385b05600436b229429cc99f30ce', 25, 'Android免费VPN', 20, 20, '749062870@qq.com', NULL, 2, '2024-01-03 00:15:10', '2024-01-03 00:25:10', 1);
INSERT INTO `order` VALUES ('c89bf71d3ada2dac0e8f07d57ea69e0a', 11, '测试产品', 1, 1, 'ghostdemog@gmail.com', '81', 1, '2023-12-29 22:26:47', '2023-12-29 22:27:27', 1);
INSERT INTO `order` VALUES ('cf4ae2c5bdd7536a24611aa6b21ef933', 28, '长期高速代理', 10, 10, '749062870@qq.com', NULL, 2, '2024-01-17 09:15:14', '2024-01-17 09:25:14', 1);
INSERT INTO `order` VALUES ('d268f4ff1ca375bc087ab63cb47ed3ae', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2024-01-10 12:26:27', '2024-01-10 12:36:28', 1);
INSERT INTO `order` VALUES ('d324cad74e5a1c603a1b1d52701a759d', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2024-01-03 00:13:48', '2024-01-03 00:23:48', 1);
INSERT INTO `order` VALUES ('d4501b087c7b8feeb4fcdf6dbdb4aa1d', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 2, '2024-01-01 08:24:58', '2024-01-01 08:34:58', 1);
INSERT INTO `order` VALUES ('da07954ddd7c87b85585c10aa1014f13', 16, '300部短文爽剧合集', 5, 5, '34575376@qq.com', NULL, 2, '2023-12-29 22:40:28', '2023-12-29 22:46:29', 1);
INSERT INTO `order` VALUES ('e2eb1ff2af5a62b1ff04d631bdf21e5d', 11, '测试产品', 1, 1, '2606816118@qq.com', '81', 2, '2023-12-29 21:31:50', '2023-12-29 21:37:50', 1);
INSERT INTO `order` VALUES ('e92e2fc4114508a236d0a2e188a78936', 11, '测试产品', 1, 4, '749062870@qq.com', '87:88:89:90', 1, '2023-12-28 23:12:10', '2023-12-28 23:12:22', 4);
INSERT INTO `order` VALUES ('ea5f633b74386383572498ae6bd7e799', 28, '每日更新', 1, 1, '749062870@qq.com', NULL, 1, '2024-01-12 13:52:32', '2024-01-12 13:53:10', 1);
INSERT INTO `order` VALUES ('ec2f9d13d2eddea5e15817d64da0832f', 21, '188G高速流量', 30, 30, '749062870@qq.com', NULL, 2, '2023-12-31 19:46:02', '2023-12-31 19:56:02', 1);
INSERT INTO `order` VALUES ('ee835f4507fce166bd8b04cdef830576', 28, '每日更新', 1, 1, '749062870@qq.com', NULL, 1, '2024-01-08 17:52:16', '2024-01-08 17:52:56', 1);
INSERT INTO `order` VALUES ('efa1e1ef0ed3109f2d9911e62aa199dc', 17, '200部短文爽剧', 4, 4, '749062870@qq.com', NULL, 2, '2023-12-31 20:00:28', '2023-12-31 20:10:29', 1);
INSERT INTO `order` VALUES ('f2040fa1a07da5b50b806c943188e9b5', 25, 'Android免费VPN', 20, 20, '749062870@qq.com', NULL, 2, '2023-12-30 18:03:27', '2023-12-30 18:09:28', 1);
INSERT INTO `order` VALUES ('f41c09aa287745d40ada47e5b0658113', 26, '美国号', 5, 5, '1272988122@qq.com', '104', 2, '2024-01-18 22:20:59', '2024-01-18 22:30:59', 1);
INSERT INTO `order` VALUES ('f4dc5d99e076ec4beaaf32987eb28c7b', 28, '每日更新', 1, 1, '749062870@qq.com', NULL, 1, '2024-01-08 17:50:00', '2024-01-08 17:50:32', 1);
INSERT INTO `order` VALUES ('f4e2c1dce427d1f6d7a1c78848a598a0', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2023-12-31 16:46:14', '2023-12-31 16:46:40', 1);
INSERT INTO `order` VALUES ('f517b485f37b06c61badc9e699645264', 28, '每日更新', 1, 1, '749062870@qq.com', NULL, 2, '2024-01-08 17:58:56', '2024-01-08 18:08:57', 1);
INSERT INTO `order` VALUES ('f580c6eb65f992e192c1c92548e7f8b0', 18, 'Tiktok破解版', 2, 2, '777', NULL, 2, '2023-12-30 01:18:18', '2023-12-30 01:24:19', 1);
INSERT INTO `order` VALUES ('f62fb51164a08c53f6735bd26ed0d1aa', 13, '无限1', 1, 1, '749062870@qq.com', NULL, 1, '2023-12-29 18:04:04', '2023-12-29 18:04:35', 1);
INSERT INTO `order` VALUES ('f6cef584f517e1d10b437d3a5bbcc088', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 0, '2024-01-20 21:24:21', '2024-01-20 21:24:21', 1);
INSERT INTO `order` VALUES ('f9c47e4432eb0194079fb49beb6780da', 18, 'Tiktok破解版', 2, 2, '749062870@qq.com', NULL, 1, '2024-01-08 16:24:59', '2024-01-08 16:25:31', 1);
INSERT INTO `order` VALUES ('f9eaf0992bc2e202103c0ba2129b5929', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2023-12-31 19:49:25', '2023-12-31 19:59:26', 1);
INSERT INTO `order` VALUES ('f9f64082bb961880cf1dd0067078a3d2', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2024-01-02 16:37:19', '2024-01-02 16:47:19', 1);
INSERT INTO `order` VALUES ('fa403f3310fb2a10d623577bda372e36', 11, '测试产品', 1, 3, '749062870@qq.com', '79:80:81', 2, '2023-12-29 18:07:19', '2023-12-29 18:13:19', 3);
INSERT INTO `order` VALUES ('fbe807fc2526847b3534e0d1e5e0ad0a', 20, '100G高速流量', 20, 20, '749062870@qq.com', NULL, 2, '2024-01-06 10:50:32', '2024-01-06 11:00:33', 1);
INSERT INTO `order` VALUES ('fc31c161e8e0d77f5b59451e9d6a656b', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 0, '2024-01-20 21:28:45', '2024-01-20 21:28:45', 1);
INSERT INTO `order` VALUES ('fc939eba7be4550658281d7a7fdf5b51', 16, '300部短文爽剧合集', 5, 5, '749062870@qq.com', NULL, 2, '2024-01-03 01:22:52', '2024-01-03 01:32:52', 1);
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
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (11, 33, '测试产品', 1, 'public/2023-12-27/8d0834182d954e2284bbf6d1cdeb7fad.png', '2023-12-27 22:54:18', '2023-12-31 16:58:07', 0, '本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿:本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿:本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿', '本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿:本商品为测试商品 请理性操作 不存在任何骗钱 手长自己身上 全凭自愿', 0, 0, 0, 0, 0, '无库存');
INSERT INTO `product` VALUES (12, 28, 'Telegram美国号', 10, 'public/2023-12-28/e290a36ae1b34bdb89b0fd4467f22593.png', '2023-12-28 16:46:03', '2024-01-01 08:51:12', 0, '如果登录不上或者出问题 直接联系客服 具体登录会发到你邮箱:网站量少 可以私信客服订购批量', '如果登录不上或者出问题 直接联系客服:网站量少 有需要可以私信客服批量订购', 0, 0, 0, 1, 0, NULL);
INSERT INTO `product` VALUES (13, 35, '无限1', 1, 'public/2023-12-28/2207a6af03a04635be7578cadc8c6da4.png', '2023-12-28 21:56:37', '2023-12-28 21:56:48', 0, '无限1无限1', '无限1无限1', 0, 0, 0, 0, 1, '无限1无限1无限1无限1无限1');
INSERT INTO `product` VALUES (14, 35, '无限2', 1, 'public/2023-12-28/31b8a53a95e04c61bdcff24b43a1191e.png', '2023-12-28 21:57:27', '2023-12-29 18:03:34', 0, '无限2无限2', '无限2无限2', 0, 0, 1, 0, 1, '无限2无限2无限2无限2无限24');
INSERT INTO `product` VALUES (15, 29, 'Twitter全新号', 10, 'public/2023-12-29/7f21e38cd29c49e98fb35b64ac8de429.png', '2023-12-29 19:03:10', '2024-01-18 19:58:04', 0, '卡密格式:登录用户名---登录密码---辅助邮箱地址---邮箱密码', '购买本商品 不用绑定手机号码 更加安全', 3, 0, 0, 0, 0, NULL);
INSERT INTO `product` VALUES (16, 37, '300部短文爽剧合集', 5, 'public/2023-12-29/b98de68807e24b58b1d60fdb7f8370cc.png', '2023-12-29 19:52:52', '2023-12-29 19:55:15', 0, '此产品为夸克网盘资源 购买后会发送链接 可以在线观看', '此产品为夸克网盘资源 购买后会发送链接 可以在线观看', 0, 0, 0, 0, 1, '夸克网盘分享了链接：https://pan.quark.cn/s/0bc3f70fc50f');
INSERT INTO `product` VALUES (17, 37, '200部短文爽剧', 4, 'public/2023-12-29/51184ae92cea4750990187d7d215c46c.png', '2023-12-29 20:00:21', '2023-12-29 20:00:42', 0, '此产品为夸克网盘资源 购买后会发送链接 可以在线观看', '此产品为夸克网盘资源 购买后会发送链接 可以在线观看', 0, 0, 0, 0, 1, '夸克网盘分享链接：https://pan.quark.cn/s/300824404e55');
INSERT INTO `product` VALUES (18, 40, 'Tiktok破解版', 2, 'public/2023-12-29/140346948fc64a8f88c1fefee8563012.jpg', '2023-12-29 22:21:09', '2023-12-29 22:21:46', 0, '本产品为夸克网盘资源 下载你想要的版本安装即可', '本产品为夸克网盘资源 下载你想要的版本安装即可', 0, 0, 0, 0, 1, '夸克网盘分享链接: https://pan.quark.cn/s/dd2eceeac541');
INSERT INTO `product` VALUES (19, 36, '超值双改号', 120, 'public/2023-12-29/f12f43e3919447ebae503691c9cb036a.png', '2023-12-30 02:41:49', '2023-12-31 01:38:52', 0, '卡密格式: 账号----密码----邮箱密码', '卡密格式: 账号----密码----邮箱密码', 0, 0, 0, 1, 0, '');
INSERT INTO `product` VALUES (20, 41, '100G高速流量', 20, 'public/2023-12-29/f66bdbbc92394abfae6b66cdabe4b52a.jpeg', '2023-12-30 03:19:42', '2023-12-30 03:19:45', 0, '20块钱100G高速流量 看什么都快 做什么都顺 不担心流量不够', '此产品为手动发货 下单后 客服会将产品发到您填写的邮箱地址', 0, 0, 1, 0, 1, NULL);
INSERT INTO `product` VALUES (21, 41, '188G高速流量', 30, 'public/2023-12-29/de6d321e7c054e53b1ef282b6498d6ba.jpeg', '2023-12-30 03:21:25', '2023-12-30 03:21:28', 0, '30块钱188G高速流量 看什么都快 做什么都顺 不担心流量不够', '此产品为手动发货 下单后 客服会将产品发到您填写的邮箱地址', 0, 0, 1, 0, 1, NULL);
INSERT INTO `product` VALUES (22, 40, 'Android翻墙软件', 20, 'public/2023-12-29/31b4529c1f1a48cc84e93b6f5d6831de.jpeg', '2023-12-30 03:32:01', '2023-12-30 03:33:02', 0, '此产品为安卓翻墙工具 购买产品后会发送下载链接到您邮箱 您只需要下载安装即可:软件使用教程在 梯子-科学上网-翻墙 分类下', '此产品切勿传播', 0, 0, 0, 0, 1, '下载地址: https://pub-pce.oss-cn-chengdu.aliyuncs.com/public/tools/ClashForAndroid.apk');
INSERT INTO `product` VALUES (23, 40, 'Windows电脑翻墙软件', 10, 'public/2023-12-29/0f0b2d280a044f38b953d2edf710db53.jpeg', '2023-12-30 03:35:52', '2023-12-30 03:35:55', 0, '此产品为windows电脑翻墙工具 购买产品后会发送下载链接到您邮箱 您只需要下载解压即可:软件使用教程在 梯子-科学上网-翻墙 分类下', '此产品切勿传播', 0, 0, 0, 0, 1, '下载链接: https://pub-pce.oss-cn-chengdu.aliyuncs.com/public/tools/ClashForWindows.7z');
INSERT INTO `product` VALUES (24, 42, '全新谷歌邮箱', 5, 'public/2023-12-29/8a45cf4a078446d683fa71d4be21ecd7.png', '2023-12-30 04:31:02', '2023-12-30 11:34:46', 0, '卡密格式: 谷歌邮箱---邮箱密码', '自己使用 不要交给别人', 1, 0, 0, 0, 0, NULL);
INSERT INTO `product` VALUES (25, 40, 'Android免费VPN', 10, 'public/2023-12-30/b60460f148fa41fca320717d049b09cc.png', '2023-12-30 18:02:28', '2024-01-08 20:30:46', 0, '此商品为永久免费vpn软件 购买后会邮件给你下载地址', '此商品为永久免费vpn软件 购买后会邮件给你下载地址', 0, 0, 0, 0, 1, '免费VPN软件下载链接: \nhttps://pub-pce.oss-cn-chengdu.aliyuncs.com/public/application/V2VPN%20-%20A%20Fast%20VPN%20Proxy_5.4.17_Apkpure.apk');
INSERT INTO `product` VALUES (26, 28, '美国号', 5, 'public/2023-12-31/b9b88a23456b46cab4453daf81f14cba.jpg', '2023-12-31 22:23:46', '2024-01-20 21:24:02', 0, '卡密格式:手机号Ｉ验证码链接', '30天可接码', 9, 1, 0, 0, 0, NULL);
INSERT INTO `product` VALUES (27, 38, 'ins全新号', 10, 'public/2024-01-03/41b4f62f27f34450bf2874c4e791c51f.jpg', '2024-01-03 23:39:15', '2024-01-04 08:03:26', 0, '卡密格式:账号----密码----邮箱地址', '请务必记住邮箱地址 出问题可找客服', 1, 0, 0, 0, 0, NULL);
INSERT INTO `product` VALUES (28, 43, '长期高速代理', 10, 'public/2024-01-08/eced5f9e43d646f8bf3dfbeb1aa21638.jpg', '2024-01-08 17:49:26', '2024-01-17 10:28:28', 0, '使用方法:方法一.将代理链接的何处输入到相应位置:方法二.复制代理链接发送给任意好友点击链接可一键连接', '每日都会更新 旧的不能用了可以再来买 价格美丽', 0, 0, 0, 0, 1, 'https://t.me/proxy?server=8.218.95.2&port=443&secret=ee7e16186e43fa1c13d81a5e0a37fc049a617a7572652e6d6963726f736f66742e636f6d');
INSERT INTO `product` VALUES (29, 40, 'Twitter', 1, 'public/2024-01-08/212d48d3df3349a8833877b5870b9ac2.png', '2024-01-08 19:51:03', '2024-01-08 19:51:58', 0, '购买后直接给你下载链接 通过邮件发送给你', '切勿传播', 0, 0, 0, 0, 1, 'https://pub-pce.oss-cn-chengdu.aliyuncs.com/public/application/Telegram.apk');
INSERT INTO `product` VALUES (30, 40, 'Telegram', 1, 'public/2024-01-08/e6b41a20564140d294c3f407be2b1fa7.jpg', '2024-01-08 19:51:54', '2024-01-08 19:51:58', 0, '购买后直接给你下载链接 通过邮件发送给你', '切勿传播', 0, 0, 0, 0, 1, 'https://pub-pce.oss-cn-chengdu.aliyuncs.com/public/application/Twitter.apk');
INSERT INTO `product` VALUES (31, 44, '高质量网站–需翻墙', 5, 'public/2024-01-12/3f767d5fd60f4eca935a9b1f81e25b30.png', '2024-01-12 18:07:04', '2024-01-12 18:13:58', 0, '购买后通过您填写的邮箱地址发送邮件给您:此产品受限于网络需要购买软件中的翻墙软件才能用:此产品无广告', '本产品请18岁以下自觉:购买后请勿传播:严格遵守法律', 0, 0, 0, 0, 1, 'https://missav.com/dm17/cn/madou');
INSERT INTO `product` VALUES (32, 44, '普通网站–无需翻墙', 5, 'public/2024-01-12/b6dc53c16fed40d892e5b1f7346c0023.png', '2024-01-12 18:16:43', '2024-01-12 20:25:55', 0, '购买后会发送到您填写的邮箱中:此产品有广告但无需翻墙软件', '此产品18岁以下请自觉:请合法使用切勿传播', 0, 0, 0, 0, 1, 'https://zh.video01.ink 请下载好chrome浏览器然后复制此链接到浏览器就可以嘿嘿嘿了');

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
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (28, 'Telegram账号', 'public/2023-12-26/9415bbb0610c478f8c997c1714110b7b.png', '2023-12-26 14:40:31', '2024-01-15 14:15:40', 0, 0);
INSERT INTO `product_category` VALUES (29, 'Twitter账号', 'public/2023-12-26/e25bf9855f0541578af25be649c442a8.png', '2023-12-26 14:40:56', '2023-12-29 18:53:17', 0, 0);
INSERT INTO `product_category` VALUES (30, 'AppleId', 'public/2023-12-26/a58d2ef193bd4cd3ac9662979ce7868a.png', '2023-12-26 14:41:08', '2023-12-30 04:42:30', 0, 1);
INSERT INTO `product_category` VALUES (31, 'TIktok账号', 'public/2023-12-29/6281b809e78142e5a574caac34ab84e3.jpg', '2023-12-26 14:41:23', '2023-12-30 04:42:22', 0, 1);
INSERT INTO `product_category` VALUES (33, '测试分类', 'public/2023-12-29/6edb9d96aecd4884928fa052533993c2.jpg', '2023-12-27 22:52:54', '2023-12-31 19:39:19', 0, 1);
INSERT INTO `product_category` VALUES (35, '无限库存商品测试分类', 'public/2023-12-28/08f51d8b30414b918db60e67c94551e6.png', '2023-12-28 21:55:44', '2023-12-29 21:19:25', 0, 1);
INSERT INTO `product_category` VALUES (36, '原神', 'public/2023-12-28/ab5ce4430461430d8969a31ef301cb65.png', '2023-12-29 01:44:25', '2023-12-31 01:39:00', 0, 1);
INSERT INTO `product_category` VALUES (37, '抖音短文爽剧', 'public/2023-12-29/081c393f10de41f2810f63b2216efe18.png', '2023-12-29 19:50:22', '2023-12-29 20:10:03', 0, 0);
INSERT INTO `product_category` VALUES (38, 'Instagram账号', 'public/2023-12-29/b95af7dc18fa42cbad260c40869a5e3a.png', '2023-12-29 20:13:46', '2024-01-03 23:38:08', 0, 0);
INSERT INTO `product_category` VALUES (39, '梯子-科学上网-翻墙', 'public/2023-12-29/431494ed07f542f8925be1dfaf13f311.jpg', '2023-12-29 20:22:53', '2023-12-30 21:17:26', 0, 1);
INSERT INTO `product_category` VALUES (40, '软件类', 'public/2023-12-29/12824535d47649cdab5e4b37981df1f7.png', '2023-12-29 20:34:49', '2023-12-29 20:34:59', 0, 0);
INSERT INTO `product_category` VALUES (41, '流量套餐', 'public/2023-12-29/9ad693b4fee64fcf8d5a74683b11539e.jpeg', '2023-12-30 03:15:49', '2023-12-30 03:15:54', 0, 0);
INSERT INTO `product_category` VALUES (42, '谷歌邮箱账号', 'public/2023-12-29/6fb3fa59408d451bad16ca3d9a63fa24.png', '2023-12-30 04:26:44', '2023-12-30 04:26:47', 0, 0);
INSERT INTO `product_category` VALUES (43, 'Telegram高速代理', 'public/2024-01-08/223fed500c714c66ad1156fa132f0ef0.jpg', '2024-01-08 17:45:35', '2024-01-08 17:45:40', 0, 0);
INSERT INTO `product_category` VALUES (44, '成人系列', 'public/2024-01-12/04f23753b7cf4bdabd3e1f9bf8be039e.png', '2024-01-12 18:03:37', '2024-01-12 18:03:41', 0, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (97, 'account_shop_dict', NULL, '账号商城字典', NULL);
INSERT INTO `sys_dict` VALUES (98, 'huaxianren_telegram', 97, '客服tg', 'https://t.me/shnajkzl');
INSERT INTO `sys_dict` VALUES (99, 'huaxianren_email', 97, '客服邮箱', 'liqisong2002@gmail.com');
INSERT INTO `sys_dict` VALUES (100, 'huaxianren_recommend_channel_url', 97, '推荐频道链接', 'https://t.me/huaxianrennews');
INSERT INTO `sys_dict` VALUES (101, 'huaxianren_recommend_channel_name', 97, '推荐频道名字', '闲人新闻');
INSERT INTO `sys_dict` VALUES (102, 'telegram_copy_dict', NULL, 'telegram群消息复制字典', NULL);
INSERT INTO `sys_dict` VALUES (103, 'order_ids', 102, '可听命的账号id', '5060527090:6349971145');
INSERT INTO `sys_dict` VALUES (104, 'resource_account', 102, '被复制的群或者频道', 'JPYPorn ');
INSERT INTO `sys_dict` VALUES (105, 'target_account', 102, '被复制到的目标账号', 'huaxianrenlee7s');
INSERT INTO `sys_dict` VALUES (106, 'redis_index_key_word', 102, 'redis中存放的消息起始id的key名字', 'min_id');
INSERT INTO `sys_dict` VALUES (107, 'contact', 102, '客服账号', 'https://t.me/shnajkzl');
INSERT INTO `sys_dict` VALUES (108, 'account_shop_url', 102, '商店地址', 'https://shop.somg.xyz');
INSERT INTO `sys_dict` VALUES (109, 'other_channel', 102, '其他频道展示消息名字_链接逗号分隔', '撸管圣地_https://t.me/huaxianrenlee7s,闲人美图_https://t.me/huaxianrenmeitu,闲人里番_https://t.me/huaxianrenlifan,闲人新闻_https://t.me/huaxianrennews,闲人商店_https://t.me/huaxianrenshare');
INSERT INTO `sys_dict` VALUES (110, 'main_channel', 102, '主频道人数最多的名字:链接', '闲人av频道:av_share_channel');
INSERT INTO `sys_dict` VALUES (111, 'resource_account_news', 102, '新闻更新源群组或者频道', 'xhqcankao');
INSERT INTO `sys_dict` VALUES (112, 'target_account_news', 102, '新闻更新目标群组或者频道', 'huaxianrennews');
INSERT INTO `sys_dict` VALUES (113, 'alipay_enable', 97, '支付宝支付是否可用 1 为不可用 0为可用', '1');
INSERT INTO `sys_dict` VALUES (114, 'usdt_enable', 97, 'usdt支付是否可用 1 不可用 0 可用', '0');

SET FOREIGN_KEY_CHECKS = 1;
