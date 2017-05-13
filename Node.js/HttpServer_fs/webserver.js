// httpモジュールを読み込み、インスタンスを生成
var http = require('http');
var fs = require('fs');

// HTTPサーバーのイベントハンドラを定義
http.createServer(function (req, res) {

    fs.readFile('hello.html', 'UTF-8', function(err, data){
        res.writeHead(200, {'Content-Type': 'text/plain'});
        res.write(data);
        res.end();
    })


}).listen(1337, '127.0.0.1'); // 127.0.0.1の1337番ポートで待機

// 実行は $ node webserver.js
// http://127.0.0.1:1337