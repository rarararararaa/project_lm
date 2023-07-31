$(function(){
	$('.book-select').on('click',function(){
		//alert('왜 안됨');
		let num = $('.book-select').val();
		//alert(num);
		$(this).toggleClass('active animated');
	})
})