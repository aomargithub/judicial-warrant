module.exports = function (app) {
    app.controller('menuDrtvCtrl', function ($scope, menuItemsFcty, appSessionSrvc) {
        var vm = this;

        vm.isMenuOpen = true;
        vm.items = menuItemsFcty.items.filter(function (item) {
            return item.showFilter(appSessionSrvc.getCurrentUser().role);
        });
        vm.toggleMenuState = function(){
            vm.isMenuOpen = !vm.isMenuOpen;
        };
        vm.getMenuState = function(){
            return vm.isMenuOpen;
        };
    });
};