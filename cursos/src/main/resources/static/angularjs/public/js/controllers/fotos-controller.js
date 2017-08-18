angular.module("alurapic")
.controller("FotosController", function ($scope, $http) {
    $scope.fotos = [];
    $scope.filterText = "";
    $http.get("v1/fotos")
        .success(result => $scope.fotos = result)
        .error(error => console.log(error));
});