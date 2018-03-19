module.exports = function (app) {
    app.controller('menuDrtvCtrl', function (menuItemsFcty, appSessionSrvc) {
        var vm = this;

        vm.isMenuOpen = true;
        vm.items = menuItemsFcty.items.filter(function (item) {
            return item.showFilter(appSessionSrvc.getCurrentUser().role);
        });
    });
};