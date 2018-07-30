module.exports=function(app){
    app.controller('loginDrtvCtrl', function (blockUI,blockUIConfig,$rootScope,$state, authenticationSrvc){
        var vm = this;
        
        blockUIConfig.message =$rootScope.messages['loading'];
        blockUIConfig.delay = 100;
        
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