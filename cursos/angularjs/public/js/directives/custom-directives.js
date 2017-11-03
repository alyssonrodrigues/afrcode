angular.module("customDirectives", [])
.directive("customPanel", function () {
    var ddo = {};
    ddo.restrict = "AE";
    ddo.transclude = true;
    ddo.scope = {
        titulo: "@"
    };
    ddo.templateUrl = "js/directives/custom-panel.html";
    return ddo;
})
.directive("customPhoto", function() {
    var ddo = {};
    ddo.restrict = "AE";
    ddo.scope = {
        url: "@",
        titulo: "@"
    };
    ddo.templateUrl = "js/directives/custom-photo.html";
    return ddo;
})
.directive("customRemove", function() {
    var ddo = {};
    ddo.restrict = "E";
    ddo.scope = {
        acao: "&",
        nome: "@"
    };
    ddo.template = 
        '<button ng-click="acao()" class="btn btn-danger btn-block">{{nome}}</button>';
    return ddo;
})
.directive("customFocus", function() {
    var ddo = {};
    ddo.restrict = "A";
    ddo.link = (scope, domElement) => {
        scope.$on("getFocus", () => domElement[0].focus());
    };
    return ddo;
});