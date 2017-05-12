// httpモジュールを読み込み、インスタンスを生成
var http = require('http');

// HTTPサーバーのイベントハンドラを定義
http.createServer(function (req, res) {

    // HTTPヘッダを出力
    res.writeHead(200, {'Content-Type': 'text/plain'});

    // レスポンスの中身を出力
    res.end('Hello World\n');

}).listen(1337, '127.0.0.1'); // 127.0.0.1の1337番ポートで待機

// 実行は $ node webserver.js
