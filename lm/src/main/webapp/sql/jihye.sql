--도서 정보
create table LIB_PRODUCT_MANAGE(
	lib_product_isbn varchar2(13) not null,
	bookname varchar2(50) not null,
	authors varchar2(10) not null,
	publisher varchar2(20) not null,
	publisher_year varchar2(5) not null,
	class_no number not null,
	bookImageURL varchar2(100) not null,
	bookDtlUrl clob not null,
	book_code varchar2(5) not null,
	constraint lib_product_manage_pk primary key (book_code)
);
