module.exports = function (app) {
    app.controller('entitledDrtvCtrl', function ($rootScope,messageFcty, $state, $scope, Entitled, EntitledAttachment, ChangeStatusRequest, entitledRegistrationSrvc, requestTypeSrvc, requestTypeAttachmentTypeSrvc, attachmentTypeSrvc, httpStatusSrvc, stringUtilSrvc, modalSrvc, appSessionSrvc, appRoleFcty, $stateParams) {
        var vm = this;
        vm.entitleds = [];
        vm.entitled = new Entitled();
        vm.entitledForAttachmentsDialog = new Entitled();
        vm.entitledAttachments = [];
        vm.entitledAttachment = new EntitledAttachment ();
        vm.attachmentTypes = [];

        vm.changeStatusRequest = new ChangeStatusRequest();
        vm.action = null;

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
                        messageFcty.handleErrorMessage(response);
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
                messageFcty.handleErrorMessage(response);
               });
           };

        vm.getEntitleds = function(){
            entitledRegistrationSrvc.getEntitleds($scope.serial).then(function success(respone) {
                vm.entitleds = response.data;
            });
        };

        vm.prepareToOpenEntitedAttachments = function (entitled) {
            vm.entitledForAttachmentsDialog = entitled;
            vm.getEntitledAttachments(entitled.id);
        }

        vm.prepareToUpdate = function (entitled) {
            vm.entitled = entitled;
        }

        vm.resetEntitled = function () {
            vm.entitled = new Entitled();
        }

        vm.showEntitledAttachmentImage = function (entitledAttachment) {
            entitledRegistrationSrvc.showEntitledAttachmentImage($scope.serial, entitledAttachment.id, entitledAttachment.ucmDocumentId).then(function success(response) {
            modalSrvc.viewContent(response);   
            })
        };

        vm.prepareToOpenWorkFlowActionDialog = function (entitled, action) {
            vm.action = action;
            vm.entitledForAttachmentsDialog = entitled;
        }

        vm.workFlowAction = function () {
            if(vm.action === 'ACCEPTED') {
                vm.acceptEntitled();
            } else if (vm.action === 'REJECTED') {
                vm.rejectionEntitled();
            } else if (vm.action === 'CARD_RECIEVED') {
                vm.cardRecievedEntitledCardRecieved();
            }
            
        }

        vm.deleteEntitled = function (id) {
            entitledRegistrationSrvc.deleteEntitled($scope.serial, id).then(function success(response) {
                vm.entitleds.forEach(function (e, index) {
                    if (e.id === id) {
                        vm.entitleds.splice(index, 1);
                    }
                });
            }, function error(response) {
                messageFcty.handleErrorMessage(response);
            });
        };

        vm.acceptEntitled = function () {
            entitledRegistrationSrvc.acceptEntitled($scope.serial, vm.entitledForAttachmentsDialog.id, vm.changeStatusRequest).then(function success(response) {
                vm.entitledForAttachmentsDialog = response.data;
                vm.entitleds.forEach(function (e, index) {
                    if (e.id === vm.entitled.id) {
                        vm.entitleds[index] = vm.entitled;
                        vm.entitledForAttachmentsDialog = new Entitled();
                    }
                });
            }, function error(response) {
                messageFcty.handleErrorMessage(response);
            });
        };

        vm.cardRecievedEntitledCardRecieved = function () {
            entitledRegistrationSrvc.cardRecievedEntitledCardRecieved($scope.serial, vm.entitledForAttachmentsDialog.id, vm.changeStatusRequest).then(function success(response) {
                vm.entitledForAttachmentsDialog = response.data;
                vm.entitleds.forEach(function (e, index) {
                    if (e.id === vm.entitled.id) {
                        vm.entitleds[index] = vm.entitledForAttachmentsDialog;
                        vm.entitledForAttachmentsDialog = new Entitled();
                    }
                });
            }, function error(response) {
                messageFcty.handleErrorMessage(response);
            });
        };

        vm.rejectionEntitled = function () {
            entitledRegistrationSrvc.rejectionEntitled($scope.serial, vm.entitledForAttachmentsDialog.id, vm.changeStatusRequest).then(function success(response) {
                vm.entitledForAttachmentsDialog = response.data;
                vm.entitleds.forEach(function (e, index) {
                    if (e.id === vm.entitled.id) {
                        vm.entitleds[index] = vm.entitledForAttachmentsDialog;
                        vm.entitledForAttachmentsDialog = new Entitled();
                    }
                });
            }, function error(response) {
                messageFcty.handleErrorMessage(response);
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
            vm.entitledAttachment.entitled = vm.entitledForAttachmentsDialog;
            entitledRegistrationSrvc.uploadEntitledAttachment(vm.entitledAttachment, $scope.serial, vm.entitledForAttachmentsDialog.id).then(function success(response) {
                vm.entitledAttachments = vm.entitledAttachments || [];
                vm.entitledAttachments.push(response.data);
                vm.entitledAttachment = new EntitledAttachment();

                resetEntitledAttachmentEntryForm();

            }, function error(response) {
                messageFcty.handleErrorMessage(response);
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