module.exports = function(app){
    app.controller('capacityDelegationDrtvCtrl', function($rootScope,organizationUnitSrvc,$scope,CapacityDelegation,RequestAttachment, capacityDelegationSrvc,attachmentTypeSrvc,requestAttachmentSrvc,httpStatusSrvc,stringUtilSrvc){
        var vm = this;
        vm.capacityDelegation = new CapacityDelegation();
        vm.requestAttachment = new RequestAttachment();
        vm.editId = null;
        vm.message = null;
        vm.editCapacityDelegation = null;
        vm.capacityDelegations = [];
        vm.attachments = []; 
        vm.requestAttachments=[];
        vm.organizationUnites=[];
        
        



        vm.page = {
            start: 0,
            end: 0
        };

        capacityDelegationSrvc.getAll().then(function(response){
            vm.capacityDelegations = response.data;
        });

        attachmentTypeSrvc.getAll().then(function(response){
            vm.attachments = response.data;
        });
        
        organizationUnitSrvc.getInternal().then(function(response){
            vm.organizationUnites = response.data;
        })
        

        requestAttachmentSrvc.getAll().then(function(response){
            vm.requestAttachments=response.data;
            
        });
        

      

        var resetEntryForm = function(){
            $scope.capacityDelegationFormFileUpload.$setPristine();
            $scope.capacityDelegationFormFileUpload.$setUntouched(); 
        }
        
        
        
       
        vm.addRequestAttachment = function(){
         
            requestAttachmentSrvc.uploadAttachment(vm.requestAttachment).then(function success(response){
                vm.requestAttachments.push(response.data);
                vm.requestAttachment = response.data;

                resetEntryForm();
               
            }, function error(response){
                var status = httpStatusSrvc.getStatus(response.status);
                if(status.code === httpStatusSrvc.badRequest.code){
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };
        


        vm.add = function(){
            
            capacityDelegationSrvc.save(vm.capacityDelegation).then(function success(response){
                vm.capacityDelegations.push(response.data);
                vm.capacityDelegation = response.data;

                resetEntryForm();
               
            }, function error(response){
                var status = httpStatusSrvc.getStatus(response.status);
                if(status.code === httpStatusSrvc.badRequest.code){
                    vm.message = $rootScope.messages[status.text];
                };
            });
        };

        vm.edit = function(id){
            requestAttachmentSrvc.getById(id).then(function(response){
                vm.editCapacityDelegation = response.data;
                vm.editCapacityDelegation.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.requestAttachment = angular.copy(vm.editCapacityDelegation);
                resetEntryForm();
                
            });
        };

        vm.refetch = function(id){
            requestAttachmentSrvc.getById(id).then(function(response){
                vm.editCapacityDelegation = response.data;
                vm.editCapacityDelegation.version = stringUtilSrvc.removeQuotes(response.headers('ETag'));
                vm.requestAttachment = angular.copy(vm.editCapacityDelegation);
                vm.message = null;
                resetEntryForm();
                
            });
        };

        vm.update = function(){
            
            requestAttachmentSrvc.update(vm.requestAttachment).then(function success(response){
               
                var tempRequestAttachment = response.data;
                vm.requestAttachment = new RequestAttachment();
                vm.editCapacityDelegation = null;
                vm.requestAttachments.forEach(function(cd, index){
                    if(cd.id === tempRequestAttachment.id){
                        vm.requestAttachments[index] = tempFile;
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
            
            requestAttachmentSrvc.delete(id).then(function success(response){               
                vm.requestAttachments.forEach(function(cd, index){
                    if(cd.id === id){
                        vm.requestAttachments.splice(index, 1);
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
            vm.requestAttachment = new RequestAttachment();
            vm.editCapacityDelegation = null;
            vm.message = null;
            resetEntryForm();
            
        };


        vm.reset = function(){
            if(vm.editCapacityDelegation){
                vm.requestAttachment = angular.copy(vm.editCapacityDelegation);
            }else{
                vm.requestAttachment = new RequestAttachment();
            }
            resetEntryForm();
           
        };

        vm.closeMessage = function(){
            vm.message = null;
        };

    });
};