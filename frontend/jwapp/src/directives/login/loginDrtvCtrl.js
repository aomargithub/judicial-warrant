module.exports=function(app){
    app.controller('loginDrtvCtrl', function ($state, authenticationSrvc){
        var vm = this;
        vm.doLogin = function(){
            authenticationSrvc.login(vm.credentials).then(function(status){
                if(status.code === 200){
                    $state.go('home');
                }else{
                    vm.statusText = status.text;
                }
            });
        };
    });
};