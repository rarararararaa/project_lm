$(function(){
	let book_list = [];	//대출 도서 저장	
	let member_list=[];	//대출 회원 저장	
	
	//대출 도서 저장

	/* --------------------
	 *	대출 도서 추가하기
	 * -------------------- */
	//대출 등록 UI호출
	$('#opener').click(function(){
		$('#dialog').dialog('open');
		//초기화
		$('#book_search').val('');
		$('#search_area1').empty();
		$('#book_list').empty();
	});
	
	//회원 정보 검색
	$('#member_search').keyup(function(){
		if($('#member_search').val().trim()==''){
			$('#search_area').empty();
			return;
		}
		//서버와 통신
		$.ajax({
			url:'/libarary/rent/memberSearchAjax.do',
			type:'post',
			data:{mem_id:$('#member_search').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					$('#member_search').attr('disabled',true);
					$('#member_search').val('로그인해야 회원 검색이 가능합니다.');
				}else if(param.result == 'success'){
					$('#search_area').empty();
					$(param.member).each(function(index,item){
						if(!member_list.includes(item.mem_id)){
							let output = '';
							output += '<li data-num="'+item.mem_num+'">';
							output += item.mem_id;
							output += '</li>';
							$('#search_area').append(output);
						}	
					});
				}else{
					alert('회원 검색 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	 
	//검색된 회원 선택하기
	$(document).on('click','#search_area li',function(){
		let id = $(this).text(); //선택한 아이디
		let mem_num = $(this).attr('data-num'); //선택한 회원번호
		if(member_list.length<1){
			member_list.push(id);
		}else{
			alert('대출 회원은 한명만 가능합니다');
			$('#member_search').val('');
			$('#search_area').empty(); //ul 태그 초기화
			return false;
		}
		//선택한 id를 화면에 표시
		let choice_id = '<span class="member-span" data-id="'+id+'">';
		choice_id += '<input type="hidden" name="mem_num" value="'+mem_num+'">';
		choice_id += id + '<sup>&times;</sup><br></span>';
		$('#member_list').append(choice_id);
		$('#member_search').val('');
		$('#search_area').empty(); //ul 태그 초기화
		
	});
	
	//선택한 채팅방 멤버 삭제하기
	$(document).on('click','.member-span',function(){
		let id = $(this).attr('data-id');
		//채팅 멤버가 저장된 배열에서 삭제할 멤버의 id 제거
		member_list.splice(member_list.indexOf(id),1);
		$(this).remove();//이벤트가 발생한 태그 제거
		
		
	});

	//도서 정보 검색
	$('#book_search').keyup(function(){
		if($('#book_search').val().trim()==''){
			$('#search_area1').empty();
			return;
		}
		//서버와 통신
		$.ajax({
			url:'/libarary/rent/bookSearchAjax.do',
			type:'post',
			data:{lib_product_bookname:$('#book_search').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					$('#book_search').attr('disabled',true);
					$('#book_search').val('로그인해야 도서 검색이 가능합니다.');
				}else if(param.result == 'success'){
					$('#search_area1').empty();
					$(param.book).each(function(index,item){
						if(!book_list.includes(item.callNumber)){
							let output = '';
							output += '<li data-num="'+item.callNumber+'">';
							output += item.callNumber+"&emsp;";
							output += item.lib_product_bookName;
							output += '</li>';
							$('#search_area1').append(output);
						}	
					});
				}else{
					alert('도서 검색 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//검색된 도서 선택하기
	$(document).on('click','#search_area1 li',function(){
		let lib_product_bookName = $(this).text(); //선택한 책이름
		let callNumber = $(this).attr('data-num'); //선택한 책 번호
		let choice_bookname = '<span class="book-span" data-id="'+callNumber+'">';
		if(book_list.length<3){
			book_list.push(callNumber);
		}else{
			alert('대출 책은 최대 3권까지 가능합니다');
			$('#book_search').val('');
			$('#search_area1').empty(); //ul 태그 초기화
			return false;
		}
		//선택한 id를 화면에 표시
		choice_bookname += '<input type="hidden" name="callNumber" value="'+callNumber+'">';
		choice_bookname += lib_product_bookName + '<sup>&times;</sup><br></span>';
		$('#book_list').append(choice_bookname);
		$('#book_search').val('');
		$('#search_area1').empty(); //ul 태그 초기화
		
	});
	
	//선택한 도서 삭제하기
	$(document).on('click','.book-span',function(){
		let callNumber = $(this).attr('data-id');
		//채팅 멤버가 저장된 배열에서 삭제할 도서의 id 제거
		book_list.splice(book_list.indexOf(callNumber),1);
		$(this).remove();//이벤트가 발생한 태그 제거
	});	
	
	//대출 정보 전송
	$('#new_form').submit(function(event){
		//기본 이벤트 제거
		event.preventDefault();
		//ajax는 폼을 만들었을때 기본 제출 이벤트 제거해줘야함, 아니면 자기자신을 호출하므로 submit 불가능
		if($('input[name="mem_num"]').length == 0){	//아무도 선택하지 않았다.
			alert('추가할 회원을 선택하세요!');
			$('#member_search').val('').focus();
			return false;
		}
		let form_data = $(this).serialize();
		
		//서버와 통신 
		$.ajax({
			url: '/library/rent/rentHistoryWrite.do',
			type: 'post',
			data: form_data,
			dataType: 'json',
			success: function(param){
				if(param.result == 'logout'){
					alert('로그인해야 사용할 수 있습니다.');
				}else if(param.result == 'success'){
					$('#dialog').dialog('close');	//다이얼로그 닫기
					alert('정상적으로 도서를 대출했습니다.');
					location.reload();
				}else if(param.result=='overValue'){
					alert('대출은 최대 3권까지만 가능합니다.');
				}else if(param.result=='alreadyRent'){
					alert('이미 대출중인 도서입니다.');
				}else{
					alert('도서 대출 오류 발생');
				}
			},
			error: function(){
				alert("네트워크 오류 발생");
			}
		});
		
	});	
});