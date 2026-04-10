const obj = new Object();
// tostringメソッドによりオブジェクトを文字列化して表示する。
document.write("オブジェクトを文字列化：" + obj.toString() + "<br />");
// constructorプロパティーにより使用したコンストラクタを表示する。
document.write("使用したコンストラクタ：" + obj.constructor + "<br />");
const num = new Number(12345);
// tostringメソッドによりオブジェクトを文字列化して表示する。
document.write("オブジェクトを文字列化：" + num.toString() + "<br />");
// constructorプロパティーにより使用したコンストラクタを表示する。
document.write("使用したコンストラクタ：" + num.constructor + "<br />");
const str = new String("こんにちは！");
// tostringメソッドによりオブジェクトを文字列化して表示する。
document.write("オブジェクトを文字列化：" + str.toString() + "<br />");
// constructorプロパティーにより使用したコンストラクタを表示する。
document.write("使用したコンストラクタ：" + str.constructor + "<br />");