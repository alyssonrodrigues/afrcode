angular.module("alurapic")
.controller("FotosController", function ($scope, $http) {
    $scope.fotos = [];
    $scope.filterText = "";
    $http.get("v1/fotos")
        .success(result => $scope.fotos = result)
        .error(error => console.log(error));
    $scope.remover = foto => {
        $http.delete(`v1/fotos/${foto._id}`)
            .success(() => {
                $scope.fotos.splice($scope.fotos.indexOf(foto), 1);
                $scope.mensagem = `Foto "${foto.titulo}" removida com sucesso!`;
            })
            .error(error => 
                $scope.mensagem = `Foto "${foto.titulo}" NÃO removida com sucesso! (${error})`);
    };
});