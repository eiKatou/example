var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('calc', { pageName: 'Calc', result: ''});
});

router.post('/add', function(req, res) {
  console.log(req.body);
  console.log(req.body.num1);
  console.log(req.body.num2);
  const result = parseInt(req.body.num1, 10) + parseInt(req.body.num2, 10);
  // res.send('POST request to the homepage');
  res.render('calc', { pageName: 'Calc' , result: result});
});

module.exports = router;
