angular.module("alurapic")
.controller("GruposController", function ($scope, $http) {
    $scope.grupos = [];
    $http.get("v1/grupos")
        .success(result => $scope.grupos = result)
        .error(error => $scope.mensagem = `Erro ao recuperar grupos! (${error})`);
});