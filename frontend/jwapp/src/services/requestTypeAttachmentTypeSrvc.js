module.exports = function(app){
    app.service('requestTypeAttachmentTypeSrvc', function($http,urlSrvc){
        var self= this;
        var requestTypeAttachmentTypesUrl = urlSrvc.getUrl('requestTypeAttachmentTypes');
       
        self.getByRequestTypeId=function(capacityDelegation){
            return $http.get(requestTypeAttachmentTypesUrl + '?requestTypeId=' + capacityDelegation.RequestType.id)
        }

        self.getAttachmentTypesByRequestTypeCode = function(requestTypeCode){
            return $http.get(requestTypeAttachmentTypesUrl + 'attachmentTypes?requestTypeCode=' + requestTypeCode)
        }

        self.getAll=function(){
            return $http.get(requestTypeAttachmentTypesUrl); 
        }
        self.save=function(requestTypeAttachmentType){
            return $http.post(requestTypeAttachmentTypesUrl,requestTypeAttachmentType);
        }
        self.getById=function(id){
            return $http.get(requestTypeAttachmentTypesUrl + id);
        }
        self.update=function(requestTypeAttachmentType){
            return $http.put(requestTypeAttachmentTypesUrl + requestTypeAttachmentType.id,requestTypeAttachmentType);
        }
        self.delete= function(id){
            return $http.delete(requestTypeAttachmentTypesUrl + id);
        }
    })
}