module.exports = function (app) {
    app.controller('requestAttachmentDrtvCtrl', function ($rootScope,  messageFcty, $state, $scope, requestTypeAttachmentTypeSrvc, RequestAttachment, requestAttachmentSrvc, httpStatusSrvc,modalSrvc) {
        var vm = this;
        vm.requestAttachment = new RequestAttachment();     
        vm.requestAttachments = [];
        vm.attachmentTypes = [];

        vm.page = {
            start: 0,
            end: 0
        };
       
       requestTypeAttachmentTypeSrvc.getAttachmentTypesByRequestTypeCode($state.current.name.replace('home.', '')).then(function success(response) {
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
                messageFcty.showSuccessMessage();
                resetRequestAttachmentEntryForm();

            }, function error(response) {
                messageFcty.handleErrorMessage(response);
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
                 messageFcty.handleErrorMessage(response);
            });
        };


        var resetRequestAttachmentEntryForm = function () {
            $scope.requestAttachmentForm.$setPristine();
            $scope.requestAttachmentForm.$setUntouched();
        }


        vm.showRequestAttachmentImage = function (requestAttachment) {
            requestAttachmentSrvc.showRequestAttachmentImage($scope.urlperfix, $scope.serial, requestAttachment.id, requestAttachment.ucmDocumentId).then(function success(response) {
            modalSrvc.viewContent(response);   
            })
        };

    });
};