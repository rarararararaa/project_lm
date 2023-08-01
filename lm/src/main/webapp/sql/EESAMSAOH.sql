CREATE TABLE LM_MEMBER_MANAGE (
	mem_num	number(5) NOT NULL,
	mem_id	VARCHAR2(12) NOT NULL,
	mem_auth	number(1) DEFAULT 4 NOT NULL,
	mem_reg_date	DATE 	DEFAULT SYSDATE NOT NULL,
	mem_stop_date	DATE	NULL,
	mem_exp_date	DATE	NULL,
	mem_stop_reason	varchar2(50)	NULL,
    CONSTRAINT PK_LM_MEMBER_MANAGE PRIMARY KEY (mem_num) 
);

CREATE TABLE LM_MEMBER_DETAIL (
	mem_num	number	NOT NULL,
	mem_name	VARCHAR2(30)	NOT NULL,
	mem_passwd	VARCHAR2(20)	NOT NULL,
	mem_cell	VARCHAR2(15)	NOT NULL,
	mem_email	VARCHAR2(50)	NOT NULL,
	mem_identify	NUMBER(14)	NOT NULL,
	mem_point	NUMBER(10) 	DEFAULT 2000	NOT NULL,
	mem_photo	VARCHAR2(150)	NULL,
	mem_modify_date	DATE	NULL,
	mem_login_date	date	NULL,
	mem_salt	varchar2(50)	NOT NULL,
	mem_grade	number(1) 	DEFAULT 0	NOT NULL,
    CONSTRAINT PK_LM_MEMBER_DETAIL PRIMARY KEY (mem_num),
    CONSTRAINT FK_LM_MEMBER_DETAIL_1 FOREIGN KEY (
        mem_num
    )
    REFERENCES LM_MEMBER_MANAGE (
        mem_num
    )    
);

CREATE TABLE STORE_MEMBER_HOME (
	home_num	NUMBER(5)	NOT NULL,
	mem_num	number	NOT NULL,
	home_title	VARCHAR2(20)	NOT NULL,
	home_zipcode	VARCHAR2(6)	NOT NULL,
	home_address	VARCHAR2(150)	NOT NULL,
	home_address_detail	VARCHAR2(150)	NOT NULL,
	home_cell	varchar2(15)	NOT NULL,
	home_status	number(5)	NOT NULL,
    CONSTRAINT PK_STORE_MEMBER_HOME PRIMARY KEY (home_num),
    CONSTRAINT FK_STORE_MEMBER_HOME_1 FOREIGN KEY (
        mem_num
    )
    REFERENCES LM_MEMBER_MANAGE (
        mem_num
    )
);

CREATE TABLE STORE_MEMBER_ZZIM (
	zzim_num	VARCHAR(255)	NOT NULL,
	store_product_num	number(5)	NOT NULL,
	mem_num	number(5)	NOT NULL,
    CONSTRAINT PK_STORE_MEMBER_ZZIM PRIMARY KEY (zzim_num),
    CONSTRAINT FK_STORE_MEMBER_ZZIM_1 FOREIGN KEY (
        mem_num
    )
    REFERENCES LM_MEMBER_MANAGE (
        mem_num
    ),
    CONSTRAINT FK_STORE_MEMBER_ZZIM_2 FOREIGN KEY (
        store_product_num
    )
    REFERENCES store_product_manage (
        store_product_num
    )    
);

CREATE TABLE STORE_PRODUCT_MANAGE (
	store_product_num	number(5)	NOT NULL,
	store_product_categoryId	number	NOT NULL,
	store_product_ItemIdType	varchar2(6) DEFAULT 'ISBN13'	NOT NULL	,
	store_product_ISBN13	varchar2(13)	NOT NULL,
	store_product_stockstatus	number	NOT NULL,
    CONSTRAINT PK_STORE_PRODUCT_MANAGE PRIMARY KEY (store_product_num)    
);

