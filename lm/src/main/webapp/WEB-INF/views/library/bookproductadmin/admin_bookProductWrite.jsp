<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 신규도서 등록 시작 -->
<!-- include libraries (jquery,bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main">
	<h2>신규등록</h2>
	<input type="button" value="도서목록" onclick="location.href='admin_booklist.do'">
	<input type="button" value="대출목록" onclick="location.href='admin_bookloanlist.do'">
	<input type="button" value="희망도서" onclick="#">
	<input type="button" value="신규등록" onclick="location.href='admin_write.do'">
	
	<form:form modelAttribute="bookProductAdminVO" action="admin_write.do"
	                               id="register_form">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="upload1">상품이미지</label>
				<input type="file" name="upload1" id="upload1" accept="image/gif,image/png,image/jpeg">
				<form:errors path="lib_product_bookImageUrl_ar" cssClass="error-color"/>
			</li>
		    <li>
				<form:label path="lib_product_isbn">상품식별번호</form:label>
				<form:input path="lib_product_isbn"/>
				<form:errors path="lib_product_isbn" cssClass="error-color"/>
			</li>
			<li>
				<label>주제분류</label>
				<form:radiobutton path="lib_product_class_no" value="0" id="lib_product_class_no0"/>총류
				<form:radiobutton path="lib_product_class_no" value="1" id="lib_product_class_no1"/>철학
				<form:radiobutton path="lib_product_class_no" value="2" id="lib_product_class_no2"/>종교
				<form:radiobutton path="lib_product_class_no" value="3" id="lib_product_class_no3"/>사회과학
				<form:radiobutton path="lib_product_class_no" value="4" id="lib_product_class_no4"/>자연과학
				<form:radiobutton path="lib_product_class_no" value="5" id="lib_product_class_no5"/>기술과학
				<form:radiobutton path="lib_product_class_no" value="6" id="lib_product_class_no6"/>예술
				<form:radiobutton path="lib_product_class_no" value="7" id="lib_product_class_no7"/>언어
				<form:radiobutton path="lib_product_class_no" value="8" id="lib_product_class_no8"/>문학
				<form:radiobutton path="lib_product_class_no" value="9" id="lib_product_class_no9"/>역사
			</li>
			<li>
				<form:label path="lib_product_bookname">도서명</form:label>
				<form:input path="lib_product_bookname"/>
				<form:errors path="lib_product_bookname" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="lib_product_authors">저자명</form:label>
				<form:input path="lib_product_authors"/>
				<form:errors path="lib_product_authors" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="lib_product_publisher">출판사</form:label>
				<form:input path="lib_product_publisher"/>
				<form:errors path="lib_product_publisher" cssClass="error-color"/>
			</li>
			
			<li>
				<form:label path="lib_product_publication_year">출판년도</form:label>
				<form:input path="lib_product_publication_year"/>
				<form:errors path="lib_product_publication_year" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="lib_product_description">도서상세분류</form:label>
				<form:input path="lib_product_description"/>
				<form:errors path="lib_product_description" cssClass="error-color"/>
			</li>
			<li><b>도서소개</b></li>
			<li>
				<form:textarea path="lib_product_detail"/>
				<form:errors path="lib_product_detail" cssClass="error-color"/>
				<script>
					function MyCustomUploadAdapterPlugin(editor){
						editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
							return new UploadAdapter(loader);
						}
					}
					
					ClassicEditor.create(document.querySelector('#lib_product_detail'),{
						           extraPlugins:[MyCustomUploadAdapterPlugin]
					             })
					             .then(editor => {
					            	 window.editor = editor;
					             })
					             .catch(error => {
					            	 console.error(error);
					             });
				</script>
			</li>
		</ul>
		<div class="align-center">
			<form:button>신규등록</form:button>
			<input type="button" value="도서목록"
			             onclick="location.href='admin_booklist.do'">
		</div>	                               
	</form:form>
</div>
<!-- 신규도서 등록 끝 -->




