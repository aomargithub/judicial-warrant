module.exports = function (app) {
    app.controller('entitledRegistrationDrtvCtrl', function ($rootScope, messageFcty,EntitledRegistrationChangeStatusRequest , $state, $scope, capacityDelegationSrvc, requestTypeSrvc, requestTypeAttachmentTypeSrvc, EntitledRegistration, CapacityDelegation, RequestAttachment, Entitled, EntitledAttachment, entitledRegistrationSrvc, attachmentTypeSrvc, requestAttachmentSrvc, httpStatusSrvc, stringUtilSrvc, modalSrvc, appSessionSrvc, appRoleFcty, $stateParams, blockUI) {
        var vm = this;
        vm.entitledRegistrationChangeStatusRequest = new EntitledRegistrationChangeStatusRequest();
        vm.entitledRegistration = new EntitledRegistration();
        vm.message = null;       
        vm.organizationUnites = [];
        vm.attachmentTypes = [];
        vm.capacityDelegations = [];

        vm.serial = $stateParams.serial;

        vm.entitleds = [];
        vm.entitled = new Entitled();
        vm.entitledAttachments = [];
        vm.entitledAttachment = new EntitledAttachment ();

        vm.workFlowTypeProcess = null;

        vm.isNotUser = false;

        vm.page = {
            start: 0,
            end: 0
        };

        if(appSessionSrvc.getCurrentUser().role === appRoleFcty.user.code) {
            vm.isNotUser = false;
         } else {
             vm.isNotUser = true;
         }

       capacityDelegationSrvc.getAll('ISSUED').then(function (response) {
            vm.capacityDelegations = response.data;
       });

       requestTypeAttachmentTypeSrvc.getAttachmentTypesByRequestTypeCode($state.current.name.replace('home.', '')).then(function success(response) {
        vm.attachmentTypes=response.data;

    });

    vm.prepareBeforeworkFlowProccess = function (workFlowTypeProcess) {
        vm.workFlowTypeProcess = workFlowTypeProcess;
    };

    vm.workFlowProccess = function () {
        console.log(vm.workFlowTypeProcess);
        if(vm.workFlowTypeProcess === 'submission'){
            vm.submission();
        } else if (vm.workFlowTypeProcess === 'inCompletion') {
            vm.inCompletion();
        } else if (vm.workFlowTypeProcess === 'rejection') {
            vm.rejection();
        } else if (vm.workFlowTypeProcess === 'inProgress') {
            vm.inProgress();
        }
    }

    vm.submission = function () {
        vm.entitledRegistrationChangeStatusRequest.entitledRegistration = vm.entitledRegistration;
        entitledRegistrationSrvc.submission(vm.entitledRegistration.request.serial, vm.entitledRegistrationChangeStatusRequest).then(function (response) {
            vm.entitledRegistration = response.data; 
            vm.reLoad();
        });
    };

    vm.inCompletion = function () {
        vm.entitledRegistrationChangeStatusRequest.entitledRegistration = vm.entitledRegistration;
        entitledRegistrationSrvc.inCompletion(vm.entitledRegistration.request.serial, vm.entitledRegistrationChangeStatusRequest).then(function (response) {
            vm.entitledRegistration = response.data;
            vm.reLoad(); 
        });
    };

    vm.rejection = function () {
        vm.entitledRegistrationChangeStatusRequest.entitledRegistration = vm.entitledRegistration;
        entitledRegistrationSrvc.rejection(vm.entitledRegistration.request.serial, vm.entitledRegistrationChangeStatusRequest).then(function (response) {
            vm.entitledRegistration = response.data; 
            vm.reLoad();
        });
    };

    vm.inProgress = function () {
        vm.entitledRegistrationChangeStatusRequest.entitledRegistration = vm.entitledRegistration;
        entitledRegistrationSrvc.inProgress(vm.entitledRegistration.request.serial, vm.entitledRegistrationChangeStatusRequest).then(function (response) {
            vm.entitledRegistration = response.data; 
            vm.reLoad();
        });
    };
    
        if (vm.serial) {
            entitledRegistrationSrvc.getBySerial(vm.serial).then(function success(response) {
                vm.entitledRegistration = response.data;    
               });
                entitledRegistrationSrvc.getEntitleds(vm.serial).then(function success(response) {
                    vm.entitleds = response.data;
                });
        };

        vm.save = function () {
         if(!vm.entitledRegistration.id) {
            blockUI.start();
            entitledRegistrationSrvc.save(vm.entitledRegistration).then(function success(response) {
                vm.entitledRegistration = response.data;
                vm.serial = vm.entitledRegistration.request.serial;
                blockUI.stop();
                // set serial in url to make user can refresh page and with same data
                vm.reLoad();
                messageFcty.showSuccessMessage();
           
            }, function error(response) {
                messageFcty.handleErrorMessage(response);
                blockUI.stop();
            });
        } else {
            vm.update();
        }
        };

        vm.update = function () {
            blockUI.start();
            entitledRegistrationSrvc.update(vm.entitledRegistration).then(function success(response) {
                vm.entitledRegistration = response.data;
                messageFcty.showSuccessMessage();
                blockUI.stop();
            }, function error(response) {
                messageFcty.handleErrorMessage(response);
                blockUI.stop();
            });
        };

        vm.prepareToUpdate = function (entitled) {
            vm.entitled = entitled;
        }

        vm.refetch = function (id) {
            entitledRegistrationSrvc.getById(id).then(function (response) {
                vm.entitledRegistration = response.data;
                vm.entitledRegistration.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                messageFcty.resetMessage();
            });
        };

        vm.closeMessage = function () {
            vm.message = null;
        };

        var resetEntryForm = function(){
            $scope.entitledRegistrationForm.$setPristine();
            $scope.entitledRegistrationForm.$setUntouched();
        }

        vm.reLoad = function() {
            // set serial in url to make user can refresh page and with same data
            // and refetch data in entitleds two change status
            return $state.go('home.ENTITLED_REGISTRATION',{serial:vm.serial},{reload: true});
        }

    });
};