CREATE TABLE STORE_PRODUCT_DETAIL (
	store_product_num	number	NOT NULL,
	store_product_title	varchar2(30)	NOT NULL,
	store_product_author	varchar2(30)	NOT NULL,
	store_product_pubdate	varchar2(50)	NOT NULL,
	store_product_cover	blob	NOT NULL,
	store_product_description	varchar2(600)	NOT NULL,
	store_product_categoryName	varchar2(50)	NOT NULL,
	store_product_categoryId	number	NOT NULL,
	store_product_priceSales	number	NOT NULL,
	store_product_priceStandard	number	NOT NULL,
	store_product_publisher	varchar2(50)	NOT NULL,
	store_product_stock	number	NOT NULL,
	store_product_seriesId	number	NULL,
	store_product_seriesLink	varchar2(600)	NULL,
	store_product_seriesName	varchar2(600)	NULL,
	store_product_customerReviewRank	number(2)	DEFAULT 0	NOT NULL,
	store_product_ratingCount	number(5)	DEFAULT 0	NOT NULL,
	store_product_discount	number DEFAULT 0	NOT NULL	,
	store_product_status	number(1)	DEFAULT 0 NOT NULL	,
	store_product_ratingScore	number(2)	DEFAULT 0	NOT NULL,
    CONSTRAINT PK_STORE_PRODUCT_DETAIL PRIMARY KEY (store_product_num),
    CONSTRAINT FK_STORE_PRODUCT_DETAIL_1 FOREIGN KEY (
        store_product_num
    )
    REFERENCES STORE_PRODUCT_MANAGE (
        store_product_num
    )    
);

CREATE TABLE STORE_CART (
	mem_cart_num	number	NOT NULL,
	mem_num	number	NOT NULL,
	store_product_num	number(5)	NOT NULL,
	cart_quantity	number(4)	DEFAULT 1 NOT NULL	,
    CONSTRAINT PK_STORE_CART PRIMARY KEY (mem_cart_num),
     CONSTRAINT FK_STORE_CART_1 FOREIGN KEY (
        store_product_num
    )
    REFERENCES STORE_PRODUCT_MANAGE (
        store_product_num
    )   ,
    CONSTRAINT FK_STORE_CART_2 FOREIGN KEY (
        mem_num
    )
    REFERENCES lm_member_manage (
        mem_num
    )    
);

CREATE TABLE STORE_ORDER_MANAGE (
	order_num	number	NOT NULL,
	home_num	NUMBER(5)	NOT NULL,
	mem_num	number(5)	NOT NULL,
	order_total_price	number(10)	NOT NULL,
	order_status	number(1)	DEFAULT 0	NOT NULL,
	order_date	DATE	DEFAULT SYSDATE	NOT NULL,
	order_notice	varchar2(100)	NULL,
	order_pay_status	number(1) DEFAULT 1	NOT NULL	,
	payment_type	number(1)	NOT NULL,
	imp_uid	varchar2(20)	NOT NULL,
    CONSTRAINT PK_STORE_ORDER_MANAGE PRIMARY KEY (order_num),
    CONSTRAINT FK_STORE_ORDER_MANAGE_1 FOREIGN KEY (
        mem_num
    )
    REFERENCES lm_member_manage (
        mem_num
    ),
    CONSTRAINT FK_STORE_ORDER_MANAGE_2 FOREIGN KEY (
       home_num
    )
    REFERENCES store_member_home (
        home_num
    )      
);

CREATE TABLE STORE_ORDER_DETAIL (
	order_detail_num	number	NOT NULL,
	order_num	number	NOT NULL,
	store_product_num	number	NOT NULL,
	order_product_price	number(7)	NOT NULL,
	order_product_quantity	number(5)	NOT NULL,
    CONSTRAINT PK_STORE_ORDER_DETAIL PRIMARY KEY (order_detail_num)  ,
     CONSTRAINT FK_STORE_ORDER_DETAIL_1 FOREIGN KEY (
       order_num
    )
    REFERENCES store_order_manage (
       order_num
    ) ,
      CONSTRAINT FK_STORE_ORDER_DETAIL_2 FOREIGN KEY (
       store_product_num
    )
    REFERENCES store_product_manage (
       store_product_num
    )   
);

