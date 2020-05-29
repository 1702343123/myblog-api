/*
 Navicat MySQL Data Transfer

 Source Server         : 我的远程
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 47.101.51.245:3306
 Source Schema         : yemu

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 11/05/2020 10:48:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `icon` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '头像',
  `pass` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'Joker', NULL, 'java');

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `a_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `a_title` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `a_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `u_id` int(11) NULL DEFAULT NULL COMMENT '作者',
  `a_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `hover_img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'hover白天图片',
  `a_img2` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '夜间图片',
  `hover_img2` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'hover夜间图片',
  `a_img` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '封面图',
  `a_scan` int(11) NULL DEFAULT NULL COMMENT '访问量',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `is_top` int(11) NULL DEFAULT 0 COMMENT '是否置顶',
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (1, '生活不止有诗和远方，还有摔伤', '善良的老挝人像守护天使一样，坚定地张开翅膀保护着我，告诉我，别害怕，你不是一个人。\n　　\n　　从摩托车上飞出去的那一刻，我觉得自己轻如羽毛。\n　　\n　　那是一个上坡，土路上遍布石子，我目测自己能骑着摩托上去，于是加足马力准备直接冲上去。可绊脚石偏不让我如意，轮胎在石子上打滑，摩托车失去了平衡，一瞬间向左歪倒，我就这样飞了出去。\n　　\n　　趴在地上十秒钟，我整个人都傻了。在东南亚整整骑了一个月都没事，这旅程快结束了，怎么会摔呢？\n　　\n　　四面八方都冲过来人，有的扶车，有的扶我，我低头看了看自己的双腿，膝盖和脚腕都有大面积的擦伤，再抬起胳膊，手肘也擦伤。同时很疼的还有腹部，哆嗦着去碰了碰，立刻疼得我龇牙咧嘴，还摸到了一手血。\n　　\n　　我不敢再碰摩托车了，就向帮我把车扶起来的人请求：“你好，可以请你帮我把摩托车推上去吗？我就在上坡路右手边那家店租的，我想先还回去。”说这话的时候，我的声音都在发抖，身体上的疼痛提醒我，我摔得很严重。\n　　\n　　对方捡起了摔碎的后视镜，直接把摩托车骑了上去。还给店主后，我赔付了10美金，取回了租车时押在这里的护照。帮我还车的人没有走，他瞧了瞧我的伤势，告诉我：“你必须去医院，要打针，不然你会发烧。”我点点头，问他：“请问医院在哪里？我走过去远不远？”他连忙摆手：“你别走了，我的车就在附近，我送你去。”\n　　\n　　我犹豫了，对方是一个身高一米八的老挝人，还是位男士，我不敢跟他走。见我为难，他立刻解释：“你别担心，我在BeerLao工作，你看我还穿着工作服呢。我来自万象，这几个月我来万荣出差，我妻子也在这里，我有一个儿子和一个女儿。你等我下，我去开车。”\n　　\n　　我知道BeerLao是老挝最大的国有啤酒公司，在东南亚都很有名，能在这里工作的老挝人不仅受教育程度高，家庭条件也很好，遂放下心来。\n　　\n　　他开来了一辆铃木车，我艰难地爬进了副驾驶座。其实医院并不远，如果没有摔伤，也就走八分钟。蹒跚地走进了急诊室，他向医生和护士解释了下，我则被安排躺到了病床上。医生走过来，按压了我的腹部，问我伤口周围疼不疼。我摇头。医生说：“别担心，就是擦伤，没大事。”然后护士给我的伤口涂了药水，又给我打了一针。\n　　\n　　当我想着去交医药费的时候，他早就拿着开好的药品和缴费清单走了过来，我执意要给他钱，因为旅行前买了保险，有任何的事故，保险公司都会全额赔付，他却不收，说没多少钱。\n　　\n　　走出急诊室的大门，他说：“我叫Lamphay，现在是我午餐时间，一起去吃个饭吧，你吃饭了吗？”我想了想说：“我是Joyce，我请你吃午餐吧。”\n　　\n　　Lamphay开车载我去了一家餐厅，也许是觉得我很害怕，他一直在跟我说话，试图让我放松下来。我们点了炒面条和蔬菜汤，他说：“我有个朋友在中国学中文，晚上带你出来玩吧。”接二连三的邀请让我盛情难却，他却很理解地说：“你下午好好休息，要是让你一个人待着，我怕你会很难过。”\n　　\n　　老挝人是不太赞成女生一个人出行的，他们觉得不安全。即使我是外国人，Lamphay也表示了十分的担忧。聊了一中午，我得知许多游客骑摩托车都会摔伤，不过他们大多是两个人，Lamphay会告诉他们医院的位置。他说：“我是第一次见一个女生自己出来玩，我冲过去帮你的时候就想，万一你不会说英语，我就把你送到游客中心；如果你会说英语，我就送你去医院。”我乐了，这是帮助过多少游客后总结出来的经验啊。\n　　\n　　下午，我就在酒店安安静静地躺着休息。到了晚饭时间，我接到了Lamphay的电话，他让他会说中文的朋友跟我讲话，那是一个声音很温柔的女生，她的中文没有一点口音：“你好，好些了吗？”我说：“好多了。”她说：“那晚上有安排吗？一起出来吃饭吧。”\n　　\n　　到了约定的时间，我终于见到了这位中文无比标准的朋友。她的中文名字是金橙荷，曾在重庆留学，专业是商务汉语，已經通过了汉语等级考试的第五级。她讲起中文来很有意思，时不时蹦出一句“哎呦喂”，地道得让我忍俊不禁。\n　　\n　　餐厅的环境很好，位于一个酒店的露台上。露台外水光粼粼，隔着水能看到对岸的灯火，天上则是放飞的孔明灯。再看眼前，老挝友人说说笑笑，大家相聚在这里，就为了让我感觉不孤单。这样的陪伴很意外，也很温暖，冲淡了我因为摔伤而产生的恐惧。\n　　\n　　生活有诗和远方，也有摔伤。诗和远方在计划之中，摔伤却在计划之外。但善良的老挝人像守护天使一样，坚定地张开翅膀保护着我，告诉我，别害怕，你不是一个人。', 1, '2019-12-01 21:02:04', 'https://img.qinbinbin.cn/article-header/56635ba1-9e5a-42d9-a6c5-cc90caf8dbc4.jpg', 'https://img.qinbinbin.cn/article-header/0e0d4751-12a1-4e49-b9e5-bd0b7efe68a9.jpg', 'https://img.qinbinbin.cn/article-header/9ce43686-ed33-483a-a9b1-a7b30dfeb7ca.jpg', 'https://img.qinbinbin.cn/article-header/8bb712a3-8d27-4000-8d61-8dcf38ff1c33.jpg', 111, 0, 0);

-- ----------------------------
-- Table structure for t_attention
-- ----------------------------
DROP TABLE IF EXISTS `t_attention`;
CREATE TABLE `t_attention`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `icon` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '图标',
  `link` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '跳转链接',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attention
-- ----------------------------
INSERT INTO `t_attention` VALUES (1, '公众号', 'https://img.panbingwen.cn/atten-carousel-icon/c875f389-7234-481a-b360-e7829066cee3.jpg', '#', 0);
INSERT INTO `t_attention` VALUES (2, '简书', 'https://img.panbingwen.cn/atten-carousel-icon/d888a3b5-f4a8-4590-96d7-55dad41e2da9.jpg', 'https://www.jianshu.com/u/2f60beddf923', 0);
INSERT INTO `t_attention` VALUES (3, '掘金', 'https://img.panbingwen.cn/atten-carousel-icon/bacaac32-2d17-48c5-adcf-8590f2432924.jpg', 'https://juejin.im/user/5cb190bc6fb9a068af37a3a6', 0);
INSERT INTO `t_attention` VALUES (4, 'GitHub', 'https://img.panbingwen.cn/atten-carousel-icon/6fa2dfa6-632c-4c87-88c0-5ecb8bdc368f.jpg', 'https://github.com/pbw123', 0);
INSERT INTO `t_attention` VALUES (5, '码云', 'https://img.panbingwen.cn/atten-carousel-icon/0a1d6047-1277-4f1a-a58e-91bcdb9121bf.jpg', 'https://gitee.com/Joker_01', 0);
INSERT INTO `t_attention` VALUES (6, 'spring', 'http://save-pan.oss-cn-shanghai.aliyuncs.com/img/7fe5837d-9c1e-4dea-9fd6-485319e0581a.jpg', '87654', 1);
INSERT INTO `t_attention` VALUES (7, '寺', '234', '', 1);

-- ----------------------------
-- Table structure for t_carousel
-- ----------------------------
DROP TABLE IF EXISTS `t_carousel`;
CREATE TABLE `t_carousel`  (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `c_pc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '轮播图片',
  `c_night` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '夜间图片',
  `c_mobile` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '手机端的轮播图',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_carousel
-- ----------------------------
INSERT INTO `t_carousel` VALUES (1, 'https://img.panbingwen.cn/atten-carousel-icon/03e7344a-3820-4003-81cf-8129e6266458.jpg', 'https://img.panbingwen.cn/atten-carousel-icon/2771f369-8571-4c28-ba29-04dacf427a50.jpg', NULL, 0);
INSERT INTO `t_carousel` VALUES (2, 'https://img.panbingwen.cn/atten-carousel-icon/87024f51-941a-455c-9ec1-48369003222b.jpg', 'https://img.panbingwen.cn/atten-carousel-icon/c10de2b5-619a-43af-b4c0-7f60c3a11d73.jpg', NULL, 0);
INSERT INTO `t_carousel` VALUES (3, 'https://img.panbingwen.cn/atten-carousel-icon/0088e051-567e-4b7e-b5bd-5924f1c21373.jpg', 'https://img.panbingwen.cn/atten-carousel-icon/08f7f432-ff67-4463-9d16-cf13004cec4a.jpg', NULL, 0);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `a_id` int(11) NULL DEFAULT NULL COMMENT '评论的文章id',
  `u_id` int(11) NULL DEFAULT NULL COMMENT '评论的用户id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '评论内容',
  `c_time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_icon
-- ----------------------------
DROP TABLE IF EXISTS `t_icon`;
CREATE TABLE `t_icon`  (
  `i_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `i_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '图片地址',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`i_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_icon
-- ----------------------------
INSERT INTO `t_icon` VALUES (1, 'https://img.panbingwen.cn/atten-carousel-icon/0d3c479f-9fd1-4891-b75e-0dd307ac8dd4.jpg', 0);
INSERT INTO `t_icon` VALUES (2, 'https://img.panbingwen.cn/atten-carousel-icon/1f3fb5d4-049f-4c71-b748-3e5e3eee7965.jpg', 0);
INSERT INTO `t_icon` VALUES (3, 'https://img.panbingwen.cn/atten-carousel-icon/6cc0f435-1c1a-4b2c-99cb-b76eab68a84a.jpg', 0);
INSERT INTO `t_icon` VALUES (4, 'https://img.panbingwen.cn/atten-carousel-icon/d8809e22-c9d9-4671-b42e-cfce8d664c23.jpg', 0);
INSERT INTO `t_icon` VALUES (5, 'https://img.panbingwen.cn/atten-carousel-icon/cfceeae9-1660-48c8-acbb-6ae1ec7154a3.jpg', 0);

-- ----------------------------
-- Table structure for t_like
-- ----------------------------
DROP TABLE IF EXISTS `t_like`;
CREATE TABLE `t_like`  (
  `l_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `a_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `u_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `is_like` int(11) NULL DEFAULT 0 COMMENT '是否喜欢文章',
  PRIMARY KEY (`l_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_like
-- ----------------------------
INSERT INTO `t_like` VALUES (1, 1, 26, 1);

-- ----------------------------
-- Table structure for t_link
-- ----------------------------
DROP TABLE IF EXISTS `t_link`;
CREATE TABLE `t_link`  (
  `l_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `l_name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '友链名',
  `l_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '链接跳转地址',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`l_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_link
-- ----------------------------

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `m_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `u_id` int(11) NULL DEFAULT NULL COMMENT '留言的用户id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '留言内容',
  `m_time` datetime(0) NULL DEFAULT NULL COMMENT '留言时间',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父留言id',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_msg_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_msg_reply`;
CREATE TABLE `t_msg_reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(11) NULL DEFAULT NULL COMMENT '留言表主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '回复的用户id',
  `reply_user_id` int(11) NULL DEFAULT NULL COMMENT '被回复的用户id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '回复内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '回复时间',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_msg_reply
-- ----------------------------

-- ----------------------------
-- Table structure for t_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_reply`;
CREATE TABLE `t_reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `c_id` int(11) NULL DEFAULT NULL COMMENT '评论表主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '回复用户的id',
  `reply_user_id` int(11) NULL DEFAULT NULL COMMENT '被回复用户的id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '回复时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '回复内容',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '是否删除，默认0不删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_reply
-- ----------------------------

-- ----------------------------
-- Table structure for t_timeline
-- ----------------------------
DROP TABLE IF EXISTS `t_timeline`;
CREATE TABLE `t_timeline`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_timeline
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_pass` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `user_icon` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '头像',
  `phone_number` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `register_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '是否删除用户',
  `is_forbid` int(11) NULL DEFAULT 0 COMMENT '是否禁言',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'Joker', 'Java', NULL, '会长', '2019-12-01 21:07:37', 0, 0);

-- ----------------------------
-- Table structure for t_web
-- ----------------------------
DROP TABLE IF EXISTS `t_web`;
CREATE TABLE `t_web`  (
  `w_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bg_img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '顶部背景图',
  `bg_img2` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '另一张背景图',
  `img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '背景图',
  `night_img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '夜间背景图',
  `logo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '网站名字',
  `logo2` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '切换图片',
  `pay_zfb` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '支付宝二维码',
  `pay_wechat` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '微信二维码',
  `gzh` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '公众号二维码',
  `icp` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站备案号',
  `w_bar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '手机端侧方导航背景图',
  `w_bar2` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '手机侧方导航栏背景图',
  PRIMARY KEY (`w_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_web
-- ----------------------------
INSERT INTO `t_web` VALUES (1, 'https://img.panbingwen.cn/web-info/ed1b71f4-c7fa-4f71-9fdf-0e057ce06c45.jpg', 'https://img.panbingwen.cn/web-info/2e993d01-bc16-4b53-bc5d-9aea840e2216.jpg', 'https://img.panbingwen.cn/web-info/ed1b71f4-c7fa-4f71-9fdf-0e057ce06c45.jpg', 'https://img.panbingwen.cn/web-info/2e993d01-bc16-4b53-bc5d-9aea840e2216.jpg', 'http://save-pan.oss-cn-shanghai.aliyuncs.com/img/73bf917c-2ebf-4109-b57f-aaf6f21cfb2b.jpg', 'http://save-pan.oss-cn-shanghai.aliyuncs.com/img/4a77f899-a31e-4d60-b2ae-a058ade65370.jpg', 'https://img.panbingwen.cn/web-info/6fd28786-f47c-4cb9-bd8c-d19e700b5bc7.jpg', 'https://img.panbingwen.cn/web-info/c9a189cc-ce8e-4726-9dee-e614a5a8a5d3.jpg', 'https://img.panbingwen.cn/web-info/01c581ac-b4e0-4a61-917f-37dcde843b8a.jpg', '桂ICP备19007129号-1	', 'http://save-pan.oss-cn-shanghai.aliyuncs.com/img/4f1dd7fc-c7c4-4b15-85cf-4707719cdcf2.jpg', 'http://save-pan.oss-cn-shanghai.aliyuncs.com/img/18862631-3298-4bc5-9965-cbc15d78437f.jpg');

SET FOREIGN_KEY_CHECKS = 1;
