
//동적 버튼 설정
//열기
function ShowDetail(count) {
  document.getElementById("show-detail-"+count).style.display = "flex";
  document.getElementById("button-more-info-close-"+count).style.display = "flex";
  document.getElementById("button-more-info-"+count).style.display = "none";
}
//닫기
function HideDetail(count) {
  document.getElementById("show-detail-"+count).style.display = "none";
  document.getElementById("button-more-info-close-"+count).style.display = "none";
  document.getElementById("button-more-info-"+count).style.display = "flex";
}