CREATE TABLE LM_BOARD_ANNOUNCE (
	board_num	NUMBER	NOT NULL,
	board_title	varchar2(300)	NOT NULL,
	board_content	clob	NOT NULL,
	board_reg_date	DATE 	DEFAULT SYSDATE	NOT NULL,
	board_modify_date	DATE	NULL,
	board_filename	varchar2(300)	NULL,
    CONSTRAINT PK_LM_BOARD_ANNOUNCE PRIMARY KEY (board_num)        
);

CREATE TABLE LM_BOARD_FAQ (
	faq_num	number	NOT NULL,
	faq_title	varchar2(300)	NOT NULL,
	faq_content	varchar2(1000)	NOT NULL,
	faq_category	number	NOT NULL,
	faq_reg_date	DATE	DEFAULT SYSDATE	NOT NULL,
    CONSTRAINT PK_LM_BOARD_FAQ PRIMARY KEY (faq_num)        

);

CREATE TABLE LM_BOARD_ASK (
	ask_num	number	NOT NULL,
	mem_num	number	NOT NULL,
	ask_title	varchar2(300)	NOT NULL,
	ask_content	varchar2(1000)	NOT NULL,
	ask_category	number	NOT NULL,
	ask_reg_date	date	DEFAULT SYSDATE	NOT NULL,
	ask_image	blob	NULL,
    CONSTRAINT PK_LM_BOARD_ASK PRIMARY KEY (ask_num),
    CONSTRAINT FK_LM_BOARD_ASK_1 FOREIGN KEY (mem_num)    
                                 REFERENCES lm_member_manage(mem_num)
);

CREATE TABLE LM_BOARD_ANSWER (
	answer_num	number	NOT NULL,
	ask_num	number	NOT NULL,
	mem_num	number	NOT NULL,
	answer_content	varchar2(1000)	NOT NULL,
	answer_reg_date	date	DEFAULT SYSDATE	NOT NULL,
	answer_image	blob	NULL,
    CONSTRAINT PK_LM_BOARD_ANSWER PRIMARY KEY (answer_num)    ,
    CONSTRAINT FK_LM_BOARD_ANSWER_1 FOREIGN KEY (mem_num)    
                                 REFERENCES lm_member_manage(mem_num),
    CONSTRAINT FK_LM_BOARD_ANSWER_2 FOREIGN KEY (ask_num)    
                                 REFERENCES lm_board_ask(ask_num)                                 
);

CREATE TABLE LM_REVIEW (
	review_num	number	NOT NULL,
	order_detail_num	number	NOT NULL,
	mem_num	number(5)	NOT NULL,
	review_content	varchar2(1000)	NOT NULL,
	review_reg_date	date	NOT NULL,
	review_image	varchar2(600)	NULL,
	review_rating	number	NOT NULL,
    CONSTRAINT PK_LM_REVIEW PRIMARY KEY (review_num),
    CONSTRAINT FK_LM_REVIEW_1 FOREIGN KEY (order_detail_num) 
                              REFERENCES store_order_detail(order_detail_num),
    CONSTRAINT FK_LM_REVIEW_2 FOREIGN KEY (mem_num) 
                              REFERENCES lm_member_manage(mem_num)                              
);

CREATE TABLE LM_COUPON (
	coupon_num	number(5)	NOT NULL,
	coupon_value	number(5)	DEFAULT 0	NOT NULL,
	coupon_status	number(1)	NOT NULL,
	coupon_name	varchar2(50)	NOT NULL,
	coupon_expire_date	date	NOT NULL,
    CONSTRAINT PK_LM_COUPON PRIMARY KEY (coupon_num)    
);

CREATE TABLE STORE_USED_PRODUCT_MANAGE (
	used_product_num	number(5)	NOT NULL,
	store_product_num	number	NOT NULL,
	mem_num	number(5)	NOT NULL,
	used_product_status	number(1)	DEFAULT 1	NOT NULL,
	used_product_condition	number(1)	NOT NULL,
	used_product_approve	number(1)	DEFAULT 0	NOT NULL,
	used_product_reg_date	DATE	DEFAULT SYSDATE	NOT NULL,
	used_product_sales_date	DATE	NULL,
	used_product_hit	number(12)	DEFAULT 0	NOT NULL,
    CONSTRAINT PK_STORE_USED_PRODUCT_MANAGE PRIMARY KEY (used_product_num),
    CONSTRAINT FK_STORE_USED_PRODUCT_MANAGE_1 FOREIGN KEY (
        store_product_num
    )
    REFERENCES STORE_PRODUCT_MANAGE (
        store_product_num
    ),
    CONSTRAINT FK_STORE_USED_PRODUCT_MANAGE_2 FOREIGN KEY (
        mem_num
    )
    REFERENCES lm_member_manage(
        mem_num
    )    
);

