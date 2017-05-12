qrcode = require('qrcode-console');

qrcode.generate('http://github.com', function (qrcode) {
  console.log("\n\n\n");
  console.log(qrcode);
  console.log("\n\n\n");
});
