const radius = window.prompt("半径を入力してください。");
// 変数areaに、関数circleに変数radiusの値を受け渡し、関数circleからから受け取った値を代入する。
const area = circle(radius);
document.write("円の面積は、" + area + " です。");

// 関数の定義
function circle(r) {
  // 変数aに受け取った変数r×r×3.14の演算結果を代入する。
  const a = r * r * 3.14;
  return a;
}