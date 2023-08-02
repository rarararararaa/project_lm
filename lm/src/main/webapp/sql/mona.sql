-- 문고 이벤트 폼
CREATE TABLE STORE_EVENT_BOARD (
	event_board_num	number	NOT NULL,
	store_product_num	number(5)	NOT NULL,
	event_board_status	number	NOT NULL,
	event_board_category	number	NOT NULL,
	event_title	varchar2(500)	NOT NULL,
	event_short_content	varchar2(500)	NOT NULL,
	event_content	varchar2(1000)	NOT NULL,
	event_img_small	blob	NOT NULL,
	event_img_big	blob	NULL,
	event_reg_date	date	NOT NULL,
	event_date_start	date	NOT NULL,
	event_date_end	date	NOT NULL,
	event_hit	number(9)	default 0 NOT NULL,
	event_quiz_sel1	varchar2(100)	NULL,
	event_quiz_sel2	varchar2(100)	NULL,
	event_quiz_an	varchar2(100)	NULL,
    CONSTRAINT PK_STORE_EVENT_BOARD PRIMARY KEY (event_board_num),
    CONSTRAINT FK_STORE_EVENT_BOARD_1 FOREIGN KEY (store_product_num)
                                            REFERENCES store_product_manage(store_product_num)     
);
create sequence store_event_board_seq;

-- 문고 이벤트 댓글
CREATE TABLE STORE_EVENT_BOARD_REPLY (
	reply_num	number	NOT NULL,
	event_board_num	number	NOT NULL,
	mem_num	number	NOT NULL,
	reply_date	date	DEFAULT SYSDATE	NOT NULL,
	reply_content	varchar2(3000)	NOT NULL,
    CONSTRAINT PK_STORE_EVENT_BOARD_REPLY PRIMARY KEY (reply_num) ,
    CONSTRAINT FK_STORE_EVENT_BOARD_REPLY_1 FOREIGN KEY (event_board_num)
                                            REFERENCES store_event_board(event_board_num),
    CONSTRAINT FK_STORE_EVENT_BOARD_REPLY_2 FOREIGN KEY (mem_num)
                                            REFERENCES lm_member_manage(mem_num)                                            
);
-- 문고 이벤트(퀴즈) 기록
CREATE TABLE STORE_EVENT_QUIZ_STATUS (
	event_quiz_status_num	number	NOT NULL,
	event_board_num	number	NOT NULL,
	mem_num	number	NOT NULL,
    CONSTRAINT PK_STORE_EVENT_QUIZ_STATUS PRIMARY KEY (event_quiz_status_num),
    CONSTRAINT FK_STORE_EVENT_QUIZ_STATUS_1 FOREIGN KEY (event_board_num)
                                            REFERENCES store_event_board(event_board_num),
    CONSTRAINT FK_STORE_EVENT_QUIZ_STATUS_2 FOREIGN KEY (mem_num)
                                            REFERENCES lm_member_manage(mem_num)                                         
);
-- 문고 이벤트(출석)기록
CREATE TABLE STORE_EVENT_ATTENDANCE (
	event_attendance_num	number(5)	NOT NULL,
	mem_num	number(5)	NOT NULL,
	event_attendance_point_num	number(5)	NOT NULL,
	--event_month	number	NOT NULL,
	event_attendance_date	DATE	DEFAULT SYSDATE	NOT NULL,
    CONSTRAINT PK_STORE_EVENT_ATTENDANCE PRIMARY KEY (event_attendance_num),
    CONSTRAINT FK_STORE_EVENT_ATTENDANCE_1 FOREIGN KEY (event_attendance_point_num)
                                            REFERENCES store_event_attendance_point(event_attendance_point_num),
    CONSTRAINT FK_STORE_EVENT_ATTENDANCE_2 FOREIGN KEY (mem_num)
                                            REFERENCES lm_member_manage(mem_num)                                            
);
create sequence store_event_attendance_seq;

 -- 문고 이벤트 출석 포인트 부여 여부
CREATE TABLE STORE_EVENT_ATTENDANCE_POINT (
	event_attendance_point_num	number(5)	NOT NULL,
	mem_num	number(5)	NOT NULL,
	event_month number NOT NULL,
	POINT_GET1	NUMBER(1)	DEFAULT 0	NOT NULL,
	POINT_GET2	number(1)	DEFAULT 0	NOT NULL,
	POINT_GET3	NUMBER(1)	DEFAULT 0	NOT NULL,
    CONSTRAINT PK_STORE_EVENT_ATTENDANCE_POINT PRIMARY KEY (event_attendance_point_num),
    CONSTRAINT FK_STORE_EVENT_ATTENDANCE_POINT_1 FOREIGN KEY (mem_num)
                                            REFERENCES lm_member_manage(mem_num)       
);
create sequence store_event_attendance_point_seq;