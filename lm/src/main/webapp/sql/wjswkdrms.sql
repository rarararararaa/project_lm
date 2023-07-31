CREATE TABLE LM_BOARD_ANNOUNCE (
	board_num	NUMBER	NOT NULL,
	board_title	varchar2(300)	NOT NULL,
	board_content	clob	NOT NULL,
	board_reg_date	DATE 	DEFAULT SYSDATE	NOT NULL,
	board_modify_date	DATE	NULL,
	board_filename	varchar2(300)	NULL,
    CONSTRAINT PK_LM_BOARD_ANNOUNCE PRIMARY KEY (board_num)        
);