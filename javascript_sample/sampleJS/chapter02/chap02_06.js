// オブジェクトのcarを引数(type、color)を定義する。
function Car(type, color) {
  // オブジェクトのインスタンスを定義する。
  this.type = type;
  this.color = color;
  // メソッドを定義し、関数から値を受け取り、文字列を編集して表示する。
  this.now = function () {
    document.write("タイプ:" + this.type + "　カラー:" + this.color + "<br />");
  };
}
const car1 = new Car("普通車", "白");
car1.now();