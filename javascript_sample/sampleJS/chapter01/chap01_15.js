// グローバル関数aに、"グローバル定義"を代入する。
a = "グローバル定義";
// ローカル関数bに、"グローバル定義"を代入する。
const b = "グローバル定義";
// 関数funcの呼び出し
func();
document.write("グローバル出力　a=" + a + "<br />");
document.write("グローバル出力　b=" + b + "<br />");

function func() {
  // グローバル関数aに、"ローカルで定義"を代入する。 
  a = "ローカルで定義";
  // ローカル関数bに、"ローカル定義"を代入する。
  const b = "ローカルで定義";
  document.write("ローカルの出力　a=" + a + "<br />");
  document.write("ローカルの出力　b=" + b + "<br />");
}