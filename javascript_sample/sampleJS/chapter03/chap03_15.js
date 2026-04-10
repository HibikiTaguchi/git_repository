const p1 = document.getElementById("p1");
p1.onmouseover = chgColor; p1.onmouseout = chgColor;
function chgColor() {
  const bg = this.style;
  // バックグラウンド色の設定
  if(bg.backgroundColor!='lightskyblue') bg.backgroundColor='lightskyblue';
  else bg.backgroundColor='white';
}

p1.addEventListener("click", clickEvt);
// 行がクリックされた時の処理
function clickEvt() {
  alert("2つめのイベントハンドラの出力");
}