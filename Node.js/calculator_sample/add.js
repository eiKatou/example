if (process.argv.length < 4) {
  console.log('usage: $node add.js 1 2');
}

const num1 = parseInt(process.argv[2], 10);
const num2 = parseInt(process.argv[3], 10);
console.log(num1 + num2);
