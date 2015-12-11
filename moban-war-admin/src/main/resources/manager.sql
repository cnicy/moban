/*==============================================================*/
/* Table: sys_service_info   服务商基础信息表                    */
/*==============================================================*/
DROP TABLE IF EXISTS sys_service_info;
CREATE TABLE sys_service_info(
	ssi_id                  INT           AUTO_INCREMENT NOT NULL COMMENT'主键id',
	ssi_idCode							VARCHAR(128)  COMMENT'id编号',
	ssi_serviceName         VARCHAR(100)  COMMENT'服务商名称',
	ssi_serviceTitle        VARCHAR(200)  COMMENT'服务商标题',
	ssi_logo            		VARCHAR(255)  COMMENT'logo',
	ssi_copyright           VARCHAR(100)  COMMENT'版权信息',
	ssi_recordInformation   VARCHAR(100)  COMMENT'备案信息',
	ssi_contact             VARCHAR(20)   COMMENT'联系人',
	ssi_contactEmail        VARCHAR(50)   COMMENT'联系邮箱',
	ssi_contactAddress      VARCHAR(150)  COMMENT'联系地址',
	ssi_contactZipcode      VARCHAR(6)    COMMENT'邮编',
	ssi_contactPhone        VARCHAR(20)   COMMENT'联系电话',
	ssi_serviceEmail        VARCHAR(50)   COMMENT'客服邮箱',
	ssi_serviePhone         VARCHAR(20)   COMMENT'客服电话',
	ssi_seoTitle            VARCHAR(200)  COMMENT'seo标题',
	ssi_seoKeywords         VARCHAR(200)  COMMENT'seo关键字',
	ssi_seoDescription      VARCHAR(200)  COMMENT'seo描述',
	ssl_log				    			DOUBLE				COMMENT'经度',
	ssl_lng				    			DOUBLE				COMMENT'纬度',
	ssi_isDel               TINYINT    	  COMMENT'显示标记',
	ssi_createDate          DATETIME      COMMENT'创建时间',
	ssi_updateDate          DATETIME      COMMENT'更新时间',
	ssi_reserved1           VARCHAR(255)  COMMENT'保留字段1',
	ssi_reserved2           VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(ssi_id)
)ENGINE=INNODB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT'服务商基础信息表';
/*==============================================================*/
/* Table: sys_user        管理员表                               */
/*==============================================================*/
DROP TABLE IF EXISTS sys_admin;
CREATE TABLE sys_admin(
	su_id								INT 											AUTO_INCREMENT			NOT NULL COMMENT'主键id',
	su_serviceId				INT												COMMENT'服务商id',
	su_parentId					INT												COMMENT '上级id',
	su_idCode						VARCHAR(128)						  COMMENT '字符串id',
	su_qrCode						VARCHAR(255)							COMMENT '二维码地址',
	su_type							TINYINT 								  COMMENT'用户类型0系统用户1服务商2管理员3商家',
	su_userName					VARCHAR(50)								COMMENT'登录名',
	su_pswd							VARCHAR(128)							COMMENT'密码',
	su_suflt						VARCHAR(128)						  COMMENT'盐值',
	su_cellphone				VARCHAR(20)								COMMENT'手机号',
	su_email						VARCHAR(30)								COMMENT'邮箱',
	su_name							VARCHAR(50)								COMMENT'姓名',
	su_nickName			  	VARCHAR(50)								COMMENT'昵称',
	su_photo						VARCHAR(255)							COMMENT'头像',
	su_level			    	TINYINT 								  COMMENT'用户等级',
	su_address			  	VARCHAR(100)							COMMENT'地址',
	su_introduce				VARCHAR(200)							COMMENT'简介',
	su_score			    	INT 										  COMMENT'积分数量',
	su_money			    	BIGINT										COMMENT'余额:分',
	su_vipEndDate				DATE											COMMENT'VIP到期时间',
	su_userTag			  	VARCHAR(200)							COMMENT'标签',
	su_log				    	DOUBLE										COMMENT'经度',
	su_lng				    	DOUBLE										COMMENT'纬度',
	su_infoComplete			SMALLINT 									COMMENT'资料完成度',
	su_validatePhone		TINYINT								    COMMENT'手机号已验证0否1是',
	su_validateEmail		TINYINT								    COMMENT'email已验证0否1是',
	su_onlineState      TINYINT  							    COMMENT'状态：0/下线 1/在线 2/离开',
  su_duty          	  VARCHAR(20) 							COMMENT'职务',
	su_state						TINYINT								    COMMENT'账号状态0未激活1正常2已锁定3已注销',
  su_loginCount     	INT     									COMMENT'登录次数',
  su_lastActivity  		DATETIME    							COMMENT'最近活动时间',
  su_remark						VARCHAR(255) 							COMMENT'备注',
	su_isDel       			TINYINT  							    COMMENT'显示标记(0:未删除，1:已删除)',
	su_createDate  			DATETIME    							COMMENT'创建时间',
	su_updateDate  			DATETIME     							COMMENT'更新时间',
	su_reserved1				VARCHAR(255) 							COMMENT'保留字段1',
	su_reserved2				VARCHAR(255) 							COMMENT'保留字段2',

	PRIMARY KEY(su_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'用户表';
/*==============================================================*/
/* Table: sys_role        用户角色表                            */
/*==============================================================*/
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
	sr_id								INT       			 NOT NULL AUTO_INCREMENT COMMENT '主键',
	sr_serviceId				INT							 COMMENT '服务商id',
	sr_name							VARCHAR(50)      COMMENT '角色名称',
	sr_aliases					VARCHAR(50)			 COMMENT '别名',
	sr_enName         	VARCHAR(50)      COMMENT '英文名称',
	sr_remark						VARCHAR(255)     COMMENT '角色描述',
	sr_isDel            TINYINT      		 COMMENT '显示标记(0:未删除，1:已删除)',
	sr_updateDate       DATETIME         COMMENT '创建时间',
	sr_createDate       DATETIME         COMMENT '更新时间',
	sr_reserved1        VARCHAR(255)     COMMENT '保留字段1',
	sr_reserved2        VARCHAR(255)     COMMENT '保留字段2',

	PRIMARY KEY(sr_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*==============================================================*/
/* Table: sys_ref_admin_role    管理员角色关联表                 */
/*==============================================================*/
DROP TABLE IF EXISTS sys_ref_admin_role;
CREATE TABLE sys_ref_admin_role(
	srur_adminId				INT   	NOT NULL COMMENT '管理员ID',
	srur_roleId					INT  		NOT NULL COMMENT '角色ID',

	PRIMARY KEY(srur_adminId,srur_roleId)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT'管理员角色关联表';
/*==============================================================*/
/* Table: sys_auth        权限表                         			  */
/*==============================================================*/
DROP TABLE IF EXISTS sys_auth;
CREATE TABLE sys_auth(
	sa_id			      		INT   					  NOT NULL AUTO_INCREMENT COMMENT '主键',
	sa_parentId		      INT   				  	COMMENT '所属资源id', -- 跟菜单有关
	sa_module						TINYINT					  COMMENT	'所属模块0系统管理1用户模块2CMS模块3微信模块4商城模块5支付模块6统计模块7监控',
	sa_parentIds        VARCHAR(50)  			COMMENT	'上级菜单多个/分隔 ',
	sa_name			      	VARCHAR(255)			COMMENT '资源名',
	sa_aliases					VARCHAR(200)			COMMENT '别名',
	sa_menuType		      TINYINT   				COMMENT '资源类型 0 菜单 1 方法 ',
	sa_url			      	VARCHAR(255) 			COMMENT '资源链接',
	sa_moudleUrl				VARCHAR(255)			COMMENT '模块地址',
	sa_permission       VARCHAR(128) 			COMMENT '菜单权限',
	sa_sort			     	 	INT(5)      			COMMENT '序号', -- 跟菜单有关
	sa_icon             VARCHAR(255) 			COMMENT '资源图标',
	sa_target		      	VARCHAR(20)  			COMMENT '跳转目标', -- 跟菜单有关
	sa_level		      	SMALLINT(2)  			COMMENT '资源级别(1 一级/2 二级/3 三级)', -- 跟菜单有关
	sa_desc			      	VARCHAR(255) 			COMMENT '资源描述',
	sa_isDel            TINYINT   				COMMENT '显示标记(0:未删除，1:已删除)',
	sa_createDate       DATETIME     			COMMENT '创建时间',
	sa_updateDate       DATETIME     			COMMENT '更新时间',
	sa_reserved1		  	VARCHAR(255) 			COMMENT '保留字段1',
	sa_reserved2		  	VARCHAR(255) 			COMMENT '保留字段2',

	PRIMARY KEY(sa_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'权限表';
/*==============================================================*/
/* Table: sys_service_auth        服务商权限表                   */
/*==============================================================*/
DROP TABLE IF EXISTS sys_service_auth;
CREATE TABLE sys_service_auth(
	ssa_id			      		INT   					  NOT NULL AUTO_INCREMENT COMMENT '主键',
	ssa_serviceId					INT								COMMENT '服务商id',
	ssa_parentId		      INT   				  	COMMENT '所属资源id', -- 跟菜单有关
	ssa_module						TINYINT					  COMMENT	'所属模块0系统管理1用户模块2CMS模块3微信模块4商城模块5支付模块6统计模块7监控',
	ssa_parentIds         VARCHAR(50)  			COMMENT	'上级菜单多个/分隔 ',
	ssa_name			      	VARCHAR(255)			COMMENT '资源名',
	ssa_aliases					  VARCHAR(200)			COMMENT '别名',
	ssa_menuType		      TINYINT   				COMMENT '资源类型 0 菜单 1 方法 ',
	ssa_url			      	  VARCHAR(255) 			COMMENT '资源链接',
	ssa_moudleUrl				  VARCHAR(255)			COMMENT '模块地址',
	ssa_permission        VARCHAR(128) 			COMMENT '菜单权限',
	ssa_sort			     	 	INT(5)      			COMMENT '序号', -- 跟菜单有关
	ssa_icon              VARCHAR(255) 			COMMENT '资源图标',
	ssa_target		      	VARCHAR(20)  			COMMENT '跳转目标', -- 跟菜单有关
	ssa_level		      	  SMALLINT(2)  			COMMENT '资源级别(1 一级/2 二级/3 三级)', -- 跟菜单有关
	ssa_desc			      	VARCHAR(255) 			COMMENT '资源描述',
	ssa_isDel             TINYINT   				COMMENT '显示标记(0:未删除，1:已删除)',
	ssa_createDate        DATETIME     			COMMENT '创建时间',
	ssa_updateDate        DATETIME     			COMMENT '更新时间',
	ssa_reserved1		  	  VARCHAR(255) 			COMMENT '保留字段1',
	ssa_reserved2		  	  VARCHAR(255) 			COMMENT '保留字段2',

	PRIMARY KEY(ssa_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'服务商权限表';
/*==============================================================*/
/* Table: sys_ref_role_auth       角色权限关联表                 */
/*==============================================================*/
DROP TABLE IF EXISTS sys_ref_role_auth;
CREATE TABLE sys_ref_role_auth (
	srra_roleId					INT(12)   NOT NULL COMMENT '角色id',
	srra_authId					INT(12)   NOT NULL COMMENT '资源id',

	PRIMARY KEY(srra_roleId,srra_authId)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT'角色权限关联表';
/*==============================================================*/
/* Table: sys_user_operate_log     系统操作日志表                */
/*==============================================================*/
DROP TABLE IF EXISTS sys_user_operate_log;
CREATE TABLE sys_user_operate_log(
	suol_id        	  BIGINT(12)   		  AUTO_INCREMENT NOT NULL COMMENT'主键id',
	suol_adminId    	INT    						COMMENT'操作人id',
	suol_title     	  VARCHAR(50) 		  COMMENT'日志标题',
	suol_module				TINYINT			  		COMMENT'所属模块0系统管理1用户模块2CMS模块3微信模块4商城模块5支付模块6统计模块7监控',
	suol_subModule		VARCHAR(50)				COMMENT'子模块名称',
	suol_package			VARCHAR(100)			COMMENT'包名',
	suol_desc      	  VARCHAR(100)  		COMMENT'描述',
	suol_type      	  TINYINT  		      COMMENT'操作类型:0添加1修改2删除3',
	suol_ipAddress  	VARCHAR(50)   		COMMENT'ip地址',
	suol_address      VARCHAR(100)			COMMENT'物理地址',
	suol_result				TINYINT						COMMENT'操作结果0失败1成功',
	suol_isDel      	TINYINT    				COMMENT'显示标记',
	suol_createDate	  DATETIME     		  COMMENT'创建时间',
	suol_updateDate	  DATETIME     		  COMMENT'修改时间',
	suol_reserved1 	  VARCHAR(255)      COMMENT'保留字段1',
	suol_reserved2    VARCHAR(255)      COMMENT'保留字段2',

	PRIMARY KEY(suol_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'系统操作日志表';
/*==============================================================*/
/* Table: sys_user_login_logs     系统登录日志表                 */
/*==============================================================*/
DROP TABLE IF EXISTS sys_user_login_logs;
CREATE TABLE sys_user_login_logs(
	sull_id        	  BIGINT(12)   		  AUTO_INCREMENT NOT NULL COMMENT'主键id',
	sull_adminId    	INT    						COMMENT '登录人id',
	sull_subModule		VARCHAR(50)				COMMENT '子模块名称',
	sull_desc      	  VARCHAR(100)  		COMMENT '描述',
	sull_loginAccount	VARCHAR(100)			COMMENT '登录账号',
	sull_ipAddress  	VARCHAR(50)   		COMMENT 'ip地址',
	sull_address			VARCHAR(100)			COMMENT '物理地址',
	sull_result				TINYINT						COMMENT '操作结果0失败1成功',
	sull_isDel      	TINYINT    				COMMENT '显示标记',
	sull_createDate	  DATETIME     		  COMMENT '创建时间',
	sull_updateDate	  DATETIME     		  COMMENT '修改时间',
	sull_reserved1 	  VARCHAR(255)      COMMENT '保留字段1',
	sull_reserved2    VARCHAR(255)      COMMENT '保留字段2',

	PRIMARY KEY(sull_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'系统登录日志表';
/*==============================================================*/
/* Table: sys_zone  行政区域表                                   */
/*==============================================================*/
DROP TABLE IF EXISTS sys_zone;
CREATE TABLE sys_zone(
	sz_id                  INT    			 AUTO_INCREMENT NOT NULL COMMENT'主键id',
	sz_parentId            INT    			 COMMENT'上级区域id',
	sz_parentIds           VARCHAR(50)   COMMENT'上级区域ids多个/分隔',
	sz_name                VARCHAR(50)   COMMENT'名称',
	sz_level               INT(3)        COMMENT'层级深度',
	sz_desc                VARCHAR(255)  COMMENT'描述',
	sz_zipcode             VARCHAR(6)    COMMENT'邮编',
	sz_code								 VARCHAR(20)	 COMMENT'区域代码',
	sz_areaCode						 VARCHAR(20)	 COMMENT'区号',
	sz_sort                INT(7)        COMMENT'顺序',
	sz_isDel               TINYINT    	 COMMENT'显示标记',
	sz_createDate          DATETIME      COMMENT'创建时间',
	sz_updateDate          DATETIME      COMMENT'更新时间',
	sz_reserved1           VARCHAR(255)  COMMENT'保留字段1',
	sz_reserved2           VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(sz_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'行政区域表';
/*==============================================================*/
/* Table: sys_word_book   字典表                                */
/*==============================================================*/
DROP TABLE IF EXISTS sys_word_book;
CREATE TABLE sys_word_book(
	swb_id                    INT           AUTO_INCREMENT NOT NULL COMMENT'主键id',
	swb_mark                  VARCHAR(10)   COMMENT'标记',
	swb_key                   VARCHAR(128)  COMMENT'字典key',
	swb_fields								VARCHAR(10)   COMMENT'field',
	swb_value                 TEXT          COMMENT'字典value',
	swb_isDel                 TINYINT    		COMMENT'显示标记',
	swb_createDate            DATETIME      COMMENT'创建时间',
	swb_updateDate            DATETIME      COMMENT'更新时间',
	swb_reserved1             VARCHAR(255)  COMMENT'保留字段1',
	swb_reserved2             VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(swb_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'字典表';
/*==============================================================*/
/* Table: sys_user_folder  文件夹表                              */
/*==============================================================*/
DROP TABLE IF EXISTS sys_user_folder;
CREATE TABLE sys_user_folder(
	suf_id                  INT			     	  AUTO_INCREMENT NOT NULL COMMENT'主键id',
	suf_parentId            INT   				 	COMMENT'上级id',
	suf_parentIds						VARCHAR(50)			COMMENT'多级id/分割',
	suf_name                VARCHAR(50)   	COMMENT'文件夹名称',
	suf_frontCover          VARCHAR(255)  	COMMENT'封面图片',
	suf_sort                INT(5)        	COMMENT'排序',
	suf_fileNums            INT(6)        	COMMENT'文件数量',
	suf_desc                VARCHAR(255)  	COMMENT'描述',
	suf_level               INT(2)        	COMMENT'层级深度',
	suf_isDel               TINYINT    			COMMENT'显示标记',
	suf_createDate          DATETIME      	COMMENT'创建时间',
	suf_updateDate          DATETIME      	COMMENT'更新时间',
	suf_reserved1           VARCHAR(255)  	COMMENT'保留字段1',
	suf_reserved2           VARCHAR(255)  	COMMENT'保留字段2',

	PRIMARY KEY(suf_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'文件夹表';

/*==============================================================*/
/* Table: sys_file  文件表                                      */
/*==============================================================*/
DROP TABLE IF EXISTS sys_file;
CREATE TABLE sys_file(
	sf_id                  INT    	 			 AUTO_INCREMENT NOT NULL COMMENT'主键id',
	sf_idCode              VARCHAR(128)  	 COMMENT'字符串id',
	sf_folderId            INT   	 				 COMMENT'文件夹id',
	sf_name                VARCHAR(50)   	 COMMENT'文件名称',
	sf_suffix              VARCHAR(20)  	 COMMENT'后缀',
	sf_desc                VARCHAR(200) 	 COMMENT'文件描述',
	sf_address             VARCHAR(255)  	 COMMENT'文件地址',
	sf_size								 VARCHAR(20)		 COMMENT'文件大小kb',
	sf_sort                INT(7)       	 COMMENT'排序',
	sf_isDel               TINYINT   	 		 COMMENT'显示标记',
	sf_createDate          DATETIME     	 COMMENT'创建时间',
	sf_updateDate          DATETIME      	 COMMENT'更新时间',
	sf_reserved1           VARCHAR(255) 	 COMMENT'保留字段1',
	sf_reserved2           VARCHAR(255)  	 COMMENT'保留字段2',

	PRIMARY KEY(sf_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'文件表';















