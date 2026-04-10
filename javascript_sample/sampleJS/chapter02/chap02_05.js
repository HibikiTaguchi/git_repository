// []を利用して配列を定義する。
const students = ["佐藤", "鈴木", "高橋", "田中"];
document.write("受講生のメンバーは<br />");
document.write(students[0] + "さん<br />");
document.write(students[1] + "さん<br />");
document.write(students[2] + "さん<br />");
// 配列[3]に"中田"を再度、代入する。
students[3] = "中田";
document.write(students[3] + "さん<br />");
// 配列[4]を追加して"伊藤"を代入する。
students[4] = "伊藤";
document.write(students[4] + "さんです。<br />");