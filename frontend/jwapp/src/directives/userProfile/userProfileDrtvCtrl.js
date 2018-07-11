module.exports = function(app){
    app.controller('userProfileDrtvCtrl', function($rootScope,internalUserSrvc,  messageFcty,PasswordChange, $scope, User,appSessionSrvc,httpStatusSrvc,stringUtilSrvc){
        var vm = this;
        vm.message = null;
        vm.CurrentUser = new User();
        vm.passwordChange = new PasswordChange();
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
                messageFcty.resetMessage(response);
            });
        };
        
        vm.update = function(){
            internalUserSrvc.update(vm.currentUser).then(function success(response){
                vm.currentUser = response.data;
            }, function error(response){
                
                messageFcty.handleErrorMessage(response);
            });
        };
if(vm.passwordChange.newPassword === vm.passwordChange.confirmNewPassword){
        vm.updatePassword = function(){
            internalUserSrvc.updatePassword(vm.currentUser.id,vm.passwordChange).then(function success(response){
            }, function error(response){
                
                messageFcty.handleErrorMessage(response);
            });
        }};


        vm.reset = function(){
            internalUserSrvc.getCurrentUser().then(function(response){ 
                vm.currentUser = response.data;
                vm.currentUser.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
              });
        };

    
    });
};