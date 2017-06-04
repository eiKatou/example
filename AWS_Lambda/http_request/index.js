var http = require ('http');
exports.handler = function(event, context) {
    console.log('value1 = ' + event.key1);
    http.get("http://www.google.co.jp/", function(res) {
        console.log("Got response: " + res.statusCode);

        let body = '';
        res.setEncoding('utf8');

        res.on("data", (chunk) => {
            body += chunk;
        });
        res.on('end', (res) => {
            // res = JSON.parse(body);
            console.log(body); // ログに出力するとCW Logsに出力される
            context.done(null, body);
        });

    }).on('error', function(e) {
        context.done('error', e);
    });
};