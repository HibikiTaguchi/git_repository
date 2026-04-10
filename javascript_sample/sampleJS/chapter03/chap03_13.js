const p1 = document.getElementById("p1");
p1.onmouseover = chgColor;
p1.onmouseout = chgColor;
const p2 = document.getElementById("p2");
p2.onmouseover = chgColor;
p2.onmouseout = chgColor;

function chgColor() {
  const bg = this.style;
  // バックグラウンドカラーの設定
  if(bg.backgroundColor!='lightskyblue') {
    bg.backgroundColor='lightskyblue';
  } else {
    bg.backgroundColor='white';
  }
}