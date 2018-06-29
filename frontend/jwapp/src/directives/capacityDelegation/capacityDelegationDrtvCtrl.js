module.exports = function (app) {
    app.controller('capacityDelegationDrtvCtrl', function ($rootScope, $q, requestSrcv, $stateParams, $state, $scope, requestTypeSrvc, organizationUnitSrvc, requestTypeAttachmentTypeSrvc, CapacityDelegation, RequestAttachment, capacityDelegationSrvc, attachmentTypeSrvc, requestAttachmentSrvc, httpStatusSrvc, stringUtilSrvc) {
        var vm = this;
        vm.capacityDelegation = new CapacityDelegation();
        vm.requestAttachment = new RequestAttachment();
        //  vm.editId = null;
        vm.message = null;
        vm.editCapacityDelegation = null;
        vm.requestAttachments = [];
        vm.capacityDelegations=[];
        vm.organizationUnites = [];
        vm.attachmentTypes = [];
        vm.imgsrc = null;


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

        // requestTypeSrvc.getAll().then(function(response){
        //     vm.capacityDelegation.RequestType = response.data;

        // });

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

            })
        };

        vm.getRequestAttachments = function () {
            capacityDelegationSrvc.getRequestAttachments($stateParams.serial).then(function (response) {
                vm.requestAttachments = response.data;


            })
        };

        if ($stateParams.serial !="") {
            vm.getCapacityDelegations();
            vm.getRequestAttachments();
        }


        //================================
        vm.edit = function (serial) {
            capacityDelegationSrvc.getRequestAttachments(vm.capacityDelegation.serial).then(function (response) {
                vm.editCapacityDelegation = response.data;
                vm.editCapacityDelegation.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.requestAttachment = angular.copy(vm.editCapacityDelegation);
                resetEntryForm();

            });
        };



        vm.submission = function () {

            requestSrcv.activeRequest($stateParams.serial).then(function success(response) {
                vm.capacityDelegation = response.data;
                vm.capacityDelegation.forEach(function (cd, index) {
                    if (cd.id === tempCapacity.id) {
                        vm.capacityDelegation[index] = tempCapacity;
                    }
                });
                resetEntryForm();

            }, function error(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                if (status.code === httpStatusSrvc.badRequest.code) {
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };

        //===================================

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
        //===============================================


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
        //=============================================
        //download service 

        // var defer =$q.defer();
        //  vm.showImage = function (requestAttachment) {
        //      capacityDelegationSrvc.showImage(vm.capacityDelegation.request.serial, requestAttachment.id, requestAttachment.ucmDocumentId).then(function success(response){
        //         defer.resolve(URL.createObjectURL(response.data));
        //         defer.promise.then(function(url){
        //             var url=capacityDelegationSrvc.capacityDelegationsUrl+ 'serial=' + vm.capacityDelegation.request.serial + '/requestAttachments=' + vm.requestAttachment.id +'/ucmDocumentId=' + vm.requestAttachment.ucmDocumentId + '/download';
        //             var reader = new FileReader();

        //             reader.onload = function(){
        //                 url = reader.result;
        //                 defer.resolve(capacityDelegationsUrl + 'serial=' + serial + '/requestAttachments/' + id +'/ucmDocumentId=' + ucmDocumentId + '/download');
        //             };    

        //         });

        //     })};

        vm.showImage = function (requestAttachment) {
            capacityDelegationSrvc.showImage(vm.capacityDelegation.request.serial, requestAttachment.id, requestAttachment.ucmDocumentId).then(function success(response) {
                var file = new Blob([response], {type: "image/png"});
                console.log(response);
                // var fileURL = URL.createObjectURL(file);
                var reader = new FileReader();
                
               
                reader.onload = (function(f) {
                    return function(e) {
                        // Here you can use `e.target.result` or `this.result`
                        // and `f.name`.
                        vm.imgsrc = e.target.result;
                        console.log(response);
                        console.log(vm.imgsrc);
                    };
                })(file);
               
                
                reader.readAsDataURL(file);
                // vm.imgsrc = fileURL;
                // window.open(fileURL, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=400,height=400");

            })
        };


    });
};