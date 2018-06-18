module.exports = function(app){
    app.controller('requestTypeAttachmentTypeDrtvCtrl', function($rootScope,$scope,RequestTypeAttachmentType,requestTypeAttachmentTypeSrvc,httpStatusSrvc,stringUtilSrvc,attachmentTypeSrvc,requestTypeSrvc){
        var vm = this;
        vm.requestTypeAttachmentType = new RequestTypeAttachmentType();
        vm.editId = null;
        vm.message = null;
        vm.editrequestTypeAttachmentType = null;
        vm.requestTypeAttachmentTypes = [];
        vm.requests = [];
        vm.attachments = []; 

        vm.page = {
            start: 0,
            end: 0
        };
        
        attachmentTypeSrvc.getAll().then(function(response){
            vm.attachments=response.data;
        });

        requestTypeAttachmentTypeSrvc.getAll().then(function(response){
            vm.requestTypeAttachmentTypes=response.data;
        });

        requestTypeSrvc.getAll().then(function(response){
            vm.requests=response.data;
        });

        var resetEntryForm = function(){
            $scope.requestTypeAttachmentTypeForm.$setPristine();
            $scope.requestTypeAttachmentTypeForm.$setUntouched(); 
        };

        vm.add = function(){
            requestTypeAttachmentTypeSrvc.save(vm.requestTypeAttachmentType).then(function success(response){
                
                vm.requestTypeAttachmentTypes.push(response.data);
                vm.requestTypeAttachmentType = new RequestTypeAttachmentType();
                resetEntryForm();
            }, function error(response){
                var status = httpStatusSrvc.getStatus(response.status);
                if(status.code === httpStatusSrvc.badRequest.code){
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };


        vm.edit = function(id){
            requestTypeAttachmentTypeSrvc.getById(id).then(function(response){
                vm.editrequestTypeAttachmentType = response.data;
                vm.editrequestTypeAttachmentType.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));

                vm.requestTypeAttachmentType = angular.copy(vm.editrequestTypeAttachmentType);
                resetEntryForm();
            });
        };


        vm.refetch = function(id){
            requestTypeAttachmentTypeSrvc.getById(id).then(function(response){
                vm.editrequestTypeAttachmentType = response.data;
                vm.editrequestTypeAttachmentType.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.requestTypeAttachmentType = angular.copy(vm.editrequestTypeAttachmentType);
                vm.message = null;
                resetEntryForm();
            });
        };


        vm.update = function(){
            
            requestTypeAttachmentTypeSrvc.update(vm.requestTypeAttachmentType).then(function success(response){
               
                var tempRequestAttachment = response.data;
                vm.requestTypeAttachmentType = new RequestTypeAttachmentType();
                vm.editrequestTypeAttachmentType = null;
                vm.requestTypeAttachmentTypes.forEach(function(ou, index){
                    if(ou.id === tempRequestAttachment.id){
                        vm.requestTypeAttachmentTypes[index] = tempRequestAttachment;
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
            
            requestTypeAttachmentTypeSrvc.delete(id).then(function success(response){               
                vm.requestTypeAttachmentTypes.forEach(function(ou, index){
                    if(ou.id === id){
                        vm.requestTypeAttachmentTypes.splice(index, 1);
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
            vm.requestTypeAttachmentType = new RequestTypeAttachmentType();
            vm.editrequestTypeAttachmentType = null;
            vm.message = null;
            resetEntryForm();
        };

        vm.reset = function(){
            if(vm.editrequestTypeAttachmentType){
                vm.requestTypeAttachmentType = angular.copy(vm.editrequestTypeAttachmentType);
            }else{
                vm.requestTypeAttachmentType = new RequestTypeAttachmentType();
            }
            resetEntryForm();
        };

        vm.closeMessage = function(){
            vm.message = null;
        };
    });
}