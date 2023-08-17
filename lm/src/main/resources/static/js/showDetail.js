
//동적 버튼 설정

//희망도서 신청
function ShowDetail(count) {
  document.getElementById("show-detail-"+count).style.display = "flex";
  document.getElementById("button-more-info-close-"+count).style.display = "flex";
  document.getElementById("button-more-info-"+count).style.display = "none";
}
function HideDetail(count) {
  document.getElementById("show-detail-"+count).style.display = "none";
  document.getElementById("button-more-info-close-"+count).style.display = "none";
  document.getElementById("button-more-info-"+count).style.display = "flex";
}

//프로그램 신청
function ShowContent(count) {
  document.getElementById("show-content-"+count).style.display = "flex";
  document.getElementById("button-more-info-close-"+count).style.display = "flex";
  document.getElementById("button-more-info-"+count).style.display = "none";
}
//닫기
function HideContent(count) {
  document.getElementById("show-content-"+count).style.display = "none";
  document.getElementById("button-more-info-close-"+count).style.display = "none";
  document.getElementById("button-more-info-"+count).style.display = "flex";
}