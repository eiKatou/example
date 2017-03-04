angular.module('myApp', [])
  .controller('myController', ['$scope', function($scope) {
    $scope.users = [
      {"name":"tanaka", "score":33.25},
      {"name":"yamada", "score":90.62},
      {"name":"hashimoto", "score":63},
      {"name":"tada", "score":62},
      {"name":"taira", "score":90.90},
      {"name":"yamamoto", "score":12.25},
      {"name":"hayashi", "score":99.99},
    ];
  }]);
