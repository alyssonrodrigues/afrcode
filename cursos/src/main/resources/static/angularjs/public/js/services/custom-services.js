angular.module("customServices", ["ngResource"])
.factory("fotosResource", function($resource) {
    return $resource("v1/fotos/:fotoId", null, 
    {
        update : {
            method: "PUT"
        }
    });
});