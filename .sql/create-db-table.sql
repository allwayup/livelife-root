CREATE DATABASE `livelife_log` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE `livelife_common` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE `livelife_config` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE `livelife_auth` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE `livelife_gateway` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE `livelife_serve_register` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE TABLE livelife_auth.au_login (
	au_lo_id BIGINT auto_increment NOT NULL COMMENT '唯一主键',
	au_lo_name varchar(10) NULL COMMENT '姓名',
	au_lo_identity_card varchar(20) NULL COMMENT '身份证',
	au_lo_phone_number varchar(20) NULL COMMENT '手机号',
	au_lo_sex varchar(2) NULL COMMENT '性别',
	au_lo_age varchar(3) NULL COMMENT '年龄',
	au_lo_head_portrait varchar(100) NULL COMMENT '头像',
	au_lo_alias varchar(50) NULL COMMENT '别名',
    au_lo_status varchar(2) DEFAULT 0 NULL COMMENT '状态(1启用0停用)',
	au_lo_sort DECIMAL(10,2) NULL COMMENT '排序',
	au_lo_create_time varchar(50) NULL COMMENT '创建时间',
	au_lo_update_time varchar(50) NULL COMMENT '更新时间',
	au_lo_is_delete varchar(2) DEFAULT 0 NULL COMMENT '记录是否已删除(0未删1已删)',
	CONSTRAINT au_login_pk PRIMARY KEY (au_lo_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='登陆用户表';

CREATE TABLE livelife_common.cm_dictionary (
	cm_di_id BIGINT auto_increment NOT NULL COMMENT '字典主键',
	cm_di_pid BIGINT DEFAULT 0 NULL COMMENT '父级主键',
	cm_di_value_type varchar(10) DEFAULT 'VALUE' NULL COMMENT '类型(VALUE,MAP,ARRAY)',
	cm_di_name varchar(50) NULL COMMENT '字典名称',
	cm_di_value LONGTEXT NULL COMMENT '字典值',
	cm_di_index varchar(100) NULL COMMENT '在父级中的下标',
    cm_di_status varchar(2) DEFAULT 0 NULL COMMENT '状态(1启用0停用)',
	cm_di_sort DECIMAL(10,2) NULL COMMENT '排序',
	cm_di_create_time varchar(50) NULL COMMENT '创建时间',
	cm_di_update_time varchar(50) NULL COMMENT '更新时间',
	cm_di_is_delete varchar(2) DEFAULT 0 NULL COMMENT '记录是否已删除(0未删1已删)',
	CONSTRAINT cm_dictionary_pk PRIMARY KEY (cm_di_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='字典表';

CREATE TABLE livelife_config.cf_springboot (
	cf_sb_id BIGINT auto_increment NOT NULL COMMENT 'springboot配置ID',
	cf_sb_serve_name varchar(100) NULL COMMENT '服务名称',
	cf_sb_name varchar(100) NULL COMMENT '配置名称',
	sf_sb_type varchar(10) NULL COMMENT '配置类型(yml,properties)',
	cf_sb_content LONGTEXT NULL COMMENT '配置内容',
    cf_sb_status varchar(2) DEFAULT 0 NULL COMMENT '状态(1启用0停用)',
	cf_sb_sort DECIMAL(10,2) NULL COMMENT '排序',
	cf_sb_create_time varchar(50) NULL COMMENT '创建时间',
	cf_sb_update_time varchar(50) NULL COMMENT '更新时间',
	cf_sb_is_delete varchar(2) DEFAULT 0 NULL COMMENT '记录是否已删除(0未删1已删)',
	CONSTRAINT cf_springboot_pk PRIMARY KEY (cf_sb_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='JAVA服务启动配置表';

