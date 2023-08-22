$(function() {

	//날짜선택 - datepicker
	$("#start_date,#end_date").datepicker({
		dateFormat: 'yy-mm-dd'
	});

	$('#start_date').datepicker("option", "maxDate", $("#end_date").val());
	$('#start_date').datepicker("option", "onClose", function(selectedDate) {
		$("#end_date").datepicker("option", "minDate", selectedDate);
	});

	$('#end_date').datepicker();
	$('#end_date').datepicker("option", "minDate", $("#start_date").val());
	$('#end_date').datepicker("option", "onClose", function(selectedDate) {
		$("#start_date").datepicker("option", "maxDate", selectedDate);
	});

	//시간 선택 - timepicker
	$("#start_time, #end_time").timepicki({
		show_meridian: false,
		min_hour_value: 0,
		max_hour_value: 23,
		step_size_minutes: 10,
		overflow_minutes: true,
		increase_direction: 'up',
		disable_keyboard_mobile: false
	});
	
	$('#start_date').on('change', function(){
		time_start_dis();
	});
	$('#end_date').on('change', function(){
		time_end_dis();
	});

	function time_start_dis(){
		 var start_date = $('#start_date').val();

        // start_date 값이 null인 경우
        if (!start_date) {
            // start_time 입력 필드를 비활성화
             $('.start-time').css('display', 'none');

            // (옵션) 시간 입력 필드의 값을 초기화
            $('#start_time').val('');
        } else {
            // start_date 값이 있을 경우 start_time 입력 필드 활성화
             $('.start-time').css('display', 'flex');
		}
		
	};
	function time_end_dis(){
		 var end_date = $('#end_date').val();

        // start_date 값이 null인 경우
        if (!end_date) {
            // start_time 입력 필드를 비활성화
            $('.end-time').css('display', 'none');

            // (옵션) 시간 입력 필드의 값을 초기화
            $('#end_time').val('');
        } else {
            // start_date 값이 있을 경우 start_time 입력 필드 활성화
            $('.end-time').css('display', 'flex');
        }
		
	};

	//일정 등록
	$("form#register_form").submit(function(event) {
		event.preventDefault(); // 기본 폼 제출 동작 막기

		// 폼 데이터 가져오기(입력한 정보 읽기)
		let form_data = $(this).serialize();

		$.ajax({
			type: "POST",
			url: "lib_SchedulePopup.do", // 백엔드 서버의 URL을 넣어주세요
			data: form_data,
			dataType: 'json',
			success: function(param) {
				if (param.result == 'success') {
					window.opener.parent.location.reload();
					window.close();
					alert('성공')
				}

			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});
	});

	//일정 수정
	$("form#modify_form").submit(function(event) {
		event.preventDefault(); // 기본 폼 제출 동작 막기

		// 폼 데이터 가져오기(입력한 정보 읽기)
		let form_data = $(this).serialize();

		$.ajax({
			type: "POST",
			url: "lib_ScheduleModify.do", // 백엔드 서버의 URL을 넣어주세요
			data: form_data,
			dataType: 'json',
			success: function(param) {
				if (param.result == 'success') {
					window.opener.parent.location.reload();
					window.close();
					alert('성공')
				}

			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});
	});

	$('#delete_btn').on('click', function() {
		//댓글 번호
		let calendar_num = $(this).attr('data-num');
		console.log("cal_num: " + calendar_num);
		//서버와 통신
		$.ajax({
			url: 'lib_ScheduleDelete.do',
			type: 'post',
			data: { calendar_num: calendar_num },
			dataType: 'json',
			success: function(param) {
				if (param.result == 'logout') {
					alert('로그인해야 삭제할 수 있습니다.');
				} else if (param.result == 'success') {
					alert('삭제 완료');
					window.opener.parent.location.reload();
					window.close();
				} else if (param.result == 'wrongAccess') {
					alert('타인의 글을 삭제할 수 없습니다.');
				} else {
					alert('댓글 삭제 오류 발생');
				}
			},
			error: function() {
				alert('네트워크 오류 발생!');
			}

		});
	});
	
	time_start_dis();
	time_end_dis();
});