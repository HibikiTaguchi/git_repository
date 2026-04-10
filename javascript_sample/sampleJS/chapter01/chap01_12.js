const end = window.prompt("指定した値までの整数を表示します。");
// カウンター変数ｉを、初期値0から1づつ加算し、変数endの値と等しくなるまで処理を繰り返す。
for (let i = 0; i <= end; i++) {
  document.write(i + " ");
}