module.exports = function(app){
    app.controller('myRequestDrtvCtrl', function( requestSrcv,$state,requestTypeSrvc,$scope){
        var vm = this;
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
        {id:'12', arabicName:'create'}]
       
        vm.filters={}; 
        
        vm.page = {
            start: 0,
            end: 0
        }
        requestTypeSrvc.getAll().then(function (response){
            vm.requestTypes = response.data;
        });
        requestSrcv.getAll().then(function(response){
            vm.requests = response.data;
        });
        vm.reLoad = function() {
            return $state.go("home.myRequests",{},{reload: "home.myRequests"});
        }


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