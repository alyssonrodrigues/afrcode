angular.module("alurapic", ["customDirectives", "ngAnimate", "ngRoute", "customServices"])
.config(function ($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);

    $routeProvider.when("/fotos", {
        templateUrl: "partials/principal.html",
        controller: "FotosController"
    });

    $routeProvider.when("/fotos/incluir", {
        templateUrl: "partials/incluir-foto.html",
        controller: "FotoController"
    });

    $routeProvider.when("/fotos/editar/:idAEditar", {
        templateUrl: "partials/incluir-foto.html",
        controller: "FotoController"
    });

    $routeProvider.otherwise({redirectTo: "/fotos"});
});