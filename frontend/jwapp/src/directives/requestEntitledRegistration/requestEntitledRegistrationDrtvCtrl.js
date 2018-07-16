module.exports = function(app){
    app.controller('requestEntitledRegistrationDrtvCtrl', function( EntitledRegistration,  messageFcty,entitledRegistrationSrvc,$state,OrganizationUnit){
        var vm = this;
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
              return    $state.go('home.entitledTrainnings',{serial:vm.serial});

            }
        }

    
    });
}