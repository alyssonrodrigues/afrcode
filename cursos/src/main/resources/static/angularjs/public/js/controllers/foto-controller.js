angular.module("alurapic")
.controller("FotoController", function ($scope, $routeParams, fotosResource, fotosService) {
    $scope.foto = {};
    if ($routeParams.idAEditar) {
        fotosResource.get({fotoId: $routeParams.idAEditar},
            result => $scope.foto = result,
            error => $scope.mensagem = `Erro ao editar! (${error})`);
    }
    $scope.submeter = function() {
        if (!$scope.formulario.$valid) return;
        fotosService.cadastrar($scope.foto)
            .then(result => {
                $scope.mensagem = result.mensagem;
                if (result.inclusao) {
                    $scope.foto = {};
                }
            })
            .catch(error => $scope.mensagem = error.mensagem);
    };
});