CREATE TABLE STORE_USED_PRODUCT_DETAIL (
	used_product_num	number(5)	NOT NULL,
	used_product_info	varchar2(1000)	NOT NULL,
	used_product_photo1	blob	NOT NULL,
	used_product_photo2	blob	NOT NULL,
	used_product_price	number(7)	NOT NULL,
    CONSTRAINT PK_STORE_USED_PRODUCT_DETAIL PRIMARY KEY (used_product_num),
    CONSTRAINT FK_STORE_USED_PRODUCT_DETAIL_1 FOREIGN KEY (
        used_product_num
    )
    REFERENCES STORE_USED_PRODUCT_MANAGE (
        used_product_num
    )    
);

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
	event_hit	number(9)	NOT NULL,
	event_quiz_sel1	varchar2(100)	NULL,
	event_quiz_sel2	varchar2(100)	NULL,
	event_quiz_an	varchar2(100)	NULL,
    CONSTRAINT PK_STORE_EVENT_BOARD PRIMARY KEY (event_board_num),
    CONSTRAINT FK_STORE_EVENT_BOARD_1 FOREIGN KEY (store_product_num)
                                            REFERENCES store_product_manage(store_product_num)     
);

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

CREATE TABLE LM_STORE_EVENT_ATTENDANCE (
	event_attendance_num	number(5)	NOT NULL,
	mem_num	number(5)	NOT NULL,
	event_attendance_point_num	number(5)	NOT NULL,
	event_month	number	NOT NULL,
	event_attendance_date	DATE	DEFAULT SYSDATE	NOT NULL,
    CONSTRAINT PK_LM_STORE_EVENT_ATTENDANCE PRIMARY KEY (event_attendance_num),
    CONSTRAINT FK_LM_STORE_EVENT_ATTENDANCE_1 FOREIGN KEY (event_attendance_point_num)
                                            REFERENCES store_event_attendance_point(event_attendance_point_num),
    CONSTRAINT FK_LM_STORE_EVENT_ATTENDANCE_2 FOREIGN KEY (mem_num)
                                            REFERENCES lm_member_manage(mem_num)                                            
);

CREATE TABLE LIB_HISTORY (
	rent_num	number	NOT NULL,
	callNumber	varchar2(10)	NOT NULL,
	mem_num	number	NOT NULL,
	lib_product_status	number	DEFAULT 1	NOT NULL,
	rent_reg_date	date	DEFAULT SYSDATE	NOT NULL,
	return_reg_deadline	date	NOT NULL,
	return_reg_date	date	NULL,
    CONSTRAINT PK_LIB_HISTORY PRIMARY KEY (rent_num),
    CONSTRAINT FK_LIB_HISTORY_1 FOREIGN KEY (mem_num)    
                                REFERENCES LM_MEMBER_MANAGE(mem_num),
    CONSTRAINT FK_LIB_HISTORY_2 FOREIGN KEY (callNumber)    
                                REFERENCES lib_product_manage(callNumber)                                
);

CREATE TABLE LIB_PRODUCT_MANAGE (
	lib_product_isbn	varchar2(13)	NOT NULL,
	callNumber	varchar2(10)	NOT NULL,
	lib_product_class_no	number(10)	NOT NULL,
	lib_product_bookname	varchar2(50)	NULL,
	lib_product_authors	varchar2(10)	NOT NULL,
	lib_product_publisher	varchar2(30)	NOT NULL,
	lib_product_bookImageURL	blob	NOT NULL,
	lib_product_publication_year	varchar2(7)	NOT NULL,
	lib_product_description	varchar2(300)	NOT NULL,
	lib_product_loanCnt	number(10)	DEFAULT 0	NOT NULL,
	lib_product_product_status	number(1)	DEFAULT 1	NOT NULL,
    CONSTRAINT PK_LIB_PRODUCT_MANAGE PRIMARY KEY (callNumber),
   CONSTRAINT FK_LIB_PRODUCT_MANAGE_1 FOREIGN KEY(lib_product_isbn)
                                    REFERENCES store_product_manage(STORE_PRODUCT_ISBN13)
);

