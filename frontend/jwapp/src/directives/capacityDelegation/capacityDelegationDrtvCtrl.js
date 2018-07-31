module.exports = function (app) {
    app.controller('capacityDelegationDrtvCtrl', function ($stateParams, $state, $scope, organizationUnitSrvc, requestTypeAttachmentTypeSrvc, CapacityDelegation, RequestAttachment, CapacityDelegationChangeStatusRequest, capacityDelegationSrvc, stringUtilSrvc, modalSrvc, messageFcty) {
        var vm = this;
        vm.capacityDelegation = new CapacityDelegation();
        vm.requestAttachment = new RequestAttachment();
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
            capacityDelegationSrvc.save(vm.capacityDelegation).then(function success(response) {
                vm.capacityDelegation = response.data;
                vm.serial = vm.capacityDelegation.request.serial;
                messageFcty.showSuccessMessage();
                vm.reload();
            }, function error(response) {
                messageFcty.handleErrorMessage(response);
            });

        } else {
            capacityDelegationSrvc.update(vm.capacityDelegation).then(function success(response) {
                vm.capacityDelegation = response.data;
            }, function error(response) {
                messageFcty.handleErrorMessage(response);
            });
        }
        };

        vm.refetch = function(){
            capacityDelegationSrvc.getCapacityDelegations(vm.capacityDelegation.request.serial).then(function(response){
                vm.editeCapacityDelegation = response.data;
                vm.editeCapacityDelegation.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                 vm.capacityDelegation = angular.copy(vm.editeCapacityDelegation);
                 messageFcty.resetMessage();
                resetEntryForm();
            });
        };


        //===================================
        vm.getCapacityDelegations = function () {
            capacityDelegationSrvc.getCapacityDelegations($stateParams.serial).then(function (response) {
                vm.capacityDelegation = response.data;
                vm.capacityDelegation.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));


            },function error(response) {
                messageFcty.handleErrorMessage(response);
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
                messageFcty.showSuccessMessage();
                resetEntryForm();
                vm.reload();
     

            },function error(response) {
                messageFcty.handleErrorMessage(response);
            });
        };


        //===============================================
        vm.showImage = function (requestAttachment) {
            capacityDelegationSrvc.showImage(vm.capacityDelegation.request.serial, requestAttachment.id, requestAttachment.ucmDocumentId).then(function success(response) {
           modalSrvc.viewContent(response);
            },function error(response) {
                messageFcty.handleErrorMessage(response);
            });
        };



        vm.reLoad = function() {
            // set serial in url to make user can refresh page and with same data
            // and refetch data to two change status
            return $state.go("home.CAPACITY_DELEGATION",{serial:vm.serial},{reload: "home.CAPACITY_DELEGATION"});
        }



    });
};