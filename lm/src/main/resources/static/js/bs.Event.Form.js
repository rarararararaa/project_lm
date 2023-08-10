$(function() {

	$('#event_board_category').click(function() {
		if (this.options[1].selected) {
			document.getElementById("event_form_div4").style.display = "block";
		} else {
			document.getElementById("event_quiz_sel1").value = null;
			document.getElementById("event_quiz_sel2").value = null;
			document.getElementById("event_quiz_an").value = null;

			document.getElementById("event_form_div4").style.display = "none";
		}
	});

	$("#event_date_start,#event_date_end").datepicker({
		dateFormat:'yy-mm-dd'
	});

	$('#event_date_start').datepicker("option", "maxDate", $("#event_date_end").val());
	$('#event_date_start').datepicker("option", "onClose", function(selectedDate) {
		$("#event_date_end").datepicker("option", "minDate", selectedDate);
	});

	$('#event_date_end').datepicker();
	$('#event_date_end').datepicker("option", "minDate", $("#event_date_start").val());
	$('#event_date_end').datepicker("option", "onClose", function(selectedDate) {
		$("#event_date_start").datepicker("option", "maxDate", selectedDate);
	});
	
	
	



});