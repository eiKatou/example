var http = require('http');
var url = require('url');
var fs = require('fs');

http.createServer(function (req, res) {
  var urlParts = url.parse(req.url);
  var path = __dirname + urlParts.pathname;
  console.log(path);
  var stream = fs.createReadStream(path);

  stream.on('data', function(data) {
    res.write(data);
  });
  stream.on('end', function(data) {
    res.end();
  });

}).listen(1337, '127.0.0.1'); // 127.0.0.1の1337番ポートで待機

// 実行は $ node webserver.js
// http://127.0.0.1:1337/a1.jpg
// http://127.0.0.1:1337/a2.jpg