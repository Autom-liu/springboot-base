/*
Navicat MySQL Data Transfer

Source Server         : myVirtualHost
Source Server Version : 50717
Source Host           : 192.168.56.101:3306
Source Database       : myadmin

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-05-03 12:40:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(60) NOT NULL,
  `bz_id` varchar(60) DEFAULT '' COMMENT '用户编号，属于业务字段，非系统自动生成，提供用户定义的唯一标识（比如学号，工号等），理论上不得重复，但在删除标志不同的条件下可以重复',
  `username` varchar(60) DEFAULT '',
  `password` varchar(60) DEFAULT NULL,
  `nickname` varchar(60) DEFAULT '',
  `avatar` varchar(60) DEFAULT '',
  `login_ip` varchar(60) DEFAULT '',
  `login_time` timestamp NULL DEFAULT NULL,
  `is_lock` tinyint(1) DEFAULT '0',
  `is_del` tinyint(1) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bz_unique` (`bz_id`,`is_del`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
