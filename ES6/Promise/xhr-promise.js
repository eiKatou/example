function getURL(URL) {
  let promise = new Promise(function(resolve, reject) {
    let req = new XMLHttpRequest();
    req.open('GET', URL, true);
    req.onload = function(){
      if(req.status="200"){
        resolve(req.responseText);
      } else {
        reject(new Error(req.statusText));
      }
    };
    req.onerror = function(){
      reject(new Error(req.statusText));
    };
    req.send();
  });
  return promise;
}

function getData() {
  getURL('http://httpbin.org/get')
    .then(function(value){
      console.log(value);
    }).catch(function(error){
      console.log(error);
    });
}