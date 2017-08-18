angular.module("alurapic").controller("FotosController", function ($scope, $http) {
    $scope.fotos = [];
    $http.get("v1/fotos")
        .success(result => $scope.fotos = result)
        .error(error => console.log(error));
});