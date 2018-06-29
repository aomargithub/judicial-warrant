module.exports = function (app) {
    app.service('capacityDelegationSrvc', function ($http, urlSrvc,$q) {
        var self = this;
        var capacityDelegationsUrl = urlSrvc.getUrl('capacityDelegations');

        self.uploadAttachment = function (requestAttachment, serial) {

            return $http({
                method: 'post',
                url: capacityDelegationsUrl + 'serial=' + serial + '/requestAttachments',
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: function () {
                    var formData = new FormData();

                    formData.append('file', requestAttachment.file), {
                        'Content-Type': 'multipart/form-data'
                    };
                    formData.append('requestAttachment', new Blob([angular.toJson(requestAttachment)], {
                        type: "application/json"
                    }));

                    return formData;
                },
                // requestAttachment: {  file: requestAttachment }
            })
        };

        self.getRequestAttachments = function (Serial) {
            return $http.get(capacityDelegationsUrl + 'serial=' + Serial + '/requestAttachments');
        };

        self.getCapacityDelegations = function (Serial) {
            return $http.get(capacityDelegationsUrl + 'serial=' + Serial);
        };

        self.deleteRequestAttachment = function (id, serial) {
            return $http.delete(capacityDelegationsUrl + 'serial=' + serial + '/requestAttachments/' + id);
        };

        
        self.getAll = function () {
            return $http.get(capacityDelegationsUrl);
        };

        self.save = function (capacityDelegation) {
            return $http.post(capacityDelegationsUrl, capacityDelegation);
        };


        self.update = function (capacityDelegation) {
            return $http.put(capacityDelegationsUrl + capacityDelegation.id, capacityDelegation);
        };


        self.showImage = function (serial,id,ucmDocumentId) {
                return $http.get(capacityDelegationsUrl + 'serial=' + serial + '/requestAttachments/' + id +'/ucmDocumentId=' + ucmDocumentId + '/download'
                , {responseType: 'arraybuffer'});
 
        };


        // self.showImage = function (index,serial,id,ucmDocumentId) {
             //var ucmDocumentId = (index.exist) ? index.ucmDocumentId : index.id;
            // var url;
            
            // if(ucmDocumentId){
                // ucmDocumentId = ucmDocumentId.substring(1, ucmDocumentId.length - 1);
                // url = capacityDelegationsUrl + 'serial=' + serial + '/requestAttachments' + id +'/ucmDocumentId=' + ucmDocumentId + '/download';
                // return $http({
                //     url: url,
                //     method: 'GET',
                //     responseType: 'blob'
                // });
               
            // }else{
            //     var reader = new FileReader();
            //     reader.onload = function(){
            //         url = reader.result;
            //         defer.resolve(url);
            //     };
                // reader.readAsDataURL(index.file);
        //     }
    
           
        // };
    

    });
};