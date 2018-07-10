module.exports = function (app) {
    app.controller('capacityDelegationDrtvCtrl', function ($rootScope, $stateParams, $state, $scope, requestTypeSrvc, organizationUnitSrvc, requestTypeAttachmentTypeSrvc, CapacityDelegation, RequestAttachment, CapacityDelegationChangeStatusRequest, capacityDelegationSrvc,httpStatusSrvc, stringUtilSrvc, modalSrvc) {
        var vm = this;
        vm.capacityDelegation = new CapacityDelegation();
        vm.requestAttachment = new RequestAttachment();
        vm.message = null;
        vm.messageWithLink = false;
        vm.messageDiscription = null;
        vm.editeCapacityDelegation = null;
        vm.serial = $stateParams.serial;

        vm.requestAttachments = [];
        vm.organizationUnites = [];
        vm.attachmentTypes = [];
        vm.imgsrc = null;
        vm.capacityDelegationChangeStatusRequest = new CapacityDelegationChangeStatusRequest();


        vm.page = {
            start: 0,
            end: 0
        };
        var resetEntryForm = function () {
            $scope.capacityDelegationForm.$setPristine();
            $scope.capacityDelegationForm.$setUntouched();
        };

        //===================================

        organizationUnitSrvc.getExternal().then(function (response) {
            vm.organizationUnites = response.data;
        });

       

        requestTypeAttachmentTypeSrvc.getAttachmentTypesByRequestTypeCode($state.current.name.slice(5)).then(function (response) {
            vm.attachmentTypes = response.data;

        });
        //===================================
 
        vm.save = function () {
        if(!vm.capacityDelegation.id) {
            capacityDelegationSrvc.save(null).then(function success(response) {
                vm.capacityDelegation = response.data;
                vm.serial = vm.capacityDelegation.request.serial;
                vm.reload();
            }, function error(response) {
                vm.handleErrorMessage(response);
            });

        } else {
            capacityDelegationSrvc.update(vm.capacityDelegation).then(function success(response) {
                vm.capacityDelegation = response.data;
            }, function error(response) {
                vm.handleErrorMessage(response);
            });
        }
        };

        vm.refetch = function(){
            capacityDelegationSrvc.getCapacityDelegations(vm.capacityDelegation.request.serial).then(function(response){
                vm.editeCapacityDelegation = response.data;
                vm.editeCapacityDelegation.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                 vm.capacityDelegation = angular.copy(vm.editeCapacityDelegation);
                vm.message = null; 
                resetEntryForm();
            });
        };


        //===================================
        vm.getCapacityDelegations = function () {
            capacityDelegationSrvc.getCapacityDelegations($stateParams.serial).then(function (response) {
                vm.capacityDelegation = response.data;
                vm.capacityDelegation.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));


            },function error(response) {
                vm.handleErrorMessage(response);
            });
        };

        if (vm.serial) {
            vm.getCapacityDelegations();
        }


        //================================

        vm.submission = function () {
            vm.capacityDelegationChangeStatusRequest.capacityDelegation = vm.capacityDelegation;
            capacityDelegationSrvc.submission(vm.capacityDelegation.request.serial, vm.capacityDelegationChangeStatusRequest).then(function (response) {
                vm.capacityDelegation = response.data;
                resetEntryForm();
                vm.reload();
     

            },function error(response) {
                vm.handleErrorMessage(response);
            });
        };


        //===============================================
        vm.showImage = function (requestAttachment) {
            capacityDelegationSrvc.showImage(vm.capacityDelegation.request.serial, requestAttachment.id, requestAttachment.ucmDocumentId).then(function success(response) {
           modalSrvc.viewContent(response);
            },function error(response) {
                vm.handleErrorMessage(response);
            });
        };


        vm.closeMessage = function () {
            vm.message = null;
            vm.messageDiscription = null;
        };


        vm.reLoad = function() {
            // set serial in url to make user can refresh page and with same data
            // and refetch data to two change status
            return $state.go('root.CAPACITY_DELEGATION',{serial:vm.serial},{reload: true});
        }

        vm.handleErrorMessage = function(response) {
            var status = httpStatusSrvc.getStatus(response.status);
                console.log(response);
                if (status.code === httpStatusSrvc.preconditionFailed.code) {
                    vm.messageWithLink = true;
                    vm.message = $rootScope.messages[status.text];
                } else {
                    vm.messageWithLink = false;
                    vm.messageDiscription = response.data.message.split(":")[0];
                    vm.message = $rootScope.messages[status.text];
                };
        }



    });
};