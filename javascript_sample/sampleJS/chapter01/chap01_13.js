try {
  const num = window.prompt("数値を入力してください。");
  // 変数numの値が数値のとき
  if (isNaN(num)) {
    throw "not a number";
  }
  document.write("数値の " + num + " が入力されました。");
  // 例外処理（変数numが数値以外のとき）
} catch (e) {
  document.write("<p style='color:red;'>数値を入力してください。</p>");
}