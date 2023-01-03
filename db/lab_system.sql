/*
 Navicat Premium Data Transfer

 Source Server         : yr_db
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 124.220.67.187:3306
 Source Schema         : lab_system

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 30/12/2022 17:35:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 420203107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (420203101, '计算机组成原理', '        计算机组成原理课程是计算机类专业主要的一门重要主干课程。设置这一课程的目的是，使学生了解和掌握计算机系统中硬件部分的组成原理， 掌握计算机的基本工作原理，了解计算机各主要部件的硬件结构，相互联系和作用，从面对整个计算机系统从硬件角度有完整的了解，为学习专业后继课程打下基础。\n        计算机组成原理课程主要介绍运用数字电路技术实现计算机部件的原理，以及计算机系统的工作原理。本课程的一个主要特点是综合性强，需要运用多学科的知识，各章之间相互联系又相互独立。', 1);
INSERT INTO `course` VALUES (420203102, '计算机网络', '这个是计算机网络', 1);
INSERT INTO `course` VALUES (420203103, '物理实验', '这个是物理实验', 2);
INSERT INTO `course` VALUES (420203104, '数据结构', '这个是数据结构', 3);
INSERT INTO `course` VALUES (420203105, '高级程序语言设计', '这个是高级程序语言设计', 1);
INSERT INTO `course` VALUES (420203106, '离散数学', '这个是离散数学', 1);
INSERT INTO `course` VALUES (420203107, 'test', 'test', 2);

-- ----------------------------
-- Table structure for guidebook
-- ----------------------------
DROP TABLE IF EXISTS `guidebook`;
CREATE TABLE `guidebook`  (
  `g_id` int NOT NULL AUTO_INCREMENT,
  `l_id` int NOT NULL,
  `t_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` int NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`g_id`) USING BTREE,
  INDEX `l_id`(`l_id` ASC) USING BTREE,
  INDEX `t_id`(`t_id` ASC) USING BTREE,
  CONSTRAINT `guidebook_ibfk_1` FOREIGN KEY (`l_id`) REFERENCES `laboratory` (`l_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `guidebook_ibfk_2` FOREIGN KEY (`t_id`) REFERENCES `teacher` (`t_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guidebook
-- ----------------------------
INSERT INTO `guidebook` VALUES (1, 1, '12345', 1, '指导书1.pdf', '1.pdf');
INSERT INTO `guidebook` VALUES (2, 2, '12345', 1, '指导书2.pdf', '2.pdf');
INSERT INTO `guidebook` VALUES (3, 3, '12345', 1, '指导书3.pdf', '3.pdf');
INSERT INTO `guidebook` VALUES (4, 1, '12345', 1, '指导书4.pdf', '4.pdf');
INSERT INTO `guidebook` VALUES (6, 2, '12345', 1, '指导书5.pdf', '5.pdf');
INSERT INTO `guidebook` VALUES (7, 3, '12345', 1, '指导书6.pdf', '6.pdf');
INSERT INTO `guidebook` VALUES (8, 4, '12345', 1, '指导书7.pdf', '7.pdf');
INSERT INTO `guidebook` VALUES (9, 1, '12345', 1, 'SE.PPT.1.pdf', '1672392806979.pdf');

-- ----------------------------
-- Table structure for laboratory
-- ----------------------------
DROP TABLE IF EXISTS `laboratory`;
CREATE TABLE `laboratory`  (
  `l_id` int NOT NULL AUTO_INCREMENT,
  `c_id` int NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `start_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `proportion` int NULL DEFAULT NULL,
  PRIMARY KEY (`l_id`) USING BTREE,
  INDEX `c_id`(`c_id` ASC) USING BTREE,
  CONSTRAINT `laboratory_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `course` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of laboratory
-- ----------------------------
INSERT INTO `laboratory` VALUES (1, 420203101, '门电路逻辑功能实验', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是门电路逻辑功能实验', NULL);
INSERT INTO `laboratory` VALUES (2, 420203101, '译码器和数码显示管实验', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是译码器和数码显示管实验', NULL);
INSERT INTO `laboratory` VALUES (3, 420203101, '数据选择器实验', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是数据选择器实验', NULL);
INSERT INTO `laboratory` VALUES (4, 420203101, '触发器实验', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是触发器实验', NULL);
INSERT INTO `laboratory` VALUES (5, 420203102, '帧中继配置实验', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是帧中继配置实验', NULL);
INSERT INTO `laboratory` VALUES (6, 420203102, 'ACL访问控制列表实验', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是ACL访问控制列表实验', NULL);
INSERT INTO `laboratory` VALUES (7, 420203102, 'OSPF动态路由实验', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是OSPF动态路由实验', NULL);
INSERT INTO `laboratory` VALUES (8, 420203102, 'VLAN配置实验项目', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是VLAN配置实验项目', NULL);
INSERT INTO `laboratory` VALUES (9, 420203102, '以太组网实验项目', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是以太组网实验项目', NULL);
INSERT INTO `laboratory` VALUES (10, 420203104, 'N皇后问题', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是N皇后问题', NULL);
INSERT INTO `laboratory` VALUES (11, 420203104, '勇闯迷宫游戏', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是勇闯迷宫游戏', NULL);
INSERT INTO `laboratory` VALUES (12, 420203104, '考试报名系统', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是考试报名系统', NULL);
INSERT INTO `laboratory` VALUES (13, 420203104, '八种排序算法的比较', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是八种排序算法的比较', NULL);
INSERT INTO `laboratory` VALUES (14, 420203105, '汉诺塔模拟', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是汉诺塔模拟', NULL);
INSERT INTO `laboratory` VALUES (15, 420203105, '扫雷游戏', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是扫雷游戏', NULL);
INSERT INTO `laboratory` VALUES (16, 420203105, '合成十游戏', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是合成十游戏', NULL);
INSERT INTO `laboratory` VALUES (17, 420203105, '五子连珠游戏', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是五子连珠游戏', NULL);
INSERT INTO `laboratory` VALUES (18, 420203106, '命题逻辑联接词', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是命题逻辑联接词', NULL);
INSERT INTO `laboratory` VALUES (19, 420203106, '最优二元树', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是最优二元树', NULL);
INSERT INTO `laboratory` VALUES (20, 420203106, '求关系的自反闭包', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是求关系的自反闭包', NULL);
INSERT INTO `laboratory` VALUES (21, 420203106, '最小生成树', '2022-09-10 00:00:00', '2023-01-10 00:00:00', '这个是最小生成树', NULL);
INSERT INTO `laboratory` VALUES (22, 420203104, 'test', '2022-12-23 00:00:00', '2023-01-07 00:00:00', '描述', 0);
INSERT INTO `laboratory` VALUES (23, 420203104, 'test2', '2022-12-30 14:30:29', '2023-01-07 00:00:00', '212313', 0);
INSERT INTO `laboratory` VALUES (24, 420203104, 'test3', '2022-12-30 14:32:20', '2023-01-07 00:00:00', '1231231', 0);
INSERT INTO `laboratory` VALUES (27, 420203104, '12131', '2022-12-30 15:24:11', '2022-12-31 00:00:00', '12312313', 0);
INSERT INTO `laboratory` VALUES (28, 420203101, '综合实验', '2022-12-30 15:26:41', '2023-01-07 00:00:00', '综合实验', 0);
INSERT INTO `laboratory` VALUES (31, 420203101, '实验', '2022-12-30 15:44:23', '2022-12-30 15:44:25', 'test', 0);

-- ----------------------------
-- Table structure for mark
-- ----------------------------
DROP TABLE IF EXISTS `mark`;
CREATE TABLE `mark`  (
  `r_id` int NOT NULL,
  `t_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `score` int NULL DEFAULT NULL,
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`r_id`, `t_id`) USING BTREE,
  INDEX `t_id`(`t_id` ASC) USING BTREE,
  CONSTRAINT `mark_ibfk_1` FOREIGN KEY (`r_id`) REFERENCES `report` (`r_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mark_ibfk_2` FOREIGN KEY (`t_id`) REFERENCES `teacher` (`t_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mark
-- ----------------------------
INSERT INTO `mark` VALUES (1, '12345', 98, '很好');
INSERT INTO `mark` VALUES (3, '20002', 96, '逻辑再清晰些');
INSERT INTO `mark` VALUES (6, '20002', 96, '逻辑再清晰些');
INSERT INTO `mark` VALUES (8, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (11, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (12, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (16, '20002', 98, '结论理解不够深刻');
INSERT INTO `mark` VALUES (17, '20002', 92, '时间复杂度太高');
INSERT INTO `mark` VALUES (19, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (20, '20002', 96, '逻辑再清晰些');
INSERT INTO `mark` VALUES (21, '20002', 96, '逻辑再清晰些');
INSERT INTO `mark` VALUES (22, '20002', 96, '逻辑再清晰些');
INSERT INTO `mark` VALUES (23, '20002', 100, '非常好');
INSERT INTO `mark` VALUES (24, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (25, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (26, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (27, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (28, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (29, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (30, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (31, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (32, '20002', 88, '有个bug 没通过测试');
INSERT INTO `mark` VALUES (33, '20002', 88, '有个bug 没通过测试');

-- ----------------------------
-- Table structure for operates
-- ----------------------------
DROP TABLE IF EXISTS `operates`;
CREATE TABLE `operates`  (
  `l_id` int NOT NULL,
  `s_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `attend_score` int NULL DEFAULT NULL,
  `feedback` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`l_id`, `s_id`) USING BTREE,
  INDEX `s_id`(`s_id` ASC) USING BTREE,
  CONSTRAINT `operates_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `operates_ibfk_2` FOREIGN KEY (`l_id`) REFERENCES `laboratory` (`l_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operates
-- ----------------------------
INSERT INTO `operates` VALUES (1, '1952168', 1, '实验内容有难度，最后没有得到预期结果');
INSERT INTO `operates` VALUES (1, '2052210', 1, '这个实验做完了  实验指导书好像有错误');
INSERT INTO `operates` VALUES (1, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (2, '1952168', 1, '实验内容有难度，最后没有得到预期结果');
INSERT INTO `operates` VALUES (2, '2052210', 1, '这个实验做完了  实验指导书好像有错误');
INSERT INTO `operates` VALUES (2, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (3, '1952168', 1, '实验内容有难度，最后没有得到预期结果');
INSERT INTO `operates` VALUES (3, '2052210', 1, '这个实验做完了  实验指导书好像有错误');
INSERT INTO `operates` VALUES (3, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (4, '1952168', 0, '实验内容有难度，最后没有得到预期结果');
INSERT INTO `operates` VALUES (4, '2052210', 1, '这个实验做完了  实验指导书好像有错误');
INSERT INTO `operates` VALUES (4, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (5, '2052210', 1, '这个实验做完了  实验指导书好像有错误');
INSERT INTO `operates` VALUES (5, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (6, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (10, '1952168', 1, '实验内容有难度，最后没有得到预期结果');
INSERT INTO `operates` VALUES (10, '2052210', 1, '这个实验做完了  实验指导书好像有错误');
INSERT INTO `operates` VALUES (10, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (11, '1952168', 1, '实验内容有难度，最后没有得到预期结果');
INSERT INTO `operates` VALUES (11, '2052210', 1, '这个实验做完了  实验指导书好像有错误');
INSERT INTO `operates` VALUES (11, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (12, '1952168', 1, '实验内容有难度，最后没有得到预期结果');
INSERT INTO `operates` VALUES (12, '2052210', 1, '这个实验做完了  实验指导书好像有错误');
INSERT INTO `operates` VALUES (12, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (13, '1952168', 1, '实验内容有难度，最后没有得到预期结果');
INSERT INTO `operates` VALUES (13, '2052210', 1, '这个实验做完了  实验指导书好像有错误');
INSERT INTO `operates` VALUES (13, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (14, '2052210', 0, NULL);
INSERT INTO `operates` VALUES (18, '2052210', 0, NULL);
INSERT INTO `operates` VALUES (18, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (19, '1952168', 1, '实验内容有难度，最后没有得到预期结果');
INSERT INTO `operates` VALUES (19, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (20, '2053677', 1, '实验指导书很清晰，没遇到什么问题');
INSERT INTO `operates` VALUES (21, '2053677', 1, '实验指导书很清晰，没遇到什么问题');

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `r_id` int NOT NULL AUTO_INCREMENT,
  `s_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `l_id` int NOT NULL,
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` int NULL DEFAULT NULL,
  `submit_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`r_id`) USING BTREE,
  INDEX `s_id`(`s_id` ASC) USING BTREE,
  INDEX `l_id`(`l_id` ASC) USING BTREE,
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `report_ibfk_2` FOREIGN KEY (`l_id`) REFERENCES `laboratory` (`l_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES (1, '2053677', 1, '门电路实验报告', 2, NULL);
INSERT INTO `report` VALUES (2, '2053677', 5, '帧中继配置实验报告', 0, NULL);
INSERT INTO `report` VALUES (3, '2053677', 10, 'N皇后实验报告', 0, NULL);
INSERT INTO `report` VALUES (4, '2053677', 14, '汉诺塔实验报告', 0, NULL);
INSERT INTO `report` VALUES (5, '2053677', 18, '命题逻辑实验报告', 0, NULL);
INSERT INTO `report` VALUES (6, '1952168', 1, '门电路实验报告', 0, NULL);
INSERT INTO `report` VALUES (7, '1952168', 5, '帧中继配置实验报告', 0, NULL);
INSERT INTO `report` VALUES (8, '1952168', 10, 'N皇后实验报告', 0, NULL);
INSERT INTO `report` VALUES (9, '1952168', 14, '汉诺塔实验报告', 0, NULL);
INSERT INTO `report` VALUES (10, '1952168', 18, '命题逻辑实验报告', 0, NULL);
INSERT INTO `report` VALUES (11, '2052210', 1, '门电路实验报告', 0, NULL);
INSERT INTO `report` VALUES (12, '2052210', 5, '帧中继配置实验报告', 0, NULL);
INSERT INTO `report` VALUES (13, '2052210', 10, 'N皇后实验报告', 0, NULL);
INSERT INTO `report` VALUES (14, '2052210', 14, '汉诺塔实验报告', 0, NULL);
INSERT INTO `report` VALUES (15, '2052210', 18, '命题逻辑实验报告', 0, NULL);
INSERT INTO `report` VALUES (16, '2053677', 2, '译码器和数码显示管实验', 2, NULL);
INSERT INTO `report` VALUES (17, '2053677', 3, '数据选择器实验', 2, NULL);
INSERT INTO `report` VALUES (18, '2053677', 4, '触发器实验', 0, NULL);
INSERT INTO `report` VALUES (19, '2053677', 21, '最优二元树实验报告', 0, NULL);
INSERT INTO `report` VALUES (20, '1952168', 2, '译码器和数码显示管实验', 0, NULL);
INSERT INTO `report` VALUES (21, '1952168', 3, '数据选择器实验', 0, NULL);
INSERT INTO `report` VALUES (22, '1952168', 4, '触发器实验', 0, NULL);
INSERT INTO `report` VALUES (23, '2053677', 11, 'N皇后实验报告', 0, NULL);
INSERT INTO `report` VALUES (24, '2053677', 12, 'N皇后实验报告', 0, NULL);
INSERT INTO `report` VALUES (25, '2053677', 13, 'N皇后实验报告', 0, NULL);
INSERT INTO `report` VALUES (26, '1952168', 11, 'N皇后实验报告', 0, NULL);
INSERT INTO `report` VALUES (27, '1952168', 12, 'N皇后实验报告', 0, NULL);
INSERT INTO `report` VALUES (28, '1952168', 13, 'N皇后实验报告', 0, NULL);
INSERT INTO `report` VALUES (29, '2052210', 2, '译码器和数码显示管实验', 0, NULL);
INSERT INTO `report` VALUES (30, '2052210', 3, '数据选择器实验', 0, NULL);
INSERT INTO `report` VALUES (31, '2052210', 4, '触发器实验', 0, NULL);
INSERT INTO `report` VALUES (32, '2052210', 11, 'N皇后实验报告', 0, NULL);
INSERT INTO `report` VALUES (33, '2052210', 12, 'N皇后实验报告', 0, NULL);
INSERT INTO `report` VALUES (34, '2053677', 19, '最小生成树实验报告', 0, NULL);
INSERT INTO `report` VALUES (35, '2053677', 20, '自反闭包实验报告', 0, NULL);
INSERT INTO `report` VALUES (39, '1952168', 19, '最小生成树实验报告', 0, NULL);
INSERT INTO `report` VALUES (40, '2052210', 13, 'N皇后实验报告', 0, NULL);

-- ----------------------------
-- Table structure for report_form
-- ----------------------------
DROP TABLE IF EXISTS `report_form`;
CREATE TABLE `report_form`  (
  `rf_id` int NOT NULL AUTO_INCREMENT,
  `rt_id` int NULL DEFAULT NULL,
  `r_id` int NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`rf_id`) USING BTREE,
  INDEX `rt_id`(`rt_id` ASC) USING BTREE,
  INDEX `r_id`(`r_id` ASC) USING BTREE,
  CONSTRAINT `report_form_ibfk_1` FOREIGN KEY (`rt_id`) REFERENCES `report_template` (`rt_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `report_form_ibfk_2` FOREIGN KEY (`r_id`) REFERENCES `report` (`r_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report_form
-- ----------------------------
INSERT INTO `report_form` VALUES (1, 7, 16, 'test2');
INSERT INTO `report_form` VALUES (2, 8, 16, 'test2');
INSERT INTO `report_form` VALUES (3, 9, 16, 'test2');
INSERT INTO `report_form` VALUES (4, 10, 16, '');
INSERT INTO `report_form` VALUES (5, 11, 16, 'test3');
INSERT INTO `report_form` VALUES (6, 12, 16, 'test3');
INSERT INTO `report_form` VALUES (7, 13, 17, 'test3');
INSERT INTO `report_form` VALUES (8, 14, 17, 'test3');
INSERT INTO `report_form` VALUES (9, 15, 17, 'test3');
INSERT INTO `report_form` VALUES (10, 16, 17, '');
INSERT INTO `report_form` VALUES (11, 17, 17, 'test3');
INSERT INTO `report_form` VALUES (12, 18, 17, 'test3');
INSERT INTO `report_form` VALUES (13, 19, 17, '');
INSERT INTO `report_form` VALUES (14, 20, 17, 'test3');
INSERT INTO `report_form` VALUES (15, 21, 17, '');
INSERT INTO `report_form` VALUES (16, 22, 17, 'test3');
INSERT INTO `report_form` VALUES (17, 2, 1, '门电路实验目的');
INSERT INTO `report_form` VALUES (18, 3, 1, '门电路实验背景');
INSERT INTO `report_form` VALUES (19, 4, 1, '门电路实验步骤');
INSERT INTO `report_form` VALUES (20, 5, 1, '');
INSERT INTO `report_form` VALUES (21, 6, 1, '门电路实验心得');
INSERT INTO `report_form` VALUES (27, 13, 30, 'test5');
INSERT INTO `report_form` VALUES (28, 14, 30, 'test5');
INSERT INTO `report_form` VALUES (29, 15, 30, 'test5');
INSERT INTO `report_form` VALUES (30, 16, 30, '');
INSERT INTO `report_form` VALUES (31, 17, 30, 'test5');
INSERT INTO `report_form` VALUES (32, 18, 30, 'test5');
INSERT INTO `report_form` VALUES (33, 19, 30, '');
INSERT INTO `report_form` VALUES (34, 20, 30, 'test5');
INSERT INTO `report_form` VALUES (35, 21, 30, '');
INSERT INTO `report_form` VALUES (36, 22, 30, 'test5');

-- ----------------------------
-- Table structure for report_image
-- ----------------------------
DROP TABLE IF EXISTS `report_image`;
CREATE TABLE `report_image`  (
  `rf_id` int NOT NULL AUTO_INCREMENT,
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`path`, `rf_id`) USING BTREE,
  INDEX `rf_id`(`rf_id` ASC) USING BTREE,
  CONSTRAINT `report_image_ibfk_1` FOREIGN KEY (`rf_id`) REFERENCES `report_form` (`rf_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report_image
-- ----------------------------
INSERT INTO `report_image` VALUES (4, '1670421438276.png', '0.png');
INSERT INTO `report_image` VALUES (10, '1670421438277.png', '0.png');
INSERT INTO `report_image` VALUES (13, '1670421438278.png', '0.png');
INSERT INTO `report_image` VALUES (15, '1670421438285.png', '0.png');
INSERT INTO `report_image` VALUES (20, '1670421438287.png', '0.png');
INSERT INTO `report_image` VALUES (35, '16704214382897.png', '0.png');
INSERT INTO `report_image` VALUES (35, '16704214382898.png', '1.png');
INSERT INTO `report_image` VALUES (30, '1670421438293.png', '0.png');
INSERT INTO `report_image` VALUES (33, '1670421438295.png', '0.png');
INSERT INTO `report_image` VALUES (33, '1670421438296.png', '1.png');

-- ----------------------------
-- Table structure for report_template
-- ----------------------------
DROP TABLE IF EXISTS `report_template`;
CREATE TABLE `report_template`  (
  `rt_id` int NOT NULL AUTO_INCREMENT,
  `l_id` int NULL DEFAULT NULL,
  `t_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order` int NULL DEFAULT NULL,
  `placeholder` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `required` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rt_id`) USING BTREE,
  INDEX `l_id`(`l_id` ASC) USING BTREE,
  INDEX `t_id`(`t_id` ASC) USING BTREE,
  CONSTRAINT `report_template_ibfk_1` FOREIGN KEY (`l_id`) REFERENCES `laboratory` (`l_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `report_template_ibfk_2` FOREIGN KEY (`t_id`) REFERENCES `teacher` (`t_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report_template
-- ----------------------------
INSERT INTO `report_template` VALUES (2, 1, '00000', '实验目的', 0, '', 'false', 'text');
INSERT INTO `report_template` VALUES (3, 1, '00000', '实验背景', 1, '', 'false', 'text');
INSERT INTO `report_template` VALUES (4, 1, '00000', '实验步骤', 2, '', 'false', 'text');
INSERT INTO `report_template` VALUES (5, 1, '00000', '实验步骤图片', 3, '', 'false', 'image');
INSERT INTO `report_template` VALUES (6, 1, '00000', '实验心得', 4, '', 'false', 'text');
INSERT INTO `report_template` VALUES (7, 2, '20045', '实验目的', 0, '', 'true', 'text');
INSERT INTO `report_template` VALUES (8, 2, '20045', '实验背景', 1, '', 'true', 'text');
INSERT INTO `report_template` VALUES (9, 2, '20045', '实验步骤', 2, '', 'true', 'text');
INSERT INTO `report_template` VALUES (10, 2, '20045', '实验步骤图', 3, '', 'true', 'image');
INSERT INTO `report_template` VALUES (11, 2, '20045', '实验心得', 4, '', 'true', 'text');
INSERT INTO `report_template` VALUES (12, 2, '20045', '思考题', 5, '思考题在课的最后讲到，为非必填项', 'false', 'text');
INSERT INTO `report_template` VALUES (13, 3, '06403', '实验目的', 0, '', 'true', 'text');
INSERT INTO `report_template` VALUES (14, 3, '06403', '实验背景', 1, '', 'true', 'text');
INSERT INTO `report_template` VALUES (15, 3, '06403', '实验环境', 2, '', 'true', 'text');
INSERT INTO `report_template` VALUES (16, 3, '06403', '实验环境拓扑图', 3, '', 'true', 'image');
INSERT INTO `report_template` VALUES (17, 3, '06403', '实验内容', 4, '', 'true', 'text');
INSERT INTO `report_template` VALUES (18, 3, '06403', '实验步骤', 5, '', 'true', 'text');
INSERT INTO `report_template` VALUES (19, 3, '06403', '实验步骤图', 6, '', 'false', 'image');
INSERT INTO `report_template` VALUES (20, 3, '06403', '实验结果', 7, '', 'true', 'text');
INSERT INTO `report_template` VALUES (21, 3, '06403', '实验结果图', 8, '', 'false', 'image');
INSERT INTO `report_template` VALUES (22, 3, '06403', '分析讨论', 9, '', 'true', 'text');
INSERT INTO `report_template` VALUES (23, 10, '12345', '算法介绍', 0, '', 'true', 'text');
INSERT INTO `report_template` VALUES (24, 10, '12345', '算法实现', 1, '', 'true', 'text');
INSERT INTO `report_template` VALUES (25, 10, '12345', '心得', 2, '', 'true', 'text');
INSERT INTO `report_template` VALUES (35, 11, '06403', '实验目的', 0, '', 'true', 'text');
INSERT INTO `report_template` VALUES (36, 11, '06403', '实验环境', 2, '本次实验是在cisco packet tracer中进行的', 'true', 'text');
INSERT INTO `report_template` VALUES (37, 11, '06403', '实验环境拓扑图', 3, '', 'true', 'image');
INSERT INTO `report_template` VALUES (38, 11, '06403', '实验内容', 4, '', 'true', 'text');
INSERT INTO `report_template` VALUES (39, 11, '06403', '实验步骤', 5, '', 'true', 'text');
INSERT INTO `report_template` VALUES (40, 11, '06403', '实验步骤图', 6, '', 'false', 'image');
INSERT INTO `report_template` VALUES (41, 11, '06403', '实验结果', 7, '', 'true', 'text');
INSERT INTO `report_template` VALUES (42, 11, '06403', '实验结果图', 8, '', 'false', 'image');
INSERT INTO `report_template` VALUES (43, 11, '06403', '分析讨论', 9, '', 'true', 'text');
INSERT INTO `report_template` VALUES (44, 12, '06403', '实验目的', 0, '', 'true', 'text');
INSERT INTO `report_template` VALUES (45, 12, '06403', '实验环境', 2, '', 'true', 'text');
INSERT INTO `report_template` VALUES (46, 12, '06403', '实验环境拓扑图', 3, '', 'true', 'image');
INSERT INTO `report_template` VALUES (47, 12, '06403', '实验内容', 4, '', 'true', 'text');
INSERT INTO `report_template` VALUES (48, 12, '06403', '实验步骤', 5, '', 'true', 'text');
INSERT INTO `report_template` VALUES (49, 12, '06403', '实验步骤图', 6, '', 'false', 'image');
INSERT INTO `report_template` VALUES (50, 12, '06403', '实验结果', 7, '', 'true', 'text');
INSERT INTO `report_template` VALUES (51, 12, '06403', '实验结果图', 8, '', 'false', 'image');
INSERT INTO `report_template` VALUES (52, 12, '06403', '分析讨论', 9, '', 'true', 'text');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `s_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1952168', '张宇');
INSERT INTO `student` VALUES ('2050260', '鲁悦骐');
INSERT INTO `student` VALUES ('2050585', '杨政熹');
INSERT INTO `student` VALUES ('2051971', '肖筱游');
INSERT INTO `student` VALUES ('2052210', '赵时可');
INSERT INTO `student` VALUES ('2052321', '邱比特');
INSERT INTO `student` VALUES ('2052635', '孙泽');
INSERT INTO `student` VALUES ('2052637', '刘文超');
INSERT INTO `student` VALUES ('2052721', '梁勤旸');
INSERT INTO `student` VALUES ('2053380', '彭浩');
INSERT INTO `student` VALUES ('2053677', '于然');
INSERT INTO `student` VALUES ('2053703', '陈雪松');
INSERT INTO `student` VALUES ('2054191', '朱天骅');

-- ----------------------------
-- Table structure for takes
-- ----------------------------
DROP TABLE IF EXISTS `takes`;
CREATE TABLE `takes`  (
  `s_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `c_id` int NOT NULL,
  `role` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade` float NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`, `c_id`) USING BTREE,
  INDEX `c_id`(`c_id` ASC) USING BTREE,
  CONSTRAINT `takes_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `takes_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `course` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of takes
-- ----------------------------
INSERT INTO `takes` VALUES ('1952168', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('1952168', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('1952168', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('1952168', 420203105, '4', NULL);
INSERT INTO `takes` VALUES ('2050260', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('2050260', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2050260', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('2050260', 420203105, '4', NULL);
INSERT INTO `takes` VALUES ('2050260', 420203106, '4', NULL);
INSERT INTO `takes` VALUES ('2050585', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('2050585', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2050585', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('2050585', 420203105, '4', NULL);
INSERT INTO `takes` VALUES ('2051971', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('2051971', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2051971', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('2051971', 420203105, '4', NULL);
INSERT INTO `takes` VALUES ('2052210', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('2052210', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2052210', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('2052321', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('2052321', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2052321', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('2052321', 420203106, '4', NULL);
INSERT INTO `takes` VALUES ('2052635', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('2052635', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2052635', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('2052635', 420203105, '4', NULL);
INSERT INTO `takes` VALUES ('2052635', 420203106, '4', NULL);
INSERT INTO `takes` VALUES ('2052637', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('2052637', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2052637', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('2052637', 420203105, '4', NULL);
INSERT INTO `takes` VALUES ('2052637', 420203106, '4', NULL);
INSERT INTO `takes` VALUES ('2052721', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('2052721', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2052721', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('2052721', 420203106, '4', NULL);
INSERT INTO `takes` VALUES ('2053380', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('2053380', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2053380', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('2053380', 420203105, '4', NULL);
INSERT INTO `takes` VALUES ('2053677', 420203101, '3', NULL);
INSERT INTO `takes` VALUES ('2053677', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2053677', 420203104, '3', NULL);
INSERT INTO `takes` VALUES ('2053677', 420203105, '4', NULL);
INSERT INTO `takes` VALUES ('2053703', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('2053703', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2053703', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('2053703', 420203106, '4', NULL);
INSERT INTO `takes` VALUES ('2054191', 420203101, '4', NULL);
INSERT INTO `takes` VALUES ('2054191', 420203102, '4', NULL);
INSERT INTO `takes` VALUES ('2054191', 420203104, '4', NULL);
INSERT INTO `takes` VALUES ('2054191', 420203106, '4', NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `t_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('00000', '默认模板');
INSERT INTO `teacher` VALUES ('04354', '胡老师');
INSERT INTO `teacher` VALUES ('06403', '张老师');
INSERT INTO `teacher` VALUES ('12345', '唐老师');
INSERT INTO `teacher` VALUES ('16034', '李老师');
INSERT INTO `teacher` VALUES ('18239', '刘老师');
INSERT INTO `teacher` VALUES ('19203', '陆老师');
INSERT INTO `teacher` VALUES ('20002', '冯老师');
INSERT INTO `teacher` VALUES ('20045', '马老师');

-- ----------------------------
-- Table structure for teaches
-- ----------------------------
DROP TABLE IF EXISTS `teaches`;
CREATE TABLE `teaches`  (
  `t_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `c_id` int NOT NULL,
  `role` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`, `c_id`) USING BTREE,
  INDEX `c_id`(`c_id` ASC) USING BTREE,
  CONSTRAINT `teaches_ibfk_1` FOREIGN KEY (`t_id`) REFERENCES `teacher` (`t_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teaches_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `course` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teaches
-- ----------------------------
INSERT INTO `teaches` VALUES ('04354', 420203104, '1');
INSERT INTO `teaches` VALUES ('04354', 420203105, '1');
INSERT INTO `teaches` VALUES ('04354', 420203106, '1');
INSERT INTO `teaches` VALUES ('06403', 420203102, '2');
INSERT INTO `teaches` VALUES ('12345', 420203101, '1');
INSERT INTO `teaches` VALUES ('12345', 420203102, '1');
INSERT INTO `teaches` VALUES ('12345', 420203103, '1');
INSERT INTO `teaches` VALUES ('12345', 420203104, '2');
INSERT INTO `teaches` VALUES ('12345', 420203105, '2');
INSERT INTO `teaches` VALUES ('12345', 420203106, '2');
INSERT INTO `teaches` VALUES ('12345', 420203107, '1');
INSERT INTO `teaches` VALUES ('16034', 420203105, '2');
INSERT INTO `teaches` VALUES ('18239', 420203104, '2');
INSERT INTO `teaches` VALUES ('19203', 420203103, '2');
INSERT INTO `teaches` VALUES ('20002', 420203106, '2');
INSERT INTO `teaches` VALUES ('20045', 420203101, '2');

-- ----------------------------
-- Table structure for todo
-- ----------------------------
DROP TABLE IF EXISTS `todo`;
CREATE TABLE `todo`  (
  `todo_id` int NOT NULL AUTO_INCREMENT,
  `c_id` int NOT NULL,
  `t_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` int NULL DEFAULT 1,
  PRIMARY KEY (`todo_id`) USING BTREE,
  INDEX `c_id`(`c_id` ASC) USING BTREE,
  INDEX `t_id`(`t_id` ASC) USING BTREE,
  CONSTRAINT `todo_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `teaches` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `todo_ibfk_2` FOREIGN KEY (`t_id`) REFERENCES `teaches` (`t_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of todo
-- ----------------------------
INSERT INTO `todo` VALUES (1, 420203101, '20045', '课程一的待办事项', '这是一条待办事项', '2022-09-10 00:00:00', '2022-11-17 00:00:00', 1);
INSERT INTO `todo` VALUES (2, 420203102, '06403', '课程二的待办事项', '这是一条待办事项', '2022-09-10 00:00:00', '2022-12-17 00:00:00', 1);
INSERT INTO `todo` VALUES (3, 420203103, '19203', '课程三的待办事项', '这是一条待办事项', '2022-09-10 00:00:00', '2022-12-20 00:00:00', 1);
INSERT INTO `todo` VALUES (4, 420203104, '18239', '课程四的待办事项', '这是一条待办事项', '2022-09-10 00:00:00', '2022-12-20 00:00:00', 1);
INSERT INTO `todo` VALUES (5, 420203105, '16034', '课程五的待办事项', '这是一条待办事项', '2022-09-10 00:00:00', '2022-12-25 00:00:00', 1);
INSERT INTO `todo` VALUES (6, 420203106, '20002', '课程六的待办事项', '这是一条待办事项', '2022-09-10 00:00:00', '2022-12-25 00:00:00', 1);
INSERT INTO `todo` VALUES (7, 420203101, '12345', '课程一的待办事项', '这是一条待办事项', '2022-09-10 00:00:00', '2022-12-31 12:00:00', 1);
INSERT INTO `todo` VALUES (8, 420203101, '12345', '门电路实验', '请在截止日期前完成实验', '2022-09-10 00:00:00', '2022-12-31 12:00:00', 2);
INSERT INTO `todo` VALUES (17, 420203101, '12345', '待办事项', '请在截止期日前完成实验项目', '2022-12-30 15:44:59', '2022-12-30 15:45:01', 2);
INSERT INTO `todo` VALUES (18, 420203101, '12345', '待办事项', '请在截止期日前完成实验项目', '2022-12-07 00:00:00', '2022-12-14 00:00:00', 2);
INSERT INTO `todo` VALUES (19, 420203101, '12345', '待办事项', '请在截止期日前完成实验项目', '2022-12-01 00:00:00', '2022-12-07 00:00:00', 2);
INSERT INTO `todo` VALUES (20, 420203101, '12345', '组成原理的待办', '这是一条待办事项', '2023-01-01 00:00:00', '2023-01-10 00:00:00', 1);
INSERT INTO `todo` VALUES (21, 420203101, '12345', '组成原理的待办', '这是一条待办事项', '2023-01-02 00:00:00', '2023-01-13 00:00:00', 1);
INSERT INTO `todo` VALUES (22, 420203101, '12345', '组成原理的待办', '这是一条待办事项', '2023-01-02 00:00:00', '2023-01-26 00:00:00', 1);
INSERT INTO `todo` VALUES (23, 420203102, '12345', '计网的待办', '这是一条待办事项', '2023-01-01 00:00:00', '2023-01-01 23:00:00', 1);
INSERT INTO `todo` VALUES (24, 420203102, '12345', '计网的待办', '这是一条待办事项', '2023-01-02 00:00:00', '2023-01-02 00:00:00', 1);
INSERT INTO `todo` VALUES (25, 420203102, '12345', '计网的待办', '这是一条待办事项', '2023-01-02 00:00:00', '2023-01-03 00:00:00', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('04354', '胡老师', '04354', '1', '2', '04354@tongji.edu.cn');
INSERT INTO `user` VALUES ('06403', '张老师', '06403', '1', '2', '06403@tongji.edu.cn');
INSERT INTO `user` VALUES ('12345', '唐老师', '12345', '1', '2', '12345@tongji.edu.cn');
INSERT INTO `user` VALUES ('16034', '李老师', '16034', '1', '2', '16034@tongji.edu.cn');
INSERT INTO `user` VALUES ('18239', '刘老师', '18239', '1', '2', '18239@tongji.edu.cn');
INSERT INTO `user` VALUES ('19203', '陆老师', '19203', '1', '2', '19203@tongji.edu.cn');
INSERT INTO `user` VALUES ('1952168', '张宇', '1952168', '1', '4', '1952168@tongji.edu.cn');
INSERT INTO `user` VALUES ('20002', '冯老师', '20002', '1', '2', '20002@tongji.edu.cn');
INSERT INTO `user` VALUES ('20045', '马老师', '20045', '1', '2', '20045@tongji.edu.cn');
INSERT INTO `user` VALUES ('2050260', '鲁悦骐', '2050260', '1', '4', '2050260@tongji.edu.cn');
INSERT INTO `user` VALUES ('2050585', '杨政熹', '2050585', '1', '4', '2050585@tongji.edu.cn');
INSERT INTO `user` VALUES ('2051971', '肖筱游', '2051971', '1', '4', '2051971@tongji.edu.cn');
INSERT INTO `user` VALUES ('2052210', '赵时可', '2052210', '1', '4', '2052210@tongji.edu.cn');
INSERT INTO `user` VALUES ('2052321', '邱比特', '2052321', '1', '4', '2052321@tongji.edu.cn');
INSERT INTO `user` VALUES ('2052635', '孙泽', '2052635', '1', '4', '2052635@tongji.edu.cn');
INSERT INTO `user` VALUES ('2052637', '刘文超', '2052637', '1', '4', '2052637@tongji.edu.cn');
INSERT INTO `user` VALUES ('2052721', '梁勤旸', '2052721', '1', '4', '2052721@tongji.edu.cn');
INSERT INTO `user` VALUES ('2053380', '彭浩', '2053380', '1', '4', '2053380@tongji.edu.cn');
INSERT INTO `user` VALUES ('2053677', '于然', '2053677', '1', '4', '205@tongji.edu.cn');
INSERT INTO `user` VALUES ('2053703', '陈雪松', '2053703', '1', '4', '2053703@tongji.edu.cn');
INSERT INTO `user` VALUES ('2054191', '朱天骅', '2054191', '1', '4', '2054191@tongji.edu.cn');
INSERT INTO `user` VALUES ('9527', '郭明暄', '9527', '1', '999', '9527@tongji.edu.cn');

SET FOREIGN_KEY_CHECKS = 1;
