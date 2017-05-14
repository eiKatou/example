console.log("hello promise");

Promise.resolve(1)
  .then((value) => value+2)
  .then((value) => console.log(`値は${value}`))
  .then(() => console.log("終了"));