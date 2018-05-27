// https://qiita.com/yuta0801/items/ff7f314f45c4f8dc8a48

const request = require('request');

var URL = 'http://zipcloud.ibsnet.co.jp/api/search';

request.get({
    uri: URL,
    headers: {'Content-type': 'application/json'},
    qs: {
      zipcode:'2300078'
    },
    json: true
}, (err, req, data) => {
    console.log(data);
    console.log('address:'
      + data.results[0].address1 + ' '
      + data.results[0].address2 + ' '
      + data.results[0].address3);

});


var RBIN_URL = 'http://requestbin.fullcontact.com/y8l6umy8';
let postData = {
  "name": "ei",
  "comment": "nononono"
};
request.post({
  uri: RBIN_URL,
  headers: { "Content-type": "application/json" },
  json: postData
}, (err, res, data) => {
  console.log(data);
});

request.get({
  uri: RBIN_URL,
  headers: { "Content-type": "application/json" }
}, (err, req, data) => {
  console.log(data);
})