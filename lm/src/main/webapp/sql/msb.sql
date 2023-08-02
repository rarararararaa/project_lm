CREATE TABLE BOARD_ANNOUNCE (
	notice_num	number	NOT NULL,
	notice_title	varchar2(300)	NOT NULL,
	notice_content	varchar2(1000)	NOT NULL,
	notice_category	number	NOT NULL,
	notice_reg_date	date	DEFAULT SYSDATE	NOT NULL,
	notice_modify_date	date	NULL,
	notice_hit number default 0 NOT NULL,
	notice_ip varchar2 NOT NULL,
    CONSTRAINT PK_BOARD_ANNOUNCE_ADMIN PRIMARY KEY (notice_num)    
);
create sequence board_announce_seq;


CREATE TABLE LIB_LOST_ITEM (
	item_num	number	NOT NULL,
	item_title	varchar2(30)	NOT NULL,
	item_content	varchar2(1000)	NOT NULL,
	item_reg_date	date	DEFAULT SYSDATE	NOT NULL,
	item_modify_date date null,
	item_status	number(1)	NOT NULL,
	item_hit	number default 0 NOT NULL,
    CONSTRAINT PK_LIB_LOST_ITEM_ADMIN PRIMARY KEY (item_num)    
);
create sequence lib_lost_item_seq;

CREATE TABLE LIB_BOARD_ASK (
	ask_num	number	NOT NULL,
	mem_num	number(5)	NOT NULL,
	ask_title	varchar2(300)	NOT NULL,
	ask_content	varchar2(1000)	NOT NULL,
	ask_category	number	NOT NULL,
	ask_reg_date	date	DEFAULT SYSDATE	NOT NULL,
	ask_image	blob	NULL,
    CONSTRAINT PK_LIB_BOARD_ASK PRIMARY KEY (ask_num),
  CONSTRAINT FK_LIB_BOARD_ASK_1 FOREIGN KEY (mem_num)    
                                REFERENCES lm_member_manage(mem_num)     
);

CREATE TABLE BOARD_ANSWER_ADMIN (
	answer_num	number	NOT NULL,
	ask_num	number	NOT NULL,
	mem_num	number(5)	NOT NULL,
	answer_content	varchar2(1000)	NOT NULL,
	answer_reg_date	date	DEFAULT SYSDATE	NOT NULL,
	answer_image	blob	NULL,
    CONSTRAINT PK_BOARD_ANSWER_ADMIN PRIMARY KEY (answer_num),
  CONSTRAINT FK_BOARD_ANSWER_ADMIN_1 FOREIGN KEY (mem_num)    
                                REFERENCES lm_member_manage(mem_num)     ,
  CONSTRAINT FK_BOARD_ANSWER_ADMIN_2 FOREIGN KEY (ask_num)    
                                REFERENCES lib_board_ask(ask_num)                                
);

CREATE TABLE LIB_SCHEDULE_ADMIN (
	schedule_num	NUMBER	NOT NULL,
	schedule_title	VARCHAR2(300)	NOT NULL,
	schedule_start	DATE	NOT NULL,
	schedule_end	DATE	NOT NULL,
    CONSTRAINT PK_LIB_SCHEDULE_ADMIN PRIMARY KEY (schedule_num)    
);

CREATE TABLE LIB_PROGRAM_ADMIN (
	program_num	number	NOT NULL,
	program_title	varchar2(300)	NOT NULL,
	program_content	varchar2(1000)	NOT NULL,
	program_reg_date	date	DEFAULT SYSDATE	NOT NULL,
	program_date	date	NOT NULL,
	program_admit	number	NOT NULL,
	program_hit	number	NOT NULL,
	program_image	blob	NULL,
    CONSTRAINT PK_LIB_PROGRAM_ADMIN PRIMARY KEY (program_num)    
);

CREATE TABLE LIB_FACILITY_ADMIN (
	facility_num	number	NOT NULL,
	facility_name	varchar2(30)	NOT NULL,
	facility_content	varchar2(1000),
	facility_image	blob,
    CONSTRAINT PK_LIB_FACILITY_ADMIN PRIMARY KEY (facility_num)    
);

CREATE TABLE LIB_FACILITY_APPLY (
	facility_apply_num	number	NOT NULL,
	facility_num	number	NOT NULL,
	mem_num	number(5)	NOT NULL,
	facility_apply_reg_date	date	DEFAULT SYSDATE	NOT NULL,
	facility_apply_start	date	NOT NULL,
	facility_apply_end	date	NOT NULL,
    CONSTRAINT PK_LIB_FACILITY_APPLY PRIMARY KEY (facility_apply_num),
  CONSTRAINT FK_LIB_FACILITY_APPLY_1 FOREIGN KEY (mem_num)    
                                REFERENCES lm_member_manage(mem_num),
 CONSTRAINT FK_LIB_FACILITY_APPLY_2 FOREIGN KEY (facility_num)    
                                REFERENCES lib_facility_admin(facility_num)                                
);

ALTER TABLE STORE_PRODUCT_MANAGE ADD CONSTRAINT STORE_PRODUCT_MANAGE_uk UNIQUE (STORE_PRODUCT_ISBN13);


