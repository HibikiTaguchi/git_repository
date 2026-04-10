const word = window.prompt("調べるキーワードを入力してください。");
//変数wordをWikipediaのURLにエンコードする。
const url = encodeURI("https://ja.wikipedia.org/wiki/" + word);
const anchor = "<a href='" + url + "'>こちら</a>"
document.write("該当するページは" + anchor + "です。");