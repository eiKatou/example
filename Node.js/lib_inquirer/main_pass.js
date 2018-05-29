var inquirer = require("inquirer");

inquirer.prompt([
  {
    type: "password",
    message: "Enter your password",
    name: "password"
  }
], function( answers ) {
  console.log( JSON.stringify(answers, null, "  ") );
});