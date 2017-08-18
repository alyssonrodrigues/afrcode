angular.module("alurapic")
.controller("FotoController", function ($scope, $http, $routeParams) {
    $scope.foto = {};
    if ($routeParams.idAEditar) {
        $http.get(`v1/fotos/${$routeParams.idAEditar}`)
            .success(result => $scope.foto = result)
            .error(error => $scope.mensagem = `Erro ao editar! (${error})`);
    }
    $scope.submeter = function() {
        if (!$scope.formulario.$valid) return;
        let successFn = () => {
            $scope.mensagem = `Foto "${$scope.foto.titulo}" salva com sucesso!`;
            $scope.foto = {};
        };
        let errorFn = error => 
            $scope.mensagem = `Foto "${$scope.foto.titulo}"  NÃO salva com sucesso! (${error})`;
        if (!$scope.foto._id) {
            $http.post("v1/fotos", $scope.foto).success(successFn).error(errorFn);
        } else {
            $http.put(`v1/fotos/${$scope.foto._id}`, $scope.foto).success(successFn).error(errorFn);
        }
    };
});