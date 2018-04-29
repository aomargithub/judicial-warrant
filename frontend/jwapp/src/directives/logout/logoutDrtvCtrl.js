module.exports=function(app){
    app.controller('logoutDrtvCtrl', function ($state, authenticationSrvc){
        var vm = this;
        vm.doLogout = function(){
                    $state.go('login');
        };
    });
};