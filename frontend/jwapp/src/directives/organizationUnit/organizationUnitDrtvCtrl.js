module.exports = function(app){
    app.controller('organizationUnitDrtvCtrl', function($rootScope, $scope, OrganizationUnit, organizationUnitSrvc, httpStatusSrvc, stringUtilSrvc){
        var vm = this;
        vm.organizationUnit = new OrganizationUnit();
        vm.editId = null;
        vm.editOrganizationUnit = null;
        vm.message = null;
        organizationUnitSrvc.getAll().then(function(response){
            vm.organizationUnits = response.data;
        });

        var resetEntryForm = function(){
            $scope.organizationUnitForm.$setPristine();
            $scope.organizationUnitForm.$setUntouched();
        }

        vm.add = function(){
            organizationUnitSrvc.save(vm.organizationUnit).then(function(response){
                vm.organizationUnits.push(response.data);
                vm.organizationUnit = new OrganizationUnit();
                resetEntryForm();
            });
        };

        vm.edit = function(id){
            organizationUnitSrvc.getById(id).then(function(response){
                vm.editOrganizationUnit = response.data;
                vm.editOrganizationUnit.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.organizationUnit = angular.copy(vm.editOrganizationUnit);
                resetEntryForm();
            });
        };

        vm.refetch = function(id){
            console.log('refetch',$scope.organizationUnitForm);
            organizationUnitSrvc.getById(id).then(function(response){
                vm.editOrganizationUnit = response.data;
                vm.editOrganizationUnit.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.organizationUnit = angular.copy(vm.editOrganizationUnit);
                vm.message = null;
                resetEntryForm();
            });
        };

        vm.update = function(){
            
            organizationUnitSrvc.update(vm.organizationUnit).then(function success(response){
               
                var tempOrganizationUnit = response.data;
                vm.organizationUnit = new OrganizationUnit();
                vm.editOrganizationUnit = null;
                $scope.organizationUnitForm.$setPristine();
                vm.organizationUnits.forEach(function(ou, index){
                    if(ou.id === tempOrganizationUnit.id){
                        vm.organizationUnits[index] = tempOrganizationUnit;
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
            vm.organizationUnit = new OrganizationUnit();
            vm.editOrganizationUnit = null;
            vm.message = null;
            resetEntryForm();
        };

        vm.reset = function(){
            console.log('reset', $scope.organizationUnitForm);
            if(vm.editOrganizationUnit){
                vm.organizationUnit = angular.copy(vm.editOrganizationUnit);
            }else{
                vm.organizationUnit = new OrganizationUnit();
            }
            resetEntryForm();
        };

        vm.closeMessage = function(){
            vm.message = null;
        };
    });
}