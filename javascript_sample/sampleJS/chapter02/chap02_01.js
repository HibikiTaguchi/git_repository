// 変数名strでインスタンスを作成する。
const str = new String("こんにちは！");
// 変数lenに、文字列の長さを得れるプロパティによりその値を代入する。
const len = str.length;
document.write("文字列の長さは " + len + " です。<br />");
// 変数chに、指定位置（4文字目）の文字を得るメソッドによりその値を代入する。
const ch = str.charAt(3);
document.write("4番目の文字は " + ch + " です。<br />");