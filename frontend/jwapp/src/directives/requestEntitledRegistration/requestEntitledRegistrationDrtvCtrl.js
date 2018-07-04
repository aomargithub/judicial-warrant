module.exports = function(app){
    app.controller('requestEntitledRegistrationDrtvCtrl', function( EntitledRegistration,entitledRegistrationSrvc,$state,OrganizationUnit){
        var vm = this;
        vm.message = null;
        vm.entitledRegistration = new EntitledRegistration();
        vm.requestEntitledRegistrations = [];
 
    
        vm.page = {
            start: 0,
            end: 0
        }
        entitledRegistrationSrvc.getAll().then(function(response){
            vm.requestEntitledRegistrations = response.data;
        });

        vm.route = function(entitledRegistration){
            vm.code = entitledRegistration.request.type.code;
            vm.serial = entitledRegistration.request.serial;
            
            if (vm.code ==='ENTITLED_REGISTRATION')
            {
              return    $state.go('root.entitledTrainnings',{serial:vm.serial});

            }
        }

    
        vm.closeMessage = function(){
            vm.message = null;
        };
    });
}