$(function(){
	function onClickSelect(e) {
	  const isActive = e.currentTarget.className.indexOf("active") !== -1;
	  if (isActive) {
	    e.currentTarget.className = "select_box_button";
	  } else {
	    e.currentTarget.className = "select_box_button active";
	  }
	}
	
	document.querySelector(".search_select .select_box_button").addEventListener("click", onClickSelect);
	
	function onClickOption(e) {
	  const selectedValue = e.currentTarget.innerHTML;
	  document.querySelector(".search_select .text").innerHTML = selectedValue;
	}
	
	var optionList = document.querySelectorAll(".search_select .optionItem");
	for (var i = 0; i < optionList.length; i++) {
	  var option = optionList[i];
	  option.addEventListener("click", onClickOption);
	}
});
function btn_toggle(){
	//alert('왜 안되는 거야 이런 젠장할');
	$('#test').toggleClass('active animated');
	//$('#test').classList.toggle('active');
}
