/*
 Navicat Premium Data Transfer

 Source Server         : mysqllocal
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : marubi_security

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 26/08/2021 09:17:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for m_auth
-- ----------------------------
DROP TABLE IF EXISTS `m_auth`;
CREATE TABLE `m_auth`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `menu_url_id` int(0) NOT NULL COMMENT 'm_sys_menu id',
  `group_id` int(0) NOT NULL COMMENT '权限组ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_auth
-- ----------------------------

-- ----------------------------
-- Table structure for m_auth_group
-- ----------------------------
DROP TABLE IF EXISTS `m_auth_group`;
CREATE TABLE `m_auth_group`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组名',
  `enable` tinyint(0) NOT NULL DEFAULT 0 COMMENT '0:已启用，1:未启用',
  `create_time` datetime(0) NOT NULL,
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_auth_group
-- ----------------------------

-- ----------------------------
-- Table structure for m_group_mapper
-- ----------------------------
DROP TABLE IF EXISTS `m_group_mapper`;
CREATE TABLE `m_group_mapper`  (
  `uid` int(0) NOT NULL COMMENT 'jk_backend_admin id',
  `group_id` int(0) NOT NULL COMMENT 'm_auth_group',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限组映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_group_mapper
-- ----------------------------


-- ----------------------------
-- Table structure for m_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `m_sys_menu`;
CREATE TABLE `m_sys_menu` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `view_url` varchar(255) DEFAULT NULL COMMENT '视图路径， 模块/操作( type=0/2)的请求路径时可为空',
                              `mapper_url` varchar(500) DEFAULT NULL COMMENT '映射视图的请求路径，选模块（type=0）时可不填',
                              `module_name` varchar(255) NOT NULL COMMENT '菜单名，模块名，操作名称',
                              `type` tinyint(4) DEFAULT NULL COMMENT '0：模块，1:二级菜单，2：操作,3:独立页面(弹框)',
                              `icon` varchar(255) DEFAULT NULL COMMENT '图标',
                              `parent_id` int(11) DEFAULT '0' COMMENT '父节点ID 0为根节点',
                              `del_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:未删除，1:已删除',
                              `enable_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:已启用，0:未启用',
                              `create_time` datetime NOT NULL,
                              `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
                              `operator` varchar(100) NOT NULL DEFAULT 'system' COMMENT '操作人',
                              `label_unq` varchar(100) NOT NULL COMMENT '唯一标识',
                              `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
                              `is_fixed` tinyint(4) DEFAULT '0' COMMENT '是否锁定，0:否、1:是',
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE KEY `uni_index` (`label_unq`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of m_sys_menu
-- ----------------------------
INSERT INTO `m_sys_menu` VALUES ('1', 'login', '/login', '登录页面', '3', null, '0', '0', '1', '2021-08-23 16:53:41', '2021-09-07 14:18:48', 'administrator', 'PAGE_LOGIN', '1', '0');
INSERT INTO `m_sys_menu` VALUES ('2', 'welcome', '/welcome', '欢迎页面', '1', null, '0', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:12:37', 'administrator', 'PAGE_WELCOME', '1', '0');
INSERT INTO `m_sys_menu` VALUES ('3', 'manufacturer/manufacturerEditOrAdd', '/manufacturerEditOrAdd', '编辑/新增生产商信息', '3', null, '16', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:13:18', 'administrator', 'IFRAME_MANUFACTURER_INFO', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('4', 'users/userAuth', '/userAuth', '管理员组授权', '3', null, '13', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:19:33', 'administrator', 'IFRAME_USER_AUTH', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('5', 'auth/authAdd', '/authAdd', '添加管理员组', '3', null, '20', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:19:35', 'administrator', 'IFRAME_AUTH_ADD', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('6', 'users/curUser', '/curUser', '修改个人资料', '3', null, '13', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:10:25', 'administrator', 'IFRAME_CURUSER', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('7', 'auth/authEdit', '/authEdit', '编辑管理员组信息', '3', null, '20', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:19:40', 'administrator', 'IFRAME_AUTH_EDIT', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('8', 'brand/test', '/t1', '测试', '3', null, '23', '1', '1', '2021-08-23 16:53:41', '2021-09-07 17:25:31', 'administrator', 'IFRAME_TEST1', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('9', 'index', '/index', '主页', '3', null, '0', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:15:44', 'administrator', 'PAGE_INDEX', '1', '0');
INSERT INTO `m_sys_menu` VALUES ('10', 'users/userUpdate', '/userUpdate', '编辑管理员信息', '3', null, '13', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:17:44', 'administrator', 'PAGE_USER_UPDATE', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('12', 'auth/authAccess', '/authAccess', '访问授权', '3', null, '20', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:18:07', 'administrator', 'IFRAME_AUTH_ACCESS', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('13', 'users/userList', '/userList', '管理员信息', '1', null, '23', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:25:02', 'system', 'PAGE_USER_LIST', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('14', 'goods/goodsList', '/goodsList', '产品列表', '1', null, '27', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:25:05', 'system', 'PAGE_GOODS_LIST', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('15', 'users/userAdd', '/userAdd', '添加管理员', '3', null, '13', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:30:31', 'administrator', 'IFRAME_USER_ADD', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('16', 'manufacturer/manufacturerList', '/manufacturerList', '生产商管理', '1', null, '27', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:27:33', 'system', 'PAGE_MANUFACTURER_LIST', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('18', 'users/userDetail', '/userDetail', '查看管理员信息', '3', null, '13', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:30:43', 'administrator', 'IFRAME_USER_DETAIL', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('19', 'brand/brandList', '/brandList', '品牌管理', '1', null, '27', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:28:14', 'system', 'PAGE_BRAND_LIST', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('20', 'auth/authList', '/authList', '权限管理', '1', null, '23', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:28:50', 'system', 'PAGE_AUTH_LIST', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('21', 'brand/brandEditOrAdd', '/brandEditOrAdd', '编辑/新增品牌信息', '3', null, '19', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:31:13', 'administrator', 'IFRAME_BRAND_INFO', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('22', 'menu/menuTree', '/menuTree', '菜单管理', '1', null, '23', '0', '1', '2021-08-23 16:53:41', '2021-09-07 17:29:27', 'system', 'PAGE_MENU_TREE', '0', '1');
INSERT INTO `m_sys_menu` VALUES ('23', null, null, '系统模块', '0', '&#xe614;', '0', '0', '1', '2021-08-23 17:14:59', '2021-09-07 17:09:19', 'system', 'MODULE_SYSTEM', '0', '1');
INSERT INTO `m_sys_menu` VALUES ('26', 'index', '/', '主页备选路径', '3', null, '23', '1', '0', '2021-08-23 16:53:41', '2021-09-07 17:31:32', 'administrator', 'PAGE_INDEX_BAC', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('27', null, null, '产品管理', '0', '&#xe653;', '0', '0', '1', '2021-08-24 10:46:14', '2021-09-07 17:09:07', 'system', 'MODULE_BUSINESS', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('28', 'configGroup/config', '/configPage', '配置管理', '1', null, '23', '0', '1', '2021-08-30 10:42:41', '2021-09-07 17:32:00', 'administrator', 'PAGE_CONFIG_MANAGEMENT', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('29', 'goods/goodsEditOrAdd', '/goodsEditOrAdd', '新增/编辑产品信息', '3', null, '14', '0', '1', '2021-08-30 10:54:23', '2021-09-07 17:32:39', 'administrator', 'IFRAME_GOODS_INFO', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('30', 'goods/goodsDetail', '/goodsDetail', '查看产品信息', '3', null, '14', '0', '1', '2021-08-31 17:11:40', '2021-09-07 17:32:54', 'administrator', 'IFRAME_GOODS_SHOW', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('31', 'goods/commentsList', '/commentsList', '查看产品评价', '3', null, '14', '0', '1', '2021-09-02 17:29:57', '2021-09-07 17:33:32', 'system', 'IFRAME_GOODS_COMMENTS', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('32', 'goods/commentsEditOrAdd', '/commentsEditOrAdd', '新增/编辑使用体验', '3', null, '31', '0', '1', '2021-09-02 18:29:04', '2021-09-07 17:33:58', 'system', 'IFRAME_COMMENTS_INFO', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('33', null, '/goods/goodsDetail', '查看产品详情', '2', null, '14', '0', '1', '2021-09-06 09:34:18', '2021-09-06 11:29:44', 'system', 'REQUEST_GOODS_DETAIL', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('34', 'menu/menuEditOrAdd', '/menuEditOrAdd', '添加/编辑菜单页面', '3', null, '22', '0', '1', '2021-09-06 12:23:57', '2021-09-06 12:23:59', 'system', 'IFRAME_MENU_INFO', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('35', '', '', '测试模块', '0', null, '0', '1', '1', '2021-09-07 15:39:25', '2021-09-07 17:06:14', 'administrator', 'MODOLE_TEST', '1', '0');
INSERT INTO `m_sys_menu` VALUES ('36', 'test/test', '/test', '测试页面', '1', null, '35', '1', '1', '2021-09-07 15:47:29', '2021-09-07 17:06:15', 'administrator', 'PAGE_TEST', '2', '0');
INSERT INTO `m_sys_menu` VALUES ('38', 'test/test', 'TEST', '测试弹窗', '3', null, '35', '1', '1', '2021-09-07 16:35:30', '2021-09-07 16:56:11', 'administrator', 'IFRAME_TEST', '1', '0');
INSERT INTO `m_sys_menu` VALUES ('39', '', '/re', '测试请求', '2', null, '35', '1', '1', '2021-09-07 16:44:05', '2021-09-07 17:06:16', 'administrator', 'REQUEST_TEST', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('40', 'TEST', 'TEST', '测试页面2', '1', null, '35', '1', '1', '2021-09-07 17:01:21', '2021-09-07 17:05:55', 'administrator', 'PAGE_TEST2', '0', '0');
INSERT INTO `m_sys_menu` VALUES ('41', '', '/sysMenu/saveMenuInfo', '添加/编辑菜单页面', '2', null, '34', '0', '1', '2021-09-07 17:45:43', '2021-09-07 17:45:43', 'administrator', 'REQUEST_SAVE_MENU_INFO', '0', '1');

SET FOREIGN_KEY_CHECKS = 1;
