/*
 Navicat Premium Data Transfer

 Source Server         : DAOKO
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : itmp

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 24/02/2022 20:03:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` tinyint NOT NULL AUTO_INCREMENT,
  `role` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '教学管理主管');
INSERT INTO `role` VALUES (2, '专业教研室负责人');
INSERT INTO `role` VALUES (3, '实施工程师');
INSERT INTO `role` VALUES (4, '市场人员');
INSERT INTO `role` VALUES (5, '就业辅导专员');
INSERT INTO `role` VALUES (6, '学生管理专员');
INSERT INTO `role` VALUES (7, '学生');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` tinyint NOT NULL COMMENT '角色ID',
  `menu_id` int NOT NULL COMMENT '菜单ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 291);
INSERT INTO `role_menu` VALUES (2, 291);
INSERT INTO `role_menu` VALUES (3, 291);
INSERT INTO `role_menu` VALUES (4, 291);
INSERT INTO `role_menu` VALUES (5, 291);
INSERT INTO `role_menu` VALUES (6, 291);
INSERT INTO `role_menu` VALUES (7, 291);
INSERT INTO `role_menu` VALUES (1, 292);
INSERT INTO `role_menu` VALUES (2, 292);
INSERT INTO `role_menu` VALUES (3, 292);
INSERT INTO `role_menu` VALUES (4, 292);
INSERT INTO `role_menu` VALUES (5, 292);
INSERT INTO `role_menu` VALUES (6, 292);
INSERT INTO `role_menu` VALUES (7, 292);
INSERT INTO `role_menu` VALUES (3, 365);
INSERT INTO `role_menu` VALUES (3, 344);
INSERT INTO `role_menu` VALUES (3, 345);
INSERT INTO `role_menu` VALUES (3, 346);
INSERT INTO `role_menu` VALUES (3, 347);
INSERT INTO `role_menu` VALUES (3, 348);
INSERT INTO `role_menu` VALUES (3, 349);
INSERT INTO `role_menu` VALUES (3, 350);
INSERT INTO `role_menu` VALUES (3, 351);
INSERT INTO `role_menu` VALUES (3, 352);
INSERT INTO `role_menu` VALUES (6, 343);
INSERT INTO `role_menu` VALUES (6, 353);
INSERT INTO `role_menu` VALUES (6, 354);
INSERT INTO `role_menu` VALUES (1, 355);
INSERT INTO `role_menu` VALUES (2, 355);
INSERT INTO `role_menu` VALUES (3, 355);
INSERT INTO `role_menu` VALUES (4, 355);
INSERT INTO `role_menu` VALUES (5, 355);
INSERT INTO `role_menu` VALUES (6, 355);
INSERT INTO `role_menu` VALUES (7, 355);
INSERT INTO `role_menu` VALUES (7, 356);
INSERT INTO `role_menu` VALUES (1, 357);
INSERT INTO `role_menu` VALUES (2, 357);
INSERT INTO `role_menu` VALUES (1, 358);
INSERT INTO `role_menu` VALUES (1, 359);
INSERT INTO `role_menu` VALUES (1, 360);
INSERT INTO `role_menu` VALUES (1, 361);
INSERT INTO `role_menu` VALUES (2, 361);
INSERT INTO `role_menu` VALUES (2, 362);
INSERT INTO `role_menu` VALUES (2, 363);
INSERT INTO `role_menu` VALUES (2, 359);
INSERT INTO `role_menu` VALUES (2, 364);
INSERT INTO `role_menu` VALUES (2, 361);
INSERT INTO `role_menu` VALUES (1, 366);
INSERT INTO `role_menu` VALUES (2, 366);
INSERT INTO `role_menu` VALUES (3, 366);
INSERT INTO `role_menu` VALUES (4, 366);
INSERT INTO `role_menu` VALUES (5, 366);
INSERT INTO `role_menu` VALUES (6, 366);
INSERT INTO `role_menu` VALUES (7, 366);
INSERT INTO `role_menu` VALUES (1, 367);
INSERT INTO `role_menu` VALUES (2, 367);
INSERT INTO `role_menu` VALUES (3, 367);
INSERT INTO `role_menu` VALUES (4, 367);
INSERT INTO `role_menu` VALUES (5, 367);
INSERT INTO `role_menu` VALUES (6, 367);
INSERT INTO `role_menu` VALUES (7, 368);

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int NOT NULL DEFAULT 0 COMMENT '父ID',
  `title` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单图标',
  `href` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '链接',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '_self' COMMENT '链接打开方式',
  `sort` int NULL DEFAULT 0 COMMENT '菜单排序',
  `status` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(0:禁用,1:启用)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_at` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_at` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `delete_at` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `href`(`href`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 369 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES (291, 0, '常规管理', 'fa fa-address-book', '', '_self', 291, 1, 'e48521856c022fab7f320f3c8010a540', '2021-11-15 20:04:54', '2022-02-24 19:17:27', NULL);
INSERT INTO `system_menu` VALUES (343, 291, '学生请假列表', '', 'page/user-leave-records.html', '_self', 0, 1, NULL, '2021-11-25 17:20:54', '2021-11-25 17:49:14', NULL);
INSERT INTO `system_menu` VALUES (344, 291, '我的师资变更列表', '', 'page/my-modification-teacher-record.html', '_self', 0, 1, NULL, '2021-11-25 17:23:01', '2021-11-25 17:49:16', NULL);
INSERT INTO `system_menu` VALUES (345, 291, '我的课程变更列表', '', 'page/my-modification-course-records.html', '_self', 0, 1, NULL, '2021-11-25 17:25:30', '2021-11-25 17:49:20', NULL);
INSERT INTO `system_menu` VALUES (346, 291, '课程设计列表', '', 'page/curriculums.html', '_self', 0, 1, NULL, '2021-11-25 17:27:47', '2021-11-25 17:49:23', NULL);
INSERT INTO `system_menu` VALUES (347, 291, '班级分组列表', '', 'page/clbum-clbumid-groups.html', '_self', 0, 1, NULL, '2021-11-25 17:29:58', '2021-11-25 17:49:26', NULL);
INSERT INTO `system_menu` VALUES (348, 291, '班级签到', '', 'page/teacher_sign.html', '_self', 0, 1, NULL, '2021-11-25 17:38:47', '2021-11-25 17:49:28', NULL);
INSERT INTO `system_menu` VALUES (349, 291, '日报', '', 'page/day_report.html', '_self', 0, 1, NULL, '2021-11-25 17:41:44', '2021-11-25 17:49:31', NULL);
INSERT INTO `system_menu` VALUES (350, 291, '我的日报列表', '', 'page/my_day_report_list.html', '_self', 0, 1, NULL, '2021-11-25 17:42:09', '2021-11-25 17:49:34', NULL);
INSERT INTO `system_menu` VALUES (351, 291, '周报', '', 'page/week_report.html', '_self', 0, 1, NULL, '2021-11-25 17:42:31', '2021-11-25 17:49:36', NULL);
INSERT INTO `system_menu` VALUES (352, 291, '我的周报列表', '', 'page/my_week_report_list.html', '_self', 0, 1, NULL, '2021-11-25 17:42:54', '2021-11-25 17:49:40', NULL);
INSERT INTO `system_menu` VALUES (353, 291, '教室列表', '', 'page/classrooms.html', '_self', 0, 1, NULL, '2021-11-25 18:09:10', '2021-11-25 18:09:10', NULL);
INSERT INTO `system_menu` VALUES (354, 291, '寝室列表', '', 'page/dorm-records.html', '_self', 0, 1, NULL, '2021-11-25 18:09:37', '2021-11-25 18:09:37', NULL);
INSERT INTO `system_menu` VALUES (355, 291, '会议列表', '', 'page/mettings.html', '_self', 0, 1, NULL, '2021-11-25 18:33:15', '2021-11-25 18:33:15', NULL);
INSERT INTO `system_menu` VALUES (356, 291, '我的请假列表', '', 'page/my-leave-records.html', '_self', 0, 1, NULL, '2021-11-25 18:38:47', '2021-11-25 18:38:52', NULL);
INSERT INTO `system_menu` VALUES (357, 291, '班级列表', '', 'page/clbum_list.html', '_self', 0, 1, NULL, '2021-11-25 18:54:31', '2021-11-25 18:54:31', NULL);
INSERT INTO `system_menu` VALUES (358, 291, '学生列表', '', 'page/studentList.html', '_self', 0, 1, NULL, '2021-11-25 18:59:13', '2021-11-25 19:01:45', NULL);
INSERT INTO `system_menu` VALUES (359, 291, '老师列表', '', 'page/teacher.html', '_self', 0, 1, NULL, '2021-11-25 19:09:56', '2021-11-25 19:09:56', NULL);
INSERT INTO `system_menu` VALUES (360, 291, '考试列表', '', 'page/exam_list.html', '_self', 0, 1, NULL, '2021-11-25 19:10:41', '2021-11-25 19:10:41', NULL);
INSERT INTO `system_menu` VALUES (361, 291, '周报列表', '', 'page/week_report_list.html', '_self', 0, 1, NULL, '2021-11-25 19:15:25', '2021-11-25 19:15:25', NULL);
INSERT INTO `system_menu` VALUES (362, 291, '师资变更请求列表', '', 'page/teacher_change_record.html', '_self', 0, 1, NULL, '2021-11-25 19:19:31', '2021-11-25 19:19:31', NULL);
INSERT INTO `system_menu` VALUES (363, 291, '课程变更请求列表', '', 'page/course_change_record.html', '_self', 0, 1, NULL, '2021-11-25 19:21:28', '2021-11-25 19:21:28', NULL);
INSERT INTO `system_menu` VALUES (364, 291, '日报列表', '', 'page/day_report_list.html', '_self', 0, 1, NULL, '2021-11-25 19:24:57', '2021-11-25 19:24:57', NULL);
INSERT INTO `system_menu` VALUES (365, 291, '请假列表', '', 'page/user-leave-records-teacher.html', '_self', 0, 1, NULL, '2021-11-25 20:14:39', '2021-11-25 20:15:30', NULL);
INSERT INTO `system_menu` VALUES (366, 291, '项目资源文件列表', '', 'page/project-resource.html', '_self', 0, 1, NULL, '2021-11-28 12:41:49', '2021-11-28 12:41:49', NULL);
INSERT INTO `system_menu` VALUES (367, 291, '学生体会表列表', '', 'page/student_experience.html', '_self', 0, 1, NULL, '2021-11-29 10:31:22', '2021-11-29 10:31:22', NULL);
INSERT INTO `system_menu` VALUES (368, 291, '学生体会表', '', 'page/student-file.html', '_self', 0, 1, NULL, '2021-11-29 10:37:01', '2021-11-29 10:37:17', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_name` varchar(126) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系方式',
  `role` tinyint NOT NULL COMMENT '角色',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0c1ae3f24a4adbb4e6cd2309c7c5dbed', '杨杰', '13233038893', 7, '2021-11-26 15:55:43', '2021-11-26 15:55:43');
INSERT INTO `user` VALUES ('1ed009a62ccf193bce340edd2d752801', '蔡雅丽', '18888888888', 3, '2021-11-23 22:20:48', '2021-11-23 22:20:48');
INSERT INTO `user` VALUES ('2ac391f011b5fcfb7168fff922fc8fbf', '刘佳伟', '13233038891', 7, '2021-11-26 15:53:41', '2021-11-26 15:53:41');
INSERT INTO `user` VALUES ('2b1c875105d7b29d52890eabd98d9067', '李维', '15555555555', 3, '2021-11-23 21:52:17', '2021-11-23 21:52:17');
INSERT INTO `user` VALUES ('2f1ef991d214c26c988459b2e895d672', '实施工程师6', '13526581456', 3, '2021-11-25 20:41:38', '2021-11-25 20:41:38');
INSERT INTO `user` VALUES ('3017eaa4fbde09996ddcaacce1c5b727', '魏海峰', '13233038888', 7, '2021-11-26 15:42:32', '2021-11-26 15:42:32');
INSERT INTO `user` VALUES ('42a9ec2533bf4ed2a5b5849000c65772', '教学管理主管', '17735014747', 1, '2021-11-15 19:35:48', '2021-11-15 19:37:21');
INSERT INTO `user` VALUES ('5040f0cb1f8f882978bfa157cad085f7', '邢志强', '13233038889', 7, '2021-11-26 15:49:04', '2021-11-26 15:49:04');
INSERT INTO `user` VALUES ('5ec4610a8c79470394459f1140305cbb', '教研室2', '17735017153', 2, '2021-11-15 19:38:40', '2021-11-15 19:38:40');
INSERT INTO `user` VALUES ('68c625f028ce448985b40624e2dc5d99', '胡学良', '19834422405', 7, '2021-11-15 19:34:03', '2021-11-15 19:34:03');
INSERT INTO `user` VALUES ('6e7382d106134bb9be6127a915354b2f', '教研室1', '17735015447', 2, '2021-11-15 19:37:14', '2021-11-15 19:37:23');
INSERT INTO `user` VALUES ('84f30db8bc3485ba78ed5bdda53d9597', '许智达', '13233038890', 7, '2021-11-26 15:50:17', '2021-11-26 15:50:17');
INSERT INTO `user` VALUES ('90c8fcee7c314519ac565569bd2d8390', '实施工程师2', '13324029981', 3, '2021-11-15 19:43:07', '2021-11-15 19:43:07');
INSERT INTO `user` VALUES ('a86e48f52449434296532a211bdee0db', '学生管理专员', '13324029937', 6, '2021-11-15 19:44:31', '2021-11-15 19:46:38');
INSERT INTO `user` VALUES ('af4568cf6658580721b8be449d4d5841', '孙小琛', '13233043636', 7, '2021-11-25 11:13:10', '2021-11-25 11:13:10');
INSERT INTO `user` VALUES ('b967e61decb2881d590b1b5c973760b3', '实施工程师4', '13754999999', 3, '2021-11-23 21:40:38', '2021-11-23 21:40:38');
INSERT INTO `user` VALUES ('be054943ac934196acc4727264da6df1', '实施工程师1', '17836135210', 3, '2021-11-15 19:40:07', '2021-11-15 19:43:11');
INSERT INTO `user` VALUES ('cd9f921c62db4a1e8996bbd46d639111', '实施工程师3', '13336588490', 3, '2021-11-23 16:32:25', '2021-11-23 16:32:25');
INSERT INTO `user` VALUES ('f39387a6585ca65c783c2aa567c3f7a9', '李斌', '15333619534', 7, '2021-11-17 16:22:07', '2021-11-17 16:22:10');
INSERT INTO `user` VALUES ('f95c83ebce9c32f510c957a4c1f697e5', '乔帅', '13233038892', 7, '2021-11-26 15:55:08', '2021-11-26 15:55:08');

-- ----------------------------
-- Table structure for user_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth`  (
  `ua_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `account` varchar(126) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `status` tinyint NOT NULL COMMENT '账号状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`ua_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_auth
-- ----------------------------
INSERT INTO `user_auth` VALUES ('160c4ff23eda4be38ac5ab8eba26ec19', 'a86e48f52449434296532a211bdee0db', '13324029937', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-15 19:45:13', '2021-11-15 19:45:13');
INSERT INTO `user_auth` VALUES ('17e6d6edce584b47a0105feb4d7372cf', 'be054943ac934196acc4727264da6df1', '17836135210', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-15 19:40:52', '2021-11-15 19:40:52');
INSERT INTO `user_auth` VALUES ('1ba0395b88fb9b1b0ac85049b7802040', '0c1ae3f24a4adbb4e6cd2309c7c5dbed', '13233038893', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-26 15:55:44', '2021-11-26 15:55:44');
INSERT INTO `user_auth` VALUES ('1bffc89dcfd4bf9a4595ea53fe6a536c', 'f95c83ebce9c32f510c957a4c1f697e5', '13233038892', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-26 15:55:08', '2021-11-26 15:55:08');
INSERT INTO `user_auth` VALUES ('245efeb5632aac62e90ea5e39f34897d', '5040f0cb1f8f882978bfa157cad085f7', '13233038889', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-26 15:49:04', '2021-11-26 15:49:04');
INSERT INTO `user_auth` VALUES ('4fd81ae648d58b022dd431b8ec51829b', '84f30db8bc3485ba78ed5bdda53d9597', '13233038890', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-26 15:50:17', '2021-11-26 15:50:17');
INSERT INTO `user_auth` VALUES ('512eb5d79a2ab3b0949159ef35255941', '3017eaa4fbde09996ddcaacce1c5b727', '13233038888', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-26 15:42:32', '2021-11-26 15:42:32');
INSERT INTO `user_auth` VALUES ('51f792304f6b4ead885998b11eab76e0', '90c8fcee7c314519ac565569bd2d8390', '13324029981', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-15 19:43:51', '2021-11-15 19:43:51');
INSERT INTO `user_auth` VALUES ('560b79948063452eab33d0e7152ff419', '5ec4610a8c79470394459f1140305cbb', '17735017153', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-15 19:39:17', '2021-11-15 19:39:17');
INSERT INTO `user_auth` VALUES ('7af967c12758a722cb2e110236400e95', '158c3d5bb613a4cd7e148487a7ad89ee', '13233038891', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-26 15:51:50', '2021-11-26 15:51:50');
INSERT INTO `user_auth` VALUES ('87a173498132ae66305e1ca8cd7e2817', '2ac391f011b5fcfb7168fff922fc8fbf', '13233038891', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-26 15:53:41', '2021-11-26 15:53:41');
INSERT INTO `user_auth` VALUES ('bcb74dc24bf04a0781aade8a782db89a', 'cd9f921c62db4a1e8996bbd46d639111', '13336588490', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-23 16:33:48', '2021-11-23 16:33:59');
INSERT INTO `user_auth` VALUES ('d620aad95f45489290517ba145803192', '42a9ec2533bf4ed2a5b5849000c65772', '17735014747', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-15 19:36:44', '2021-11-15 19:36:44');
INSERT INTO `user_auth` VALUES ('dc13350946dc4bff8e5ad4a968db4af4', '68c625f028ce448985b40624e2dc5d99', '19834422405', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-15 19:34:58', '2021-11-15 19:34:58');
INSERT INTO `user_auth` VALUES ('e8f5d7b01dd846c9b546852c2bcbaaf3', '6e7382d106134bb9be6127a915354b2f', '17735015447', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-15 19:38:06', '2021-11-15 19:38:06');
INSERT INTO `user_auth` VALUES ('ed8220903cab8a5c4dbd6d236c9b3572', 'f39387a6585ca65c783c2aa567c3f7a9', '15333619534', '96e79218965eb72c92a549dd5a330112', 1, '2021-11-17 16:23:55', '2021-11-17 16:24:52');

SET FOREIGN_KEY_CHECKS = 1;
