angular.module("alurapic")
.controller("FotoController", function ($scope, $routeParams, fotosResource) {
    $scope.foto = {};
    if ($routeParams.idAEditar) {
        fotosResource.get({fotoId: $routeParams.idAEditar},
            result => $scope.foto = result,
            error => $scope.mensagem = `Erro ao editar! (${error})`);
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
            fotosResource.save($scope.foto, successFn ,errorFn);
        } else {
            fotosResource.update({fotoId: $scope.foto._id}, $scope.foto, successFn, errorFn);
        }
    };
});