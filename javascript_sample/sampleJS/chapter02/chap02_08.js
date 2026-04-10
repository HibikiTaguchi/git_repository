function Car(arg) {
  let type = arg;
  // ゲッターメソッドを定義する。
  this.getType = function () { return type; };
  // セッターメソッドを定義する。
  this.setType = function (t) { type = t; };
}
const car3 = new Car("普通車");
document.write("車種は、" + car3.getType() + " です。<br />");
// セッターメソッドに、"スポーツカー"を代入する。 
car3.setType("スポーツカー");
document.write("車種は、" + car3.getType() + " です。<br />");