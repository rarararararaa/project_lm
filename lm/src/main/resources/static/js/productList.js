$(function(){
	$('.select-cate').click(function(){
		let detail = $(this).attr('data-category');
		//alert(detail);
		location.href='/bookstore/product/productCeteList.do?detail='+detail;
	})
})
//===================함수====================//
/*function showList(bookList){
	let output = '';
	$(bookList).foreach(function(index,item){
		output += '<tr>';
		output += '<td>';
		output += '<a href="/bookstore/product/productDetail.do?store_product_isbn13='+bookList.store_product_isbn13+'"><img src="'+bookList.store_product_cover+'"></a>'
		output += '<div class="book-detail"><ul id="test">';
		output += '<li data-num="'+bookList.store_product_num+'"><a href="/bookstore/product/productDetail.do?store_product_isbn13='+bookList.store_product_isbn13+'">'+bookList.store_product_title+'</a></li>';
		output += '<li>'+bookList.store_product_discount+'%</li>'
		output += '<li>'+bookList.store_product_pricestandard+'원</li>'
		output += '<li>('+bookList.store_product_pricestandard*point+'P)</li>'
		output += '</ul></div>'
		output += '</td>';
		output += '<td colspan="2">'
		if(bookListstore_product_description == ' '){
			output += '<div> 도서 상세 정보가 없습니다. </div>'
		}else{
			output += '<div> '+bookList.store_product_description+' </div>'
		}
		output += '</td>'
		output += '<td>';
		output += '<button class="product-btn" id="cart">장바구니</button>'
		output += '<button class="product-btn" id="pay">바로구매</button>'
		output += '</td>';
		output += '</tr>';
	})
}*/
