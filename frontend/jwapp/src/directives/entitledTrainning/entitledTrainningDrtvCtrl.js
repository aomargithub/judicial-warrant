module.exports = function(app){
    app.controller('entitledTrainningDrtvCtrl', function(ChangeStatusRequest,$stateParams,appSessionSrvc,appRoleFcty,Entitled,entitledRegistrationSrvc){
        var vm = this;
        vm.message = null;
        vm.entitled = new Entitled();
        vm.changeStatusRequest = new ChangeStatusRequest ();
        vm.entitleds = [];
        vm.page = {
            start: 0,
            end: 0
        }
        if ($stateParams.serial) {
            entitledRegistrationSrvc.getEntitleds($stateParams.serial).then(function(response){
                vm.entitleds = response.data;
            });
        }

        if(appSessionSrvc.getCurrentUser().role === appRoleFcty.training.code) {
            vm.isNotUser = false;
         } else {
             vm.isNotUser = true;
         }
        vm.prepareBeforeworkFlowProccess = function (workFlowTypeProcess, entitled) {
            vm.workFlowTypeProcess = workFlowTypeProcess;
            vm.entitled = entitled;
        };
    
        vm.workFlowProccess = function () {
            if(vm.workFlowTypeProcess === 'inTraining'){
                vm.getEntitledInTraining();
            } else if (vm.workFlowTypeProcess === 'passed') {
                vm.getEntitledPassed();
            } else if (vm.workFlowTypeProcess === 'failture') {
                vm.getEntitledFailture();
            }
        }
    
        vm.getEntitledInTraining = function () {
            entitledRegistrationSrvc.getEntitledInTraining($stateParams.serial, vm.entitled.id, vm.changeStatusRequest ).then(function (response) {
                vm.entitled = response.data; 
            });
        };
    
        vm.getEntitledPassed = function () {
            entitledRegistrationSrvc.getEntitledPassed($stateParams.serial, vm.entitled.id, vm.changeStatusRequest ).then(function (response) {
                vm.entitled  = response.data; 
            });
        };
    
        vm.getEntitledFailture = function () {
            entitledRegistrationSrvc.getEntitledFailture($stateParams.serial, vm.entitled.id, vm.changeStatusRequest ).then(function (response) {
                vm.entitled  = response.data; 
            });
        };
    

    
        vm.closeMessage = function(){
            vm.message = null;
        };
    });
}