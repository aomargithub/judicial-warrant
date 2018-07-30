module.exports = function(app){
    app.controller('requestEntitledRegistrationDrtvCtrl', function( EntitledRegistration,capacityDelegationSrvc,entitledRegistrationSrvc,$state,requestTypeSrvc,$scope){
        var vm = this;
        vm.entitledRegistration = new EntitledRegistration();
        vm.requestEntitledRegistrations = [];
 
        vm.filters={}; 
        
        $scope.statues=[
            {id:'1', arabicName:'DRAFT'},
            {id:'2', arabicName:'SUBMITED'},
            {id:'3', arabicName:'INCOMPLETE'},
            {id:'4', arabicName:'ACCEPTED'},
            {id:'5', arabicName:'REJECTED'},
            {id:'6', arabicName:'TRAINNING'},
            {id:'7', arabicName:'PASSED'},
            {id:'8', arabicName:'FAILED'},
            {id:'9', arabicName:'CARD_RECIEVED'},
            {id:'10', arabicName:'INPROGRESS'},
            {id:'11', arabicName:'ISSUED'},
            {id:'12', arabicName:'create'}];
    
        vm.page = {
            start: 0,
            end: 0
        }

        capacityDelegationSrvc.getAll('ISSUED').then(function (response) {
            vm.capacityDelegations = response.data;
       });

        entitledRegistrationSrvc.getAll().then(function(response){
            vm.requestEntitledRegistrations = response.data;
        });

        requestTypeSrvc.getAll().then(function (response){
            vm.requestTypes = response.data;
        });

      
        vm.reLoad = function() {
            return $state.go("home.requestEntitledRegistrations",{},{reload: "home.requestEntitledRegistrations"});
        }

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