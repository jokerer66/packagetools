/*
 Navicat MySQL Data Transfer

 Source Server         : 2.55
 Source Server Version : 50713
 Source Host           : 192.168.2.55
 Source Database       : packagetool

 Target Server Version : 50713
 File Encoding         : utf-8

 Date: 02/06/2017 19:23:05 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `androidlog`
-- ----------------------------
DROP TABLE IF EXISTS `androidlog`;
CREATE TABLE `androidlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `packname` varchar(255) DEFAULT NULL,
  `main_version` varchar(255) DEFAULT NULL,
  `svn_version` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `store_root_path` varchar(255) DEFAULT NULL,
  `store_path` varchar(255) DEFAULT NULL,
  `svn_url` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `edition` varchar(255) DEFAULT NULL,
  `platform` varchar(255) DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `on_off` varchar(255) DEFAULT NULL,
  `nums` int(11) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `pack_time` datetime DEFAULT NULL,
  `isstore` varchar(255) DEFAULT NULL,
  `viewflag` int(11) DEFAULT NULL,
  `exone` varchar(255) DEFAULT NULL,
  `extwo` varchar(255) DEFAULT NULL,
  `exthree` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1711 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL COMMENT '更新表其他字段数据使用的唯一标示',
  `packname` varchar(255) DEFAULT NULL,
  `svn_version` varchar(255) DEFAULT NULL,
  `forder_name` varchar(255) DEFAULT NULL COMMENT '打包出来的文件夹名称一般命名为:soma-ios-1.1.3-agora-2015.11.17-11:26-44929-online',
  `exe_file_path` varchar(255) DEFAULT NULL COMMENT 'shell脚本所有在的路径',
  `store_root_path` varchar(255) DEFAULT NULL,
  `store_path` varchar(255) DEFAULT NULL COMMENT '编译结果存储路径',
  `versions_path` varchar(255) DEFAULT NULL COMMENT '临时存放ios的ipa包的路径。',
  `enterprise_path` varchar(255) DEFAULT NULL,
  `enterprise_name` varchar(255) DEFAULT NULL,
  `package_time` varchar(255) DEFAULT NULL COMMENT '每次打包的开始时间',
  `exone` varchar(255) DEFAULT NULL,
  `extwo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `global_value`
-- ----------------------------
DROP TABLE IF EXISTS `global_value`;
CREATE TABLE `global_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyid` int(11) NOT NULL,
  `keyname` varchar(255) DEFAULT NULL,
  `keyvalue` varchar(255) DEFAULT NULL,
  `keycomment` varchar(255) DEFAULT NULL,
  `keyex1` varchar(255) DEFAULT NULL,
  `keyex2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`keyid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `globalset`
-- ----------------------------
DROP TABLE IF EXISTS `globalset`;
CREATE TABLE `globalset` (
  `setid` int(8) NOT NULL AUTO_INCREMENT,
  `sdkinfo` varchar(255) DEFAULT NULL,
  `codepath` varchar(255) DEFAULT NULL,
  `android_packpath` varchar(255) DEFAULT NULL,
  `ios_packpath` varchar(255) DEFAULT NULL,
  `tomcat_path` varchar(255) DEFAULT NULL,
  `hostip` varchar(255) DEFAULT NULL,
  `svnusername` varchar(255) DEFAULT NULL,
  `svnpassword` varchar(255) DEFAULT NULL,
  `downipa_filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`setid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `globalset`
-- ----------------------------
BEGIN;
INSERT INTO `globalset` VALUES ('1', '/Users/xxx/xxx/sdk', '/Users/xxx/qatool', '/Users/xxx/Desktop/cocoversion/apps_android', '/Users/xxx/Desktop/ios_build/apps', '/Users/xxx/xxx/apache-tomcat-7.0.69', 'app.xxx.com', 'username', 'password', 'downipa');
COMMIT;

-- ----------------------------
--  Table structure for `ioslog`
-- ----------------------------
DROP TABLE IF EXISTS `ioslog`;
CREATE TABLE `ioslog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `packname` varchar(255) DEFAULT NULL,
  `main_version` varchar(255) DEFAULT NULL,
  `svn_version` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `store_root_path` varchar(255) DEFAULT NULL,
  `store_path` varchar(255) DEFAULT NULL,
  `svn_url` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `edition` varchar(255) DEFAULT NULL,
  `platform` varchar(255) DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `on_off` varchar(255) DEFAULT NULL,
  `nums` int(11) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `pack_time` datetime DEFAULT NULL,
  `isstore` varchar(255) DEFAULT NULL,
  `viewflag` int(11) DEFAULT NULL,
  `exone` varchar(255) DEFAULT NULL,
  `extwo` varchar(255) DEFAULT NULL,
  `exthree` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2237 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `productinfo`
-- ----------------------------
DROP TABLE IF EXISTS `productinfo`;
CREATE TABLE `productinfo` (
  `productid` int(8) NOT NULL AUTO_INCREMENT,
  `productname` varchar(255) NOT NULL,
  `exone` varchar(255) DEFAULT NULL,
  `extwo` varchar(255) DEFAULT NULL,
  `exthree` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `projectinfo`
-- ----------------------------
DROP TABLE IF EXISTS `projectinfo`;
CREATE TABLE `projectinfo` (
  `projectid` int(8) NOT NULL AUTO_INCREMENT,
  `projectname` varchar(255) NOT NULL,
  `productname` varchar(255) NOT NULL,
  `iosbundleid` varchar(255) NOT NULL,
  `onoff` varchar(255) DEFAULT NULL,
  `platform` varchar(255) DEFAULT NULL,
  `exe_before_context` longtext,
  `exe_on_context` longtext,
  `exe_after_context` longtext,
  `iosbuildtype` varchar(255) DEFAULT NULL,
  `proversionprofile` varchar(255) DEFAULT NULL,
  `iosapppath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`projectid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `svninfo`
-- ----------------------------
DROP TABLE IF EXISTS `svninfo`;
CREATE TABLE `svninfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `packname` varchar(255) NOT NULL,
  `main_version` varchar(255) DEFAULT NULL COMMENT '打包脚本以及包存放的根目录地址',
  `svn_url` varchar(255) DEFAULT NULL,
  `local_path` varchar(255) DEFAULT NULL COMMENT '本地源代码存放路径',
  `on_off` varchar(255) DEFAULT NULL COMMENT '线下0，线上1，预发2，线上+线下3，线上+预发4',
  `platform` varchar(255) DEFAULT NULL COMMENT '平台Android、ios',
  `busy_status` varchar(255) DEFAULT NULL COMMENT '忙碌busy，空闲free',
  `in_time` datetime DEFAULT NULL,
  `sort` varchar(10) DEFAULT NULL COMMENT '页面显示排序',
  `exone` varchar(255) DEFAULT NULL,
  `extwo` varchar(255) DEFAULT NULL,
  `exthree` varchar(255) DEFAULT NULL,
  `projectname` varchar(255) DEFAULT NULL,
  `productname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `userAge` int(11) DEFAULT NULL,
  `userAddress` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
