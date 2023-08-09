$(function(){
	let today = new Date();   

	let year = today.getFullYear(); // 년도
	let month = today.getMonth() + 1;  // 월
	let date = today.getDate();  // 날짜
	
	$("select[name=year] option[value='"+year+"']").prop("selected", true);
	$("select[name=month] option[value='"+month+"']").prop("selected", true);
	$("select[name=date] option[value='"+date+"']").prop("selected", true);
	
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
			$("#"+i).prop('checked', true);
		}
	});
	
});