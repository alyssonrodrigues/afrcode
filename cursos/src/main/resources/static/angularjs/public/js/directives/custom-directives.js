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
});