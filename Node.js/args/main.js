console.log("argv[0]:" + process.argv[0]);
console.log("argv[1]:" + process.argv[1]);

for(var i = 0;i < process.argv.length; i++){
  console.log("argv[" + i + "] = " + process.argv[i]);
}