module.exports = function(app){
    app.controller('logoutDrtvCtrl', function($state, appSessionSrvc,authenticationSrvc){
        var vm = this;
       vm.currentUser=[];
      
       vm.currentUser = appSessionSrvc.getCurrentUser();
    
      

        vm.doLogout = function(){
            authenticationSrvc.logout().then(function(status){
                if(status.code === 200){
                    $state.go('login');
                }else{
                    vm.statusText = status.text;
                }
            });
        }
    });
};