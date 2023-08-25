//관리자 이벤트 클릭수 수정
function clickSchedule(calendar_num) {
	let option = "width=600, height=400, left=100, top=50, location=no"
	window.open('lib_ScheduleModify.do?calendar_num=' + calendar_num, '일정추가', option);
}
$(function() {
	//날짜 형식
	function dateFormat(date) {
		let month = date.getMonth() + 1;
		let day = date.getDate();
		let hour = date.getHours();
		let minute = date.getMinutes();
		let second = date.getSeconds();

		month = month >= 10 ? month : '0' + month;
		day = day >= 10 ? day : '0' + day;
		hour = hour >= 10 ? hour : '0' + hour;
		minute = minute >= 10 ? minute : '0' + minute;
		second = second >= 10 ? second : '0' + second;

		return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
	}

	$('#add_schedule').on('click', function() {
		let option = "width=600, height=400, left=100, top=50, location=no"
		window.open('lib_SchedulePopup.do', '일정추가', option);
	});



	function displayCalendar() {

		$.ajax({
			url: 'getSchedule.do',
			type: 'post',
			data: {},
			dataType: 'json',
			success: function(param) {
				let edit = false;
				console.log('param.mem_auth: ' + param.mem_auth);
				if (param.mem_auth == "admin") {
					edit = true;
				} else if (param.mem_auth == "no-admin") {
					edit = false;
				}


				//console.log('edit: '+edit);
				var calendarEl = document.getElementById('calendar');
				var calendar = new FullCalendar.Calendar(calendarEl, {
					googleCalendarApiKey: 'AIzaSyC6UIy6dJJAFw8qXmI-r2EvXYFgOoVW2mo',
					/*editable: edit,
					droppable: false,*/
					//일정 클릭시 해당일정 수정,삭제 창
					eventClick: function(info) {
						// Prevent redirect to Google Calendar
						info.jsEvent.cancelBubble = true;
						info.jsEvent.preventDefault();

						if (info.event.title === '휴관일' || info.event.extendedProps.description == '공휴일') {
							return; // 클릭 이벤트 무시
						}

						//관리자만 클릭 이벤트 발동하도록
						if (edit) {

							var startDate = info.event.start;
							clickDate = dateFormat(startDate);
							//alert('id: ' + info.event);
							clickSchedule(info.event.id);
							console.log(info.event);
							console.log(info.event.extendedProps.description);
						}
					},
					events: [
						{
							title: '휴관일',
							startRecur: '2023-01-01', // Start date for the recurrence (adjust as needed)
							endRecur: '2030-12-31',   // End date for the recurrence (adjust as needed)
							daysOfWeek: [1],           // 1 represents Monday
							startTime: '08:00',       // Start time for the event (adjust as needed)
							endTime: '09:00',          // End time for the event (adjust as needed)
							color: 'red',

							editable: false
						}],
					eventSources: [
						{
							googleCalendarId: 'ko.south_korea#holiday@group.v.calendar.google.com',
							color: '#be5683', //rgb,#ffffff 등의 형식으로 할 수 있어요.
							//textColor: 'black' 
							editable: false

						}]

				});


				$(param.list).each(function(index, item) {
					let start = item.start_date
					let end = item.end_date
					if (item.start_time != null) {
						start += 'T' + item.start_time;
					}
					if (item.end_time != null) {
						end += 'T' + item.end_time;
					}
					if (item.end_date != null && item.allday) {
						// end_date를 JavaScript Date 객체로 파싱
						let endDate = new Date(end);

						// endDate를 하루 증가시킴
						endDate.setDate(endDate.getDate() + 1);

						// end_date를 문자열로 변환 (YYYY-MM-DD 형식)
						end = endDate.toISOString()
					}
					calendar.addEvent({
						id: item.calendar_num,
						title: item.title,
						start: start,
						end: end, // Assuming you have an end_date property in your data
						allDay: item.allday
					});
				});

				calendar.render();


			},
			error: function() {
				alert('네트위크 오류 발생')
			}

		});

	}


	displayCalendar();



});
