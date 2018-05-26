var fs = require('fs');

// 参考：https://nodejs.org/api/fs.html

readFile('./rfc_http.txt');
writeFile("test.txt", "test OK!");

//ファイル読み込み関数
function readFile(path) {
  fs.readFile(path, 'utf8', function (err, data) {
    if (err) {
        throw err;
    }
    console.log(data);
  });
}

//ファイルの書き込み関数
function writeFile(path, data) {
  fs.writeFile(path, data, function (err) {
    if (err) {
      throw err;
    }
  });
}
