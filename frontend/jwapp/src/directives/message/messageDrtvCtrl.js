module.exports = function (app) {
    app.controller('messageDrtvCtrl', function ($filter,$rootScope,blockUIConfig, $scope, messageFcty) {
        var vm = this;
        $scope.messageFcty = messageFcty;

        vm.blockUiMessage ={arabicName: $rootScope.messages['loadingArabic'] , englishName: $rootScope.messages['loadingEnglish']};
        blockUIConfig.message = $filter('localeFltr')(vm.blockUiMessage);  
        blockUIConfig.delay = 100;

        vm.closeMessage = function () {
            messageFcty.resetMessage();
        };

        vm.closeSuccessMessage = function () {
            messageFcty.resetSuccessMessage();
        };
        
    });
};