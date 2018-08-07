module.exports = function(app){
    app.controller('internalUserDrtvCtrl', function($state,$rootScope,$filter,messageFcty, $scope, User, internalUserSrvc, roleSrvc,httpStatusSrvc,stringUtilSrvc){
        var vm = this;
        vm.user = new User();
        vm.editId = null;
        vm.editUser = null;
        vm.emailPattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$";
        vm.mobilePattern="/^[0-9]{10,10}$/";
        vm.users = [];
        vm.roles = [];

        vm.filters={}; 
        vm.status= [$rootScope.messages.isActive['ArabicName'],$rootScope.messages.isActive['EnglishName']];

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
        }

        vm.add = function(){
            internalUserSrvc.save(vm.user).then(function success(response){
                
                vm.users.push(response.data);
                vm.user = new User();
                messageFcty.showSuccessMessage();
                resetEntryForm();
            }, function error(response){
                messageFcty.handleErrorMessage(response);
            });
        };

        vm.edit = function(id){
            internalUserSrvc.getById(id).then(function(response){
                vm.editUser = response.data;
                vm.editUser.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));

                vm.user = angular.copy(vm.editUser);
                resetEntryForm();
            });
        };

        vm.refetch = function(id){
            internalUserSrvc.getById(id).then(function(response){
                vm.editUser = response.data;
                vm.editUser.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.user = angular.copy(vm.editUser);
                messageFcty.resetMessage(response);
                resetEntryForm();
            });
        };

        vm.update = function(){
            
            internalUserSrvc.update(vm.user).then(function success(response){
               
                var tempUser = response.data;
                vm.user = new User();
                vm.editUser = null;
                vm.users.forEach(function(ou, index){
                    if(ou.id === tempUser.id){
                        vm.users[index] = tempUser;
                    }
                });
                messageFcty.showSuccessMessage();
                resetEntryForm();
            }, function error(response){
                
                messageFcty.handleErrorMessage(response);
                  resetEntryForm();
            });
        };

        vm.delete = function(id){
            
            internalUserSrvc.delete(id).then(function success(response){               
                vm.users.forEach(function(ou, index){
                    if(ou.id === id){
                        vm.users.splice(index, 1);
                    }
                });

                resetEntryForm();
            }, function error(response){
                messageFcty.handleErrorMessage(response);
                                resetEntryForm();
            }); 
        };

        vm.cancel = function(){
            vm.user = new User();
            vm.editUser = null;
            resetEntryForm();
        };


        vm.reset = function(){
            if(vm.editUser){
                vm.user = angular.copy(vm.editUser);
            }else{
                vm.user = new User();
            }
            resetEntryForm();
        };

        vm.reLoad = function() {
            return $state.go("internalUsers",{},{reload: "internalUsers"});
        }

    });
};