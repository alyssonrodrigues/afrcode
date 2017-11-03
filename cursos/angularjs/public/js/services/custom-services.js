angular.module("customServices", ["ngResource"])
.factory("fotosResource", function($resource) {
    return $resource("v1/fotos/:fotoId", null, 
    {
        update : {
            method: "PUT"
        }
    });
})
.factory("fotosService", function(fotosResource, $q) {
    let service = {};
    service.cadastrar = (foto) => {
        return $q((resolve, reject) => {
            let successFn = () => {
                resolve({
                    mensagem: `Foto "${foto.titulo}" salva com sucesso!`,
                    inclusao: foto._id == undefined
                });
            };
            let errorFn = error => 
                reject({
                    mensagem: `Foto "${foto.titulo}"  NÃO salva com sucesso! (${error})`
                });
            if (!foto._id) {
                fotosResource.save(foto, successFn ,errorFn);
            } else {
                fotosResource.update({fotoId: foto._id}, foto, successFn, errorFn);
            }
        });
    }
    return service;
});