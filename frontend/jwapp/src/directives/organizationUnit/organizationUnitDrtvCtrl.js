module.exports = function(app){
    app.controller('organizationUnitDrtvCtrl', function(OrganizationUnit, organizationUnitSrvc, httpStatusSrvc, stringUtilSrvc, $rootScope){
        var vm = this;
        vm.organizationUnit = new OrganizationUnit();
        vm.editId = null;
        vm.message = null;
        organizationUnitSrvc.getAll().then(function(response){
            vm.organizationUnits = response.data;
        });

        vm.add = function(){
            organizationUnitSrvc.save(vm.organizationUnit).then(function(response){
                vm.organizationUnits.push(response.data);
            });
        };

        vm.edit = function(id){

            organizationUnitSrvc.getById(id).then(function(response){
                vm.organizationUnit = response.data;
                vm.organizationUnit.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.editId = id;
            });
        };

        vm.refetch = function(id){
            organizationUnitSrvc.getById(id).then(function(response){
                vm.organizationUnit = response.data;
                vm.organizationUnit.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.message = null;
            });
        };

        vm.update = function(){
            organizationUnitSrvc.update(vm.organizationUnit).then(function success(response){
                
                var tempOrganizationUnit = response.data;
                vm.organizationUnit = new OrganizationUnit();
                vm.editId = null;
                vm.organizationUnits.forEach(function(ou, index){
                    if(ou.id === tempOrganizationUnit.id){
                        vm.organizationUnits[index] = tempOrganizationUnit;
                    }
                });    
            }, function error(response){
                var status = httpStatusSrvc.getStatus(response.status);
               
                if(status.code === httpStatusSrvc.preconditionFailed.code){
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };

        vm.cancel = function(){
            vm.organizationUnit = new OrganizationUnit();
            vm.editId = null;
            vm.message = null;
        };

        vm.closeMessage = function(){
            vm.message = null;
        }
    });
}