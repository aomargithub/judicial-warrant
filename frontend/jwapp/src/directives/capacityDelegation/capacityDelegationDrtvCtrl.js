module.exports = function (app) {
    app.controller('capacityDelegationDrtvCtrl', function ($rootScope, $state, $scope, requestTypeSrvc, organizationUnitSrvc, requestTypeAttachmentTypeSrvc, CapacityDelegation, RequestAttachment, capacityDelegationSrvc, attachmentTypeSrvc, requestAttachmentSrvc, httpStatusSrvc, stringUtilSrvc) {
        var vm = this;
        vm.capacityDelegation = new CapacityDelegation();
        vm.requestAttachment = new RequestAttachment();
      //  vm.editId = null;
        vm.message = null;
      //  vm.editCapacityDelegation = null;
       
     //   vm.attachments = [];
        vm.requestAttachments = [];
        vm.organizationUnites = [];
        vm.attachmentTypes = [];





        vm.page = {
            start: 0,
            end: 0
        };

       
       
       
        organizationUnitSrvc.getExternal().then(function (response) {
            vm.organizationUnites = response.data;
        });

        // requestTypeSrvc.getAll().then(function(response){
        //     vm.capacityDelegation.RequestType = response.data;

        // });

        requestTypeAttachmentTypeSrvc.getAttachmentTypesByRequestTypeCode($state.current.name).then(function(response){
              vm.attachmentTypes=response.data;

        });

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

        // if (vm.capacityDelegation.id) {
        //     capacityDelegationSrvc.getRequestAttachments(vm.capacityDelegation.request.serial).then(function (response) {
        //         vm.requestAttachments = response.data;

        //     })
        // };





        var resetEntryForm = function () {
            $scope.capacityDelegationForm.$setPristine();
            $scope.capacityDelegationForm.$setUntouched();
        }




        vm.addRequestAttachment = function () {
            capacityDelegationSrvc.uploadAttachment(vm.requestAttachment, vm.capacityDelegation).then(function success(response) {
                //vm.requestAttachments = vm.requestAttachments || [];
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



        

        // vm.edit = function (id) {
        //     capacityDelegationSrvc.getById(id).then(function (response) {
        //         vm.editCapacityDelegation = response.data;
        //         vm.editCapacityDelegation.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
        //         vm.requestAttachment = angular.copy(vm.editCapacityDelegation);
        //         resetEntryForm();

        //     });
        // };

        vm.refetch = function (id) {
            capacityDelegationSrvc.getById(id).then(function (response) {
                vm.editCapacityDelegation = response.data;
                vm.editCapacityDelegation.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.requestAttachment = angular.copy(vm.editCapacityDelegation);
                vm.message = null;
                resetEntryForm();

            });
        };

        vm.update = function () {

            capacityDelegationSrvc.update(vm.requestAttachment).then(function success(response) {

                var tempRequestAttachment = response.data;
                vm.requestAttachment = new RequestAttachment();
                vm.editCapacityDelegation = null;
                vm.requestAttachments.forEach(function (cd, index) {
                    if (cd.id === tempRequestAttachment.id) {
                        vm.requestAttachments[index] = tempFile;
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

        vm.cancel = function () {
            vm.requestAttachment = new RequestAttachment();
            vm.editCapacityDelegation = null;
            vm.message = null;
            resetEntryForm();

        };


        vm.reset = function () {
            if (vm.editCapacityDelegation) {
                vm.requestAttachment = angular.copy(vm.editCapacityDelegation);
            } else {
                vm.requestAttachment = new RequestAttachment();
            }
            resetEntryForm();

        };

        vm.closeMessage = function () {
            vm.message = null;
        };

    });
};