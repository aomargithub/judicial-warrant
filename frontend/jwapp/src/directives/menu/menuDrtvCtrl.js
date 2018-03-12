module.exports = function (app) {
    app.controller('menuDrtvCtrl', function ($scope, menuItemsFcty, appSessionSrvc) {
        var self = this;

        $scope.isMenuOpen = true;
        $scope.preventDefault = function(event){
            event.preventDefault();
        };
        $scope.items = menuItemsFcty.items.filter(function (item) {
            return item.showFilter(appSessionSrvc.getCurrentUser().role);
        });
    });
};