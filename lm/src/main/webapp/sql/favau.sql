--lm_member_manage--
CREATE TABLE lm_member_manage(
    mem_num number(5) not null,
    mem_id varchar2(15) unique not null,
    mem_auth number(1) default 4 not null,
    mem_reg_date date default SYSDATE not null,
    mem_stop_date date,
    mem_exp_date date,
    mem_stop_reason varchar2(100),
    constraint lm_member_manage_pk primary key (mem_num)
);
CREATE SEQUENCE lm_member_manage_seq;

--lm_member_detail--
CREATE TABLE lm_member_detail(
    mem_num number(5) not null,
    mem_au_id varchar2(36) unique, --자동 로그인에 사용되는 식별값
    mem_name varchar2(30) not null,
    mem_passwd varchar2(256) not null,
    mem_cell varchar2(15) unique not null,
    mem_email varchar2(50) unique not null,
    mem_identify varchar2(14) unique not null,
    mem_point number(10) default 2000 not null,
    mem_photo blob,
    mem_login_date date,
    mem_modify_date date,
    mem_salt varchar2(64) unique not null,
    mem_grade number(1) default 0 not null,
    constraint lm_member_detail_pk primary key (mem_num),
    constraint lm_member_detail_fk foreign key (mem_num) references lm_member_manage (mem_num)
);

--lib_event_admin--
CREATE TABLE lib_event_admin(
    event_admin_num number(5) not null,
    event_start_date date not null,
    event_end_date date not null,
    event_status number(1) not null,
    event_view number(5) default 0 not null,
    event_title varchar2(100) not null,
    event_content varchar2(300) not null,
    constraint lib_event_admin_pk primary key (event_admin_num)
);
CREATE SEQUENCE lib_event_admin_seq;

--lib_event_user--
CREATE TABLE lib_event_user(
    event_user_num number(5) not null,
    event_admin_num  number(5) not null,
    mem_num number(5) not null,
    event_rank number(1) not null,
    event_reason varchar2( 100) not null,
    constraint lib_event_user_pk primary key (event_user_num),
    constraint lib_event_user_fk1 foreign key (event_admin_num) references lib_event_admin (event_admin_num),
    constraint lib_event_user_fk2 foreign key (mem_num) references lm_member_manage (mem_num)
);
CREATE SEQUENCE lib_event_user_seq;

--store_member_home--
CREATE TABLE store_member_home(
    home_num number(5) not null,
    mem_num number(5) not null,
    home_title varchar2(50) not null,
    home_zipcode varchar2(6) not null,
    home_address varchar2(150) not null,
    home_address_detail varchar2(150) not null,
    home_cell varchar2(15) not null,
    home_name varchar2(30) not null,
    constraint store_member_home_pk primary key (home_num),
    constraint store_member_home_fk1 foreign key (mem_num) references lm_member_manage (mem_num)
);
CREATE sequence store_member_home_seq;