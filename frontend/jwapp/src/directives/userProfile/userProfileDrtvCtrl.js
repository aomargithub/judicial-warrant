module.exports = function(app){
    app.controller('userProfileDrtvCtrl', function($rootScope,internalUserSrvc, $scope, User,appSessionSrvc,httpStatusSrvc,stringUtilSrvc){
        var vm = this;
        vm.message = null;
        vm.CurrentUser = new User();
        vm.emailPattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$";
        vm.mobilePattern="/^[0-9]{10,10}$/";


        
       
        internalUserSrvc.getCurrentUser().then(function(response){ 
               vm.currentUser = response.data;
               vm.currentUser.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
             });

        vm.refetch = function(id){
            internalUserSrvc.getCurrentUser().then(function(response){
                vm.currentUser = response.data;
                vm.currentUser.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.message = null;
            });
        };

        vm.update = function(){
            internalUserSrvc.update(vm.currentUser).then(function success(response){
                vm.currentUser = response.data;
            }, function error(response){
                
                var status = httpStatusSrvc.getStatus(response.status);
                if(status.code === httpStatusSrvc.preconditionFailed.code){
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };


        vm.reset = function(){
            internalUserSrvc.getCurrentUser().then(function(response){ 
                vm.currentUser = response.data;
                vm.currentUser.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
              });
        };

        vm.closeMessage = function(){
            vm.message = null;
        };

    });
};