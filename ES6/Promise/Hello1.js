console.log("hello promise");

let promise = new Promise(function(resolve, reject) {
  console.log("時間がかかる処理開始");
  setTimeout(function() {
    console.log("時間がかかる処理完了");
    resolve("OK");
  }, 1000);
});

promise.then(function(value){
  console.log(value);
}).catch(function (error) {
    console.error(error);
});