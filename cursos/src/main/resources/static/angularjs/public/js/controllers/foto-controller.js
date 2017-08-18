angular.module("alurapic")
.controller("FotoController", function ($scope, $http) {
    $scope.foto = {};
    $scope.submeter = function() {
        if (!$scope.formulario.$valid) return;

        $http.post("v1/fotos", $scope.foto)
            .success(() => {
                $scope.mensagem = `Foto "${$scope.foto.titulo}" incluída com sucesso!`;
                $scope.foto = {};
            })
            .error(error => 
                $scope.mensagem = `Foto "${$scope.foto.titulo}"  NÃO incluída com sucesso!`);
    };
});