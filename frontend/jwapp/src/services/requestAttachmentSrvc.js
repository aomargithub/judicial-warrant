module.exports = function(app){
    app.service('requestAttachmentSrvc', function($http, urlSrvc){
        var self = this;
        var requestAttachmentUrl = urlSrvc.getUrl('requestAttachments');


        
       
        self.uploadAttachment = function(requestAttachment){
        
            $http({
              method: 'post',
              url: requestAttachmentUrl,
              headers: {'Content-Type': undefined},
              transformRequest: function (requestAttachment) {
                var formData = new FormData();
        
                formData.append('attachmentType', new Blob([angular.toJson(requestAttachment.attachmentType)], {
                    type: "application/json"
                }));
                formData.append("file", requestAttachment.file),{
                    'Content-Type': 'multipart/form-data'
                };
                return formData;
            },
            data: { attachmentType: requestAttachment.attachmentType, file: requestAttachment.file }
        
        
            }).then(function successCallback(response) {  
              // Store response data
             // vm.requestAttachments.push(response.data);
            })};
            

        self.getAll = function(){
            return $http.get(requestAttachmentUrl);
        };

        
        self.getById = function(id){
            return $http.get(requestAttachmentUrl + id);
        };
        

        self.update = function(requestAttachment){
            return $http.put(requestAttachmentUrl + requestAttachment.id, requestAttachment);
        };

        self.delete = function(id){
            return $http.delete(requestAttachmentUrl + id);
        };
    });
};