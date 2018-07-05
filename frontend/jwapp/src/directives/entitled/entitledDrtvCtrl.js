module.exports = function (app) {
    app.controller('entitledDrtvCtrl', function ($rootScope, $state, $scope, Entitled, EntitledAttachment, entitledRegistrationSrvc, requestTypeSrvc, requestTypeAttachmentTypeSrvc, attachmentTypeSrvc, httpStatusSrvc, stringUtilSrvc, modalSrvc, appSessionSrvc, appRoleFcty, $stateParams) {
        var vm = this;
        vm.entitleds = [];
        vm.entitled = new Entitled();
        vm.entitledAttachments = [];
        vm.entitledAttachment = new EntitledAttachment ();
        vm.attachmentTypes = [];

        vm.page = {
            start: 0,
            end: 0
        };
    
        requestTypeAttachmentTypeSrvc.getAttachmentTypesByRequestTypeCode($state.current.name.replace('root.', '')).then(function success(response) {
            vm.attachmentTypes=response.data;
    
        });

        entitledRegistrationSrvc.getEntitleds($scope.serial).then(function success(response) {
            vm.entitleds = response.data;
        });


        vm.save = function () {
            entitledRegistrationSrvc.getBySerial($scope.serial).then(function success(respone){
                vm.entitled.entitledRegistration = respone.data;

                if(!vm.entitled.id) {
                    entitledRegistrationSrvc.saveEntitled($scope.serial, vm.entitled).then(function success(response) {
                        vm.entitleds = vm.entitleds || [];
                        vm.entitleds.push(response.data);
                        vm.entitled = new Entitled();
                    }, function error(response) {
                        var status = httpStatusSrvc.getStatus(response.status);
                        if (status.code === httpStatusSrvc.badRequest.code) {
                            vm.message = $rootScope.messages[status.text];
                        };
                    });
                } else {
                    vm.updateEntitled();
                }
                

            });
        };
   
        vm.updateEntitled = function () {
               entitledRegistrationSrvc.updateEntitled($scope.serial, vm.entitled).then(function success(response) {
                   vm.entitled = response.data;
               }, function error(response) {
                   var status = httpStatusSrvc.getStatus(response.status);
                   if (status.code === httpStatusSrvc.preconditionFailed.code) {
                       vm.message = $rootScope.messages[status.text];
                   };
               });
           };

        vm.getEntitleds = function(){
            entitledRegistrationSrvc.getEntitleds($scope.serial).then(function success(respone) {
                vm.entitleds = response.data;
            });
        };

        vm.prepareToOpenEntitedAttachments = function (entitled) {
            vm.entitled = entitled;
            vm.getEntitledAttachments(entitled.id);
        }

        vm.showEntitledAttachmentImage = function (entitledAttachment) {
            entitledRegistrationSrvc.showEntitledAttachmentImage($scope.serial, entitledAttachment.id, entitledAttachment.ucmDocumentId).then(function success(response) {
            modalSrvc.viewContent(response);   
            })
        };

        vm.deleteEntitled = function (id) {
            entitledRegistrationSrvc.deleteEntitled($scope.serial, id).then(function success(response) {
                vm.entitleds.forEach(function (e, index) {
                    if (e.id === id) {
                        vm.entitleds.splice(index, 1);
                    }
                });
            }, function error(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.preconditionFailed.code) {
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };
        
        var resetEntitledAttachmentEntryForm = function () {
            $scope.entitledAttachmentForm.$setPristine();
            $scope.entitledAttachmentForm.$setUntouched();
        }
        
        vm.getEntitledAttachments = function (entitledId) {
            entitledRegistrationSrvc.getAllEntitledAttachments($scope.serial, entitledId).then(function success(response) {
                vm.entitledAttachments = response.data;
            });
        }

        vm.addEntitledAttachment = function () {
            vm.entitledAttachment.entitled = vm.entitled;
            entitledRegistrationSrvc.uploadEntitledAttachment(vm.entitledAttachment, $scope.serial, vm.entitled.id).then(function success(response) {
                vm.entitledAttachments = vm.entitledAttachments || [];
                vm.entitledAttachments.push(response.data);
                vm.entitledAttachment = new EntitledAttachment();

                resetEntitledAttachmentEntryForm();

            }, function error(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.badRequest.code) {
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };

        vm.deleteEntitledAttachment = function (id) {
            entitledRegistrationSrvc.deleteEntitledAttachment($scope.serial, id).then (function success (response){
                vm.entitledAttachments.forEach(function (ea, index) {
                    if (ea.id === id) {
                        vm.entitledAttachments.splice(index, 1);
                    }
                });
            });
        
        };


    });
};