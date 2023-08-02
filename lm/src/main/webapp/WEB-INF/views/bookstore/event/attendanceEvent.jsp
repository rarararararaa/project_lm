<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/BsAttendanceStyle.css">
<!-- 출석 이벤트 페이지 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/attendanceEvent.js"></script>
<body>
	<input type="hidden" value="${status.event_attendance_point_num}" id="event_attendance_point_num">
	<div>
		<div>
			<h2>출석체크 이벤트</h2>
			
		</div>
		<div>
			<ul class="attendance-status">
				<li class="stampCount">
					<span>총 획득 도장</span>
					<span id="attendance_count"></span>
					<!-- ${count} -->
				</li>
				<li id="point_get1" class="noPoint">
					<span>100</span>
					<span>Point</span>
				</li>
				<li id="point_get2" class="noPoint">
					<span>200</span>
					<span>Point</span>
				</li>
				<li id="point_get3" class="noPoint">
					<span>300</span>
					<span>Point</span>
					</li>
			</ul>
		</div>
		<div>
			<div class="calendar">
				<div class="calendar-Th">
					<span>일</span>
					<span>월</span>
					<span>화</span>
					<span>수</span>
					<span>목</span>
					<span>금</span>
					<span>토</span>
				</div>
				<div class="calendar-tb" id="calendar-tb"></div>
			<script type="text/javascript">
			
				let now = new Date();
				let currentYear = now.getYear();
				let currentMonth = now.getMonth() + 1;
				
				let currentDay = now.getDate();
				let last = new Date(currentYear, currentMonth, 0);   
				let lastDay = last.getDate();
				
				let first = new Date(currentYear, currentMonth-1,2);
				let firstYoil = first.getDay();
				let dayList = [];
				
				if(firstYoil != 6){
					for(let i=0; i<= firstYoil; i++){
						dayList.push(0);
					}
				}
				for(let j=1; j<=lastDay; j++){
					dayList.push(j);
				}
				//console.log(dayList);
				
				let totalDay = dayList.length;
				
				let week_num;
				
				if((totalDay%7) == 0){
					week_num = parseInt( totalDay / 7 );
				}else{
					week_num = parseInt( totalDay / 7 ) + 1;
				}
				//console.log(week_num);
				
				let output='';
				let dayIndex = 0;
				for(let w=0; w<week_num; w++){
					output += '<div class="calRow">';
					for(let i=0; i<7; i++){
						if(dayList[dayIndex] == 0){
							output += '<span class="day-num"></span>';
						}else{
							if(dayList[dayIndex]==null){
								break;
							}
							output += '<span class="day-num day'+dayList[dayIndex]+'">';
							output += '<span class="bgStamp"></span>';
							output += '<span class="num">'+dayList[dayIndex]+'</span>';
							output += '</span>'
						}
						dayIndex++;
					}
					output += '</div>';
				}
				//console.log(output);
				//문서 객체에 추가
				$('#calendar-tb').append(output);
				$('#attendanceCheck').attr('data-nowDay', currentDay);
				$('#attendanceCheck').attr('data-eventmonth', currentMonth);
			
			
			</script>
			
			</div>
		</div>
		<div >
			<input type="button" value="출석체크" id="attendanceCheck">
		</div>


	</div>
</body>
</html>