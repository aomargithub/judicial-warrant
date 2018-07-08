module.exports = function (app) {
    app.controller('requestAttachmentDrtvCtrl', function ($rootScope, $state, $scope, requestTypeAttachmentTypeSrvc, RequestAttachment, requestAttachmentSrvc, httpStatusSrvc,modalSrvc) {
        var vm = this;
        vm.requestAttachment = new RequestAttachment();
        vm.message = null;       
        vm.requestAttachments = [];
        vm.attachmentTypes = [];

        vm.page = {
            start: 0,
            end: 0
        };

       requestTypeAttachmentTypeSrvc.getAttachmentTypesByRequestTypeCode($state.current.name.replace('root.', '')).then(function success(response) {
        vm.attachmentTypes=response.data;

        if($scope.serial) {
            requestAttachmentSrvc.getRequestAttachments($scope.urlperfix, $scope.serial).then(function success(response) {
            vm.requestAttachments = response.data;    
            });
        }

    });


        vm.addRequestAttachment = function () {
            requestAttachmentSrvc.uploadAttachment($scope.urlperfix, vm.requestAttachment, $scope.serial).then(function success(response) {
                vm.requestAttachments = vm.requestAttachments || [];
                vm.requestAttachments.push(response.data);
                vm.requestAttachment = new RequestAttachment();

                resetRequestAttachmentEntryForm();

            }, function error(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.badRequest.code) {
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };

        vm.deleteRequestAttachment = function (id) {
            requestAttachmentSrvc.deleteRequestAttachment($scope.urlperfix, id, $scope.serial).then(function success(response) {
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


        var resetRequestAttachmentEntryForm = function () {
            $scope.requestAttachmentForm.$setPristine();
            $scope.requestAttachmentForm.$setUntouched();
        }

        vm.closeMessage = function () {
            vm.message = null;
        };

        vm.showRequestAttachmentImage = function (requestAttachment) {
            requestAttachmentSrvc.showRequestAttachmentImage($scope.urlperfix, $scope.serial, requestAttachment.id, requestAttachment.ucmDocumentId).then(function success(response) {
            modalSrvc.viewContent(response);   
            })
        };

    
    });
};