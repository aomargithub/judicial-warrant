module.exports = function(app){
    app.controller('externalUserDrtvCtrl', function($rootScope, $scope, User, externalUserSrvc,httpStatusSrvc,stringUtilSrvc,organizationUnitSrvc,roleSrvc){
        var vm = this;
        vm.user = new User();
        vm.editId = null;
        vm.message = null;
        vm.editUser = null;
        vm.emailPattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$";
        vm.mobilePattern="/^[0-9]{10,10}$/;"
        vm.users = [];
        vm.organizations = []; 
        vm.roles = [];
       

        vm.page = {
            start: 0,
            end: 0
        };

        externalUserSrvc.getAll().then(function(response){
            vm.users = response.data;
        });

        organizationUnitSrvc.getExternal().then(function(response){
            vm.organizations = response.data;
        });

        roleSrvc.getInternalFalse().then(function(response){
            vm.roles = response.data;
        });

        var resetEntryForm = function(){
            $scope.userForm.$setPristine();
            $scope.userForm.$setUntouched(); 
        }

        


        vm.add = function(){
            vm.user.role = ('filter')(vm.roles, { isInternal: "0" });
            
            externalUserSrvc.save(vm.user).then(function success(response){
                vm.users.push(response.data);
                vm.user = new User();

                resetEntryForm();
            }, function error(response){
                var status = httpStatusSrvc.getStatus(response.status);
                if(status.code === httpStatusSrvc.badRequest.code){
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };

        vm.edit = function(id){
            externalUserSrvc.getById(id).then(function(response){
                vm.editUser = response.data;
                vm.editUser.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.user = angular.copy(vm.editUser);
                resetEntryForm();
            });
        };

        vm.refetch = function(id){
            externalUserSrvc.getById(id).then(function(response){
                vm.editUser = response.data;
                vm.editUser.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.user = angular.copy(vm.editUser);
                vm.message = null;
                resetEntryForm();
            });
        };

        vm.update = function(){
            
            externalUserSrvc.update(vm.user).then(function success(response){
               
                var tempUser = response.data;
                vm.user = new User();
                vm.editUser = null;
                vm.users.forEach(function(ou, index){
                    if(ou.id === tempUser.id){
                        vm.users[index] = tempUser;
                    }
                });

                resetEntryForm();
            }, function error(response){
                
                var status = httpStatusSrvc.getStatus(response.status);
                if(status.code === httpStatusSrvc.preconditionFailed.code){
                    vm.message = $rootScope.messages[status.text];
                };

                resetEntryForm();
            });
        };

        vm.delete = function(id){
            
            externalUserSrvc.delete(id).then(function success(response){               
                vm.users.forEach(function(ou, index){
                    if(ou.id === id){
                        vm.users.splice(index, 1);
                    }
                });

                resetEntryForm();
            }, function error(response){
                
                var status = httpStatusSrvc.getStatus(response.status);
                if(status.code === httpStatusSrvc.preconditionFailed.code){
                    vm.message = $rootScope.messages[status.text];
                };

                resetEntryForm();
            }); 
        };

        vm.cancel = function(){
            vm.user = new User();
            vm.editUser = null;
            vm.message = null;
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

        vm.closeMessage = function(){
            vm.message = null;
        };

    });
};