module.exports = function (app) {
    app.controller('messageDrtvCtrl', function ($rootScope, $stateParams, $state, $scope,httpStatusSrvc, messageFcty) {
        var vm = this;
        $scope.messageFcty = messageFcty;

        vm.closeMessage = function () {
            messageFcty.resetMessage();
        };
        
    });
};