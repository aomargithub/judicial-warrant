module.exports = function(app){
    app.service('requestAttachmentSrvc', function($http, urlSrvc){
        var self = this;
        var requestAttachmentUrl = urlSrvc.getUrl('requestAttachments');
        var vm=this;
        vm.requestAttachment=[];

        
       
        self.uploadAttachment = function(){ 
        
            $http({
              method: 'post',
              url: requestAttachmentUrl,
              headers: {'Content-Type': undefined},
              transformRequest: function () {
                var formData = new FormData();
                
                formData.append('file', vm.requestAttachment.file),{
                    'Content-Type': 'multipart/form-data'
                };
                formData.append('dto', new Blob([angular.toJson(vm.requestAttachment)], {
                    type: "application/json"
                }));
               
                return formData;
            },        
           // requestAttachment: {  file: requestAttachment }
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