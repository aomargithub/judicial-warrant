module.exports = function(app){
    app.controller('attachmentTypeDrtvCtrl', function($rootScope, $scope, AttachmentType, attachmentTypeSrvc, httpStatusSrvc, stringUtilSrvc){
        var vm = this;
        vm.attachmentType = new AttachmentType();
        vm.editId = null;
        vm.editAttachmentType = null;
        vm.message = null;
        vm.attachmentTypes = [];
        vm.page = {
            start: 0,
            end: 0
        }
        attachmentTypeSrvc.getAll().then(function(response){
            vm.attachmentTypes = response.data;
        });

        var resetEntryForm = function(){
            $scope.attachmentTypeForm.$setPristine();
            $scope.attachmentTypeForm.$setUntouched();
        }

        vm.add = function(){
            attachmentTypeSrvc.save(vm.attachmentType).then(function success(response){
                vm.attachmentTypes.push(response.data);
                vm.attachmentType = new AttachmentType();
                resetEntryForm();
            }, function error(response){
                var status = httpStatusSrvc.getStatus(response.status);
                if(status.code === httpStatusSrvc.badRequest.code){
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };

        vm.edit = function(id){
            attachmentTypeSrvc.getById(id).then(function(response){
                vm.editAttachmentType = response.data;
                vm.editAttachmentType.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.attachmentType = angular.copy(vm.editAttachmentType);
                resetEntryForm();
            });
        };

        vm.refetch = function(id){
            attachmentTypeSrvc.getById(id).then(function(response){
                vm.editAttachmentType = response.data;
                vm.editAttachmentType.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.attachmentType = angular.copy(vm.editAttachmentType);
                vm.message = null;
                resetEntryForm();
            });
        };

        vm.update = function(){
            
            attachmentTypeSrvc.update(vm.attachmentType).then(function success(response){
               
                var tempAttachmentType = response.data;
                vm.attachmentType = new AttachmentType();
                vm.editAttachmentType = null;
                vm.attachmentTypes.forEach(function(ou, index){
                    if(ou.id === tempAttachmentType.id){
                        vm.attachmentTypes[index] = tempAttachmentType;
                    }
                });

                resetEntryForm();
            }, function error(response){
                
                var status = httpStatusSrvc.getStatus(response.status);
                if(status.code === httpStatusSrvc.preconditionFailed.code){
                    vm.message = $rootScope.messages[status.text];
                };

                resetEntryForm();
            });
        };

        vm.delete = function(id){
            
            attachmentTypeSrvc.delete(id).then(function success(response){               
                vm.attachmentTypes.forEach(function(ou, index){
                    if(ou.id === id){
                        vm.attachmentTypes.splice(index, 1);
                    }
                });

                resetEntryForm();
            }, function error(response){
                
                var status = httpStatusSrvc.getStatus(response.status);
                if(status.code === httpStatusSrvc.preconditionFailed.code){
                    vm.message = $rootScope.messages[status.text];
                };

                resetEntryForm();
            });
        };

        vm.cancel = function(){
            vm.attachmentType = new AttachmentType();
            vm.editAttachmentType = null;
            vm.message = null;
            resetEntryForm();
        };

        vm.reset = function(){
            if(vm.editAttachmentType){
                vm.attachmentType = angular.copy(vm.editAttachmentType);
            }else{
                vm.attachmentType = new AttachmentType();
            }
            resetEntryForm();
        };

        vm.closeMessage = function(){
            vm.message = null;
        };
    });
}