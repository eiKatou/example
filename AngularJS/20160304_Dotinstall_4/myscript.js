angular.module('myApp', [])
  .controller('myController', ['$scope', function($scope) {
    $scope.users = [
      {"name":"tanaka", "score":100.12345},
      {"name":"yamada", "score":90},
      {"name":"hashimoto", "score":63},
    ];

    $scope.today = new Date();

  }]);
