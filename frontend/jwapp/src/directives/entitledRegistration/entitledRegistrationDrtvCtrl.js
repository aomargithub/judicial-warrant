module.exports = function (app) {
    app.controller('entitledRegistrationDrtvCtrl', function ($rootScope, $state, $scope, capacityDelegationSrvc, requestTypeSrvc, requestTypeAttachmentTypeSrvc, EntitledRegistration, CapacityDelegation, RequestAttachment, entitledRegistrationSrvc, attachmentTypeSrvc, requestAttachmentSrvc, httpStatusSrvc, stringUtilSrvc) {
        var vm = this;
        vm.entitledRegistration = new EntitledRegistration();
        vm.requestAttachment = new RequestAttachment();
        vm.message = null;       
        vm.requestAttachments = [];
        vm.organizationUnites = [];
        vm.attachmentTypes = [];
        vm.capacityDelegations = [];

        vm.page = {
            start: 0,
            end: 0
        };

       capacityDelegationSrvc.getAll().then(function (response) {
            vm.capacityDelegations = response.data;
       });

        requestTypeAttachmentTypeSrvc.getAttachmentTypesByRequestTypeCode($state.current.name.replace('root.', '')).then(function(response){
              vm.attachmentTypes=response.data;

        });

        vm.save = function () {

            entitledRegistrationSrvc.save(vm.entitledRegistration).then(function success(response) {
                vm.entitledRegistration = response.data;
           
            }, function error(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.badRequest.code) {
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };

        vm.update = function () {

            entitledRegistrationSrvc.update(vm.entitledRegistration).then(function success(response) {
                vm.entitledRegistration = response.data;
            }, function error(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.preconditionFailed.code) {
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };

        vm.addRequestAttachment = function () {
            entitledRegistrationSrvc.uploadAttachment(vm.requestAttachment, vm.entitledRegistration).then(function success(response) {
                //vm.requestAttachments = vm.requestAttachments || [];
                vm.requestAttachments.push(response.data);
                vm.requestAttachment = new RequestAttachment();

                resetEntitledRegistrationAttachmentEntryForm();

            }, function error(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.badRequest.code) {
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };

        vm.deleteRequestAttachment = function (id) {
            entitledRegistrationSrvc.deleteRequestAttachment(id, vm.entitledRegistration).then(function success(response) {
                vm.requestAttachments.forEach(function (cd, index) {
                    if (cd.id === id) {
                        vm.requestAttachments.splice(index, 1);
                    }
                });
            }, function error(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.preconditionFailed.code) {
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };


        var resetEntitledRegistrationAttachmentEntryForm = function () {
            $scope.entitledRegistrationAttachmentForm.$setPristine();
            $scope.entitledRegistrationAttachmentForm.$setUntouched();
        }

        vm.refetch = function (id) {
            entitledRegistrationSrvc.getById(id).then(function (response) {
                vm.entitledRegistration = response.data;
                vm.entitledRegistration.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.message = null;
            });
        };

        vm.closeMessage = function () {
            vm.message = null;
        };

        var resetEntryForm = function(){
            $scope.entitledRegistrationForm.$setPristine();
            $scope.entitledRegistrationForm.$setUntouched();
        }

    });
};