-- 문고 이벤트 폼
CREATE TABLE STORE_EVENT_BOARD (
	event_board_num	number	NOT NULL,
	store_product_num	number(5),
	event_board_status	number	NOT NULL,
	event_board_category	number	NOT NULL,
	event_title	varchar2(500)	NOT NULL,
	event_short_content	varchar2(500)	NOT NULL,
	event_content	clob	NOT NULL,
	event_img_small	blob	NOT NULL,
	event_img_big	blob	NULL,
	event_reg_date	date	NOT NULL,
	event_date_start	varchar2(10)	NOT NULL,
	event_date_end	varchar2(10)	NOT NULL,
	event_hit	number(9)	default 0 NOT NULL,
	event_quiz_sel1	varchar2(100)	NULL,
	event_quiz_sel2	varchar2(100)	NULL,
	event_quiz_an	number	NULL,
	event_modify_date date,
    CONSTRAINT PK_STORE_EVENT_BOARD PRIMARY KEY (event_board_num),
);
create sequence store_event_board_seq;

-- 문고 이벤트 댓글
CREATE TABLE STORE_EVENT_BOARD_REPLY (
	reply_num	number	NOT NULL,
	event_board_num	number	NOT NULL,
	mem_num	number	NOT NULL,
	reply_date	date	DEFAULT SYSDATE	NOT NULL,
	reply_modify_date date,
	reply_content	varchar2(3000)	NOT NULL,
    CONSTRAINT PK_STORE_EVENT_BOARD_REPLY PRIMARY KEY (reply_num) ,
    CONSTRAINT FK_STORE_EVENT_BOARD_REPLY_1 FOREIGN KEY (event_board_num)
                                            REFERENCES store_event_board(event_board_num),
    CONSTRAINT FK_STORE_EVENT_BOARD_REPLY_2 FOREIGN KEY (mem_num)
                                            REFERENCES lm_member_manage(mem_num)                                            
);
create sequence STORE_EVENT_BOARD_REPLY_seq;
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
create sequence store_event_quiz_status_seq;

-- 문고 이벤트 당첨자 발표 게시판 테이블
create table store_event_announce_board (
	event_announce_board_num number,
	title varchar2(90) not null,
 	content clob not null,
 	hit number(8) default 0 not null,
 	reg_date date default sysdate not null,
 	modify_date date,
	constraint store_event_announce_board_pk primary key (event_announce_board_num)
);
create sequence store_event_announce_board_seq;

-- 분실 도서 신고
CREATE TABLE LIB_BOOK_LOST_REPORT (
	lost_report_num	NUMBER NOT NULL,
	callNumber	varchar2(10)	NOT NULL,
	mem_num	number(5)	NOT NULL,
	rent_num	number	NOT NULL,
	store_product_pricesales	number	NOT NULL,
	lost_reg_date	DATE	DEFAULT SYSDATE	NOT NULL,
	lost_payment_status number NOT NULL,
	lost_payment_type number NOT NULL,
	imp_uid varchar(20) NOT NULL,
    CONSTRAINT PK_LIB_BOOK_LOST_REPORT PRIMARY KEY (lost_report_num),
    CONSTRAINT FK_LIB_BOOK_LOST_REPORT_1 FOREIGN KEY (mem_num)
                                            REFERENCES lm_member_manage(mem_num),
    CONSTRAINT FK_LIB_BOOK_LOST_REPORT_2 FOREIGN KEY (callNumber)
                                            REFERENCES lib_product_manage(callNumber) ,
    CONSTRAINT FK_LIB_BOOK_LOST_REPORT_3 FOREIGN KEY (rent_num)
                                            REFERENCES LIB_HISTORY(rent_num)                                           
);

create sequence lib_book_lost_report_seq;

CREATE TABLE lib_calendar(
	calendar_num number not null,
	title varchar2(50) not null,
	start_date varchar2(10) not null,
	start_time varchar2(10),
	end_date varchar2(10),
	end_time varchar2(10),
	allday number not null,
	event_status number,
	CONSTRAINT PK_LiB_CALENDAR PRIMARY KEY (calendar_num)
);

create sequence lib_calendar_seq;
