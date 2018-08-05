module.exports = function(app){
    app.controller('organizationUnitDrtvCtrl', function($state, $scope,messageFcty, OrganizationUnit, organizationUnitSrvc, httpStatusSrvc, stringUtilSrvc){
        var vm = this;
        vm.organizationUnit = new OrganizationUnit();
        vm.editId = null;
        vm.editOrganizationUnit = null;
        vm.organizationUnits = [];
        vm.page = {
            start: 0,
            end: 0
        }
        
        
        vm.filters={}; 
        vm.status= [true, false];

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
                messageFcty.showSuccessMessage();
                resetEntryForm();
            },function error (response){
                messageFcty.handleErrorMessage(response);
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
            organizationUnitSrvc.getById(id).then(function(response){
                vm.editOrganizationUnit = response.data;
                vm.editOrganizationUnit.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.organizationUnit = angular.copy(vm.editOrganizationUnit);
                messageFcty.resetMessage(response);
                resetEntryForm();
            });
        };

        vm.update = function(){
            
            organizationUnitSrvc.update(vm.organizationUnit).then(function success(response){
               
                var tempOrganizationUnit = response.data;
                vm.organizationUnit = new OrganizationUnit();
                vm.editOrganizationUnit = null;
                vm.organizationUnits.forEach(function(ou, index){
                    if(ou.id === tempOrganizationUnit.id){
                        vm.organizationUnits[index] = tempOrganizationUnit;
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
            
            organizationUnitSrvc.delete(id).then(function success(response){               
                vm.organizationUnits.forEach(function(ou, index){
                    if(ou.id === id){
                        vm.organizationUnits.splice(index, 1);
                    }
                });

                resetEntryForm();
            }, function error(response){
                messageFcty.handleErrorMessage(response);

                resetEntryForm();
            });
        };

        vm.cancel = function(){
            vm.organizationUnit = new OrganizationUnit();
            vm.editOrganizationUnit = null;
            resetEntryForm();
        };

        vm.reset = function(){
            if(vm.editOrganizationUnit){
                vm.organizationUnit = angular.copy(vm.editOrganizationUnit);
            }else{
                vm.organizationUnit = new OrganizationUnit();
            }
            resetEntryForm();
        };
        vm.reLoad = function() {
            return $state.go("organizationUnits",{},{reload: "organizationUnits"});
        }
    });
}