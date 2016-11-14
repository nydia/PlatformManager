
USE DATABASE platform_manager;

/*Table structure for table `sys_user` */
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `login_flag` varchar(64) DEFAULT NULL COMMENT '是否可登录',
  `login_times` bigint(20) DEFAULT '0' COMMENT '用户累计登录次数',
  `salt` varchar(64) DEFAULT NULL COMMENT '盐值',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `brand_custom_id` varchar(64) DEFAULT NULL COMMENT '品牌客户id',
  PRIMARY KEY (`id`),
  KEY `sys_user_login_name` (`login_name`) USING BTREE,
  KEY `sys_user_update_date` (`update_date`) USING BTREE,
  KEY `sys_user_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


/*Table structure for table `sys_user_info` */
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `user_id` bigint(20) unsigned zerofill NOT NULL COMMENT '用户id',
  `company_id` varchar(64) DEFAULT NULL COMMENT '归属公司',
  `card_uid` varchar(30) DEFAULT NULL,
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `photo` varchar(1000) DEFAULT NULL COMMENT '用户头像',
  `sex` varchar(64) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `idno` varchar(64) DEFAULT NULL COMMENT '身份证号码',
  `emergency_contacts` varchar(64) DEFAULT NULL COMMENT '紧急联系人',
  `emergency_phone` varchar(64) DEFAULT NULL COMMENT '紧急联系人号',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `brand_custom_id` varchar(64) DEFAULT NULL COMMENT '品牌客户id',
  PRIMARY KEY (`id`),
  KEY `sys_user_info_company_id` (`company_id`) USING BTREE,
  KEY `sys_user_info_update_date` (`update_date`) USING BTREE,
  KEY `sys_user_info_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详细信息表';
