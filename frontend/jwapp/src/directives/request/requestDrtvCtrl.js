module.exports = function(app){
    app.controller('requestDrtvCtrl', function( requestSrcv,$state,OrganizationUnit){
        var vm = this;
      
        vm.organizationUnit = new OrganizationUnit();

      
        vm.page = {
            start: 0,
            end: 0
        }
        requestSrcv.getAll().then(function(response){
            vm.requests = response.data;
        });

        vm.route = function(request){
            vm.code = request.type.code;
            vm.serial = request.serial;
            if (vm.code ==='CAPACITY_DELEGATION')
            {
              return    $state.go('home.CAPACITY_DELEGATION',{serial:vm.serial});
            }
            else(vm.code ==='ENTITLED_REGISTRATION')
            {
              return    $state.go('home.ENTITLED_REGISTRATION',{serial:vm.serial});

            }
        }

    
     
    });
}