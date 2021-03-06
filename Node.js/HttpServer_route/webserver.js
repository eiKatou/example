// httpモジュールを読み込み、インスタンスを生成
var http = require('http');
var fs = require('fs');

// HTTPサーバーのイベントハンドラを定義
http.createServer(function (req, res) {
  var target;

  switch(req.url) {
    case '/':
    case '/index':
      target = 'index.html';
      break;
    case '/next':
      target = 'next.html';
      break;
    default:
      res.writeHead(404, {'Content-Type': 'text/html'});
      res.end('Bad request');
    return;
  }

  fs.readFile(target, 'UTF-8', function(err, data){
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.write(data);
    res.end();
  })


}).listen(1337, '127.0.0.1'); // 127.0.0.1の1337番ポートで待機

// 実行は $ node webserver.js
// http://127.0.0.1:1337