CREATE TABLE LIB_BOOK_REPORT (
	rep_num	number(5)	NOT NULL,
	mem_num	number(5)	NOT NULL,
	callNumber	varchar2(10)	NOT NULL,
	rep_title	varchar2(100)	NOT NULL,
	rep_content	varchar2(1000)	NOT NULL,
	rep_hit	number	DEFAULT 0	NOT NULL,
	rep_reg_date	date	DEFAULT SYSDATE	NOT NULL,
    CONSTRAINT PK_LIB_BOOK_REPORT PRIMARY KEY (rep_num),
    CONSTRAINT FK_LIB_BOOK_REPORT_1 FOREIGN KEY (mem_num)    
                                REFERENCES lm_member_manage(mem_num),
  CONSTRAINT FK_LIB_BOOK_REPORT_2 FOREIGN KEY (callNumber)    
                                REFERENCES lib_product_manage(callNumber)                                
);

CREATE TABLE LIB_MEMBER_ZZIM (
	zzim_num	number(5)	NOT NULL,
	mem_num	number(5)	NOT NULL,
	callNumber	varchar2(10)	NOT NULL,
    CONSTRAINT PK_LIB_MEMBER_ZZIM PRIMARY KEY (zzim_num),
  CONSTRAINT FK_LIB_MEMBER_ZZIM_1 FOREIGN KEY (callNumber)    
                                REFERENCES lib_product_manage(callNumber),
  CONSTRAINT FK_LIB_MEMBER_ZZIM_2 FOREIGN KEY (mem_num)    
                                REFERENCES lm_member_manage(mem_num)                                 
);

CREATE TABLE LIB_PROGRAM_APPLY_USER (
	program_apply_num	number	NOT NULL,
	mem_num	number(5)	NOT NULL,
	program_num	number	NOT NULL,
	program_content	varchar2(1000)	NOT NULL,
	program_reg_date	date	DEFAULT SYSDATE	NOT NULL,
    CONSTRAINT PK_LIB_PROGRAM_APPLY_USER PRIMARY KEY (program_apply_num),
  CONSTRAINT FK_LIB_PROGRAM_APPLY_USER_1 FOREIGN KEY (mem_num)    
                                REFERENCES lm_member_manage(mem_num),
  CONSTRAINT FK_LIB_PROGRAM_APPLY_USER_2 FOREIGN KEY (program_num)    
                                REFERENCES lib_program_admin(program_num)                                
);

