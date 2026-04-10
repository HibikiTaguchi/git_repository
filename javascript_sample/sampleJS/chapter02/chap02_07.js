function Car() {
  this.x = 0;
  this.y = 0;
}
// プロトタイプメソッドを使用して引数を受け取り、文字列を編集して表示する。
Car.prototype.now = function () {
  document.write("現在位置(x,y)は (" + this.x + ", " + this.y + ") です。<br />");
}
// プロトタイプメソッドを使用して引数を受け取り、文字列を編集して表示し、this.ｘに1を加算する。
Car.prototype.move = function () {
  document.write("前進します。<br />");
  ++this.x;
};
const car2 = new Car();
car2.now();
car2.move();
car2.move();
car2.now();