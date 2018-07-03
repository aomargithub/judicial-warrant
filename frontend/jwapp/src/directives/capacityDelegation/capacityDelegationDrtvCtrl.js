module.exports = function (app) {
    app.controller('capacityDelegationDrtvCtrl', function ($rootScope, $stateParams, $state, $scope, requestTypeSrvc, organizationUnitSrvc, requestTypeAttachmentTypeSrvc, CapacityDelegation, RequestAttachment, CapacityDelegationChangeStatusRequest, capacityDelegationSrvc,httpStatusSrvc, stringUtilSrvc, modalSrvc) {
        var vm = this;
        vm.capacityDelegation = new CapacityDelegation();
        vm.requestAttachment = new RequestAttachment();
        vm.message = null;
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
            capacityDelegationSrvc.save(vm.capacityDelegation).then(function success(response) {
                vm.capacityDelegation = response.data;
                resetEntryForm();
            }, function error(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.badRequest.code) {
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };

        vm.addRequestAttachment = function () {
            capacityDelegationSrvc.uploadAttachment(vm.requestAttachment, vm.capacityDelegation.request.serial).then(function success(response) {
                 vm.requestAttachments = vm.requestAttachments || [];
                vm.requestAttachments.push(response.data);
                vm.requestAttachment = new RequestAttachment();
                resetEntryForm();
            }, function error(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.badRequest.code) {
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };


        //===================================
        vm.getCapacityDelegations = function () {
            capacityDelegationSrvc.getCapacityDelegations($stateParams.serial).then(function (response) {
                vm.capacityDelegation = response.data;
                vm.capacityDelegation.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));


            })
        };

        vm.getRequestAttachments = function () {
            capacityDelegationSrvc.getRequestAttachments($stateParams.serial).then(function (response) {
                vm.requestAttachments = response.data;


            })
        };

        if ($stateParams.serial) {
            vm.getCapacityDelegations();
            vm.getRequestAttachments();
        }


        //================================

        vm.submission = function () {
            vm.capacityDelegationChangeStatusRequest.capacityDelegation = vm.capacityDelegation;
            capacityDelegationSrvc.submission(vm.capacityDelegation.request.serial, vm.capacityDelegationChangeStatusRequest).then(function (response) {
                vm.capacityDelegation = response.data;
                resetEntryForm();
     

            });
        };


        //===============================================
        vm.showImage = function (requestAttachment) {
            capacityDelegationSrvc.showImage(vm.capacityDelegation.request.serial, requestAttachment.id, requestAttachment.ucmDocumentId).then(function success(response) {
           modalSrvc.viewContent(response);
            })
        };

        vm.deleteRequestAttachment = function (id) {

            capacityDelegationSrvc.deleteRequestAttachment(id, vm.capacityDelegation).then(function success(response) {
                vm.requestAttachments.forEach(function (cd, index) {
                    if (cd.id === id) {
                        vm.requestAttachments.splice(index, 1);
                    }
                });

                resetEntryForm();

            }, function error(response) {

                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.preconditionFailed.code) {
                    vm.message = $rootScope.messages[status.text];
                };

                resetEntryForm();

            });
        };
        vm.closeMessage = function () {
            vm.message = null;
        };






    });
};