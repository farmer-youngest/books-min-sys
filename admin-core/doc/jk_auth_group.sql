/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 127.0.0.1:3306
 Source Schema         : marubi-security

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 15/09/2021 22:24:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jk_auth_group
-- ----------------------------
DROP TABLE IF EXISTS `jk_auth_group`;
CREATE TABLE `jk_auth_group`  (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT,
  `title` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组名',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户组描述',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态：为1正常，为0禁用',
  `rules` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户组拥有的规则id, 多个规则\",\"隔开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员组明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jk_auth_group
-- ----------------------------
INSERT INTO `jk_auth_group` VALUES (1, '管理员', '系统管理员', 1, '1,2,3,4,5,6,7,8,9,10,12,13,14,15,16,18,19,20,21,22,23,26,27,32,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67');
INSERT INTO `jk_auth_group` VALUES (2, '春纪编辑', '编辑数据内容', 1, '1,2,3,4,5,6,7,8,9,10,12,13,14,15,16,18,19,20,21,22,23,26,27,32,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67');
INSERT INTO `jk_auth_group` VALUES (3, '丸美编辑', '丸美编辑', 1, '1,2,3,4,5,6,7,8,9,10,12,13,14,15,16,18,19,20,21,22,23,26,27,32,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67');

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `marubi-security`.`m_sys_menu`(`id`, `view_url`, `mapper_url`, `module_name`, `type`, `icon`, `parent_id`, `del_status`, `enable_status`, `create_time`, `update_time`, `operator`, `label_unq`, `sort`, `is_fixed`) VALUES (68, 'error/403', '/error/403', '无权限页面', 3, NULL, 0, 0, 1, '2021-09-15 22:30:33', '2021-09-15 22:35:22', 'system', 'ERROR_403', 0, 0);
