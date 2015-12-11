/*==============================================================*/
/* Table: uc_customer        会员表                             */
/*==============================================================*/
DROP TABLE IF EXISTS uc_customer;
CREATE TABLE uc_customer(
	uc_id								INT 											AUTO_INCREMENT			NOT NULL COMMENT'主键id',
	uc_serviceId				INT												COMMENT'服务商id',
	uc_zoneId						INT												COMMENT'区域id',
	uc_zoneIds          VARCHAR(50)  							COMMENT'行政区域id多个/分隔',
	uc_idCode						VARCHAR(128)						  COMMENT'字符串id',
	uc_qrCode						VARCHAR(255)							COMMENT'二维码地址',
	uc_userName					VARCHAR(50)								COMMENT'登录名',
	uc_pswd							VARCHAR(128)							COMMENT'密码',
	uc_payPswd          VARCHAR(128)              COMMENT'支付密码',
	uc_salt						  VARCHAR(128)						  COMMENT'盐值',
	uc_cellphone				VARCHAR(20)								COMMENT'手机号',
	uc_email						VARCHAR(30)								COMMENT'邮箱',
	uc_name							VARCHAR(50)								COMMENT'姓名',
	uc_gender						TINYINT										COMMENT'性别0男1女',
	uc_nickName			  	VARCHAR(50)								COMMENT'昵称',
	uc_photo						VARCHAR(255)							COMMENT'头像',
	uc_birthday         DATETIME      						COMMENT'生日',
	uc_level			    	TINYINT 									COMMENT'用户等级',
	uc_address			  	VARCHAR(200)							COMMENT'详细地址',
	uc_score			    	DOUBLE 										COMMENT'积分数量',
	uc_money			    	BIGINT										COMMENT'余额',
	uc_userTag			  	VARCHAR(200)							COMMENT'标签',
	uc_infoComplete			INT 											COMMENT'资料完成度',
	uc_validatePhone		TINYINT										COMMENT'手机号已验证0否1是',
	uc_validateEmail		TINYINT										COMMENT'email已验证0否1是',
	uc_onlineState      TINYINT  									COMMENT'状态：0/下线 1/在线 2/离开',
	uc_state						TINYINT										COMMENT'账号状态0未激活1正常2已锁定3已注销',
  uc_loginCount     	INT(11)     							COMMENT'登录次数',
  uc_lastActivity  		DATETIME    							COMMENT'最近活动时间',
	uc_lastLoginOS      TINYINT  									COMMENT'最后一次登录设备信息0安卓1苹果2网页',
  uc_remark						VARCHAR(255) 							COMMENT'备注',
	uc_isDel       			TINYINT  									COMMENT'显示标记(0:未删除，1:已删除)',
	uc_createDate  			DATETIME    							COMMENT'创建时间',
	uc_updateDate  			DATETIME     							COMMENT'更新时间',
	uc_reserved1				VARCHAR(255) 							COMMENT'保留字段1',
	uc_reserved2				VARCHAR(255) 							COMMENT'保留字段2',

	PRIMARY KEY(uc_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'会员表';
/*==============================================================*/
/* Table: uc_customer_base_info        会员基础信息表            */
/*==============================================================*/
DROP TABLE IF EXISTS uc_customer_base_info;
CREATE TABLE uc_customer_base_info(
	ucb_id								INT 											NOT NULL COMMENT'主键id',
	ucb_introduce				  VARCHAR(200)							COMMENT'简介',
	ucb_registerSource    TINYINT  									COMMENT'注册来源0安卓1苹果2网页3第三方',
	ucb_idNumber          VARCHAR(20)   						COMMENT'身份证号',
	ucb_log								DOUBLE										COMMENT'经度',
	ucb_lng				    		DOUBLE										COMMENT'纬度',
	ucb_qq								VARCHAR(15)								COMMENT'qq',
	ucb_profession				VARCHAR(50)								COMMENT'职业',
	ucb_hobbles         	VARCHAR(100)  						COMMENT'兴趣爱好',
	ucb_marriage         	TINYINT   								COMMENT'0未婚1已婚2保密',
	ucb_hasChild					TINYINT										COMMENT'有无子女0无1有',
	ucb_eduLevel          SMALLINT(2)   						COMMENT'教育程度:0初中1高中2中专3大专4本科5硕士6博士7其他',
	ucb_vipEndDate				DATE											COMMENT'VIP到期时间',
	ucb_levelEndDate      DATE											COMMENT'等级到期时间',

	PRIMARY KEY(ucb_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'会员基础信息表';
/*==============================================================*/
/* Table: uc_score_rule     积分规则表            					      */
/*==============================================================*/
DROP TABLE IF EXISTS uc_score_rule;
CREATE TABLE uc_score_rule(
	usr_id						INT								AUTO_INCREMENT	NOT NULL COMMENT '主键id',
	usr_idCode				VARCHAR(50)				COMMENT 'id标识',
	usr_name					VARCHAR(50)				COMMENT '名称',
	usr_aliases				VARCHAR(50)				COMMENT '别名',
	usr_module				VARCHAR(100)			COMMENT '模块',
	usr_path					VARCHAR(50)				COMMENT '路径',
	usr_isDel      	  TINYINT    				COMMENT '显示标记',
	usr_createDate	  DATETIME     		  COMMENT '创建时间',
	usr_updateDate	  DATETIME     		  COMMENT '修改时间',
	usr_reserved1 	  VARCHAR(255)      COMMENT '保留字段1',
	usr_reserved2     VARCHAR(255)      COMMENT '保留字段2',

	PRIMARY KEY (usr_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHAR SET =utf8	COMMENT '服务商积分规则表';
/*==============================================================*/
/* Table: uc_ref_service_score_rule     服务商积分规则表         */
/*==============================================================*/
DROP TABLE IF EXISTS uc_ref_service_score_rule;
CREATE TABLE uc_ref_service_score_rule(
	urssr_id					  INT								AUTO_INCREMENT	NOT NULL COMMENT '主键id',
	urssr_scoreRuleId		INT								COMMENT '规则id',
	urssr_serviceId			INT 							COMMENT '服务商id',
	urssr_score				  INT								COMMENT '奖励积分数量',
	urssr_type					TINYINT						COMMENT '类型0惩罚1奖励',

	PRIMARY KEY (urssr_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHAR SET =utf8	COMMENT '服务商积分规则表';
/*==============================================================*/
/* Table: sys_user_login_logs     会员登录日志表                 */
/*==============================================================*/
DROP TABLE IF EXISTS uc_customer_login_log;
CREATE TABLE uc_customer_login_log(
	ucll_id        	  BIGINT(12)   		  AUTO_INCREMENT NOT NULL COMMENT'主键id',
	ucll_serviceId		INT 							COMMENT '服务商id',
	ucll_custId    	  INT    						COMMENT '登录会员id',
	ucll_subModule		VARCHAR(50)				COMMENT '子模块名称',
	ucll_loginAccount	VARCHAR(100)			COMMENT '登录账号',
	ucll_desc      	  VARCHAR(100)  		COMMENT '描述',
	ucll_ipAddress  	VARCHAR(50)   		COMMENT 'ip地址',
	ucll_address			VARCHAR(100)			COMMENT '物理地址',
	ucll_result				TINYINT						COMMENT '操作结果0失败1成功',
	ucll_isDel      	TINYINT    				COMMENT '显示标记',
	ucll_createDate	  DATETIME     		  COMMENT '创建时间',
	ucll_updateDate	  DATETIME     		  COMMENT '修改时间',
	ucll_reserved1 	  VARCHAR(255)      COMMENT '保留字段1',
	ucll_reserved2    VARCHAR(255)      COMMENT '保留字段2',

	PRIMARY KEY(ucll_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'会员登录日志表';
/*==============================================================*/
/* Table: uc_customer_score_history        会员积分历史记录表    */
/*==============================================================*/
DROP TABLE IF EXISTS uc_customer_score_history;
CREATE TABLE uc_customer_score_history(
	ucsh_id                  INT    			 AUTO_INCREMENT NOT NULL COMMENT'主键id',
	ucsh_custId              INT    			 COMMENT'会员id',
	ucsh_serviceId					 INT 					 COMMENT'服务商id',
	ucsh_giftId              INT    			 COMMENT'积分兑换礼品id',
	ucsh_integralRuleId      INT    			 COMMENT'积分规则id',
	ucsh_type                TINYINT    	 COMMENT'0扣除1新增',
	ucsh_score               DOUBLE        COMMENT'发生积分量',
	ucsh_makePath            VARCHAR(100)  COMMENT'发生路径',
	ucsh_currentScore				 INT					 COMMENT'当前积分',
	ucsh_desc                VARCHAR(255)  COMMENT'描述',
	ucsh_isDel               TINYINT    	 COMMENT'显示标记',
	ucsh_createDate          DATETIME      COMMENT'创建时间',
	ucsh_updateDate          DATETIME      COMMENT'更新时间',
	ucsh_reserved1           VARCHAR(255)  COMMENT'保留字段1',
	ucsh_reserved2           VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(ucsh_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'会员积分历史记录表';
/*==============================================================*/
/* Table: uc_deliver_address      收货地址                       */
/*==============================================================*/
DROP TABLE IF EXISTS uc_deliver_address;
CREATE TABLE uc_deliver_address(
	uda_id                  INT    				AUTO_INCREMENT NOT NULL COMMENT'主键id',
	uda_serviceId			      INT 					COMMENT '服务商id',
	uda_custId              INT    				COMMENT'用户id',
	uda_zoneId              INT    				COMMENT'行政区域id',
	uda_zoneIds             VARCHAR(50)   COMMENT'行政区域ids多个/分隔',
	uda_receiver            VARCHAR(20)   COMMENT'收货人',
	uda_address             VARCHAR(200)  COMMENT'-详细地址',
	uda_phone               VARCHAR(11)   COMMENT'电话',
	uda_mobile              VARCHAR(11)   COMMENT'手机号',
	uda_email               VARCHAR(50)   COMMENT'邮箱',
	uda_alias               VARCHAR(50)   COMMENT'别名',
	uda_remark              VARCHAR(255)  COMMENT'备注信息',
	uda_enable              TINYINT    	  COMMENT'是否默认0否1是',
	uda_isDel               TINYINT    	  COMMENT'显示标记',
	uda_createDate          DATETIME      COMMENT'创建时间',
	uda_updateDate          DATETIME      COMMENT'更新时间',
	uda_reserved1           VARCHAR(255)  COMMENT'保留字段1',
	uda_reserved2           VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(uda_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'收货地址';
/*==============================================================*/
/* Table: uc_user_attention        用户关注表                    */
/*==============================================================*/
DROP TABLE IF EXISTS uc_user_attention;
CREATE TABLE uc_user_attention(
	uua_id                  INT   				AUTO_INCREMENT NOT NULL COMMENT'主键id',
	uua_serviceId						INT 					COMMENT'服务商id',
	uua_custId              INT				    COMMENT'用户id',
	uua_objectId            INT   				COMMENT'关注对象id',
	uua_type								TINYINT			  COMMENT'关注类型0店铺1活动2商品',
	uua_objName             VARCHAR(255)  COMMENT'关注对象名称',
 	uua_objDesc							VARCHAR(255)  COMMENT'关注对象描述',
	uua_objPrice            BIGINT			  COMMENT'关注时价格',
	uua_objTags							VARCHAR(255)  COMMENT'关注对象标签',
	uua_isDel               TINYINT    	  COMMENT'显示标记',
	uua_createDate          DATETIME      COMMENT'创建时间',
	uua_updateDate          DATETIME      COMMENT'更新时间',
	uua_reserved1           VARCHAR(255)  COMMENT'保留字段1',
	uua_reserved2           VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(uua_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'用户关注表';
/*==============================================================*/
/* Table: uc_bank        银行表                                 */
/*==============================================================*/
DROP TABLE IF EXISTS uc_bank;
CREATE TABLE uc_bank(
	ub_id                  INT    			 AUTO_INCREMENT NOT NULL COMMENT'主键id',
	ub_name                VARCHAR(100)  COMMENT'名称',
	ub_pic                 VARCHAR(255)  COMMENT'显示logo',
	ub_website             VARCHAR(255)  COMMENT'网址',
	ub_enable              TINYINT    	 COMMENT'0不可用1可以',
	ub_remark              VARCHAR(255)  COMMENT'备注信息',
	ub_isDel               TINYINT    	 COMMENT'显示标记',
	ub_createDate          DATETIME      COMMENT'创建时间',
	ub_updateDate          DATETIME      COMMENT'更新时间',
	ub_reserved1           VARCHAR(255)  COMMENT'保留字段1',
	ub_reserved2           VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(ub_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'银行表';
/*==============================================================*/
/* Table: uc_bank_card        银行卡号                           */
/*==============================================================*/
DROP TABLE IF EXISTS uc_bank_card;
CREATE TABLE uc_bank_card(
	ubc_id                  INT			      AUTO_INCREMENT NOT NULL COMMENT'主键id',
	ubc_bankId              INT    			  COMMENT'所属银行id',
	ubc_custId              INT    				COMMENT'用户id',
	ubc_serviceId						INT 					COMMENT'服务商id',
	ubc_type                INT(1)        COMMENT'0会员',
	ubc_accountName         VARCHAR(50)   COMMENT'账户名称',
	ubc_openBank            VARCHAR(50)   COMMENT'开户行',
	ubc_openAddress					VARCHAR(200)	COMMENT'开户网点',
	ubc_phone               VARCHAR(20)   COMMENT'开户手机号',
	ubc_cardNo              VARCHAR(50)   COMMENT'卡号',
	ubc_remark              VARCHAR(255)  COMMENT'备注信息',
	ubc_enable              TINYINT    	  COMMENT'0不可用1可用',
	ubc_isDel               TINYINT       COMMENT'显示标记',
	ubc_createDate          DATETIME      COMMENT'创建时间',
	ubc_updateDate          DATETIME      COMMENT'更新时间',
	ubc_reserved1           VARCHAR(255)  COMMENT'保留字段1',
	ubc_reserved2           VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(ubc_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'银行卡号';
/*==============================================================*/
/* Table: uc_registration     会员签到表                         */
/*==============================================================*/
DROP TABLE IF EXISTS uc_registration;
CREATE TABLE uc_registration(
	ur_id								   INT 					 AUTO_INCREMENT		COMMENT '主键id',
	ur_serviceId					 INT 					 COMMENT'服务商id',
	ur_custId							 INT					 COMMENT'用户id',
	ur_score							 DOUBLE				 COMMENT'赠送积分数量',
	ur_remark              VARCHAR(255)  COMMENT'备注信息',
	ur_isDel               TINYINT       COMMENT'显示标记',
	ur_createDate          DATETIME      COMMENT'创建时间',
	ur_updateDate          DATETIME      COMMENT'更新时间',
	ur_reserved1           VARCHAR(255)  COMMENT'保留字段1',
	ur_reserved2           VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(ur_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'会员签到表';
/*==============================================================*/
/* Table: uc_user_level       会员等级规则表                     */
/*==============================================================*/
DROP TABLE IF EXISTS uc_user_level;
CREATE TABLE uc_user_level(
	uul_id                  INT			      AUTO_INCREMENT NOT NULL COMMENT'主键id',
	uul_serviceId					  INT 					COMMENT'服务商id',
	uul_name                VARCHAR(50)   COMMENT'等级名称',
	uul_level								TINYINT			  COMMENT'等级数字标识',
	uul_discount            DOUBLE        COMMENT'折扣价格',
	uul_discount1           DOUBLE        COMMENT'折扣价格(备用)',
	uul_desc                VARCHAR(255)  COMMENT'等级描述',
	uul_levelPic            VARCHAR(255)  COMMENT'等级图片',
	uul_growthBeginValue    DOUBLE        COMMENT'成长值(下限)',
	uul_growthEndValue      DOUBLE        COMMENT'成长值(上限)',
	uul_validBeginDate      DATETIME      COMMENT'有效期开始时间(预留)',
	uul_validEndDate        DATETIME      COMMENT'有效期结束时间(预留)',
	uul_introduce						TEXT					COMMENT'等级简介',
	uul_isDel               TINYINT       COMMENT'显示标记',
	uul_createDate          DATETIME      COMMENT'创建时间',
	uul_updateDate          DATETIME      COMMENT'更新时间',
	uul_reserved1           VARCHAR(255)  COMMENT'保留字段1',
	uul_reserved2           VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(uul_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'会员等级';
/*==============================================================*/
/* Table: uc_message        系统消息表                          */
/*==============================================================*/
DROP TABLE IF EXISTS uc_message;
CREATE TABLE uc_message(
	um_id                  INT    			 AUTO_INCREMENT NOT NULL COMMENT'主键id',
	um_type      					 TINYINT    	 COMMENT'消息类型0系统消息',
	um_custId              INT    			 COMMENT'发件人',
	um_sender							 VARCHAR(50)	 COMMENT'发件人名称',
	um_title               VARCHAR(100)  COMMENT'消息标题',
	um_desc                VARCHAR(200)  COMMENT'描述',
	um_content             TEXT          COMMENT'消息内容',
	um_isExternal          TINYINT    	 COMMENT'是否外部链接0否1是',
	um_url                 VARCHAR(255)  COMMENT'外部链接',
	um_endDate             DATETIME      COMMENT'截止日期',
	um_isDel               TINYINT   	   COMMENT'显示标记',
	um_createDate          DATETIME      COMMENT'创建时间',
	um_updateDate          DATETIME      COMMENT'更新时间',
	um_reserved1           VARCHAR(255)  COMMENT'保留字段1',
	um_reserved2           VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(um_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'站内信';
/*==============================================================*/
/* Table: uc_ref_user_message        用户消息关联表              */
/*==============================================================*/
DROP TABLE IF EXISTS uc_ref_user_message;
CREATE TABLE uc_ref_user_message(
	urum_id                  INT   				 AUTO_INCREMENT NOT NULL COMMENT'主键id',
	urum_serviceId					 INT 					 COMMENT'服务商id',
	urum_custId              INT   				 COMMENT'用户id',
	urum_messageId           INT    			 COMMENT'消息id',
	urum_haveRead            TINYINT   	   COMMENT'是否已读',
	urum_haveMark            TINYINT    	 COMMENT'是否标记',
	urum_isDel               TINYINT    	 COMMENT'显示标记',
	urum_createDate          DATETIME      COMMENT'创建时间',
	urum_updateDate          DATETIME      COMMENT'更新时间',
	urum_reserved1           VARCHAR(255)  COMMENT'保留字段1',
	urum_reserved2           VARCHAR(255)  COMMENT'保留字段2',

	PRIMARY KEY(urum_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'用户消息关联表';
/*==============================================================*/
/* Table: uc_cust_card        会员卡类型表                       */
/*==============================================================*/
DROP TABLE IF EXISTS uc_cust_card;
CREATE TABLE uc_cust_card(
	ucc_id                  INT    				AUTO_INCREMENT NOT NULL COMMENT'主键id',
	ucc_serviceId						INT		 				COMMENT '服务商id',
	ucc_name								VARCHAR(50)		COMMENT '会员卡名称',
	ucc_desc 								VARCHAR(255)	COMMENT '描述',
	ucc_pic									VARCHAR(255)	COMMENT '图片地址',
	ucc_rule								VARCHAR(200)	COMMENT '生成规则',
	ucc_introduce						TEXT					COMMENT '详细介绍',
	ucc_isDel               TINYINT    	  COMMENT '显示标记',
	ucc_createDate          DATETIME      COMMENT '创建时间',
	ucc_updateDate          DATETIME      COMMENT '更新时间',
	ucc_reserved1           VARCHAR(255)  COMMENT '保留字段1',
	ucc_reserved2           VARCHAR(255)  COMMENT '保留字段2',

	PRIMARY KEY(ucc_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'会员卡类型表';
/*==============================================================*/
/* Table: uc_leave_message        会员留言表                     */
/*==============================================================*/
DROP TABLE IF EXISTS uc_leave_message;
CREATE TABLE uc_leave_message(
	ulm_id                  INT    				AUTO_INCREMENT NOT NULL COMMENT'主键id',
	ulm_serviceId			 		  INT 					COMMENT '服务商id',
	ulm_custId							INT		 				COMMENT '用户id',
	ulm_name								VARCHAR(255)  COMMENT '姓名',
	ulm_email								VARCHAR(50)		COMMENT 'emial',
	ulm_phone								VARCHAR(15)		COMMENT '手机号',
	ulm_context							TEXT					COMMENT '内容',
	ulm_contact							VARCHAR(100)	COMMENT '联系方式',
	ulm_isDel               TINYINT    	  COMMENT '显示标记',
	ulm_createDate          DATETIME      COMMENT '创建时间',
	ulm_updateDate          DATETIME      COMMENT '更新时间',
	ulm_reserved1           VARCHAR(255)  COMMENT '保留字段1',
	ulm_reserved2           VARCHAR(255)  COMMENT '保留字段2',

	PRIMARY KEY(ulm_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT'会员留言表';



