$(function(){
	let today = new Date();   

	let year = today.getFullYear(); // 년도
	let month = today.getMonth() + 1;  // 월
	let date = today.getDate();  // 날짜
	
	$("select[name=year] option[value='"+year+"']").prop("selected", true);
	$("select[name=month] option[value='"+month+"']").prop("selected", true);
	$("select[name=date] option[value='"+date+"']").prop("selected", true);
	
	timeCheck();
		

	$('#date').on('change',function(){
		timeCheck();
	});
	
	$('input[name="time"]').on('click',function(){
		let checked = document.querySelectorAll('input[name="time"]:checked');
		
		if($(this).prop('checked')){
			if(checked.length==1){
				$('#start').val($(this).val());
				$('#end').val(Number($(this).val())+1);
			}else{
				if(Number($(this).val())> Number($('#end').val())){
					$('#end').val(Number($(this).val())+1)
				}else{
					$('#start').val($(this).val())
				}
			}
		}else{
			if(checked.length==0){
				$('#start').val(0);
				$('#end').val(0);
			}else{
				if($('#end').val()-$(this).val()>$(this).val()-$('#start').val()){
					$('#start').val(Number($(this).val())+1);
				}else{
					$('#end').val($(this).val());
				}
			}
		}
		
		
		$('input[name="time"]').prop('checked', false);
		
		for(let i = $('#start').val();Number(i)<$('#end').val();i++){
			if($("#"+i).is(".unchecked") === true){
				alert('hello');
				return;
			}
			$("#"+i).prop('checked', true);
		}
	});
		function timeCheck(){
		let sdate = $('#yearSelect').val();
		if($('#monthSelect').val()<10){
			sdate += '0' + $('#monthSelect').val();
		}else{
			sdate += $('#monthSelect').val();
		}
		if($('#dateSelect').val()<10){
			sdate += '0' + $('#dateSelect').val();
		}else{
			sdate += $('#dateSelect').val();
		}
		
		for(let i = 9; i < 18;i++){
			$('#'+i).removeClass("unckecked");
		}
		$.ajax({
			url:'../library/timeCheck.do',
			type:'post',
			data:{date:sdate},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 사용할 수 있습니다.');
					return;
				}else if(param.result == 'success'){
					$(param.list).each(function(index,item){
						$('#'+item).addClass("unckecked");
					})
				}else{
					alert('예약현황 불러오기 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
});