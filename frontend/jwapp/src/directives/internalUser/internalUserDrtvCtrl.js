module.exports = function(app){
    app.controller('internalUserDrtvCtrl', function($rootScope, $scope, User, internalUserSrvc, roleSrvc){
        var vm = this;
        vm.user = new User();
        vm.editId = null;
        vm.message = null;
        vm.editUser = null;
        vm.users = [];
        vm.roles = [];

        vm.page = {
            start: 0,
            end: 0
        };

        internalUserSrvc.getAll().then(function(response){
            vm.users = response.data;
        });

        roleSrvc.getInternal().then(function(response){
            vm.roles = response.data;
        });

        var resetEntryForm = function(){
            $scope.userForm.$setPristine();
            $scope.userForm.$setUntouched();
        };

    });
};