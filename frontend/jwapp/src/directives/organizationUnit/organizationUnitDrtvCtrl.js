module.exports = function(app){
    app.controller('organizationUnitDrtvCtrl', function(OrganizationUnit, organizationUnitSrvc){
        var vm = this;
        vm.organizationUnit = new OrganizationUnit();

        organizationUnitSrvc.getAll().then(function(response){
            vm.organizationUnits = response.data;
        });

        vm.add = function(){
            organizationUnitSrvc.save(vm.organizationUnit).then(function(response){
                vm.organizationUnits.push(response.data);
            });
        };
    });
}