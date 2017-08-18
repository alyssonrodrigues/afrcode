angular.module("customDirectives", []).directive("customPanel", function () {
    var ddo = {};
    ddo.restrict = "AE";
    ddo.transclude = true;
    ddo.scope = {
        titulo: "@"
    };
    ddo.template = 
          '<div class="panel panel-default">'
        + '    <div class="panel-heading">'
        + '        <h3 class="panel-title">{{titulo}}</h3>'
        + '    </div>'
        + '    <div class="panel-body" ng-transclude>'
        + '    </div>'
        + '</div>';
    return ddo;
});