Promise.resolve(42)
  .then(function(value){
    console.log(value);
    return 52;
  }).then(function(value){
    console.log(value);
  });