angular.module("alurapic")
.controller("FotosController", function ($scope, fotosResource) {
    $scope.fotos = [];
    $scope.filterText = "";
    fotosResource.query(result => $scope.fotos = result, 
        error => console.log(error));
    $scope.remover = foto => {
        fotosResource.delete({fotoId: foto._id},
        () => {
            $scope.fotos.splice($scope.fotos.indexOf(foto), 1);
            $scope.mensagem = `Foto "${foto.titulo}" removida com sucesso!`;
        },
        (error) => $scope.mensagem = `Foto "${foto.titulo}" NÃO removida com sucesso! (${error})`);
    };
});