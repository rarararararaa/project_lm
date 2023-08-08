CREATE TABLE LM_BOARD_ANNOUNCE (
	board_num	NUMBER	NOT NULL,
	board_title	varchar2(300)	NOT NULL,
	board_content	clob	NOT NULL,
	board_reg_date	DATE 	DEFAULT SYSDATE	NOT NULL,
	board_modify_date	DATE	NULL,
	board_filename	varchar2(300)	NULL,
    CONSTRAINT PK_LM_BOARD_ANNOUNCE PRIMARY KEY (board_num)        
);

CREATE TABLE LIB_BOOK_DONATION (
	donation_num	number(5)	NOT NULL,
	donation_book_info	varchar2(300)	NOT NULL,
	donation_title	varchar2(200)	NOT NULL,
	donation_content	varchar2(1000)	NOT NULL,
	donation_reg_date	date	DEFAULT SYSDATE	NOT NULL,
	donation_name	varchar2(300)	NOT NULL,
	donation_info	varchar2(300)	NULL,
	donation_status	number(1)	DEFAULT 0	NOT NULL,
    CONSTRAINT PK_LIB_BOOK_DONATION PRIMARY KEY (donation_num)    
);
CREATE TABLE LIB_FACILITY_ADMIN (
	facility_num	number	NOT NULL,
	facility_name	varchar2(30)	NOT NULL,
	facility_content	varchar2(1000),
	facility_image	blob,
	facility_imagename varchar2(25),
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

CREATE TABLE LM_BOARD_FAQ (
	faq_num	number	NOT NULL,
	faq_title	varchar2(300)	NOT NULL,
	faq_content	varchar2(4000)	NOT NULL,
	faq_category	number	NOT NULL,
	faq_reg_date	DATE	DEFAULT SYSDATE	NOT NULL,
    CONSTRAINT PK_LM_BOARD_FAQ PRIMARY KEY (faq_num)        

);