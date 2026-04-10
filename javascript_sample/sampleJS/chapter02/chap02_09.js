function Car(type, color, seats) {
  // プロパティを設定する。
  this.type = type;
  this.color = color;
  this.seats = seats;
}
// インスタンス化する。
const car4 = new Car("軽自動車", "黄色", 4);
// 処理を繰り返す。
for (let item in car4) {
  document.write(item + ": " + car4[item] + "<br />");
}