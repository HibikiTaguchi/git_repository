// 最後に表示される確認ダイアログで「キャンセル」が選ばれるまで、処理を繰り返す。
let retry;
do {
   const name = window.prompt("お名前は？");
   const age = window.prompt("お歳は？");
   alert(name + "さん" + age + "歳ですね。");
   // メッセージと、OK, キャンセルの 2 つのボタンを持つモーダルダイアログを表示します。
   retry = window.confirm("最初からやりなおしますか？");
} while (retry);