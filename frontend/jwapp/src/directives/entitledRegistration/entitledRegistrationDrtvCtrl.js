module.exports = function (app) {
    app.controller('entitledRegistrationDrtvCtrl', function ($rootScope, $state, $scope, capacityDelegationSrvc, requestTypeSrvc, requestTypeAttachmentTypeSrvc, EntitledRegistration, CapacityDelegation, RequestAttachment, Entitled, EntitledAttachment, entitledRegistrationSrvc, attachmentTypeSrvc, requestAttachmentSrvc, httpStatusSrvc, stringUtilSrvc, modalSrvc, $stateParams) {
        var vm = this;
        vm.entitledRegistration = new EntitledRegistration();
        vm.requestAttachment = new RequestAttachment();
        vm.message = null;       
        vm.requestAttachments = [];
        vm.organizationUnites = [];
        vm.attachmentTypes = [];
        vm.capacityDelegations = [];

        vm.entitleds = [];
        vm.entitled = new Entitled();
        vm.entitledAttachments = [];
        vm.entitledAttachment = new EntitledAttachment ();

        vm.page = {
            start: 0,
            end: 0
        };

       capacityDelegationSrvc.getAll('ISSUED').then(function (response) {
            vm.capacityDelegations = response.data;
       });

       requestTypeAttachmentTypeSrvc.getAttachmentTypesByRequestTypeCode($state.current.name.replace('root.', '')).then(function success(response) {
        vm.attachmentTypes=response.data;

    });

    
        if ($stateParams.serial) {
            entitledRegistrationSrvc.getBySerial($stateParams.serial).then(function success(response) {
                vm.entitledRegistration = response.data;   
               });
               entitledRegistrationSrvc.getRequestAttachments($stateParams.serial).then(function success(response) {
                vm.requestAttachments = response.data;    
                });
                entitledRegistrationSrvc.getEntitleds($stateParams.serial).then(function success(response) {
                    vm.entitleds = response.data;
                });
        };

        vm.save = function () {

         if(!vm.entitledRegistration.id) {
            entitledRegistrationSrvc.save(vm.entitledRegistration).then(function success(response) {
                vm.entitledRegistration = response.data;
           
            }, function error(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.badRequest.code) {
                    vm.message = $rootScope.messages[status.text];
                };
            });
        } else {
            vm.update();
        }
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

        vm.prepareToUpdate = function (entitled) {
            vm.entitled = entitled;
        }

        vm.addRequestAttachment = function () {
            entitledRegistrationSrvc.uploadAttachment(vm.requestAttachment, vm.entitledRegistration.request.serial).then(function success(response) {
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

        vm.showRequestAttachmentImage = function (requestAttachment) {
            entitledRegistrationSrvc.showRequestAttachmentImage(vm.entitledRegistration.request.serial, requestAttachment.id, requestAttachment.ucmDocumentId).then(function success(response) {
            modalSrvc.viewContent(response);   
            })
        };


        vm.saveEntitled = function () {
            if(!vm.entitled.id) {
                vm.entitled.entitledRegistration = vm.entitledRegistration;
               entitledRegistrationSrvc.saveEntitled(vm.entitledRegistration.request.serial, vm.entitled).then(function success(response) {
                   vm.entitled = response.data;
                   vm.entitleds.push(vm.entitled);
               }, function error(response) {
                   var status = httpStatusSrvc.getStatus(response.status);
                   if (status.code === httpStatusSrvc.badRequest.code) {
                       vm.message = $rootScope.messages[status.text];
                   };
               });
           } else {
               vm.updateEntitled();
           }
        };
   
        vm.updateEntitled = function () {
               entitledRegistrationSrvc.updateEntitled(vm.entitledRegistration.request.serial, vm.entitled).then(function success(response) {
                   vm.entitled = response.data;
               }, function error(response) {
                   var status = httpStatusSrvc.getStatus(response.status);
                   if (status.code === httpStatusSrvc.preconditionFailed.code) {
                       vm.message = $rootScope.messages[status.text];
                   };
               });
           };

        vm.getEntitleds = function(){
            entitledRegistrationSrvc.getEntitleds(vm.entitledRegistration.request.serial).then(function success(respone) {
                vm.entitleds = response.data;
            });
        };

        vm.prepareToOpenEntitedAttachments = function (entitled) {
            vm.entitled = entitled;
            vm.getEntitledAttachments(entitled.id);
        }

        vm.showEntitledAttachmentImage = function (entitledAttachment) {
            entitledRegistrationSrvc.showEntitledAttachmentImage(vm.entitledRegistration.request.serial, entitledAttachment.id, entitledAttachment.ucmDocumentId).then(function success(response) {
            modalSrvc.viewContent(response);   
            })
        };

        vm.deleteEntitled = function (id) {
            entitledRegistrationSrvc.deleteEntitled(vm.entitledRegistration.request.serial, id).then(function success(response) {
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
            entitledRegistrationSrvc.getAllEntitledAttachments(vm.entitledRegistration.request.serial, entitledId).then(function success(response) {
                vm.entitledAttachments = response.data;
            });
        }

        vm.addEntitledAttachment = function () {
            vm.entitledAttachment.entitled = vm.entitled;
            entitledRegistrationSrvc.uploadEntitledAttachment(vm.entitledAttachment, vm.entitledRegistration.request.serial, vm.entitled.id).then(function success(response) {
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
            entitledRegistrationSrvc.deleteEntitledAttachment(vm.entitledRegistration.request.serial, id).then (function success (response){
                vm.entitledAttachments.forEach(function (ea, index) {
                    if (ea.id === id) {
                        vm.entitledAttachments.splice(index, 1);
                    }
                });
            });
        
        };

    });
};