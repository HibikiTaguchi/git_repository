const btn = document.getElementById("cvs");
btn.onclick = click;
function click(evt){
  // マウスクリック時のイベント処理
  alert("x座標:" + evt.clientX + " y座標:" + evt.clientY + " がクリックされました。");
}