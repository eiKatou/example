var http = require('http');
const util = require('util');

var server = http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/plain'});
    res.end('Hello World\n');
});
server.listen(1337, '127.0.0.1');

// http://nodejs.jp/nodejs.org_ja/docs/v0.8/api/events.html
server.on('connection', function (stream) {
  console.log('someone connected!');
});

server.once('connection', function (stream) {
  console.log('Ah, we have our first user!');
});

console.log(util.inspect(server.listeners('connection'))); // [ [Function] ]

// 実行は $ node webserver.js
// http://127.0.0.1:1337
