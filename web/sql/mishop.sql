/*
Navicat MySQL Data Transfer

Source Server         : shop
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : mishop

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-08-28 15:37:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址实体的唯一主键列',
  `u_id` int(11) DEFAULT NULL COMMENT '用户实体的主键属性',
  `a_name` varchar(30) DEFAULT NULL COMMENT '地址的收件人',
  `a_phone` varchar(14) DEFAULT NULL COMMENT '收件人电话',
  `a_detail` varchar(200) DEFAULT NULL COMMENT '收货人详细地址',
  `a_state` int(11) DEFAULT NULL COMMENT '是否是默认地址 0 不是 1是默认地址',
  PRIMARY KEY (`a_id`),
  KEY `FK_u_a_fk` (`u_id`),
  CONSTRAINT `FK_u_a_fk` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户个人中心的地址实体，用于存储地址信息';

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '5', '田七', '13888888888', '深圳市宝安区', '0');
INSERT INTO `address` VALUES ('3', '5', '拉赛乐', '1999999999', '深圳市宝安区西乡街道', '0');
INSERT INTO `address` VALUES ('7', '5', '123', '124', '124', '0');
INSERT INTO `address` VALUES ('8', '5', '暗示的', '21312432421', '艾弗森发我按个人啊 ', '0');
INSERT INTO `address` VALUES ('9', '5', 'R1463 ', '52311`', '142H', '1');
INSERT INTO `address` VALUES ('10', '11', '421', '214', '14', '0');
INSERT INTO `address` VALUES ('11', '11', '124', '14', '41', '0');
INSERT INTO `address` VALUES ('12', '11', '14', '41', '14', '1');
INSERT INTO `address` VALUES ('15', '11', ' ', ' ', ' ', '0');
INSERT INTO `address` VALUES ('16', '11', ' ', ' ', ' ', '0');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车的唯一标识',
  `u_id` int(11) DEFAULT NULL COMMENT '用户实体的主键属性',
  `p_id` int(11) DEFAULT NULL COMMENT '商品的唯一主键',
  `c_count` decimal(12,2) DEFAULT NULL COMMENT '购物车小计',
  `c_num` int(11) DEFAULT NULL COMMENT '购物车商品数量',
  PRIMARY KEY (`c_id`),
  KEY `FK_p_c_fk` (`p_id`),
  KEY `FK_u_c_fk` (`u_id`),
  CONSTRAINT `FK_p_c_fk` FOREIGN KEY (`p_id`) REFERENCES `product` (`p_id`),
  CONSTRAINT `FK_u_c_fk` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='购物车实体';

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `i_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单项的唯一标识',
  `o_id` varchar(64) DEFAULT NULL COMMENT '订单编号是字符串类型但是也是唯一标识',
  `p_id` int(11) DEFAULT NULL COMMENT '商品的唯一主键',
  `i_count` decimal(12,2) DEFAULT NULL COMMENT '订单项的小计',
  `i_num` int(11) DEFAULT NULL COMMENT '订单项的数量',
  PRIMARY KEY (`i_id`),
  KEY `FK_o_i_fk` (`o_id`),
  KEY `FK_p_i_fk` (`p_id`),
  CONSTRAINT `FK_o_i_fk` FOREIGN KEY (`o_id`) REFERENCES `orders` (`o_id`),
  CONSTRAINT `FK_p_i_fk` FOREIGN KEY (`p_id`) REFERENCES `product` (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='订单内部的具体商品展示项';

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '20200608152026463', '2', '1999.00', '1');
INSERT INTO `item` VALUES ('2', '20200608152026463', '15', '20.00', '1');
INSERT INTO `item` VALUES ('3', '20200608155928272', '3', '1999.00', '1');
INSERT INTO `item` VALUES ('4', '20200608155928272', '16', '20.00', '1');
INSERT INTO `item` VALUES ('5', '20200608165452257', '2', '1999.00', '1');
INSERT INTO `item` VALUES ('6', '20210827154419867c2', '2', '5997.00', '3');
INSERT INTO `item` VALUES ('7', '20210827154419867c2', '5', '3998.00', '2');
INSERT INTO `item` VALUES ('8', '20210827154419867c2', '4', '1999.00', '1');
INSERT INTO `item` VALUES ('9', '20210827154822877264', '2', '1999.00', '1');
INSERT INTO `item` VALUES ('10', '202108271745146042ee', '2', '1999.00', '1');
INSERT INTO `item` VALUES ('11', '20210827174637427207', '14', '40.00', '2');
INSERT INTO `item` VALUES ('12', '2021082720365551723f', '3', '1999.00', '1');
INSERT INTO `item` VALUES ('13', '2021082720365551723f', '2', '1999.00', '1');
INSERT INTO `item` VALUES ('14', '20210827204137457d2', '15', '20.00', '1');
INSERT INTO `item` VALUES ('15', '202108281143595711bf', '2', '1999.00', '1');
INSERT INTO `item` VALUES ('16', '202108281143595711bf', '3', '1999.00', '1');
INSERT INTO `item` VALUES ('17', '202108281143595711bf', '1', '1999.00', '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `o_id` varchar(64) NOT NULL COMMENT '订单编号是字符串类型但是也是唯一标识',
  `u_id` int(11) DEFAULT NULL COMMENT '用户实体的主键属性',
  `a_id` int(11) DEFAULT NULL COMMENT '地址实体的唯一主键列',
  `o_count` decimal(12,2) DEFAULT NULL COMMENT '订单的总金额',
  `o_time` datetime DEFAULT NULL COMMENT '订单的详细时间',
  `o_state` int(11) DEFAULT NULL COMMENT '订单状态 0 未付款，1已经付款未发货 2发货待收货 3 收货待评价 4订单完成 5 退货状态',
  PRIMARY KEY (`o_id`),
  KEY `FK_a_o_fk` (`a_id`),
  KEY `FK_u_o_fk` (`u_id`),
  CONSTRAINT `FK_a_o_fk` FOREIGN KEY (`a_id`) REFERENCES `address` (`a_id`),
  CONSTRAINT `FK_u_o_fk` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单模块的订单实体';

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('20200608152026463', '5', '3', '2019.00', '2019-06-08 15:20:26', '1');
INSERT INTO `orders` VALUES ('20200608155928272', '5', '1', '2019.00', '2018-01-08 15:59:28', '2');
INSERT INTO `orders` VALUES ('20200608165452257', '5', '1', '1999.00', '2019-06-20 16:54:52', '1');
INSERT INTO `orders` VALUES ('20200608165605137', '5', '1', '1999.00', '2016-08-20 16:56:05', '1');
INSERT INTO `orders` VALUES ('20210827154419867c2', '11', '10', '11994.00', '2021-08-27 15:44:19', '1');
INSERT INTO `orders` VALUES ('20210827154428832135', '11', '12', '11994.00', '2021-08-27 15:44:28', '1');
INSERT INTO `orders` VALUES ('20210827154822877264', '11', '10', '1999.00', '2021-08-27 15:48:22', '1');
INSERT INTO `orders` VALUES ('202108271745146042ee', '11', '10', '1999.00', '2021-08-27 17:45:14', '2');
INSERT INTO `orders` VALUES ('20210827174637427207', '11', '10', '40.00', '2021-08-27 17:46:37', '2');
INSERT INTO `orders` VALUES ('2021082720365551723f', '11', '10', '3998.00', '2021-08-27 20:36:55', '2');
INSERT INTO `orders` VALUES ('20210827204137457d2', '11', '10', '20.00', '2021-08-27 20:41:37', '2');
INSERT INTO `orders` VALUES ('202108281143595711bf', '11', '12', '5997.00', '2021-08-28 11:43:59', '2');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品的唯一主键',
  `t_id` int(11) DEFAULT NULL COMMENT '类别的主键id',
  `p_name` varchar(50) DEFAULT NULL COMMENT '商品的名称',
  `p_time` date DEFAULT NULL COMMENT '商品的上市时间',
  `p_image` varchar(100) DEFAULT NULL COMMENT '商品图片的路径',
  `p_price` decimal(12,2) DEFAULT NULL COMMENT '商品的价格',
  `p_state` int(11) DEFAULT NULL COMMENT '商品的热门指数',
  `p_info` varchar(200) DEFAULT NULL COMMENT '商品的描述',
  PRIMARY KEY (`p_id`),
  KEY `FK_t_p_fk` (`t_id`),
  CONSTRAINT `FK_t_p_fk` FOREIGN KEY (`t_id`) REFERENCES `type` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='商品模块的商品实体';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('2', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('3', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('4', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('5', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('6', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('7', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('8', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('9', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('10', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('11', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('12', '1', '红米4', '1990-01-01', 'image/liebiao_hongmin4.jpg', '1999.00', '4', '红米4手机，主打性价比！吸引年轻');
INSERT INTO `product` VALUES ('13', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('14', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('15', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('16', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('17', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('18', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('19', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('20', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('21', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('22', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('23', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('24', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('25', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');
INSERT INTO `product` VALUES ('26', '2', '红米4手机壳', '1990-01-01', 'image/peijian2.jpg', '20.00', '5', '红米4手机壳，用心保护你的手机');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别的主键id',
  `t_name` varchar(20) DEFAULT NULL COMMENT '类别的名称',
  `t_info` varchar(200) DEFAULT NULL COMMENT '类别的描述',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品模块的类别实体';

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '手机', '小米手机，为发骚而生！');
INSERT INTO `type` VALUES ('2', '配件', '小米手机专用配件，爱护你的手机！');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户实体的主键属性',
  `u_name` varchar(20) NOT NULL COMMENT '用户账号',
  `u_password` varchar(64) NOT NULL COMMENT '用户密码',
  `u_email` varchar(50) NOT NULL COMMENT '用户的邮箱！用于激活使用！',
  `u_sex` varchar(4) DEFAULT NULL COMMENT '用户性别！',
  `u_status` varchar(11) DEFAULT NULL COMMENT '用户的激活状态 0 未激活 1 激活',
  `u_code` varchar(64) DEFAULT NULL COMMENT '邮件激活码',
  `u_role` int(11) DEFAULT NULL COMMENT '用户 0 管理员 1',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户模块的用户实体';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'lisi', '202cb962ac59075b964b07152d234b70', '123555@qq.com', '男', '0', '20200607014947393a9', '0');
INSERT INTO `user` VALUES ('3', 'wangwu', '202cb962ac59075b964b07152d234b70', 'chenzetao88886@163.com', '男', '0', '20200607020622011329', '0');
INSERT INTO `user` VALUES ('4', 'zhaoliu', '202cb962ac59075b964b07152d234b70', 'zhaoliu@qq.com', '男', '0', '202006070238454171f9', '0');
INSERT INTO `user` VALUES ('5', 'tianqi', '202cb962ac59075b964b07152d234b70', 'chenzetao88886@163.com', '男', '1', '2020060702424748230d', '0');
INSERT INTO `user` VALUES ('6', 'uuuu', '202cb962ac59075b964b07152d234b70', '123', '男', '0', '20210824184720619316', '0');
INSERT INTO `user` VALUES ('7', 'uuuuIU', 'c20ad4d76fe97759aa27a0c99bff6710', '123', '男', '0', '20210825085542419261', '0');
INSERT INTO `user` VALUES ('8', 'eamilText', '202cb962ac59075b964b07152d234b70', '1369960580@qq.com', '男', '0', '20210825110750977179', '0');
INSERT INTO `user` VALUES ('9', 'eamilTexts', '202cb962ac59075b964b07152d234b70', '1369960580@qq.com', '男', '1', '202108251126059383d6', '0');
INSERT INTO `user` VALUES ('10', 'aaa', '202cb962ac59075b964b07152d234b70', '1369960580@qq.com', '男', '1', '202108251145082032a4', '0');
INSERT INTO `user` VALUES ('11', '123', '202cb962ac59075b964b07152d234b70', '1369960580@qq.com', '男', '1', '20210825141650338136', '0');
INSERT INTO `user` VALUES ('12', '456', '250cf8b51c773f3f8dc8b4be867a9a02', '1369960580@qq.com', '男', '1', '20210825153729705ba', '0');
