module.exports = function(app){
    app.controller('myRequestDrtvCtrl', function( requestSrcv,CapacityDelegation,  httpStatusSrvc, stringUtilSrvc,$state){
        var vm = this;
  
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
             
              return    $state.go('root.CAPACITY_DELEGATION',{serial:vm.serial});
            }
            else(vm.code ==='ENTITLED_REGISTRATION')
            {
               
              return    $state.go('root.ENTITLED_REGISTRATION',{serial:vm.serial});

            }
        }
       
    });
}