CREATE TABLE LIB_BOOK_APPLY (
	book_apply_num	number	NOT NULL,
	mem_num	number(5)	NOT NULL,
	book_apply_content	varchar2(1000)	NOT NULL,
	book_apply_status	number(1)	DEFAULT 0	NOT NULL,
	book_apply_reg_date	date	DEFAULT SYSDATE	NOT NULL,
	book_apply_title	varchar2(200)	NOT NULL,
    CONSTRAINT PK_LIB_BOOK_APPLY PRIMARY KEY (book_apply_num),
    CONSTRAINT FK_LIB_BOOK_APPLY_1 FOREIGN KEY (mem_num)    
                                REFERENCES lm_member_manage(mem_num)    
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

CREATE TABLE LIB_RESERVATION (
	reservation_num	number	NOT NULL,
	callNumber	varchar2(10)	NOT NULL,
	mem_num	number(5)	NOT NULL,
	reservation_status	number	DEFAULT 0	NOT NULL,
	reservation_submit_date	date	DEFAULT SYSDATE	NOT NULL,
    CONSTRAINT PK_LIB_RESERVATION PRIMARY KEY (reservation_num),
  CONSTRAINT FK_LIB_RESERVATION_1 FOREIGN KEY (mem_num)    
                                REFERENCES lm_member_manage(mem_num),
  CONSTRAINT FK_LIB_RESERVATION_2 FOREIGN KEY (callNumber)    
                                REFERENCES lib_product_manage(callNumber)                                
);

CREATE TABLE LM_COUPON_DETAIL (
	coupon_num	number(5)	NOT NULL,
	mem_num	number(5)	NOT NULL,
    CONSTRAINT PK_LM_COUPON_DETAIL PRIMARY KEY (coupon_num),
    CONSTRAINT FK_LM_COUPON_DETAIL_1 FOREIGN KEY (
        coupon_num
    )
    REFERENCES LM_COUPON (
        coupon_num
    ),
    CONSTRAINT FK_LM_COUPON_DETAIL_2 FOREIGN KEY (
        mem_num
    )
    REFERENCES LM_MEMBER_MANAGE(
        mem_num
    )       
);

CREATE TABLE STORE_EVENT_ATTENDANCE_POINT (
	event_attendance_point_num	number(5)	NOT NULL,
	mem_num	number(5)	NOT NULL,
	POINT_GET1	NUMBER(1)	DEFAULT 0	NOT NULL,
	POINT_GET2	number(1)	DEFAULT 0	NOT NULL,
	POINT_GET3	NUMBER(1)	DEFAULT 0	NOT NULL,
    CONSTRAINT PK_STORE_EVENT_ATTENDANCE_POINT PRIMARY KEY (event_attendance_point_num),
    CONSTRAINT FK_LM_STORE_EVENT_ATTENDANCE_POINT_1 FOREIGN KEY (mem_num)
                                            REFERENCES lm_member_manage(mem_num)       
);

CREATE TABLE LIB_BOOK_LOST_REPORT (
	lost_report_num	NUMBER NOT NULL,
	callNumber	varchar2(10)	NOT NULL,
	mem_num	number(5)	NOT NULL,
	rent_num	number	NOT NULL,
	store_product_pricesales	number	NOT NULL,
	lost_reg_date	DATE	DEFAULT SYSDATE	NOT NULL,
    CONSTRAINT PK_LIB_BOOK_LOST_REPORT PRIMARY KEY (lost_report_num),
    CONSTRAINT FK_LIB_BOOK_LOST_REPORT_1 FOREIGN KEY (mem_num)
                                            REFERENCES lm_member_manage(mem_num),
    CONSTRAINT FK_LIB_BOOK_LOST_REPORT_2 FOREIGN KEY (callNumber)
                                            REFERENCES lib_product_manage(callNumber) ,
    CONSTRAINT FK_LIB_BOOK_LOST_REPORT_3 FOREIGN KEY (rent_num)
                                            REFERENCES LIB_HISTORY(rent_num)                                           
);

CREATE TABLE LIB_EVENT_USER (
	event_user_num	number(5)	NOT NULL,
	event_admin_num	number(5)	NOT NULL,
	mem_num	number(5)	NOT NULL,
	event_rank	number(1)	NOT NULL,
	event_reason	varchar2(100)	NOT NULL,
    CONSTRAINT PK_LIB_EVENT_USER PRIMARY KEY (event_user_num),
    CONSTRAINT FK_LIB_EVENT_USER_1 FOREIGN KEY (event_admin_num)
                                            REFERENCES lib_event_admin(event_admin_num),
    CONSTRAINT FK_LIB_EVENT_USER_2 FOREIGN KEY (mem_num)
                                            REFERENCES lm_member_manage(mem_num)                                            
);

CREATE TABLE LIB_EVENT_ADMIN (
	event_admin_num	number(5)	NOT NULL,
	event_start_date	date	DEFAULT SYSDATE	NOT NULL,
	event_end_date	date	NOT NULL,
	event_status	number(1)	NOT NULL,
	event_view	number(5)	DEFAULT 0	NOT NULL,
	event_title	varchar2(100)	NOT NULL,
	event_content	varchar2(300)	NOT NULL,
    CONSTRAINT PK_LIB_EVENT_ADMIN PRIMARY KEY (event_admin_num)
    
);

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


