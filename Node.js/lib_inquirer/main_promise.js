var inquirer = require("inquirer");

var questions = [
  {
    type: "input",
    name: "first_name",
    message: "What's your first name"
  },
  {
    type: "input",
    name: "last_name",
    message: "What's your last name"
  }
];

inquirer.prompt(questions).then(answers => {
  console.log( JSON.stringify(answers, null, "  ") );
}).then(() => {
  return inquirer.prompt(questions);
}).then(answers => {
  console.log( JSON.stringify(answers, null, "  ") );
});