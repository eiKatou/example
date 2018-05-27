const rp = require('request-promise');
const getOptions = {
    uri: 'http://httpbin.org/get',
    json: true
};
const postOptions = {
  uri: 'http://httpbin.org/post',
  method: 'POST',
  form: {
    foo: 'bar'
  },
  json: true
};

rp(getOptions)
  .then(res => {
    console.log('first time : call api 1.');
    console.log(res.origin);
  })
  .catch(err => {
    console.log(err);
  });

rp(getOptions)
  .then(res => {
    console.log('first time : call api 2.');
    console.log(res.origin);
  })
  .then(() => {
    console.log('second time : call api 2.');
    return rp(getOptions);
  })
  .then(res => {
    console.log(res.origin);
  })
  .catch(err => {
    console.log(err);
  });


rp(postOptions)
  .then(res => {
    console.log('first time : call api 3.');
    console.log(res.form.foo);
  })
  .catch(err => {
    console.log(err);
  })
