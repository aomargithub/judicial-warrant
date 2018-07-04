module.exports = function(app){
    app.service('requestAttachmentSrvc', function($http, urlSrvc){
        var self = this;
        var vm=this;
        vm.requestAttachment=[];

        
       
        self.uploadAttachment = function (urlprefix, requestAttachment, serial) {

            return $http({
                method: 'post',
                url: urlSrvc.getUrl(urlprefix) + 'serial=' + serial + '/requestAttachments',
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
            
        self.getRequestAttachments = function (urlprefix, serial) {
            return $http.get(urlSrvc.getUrl(urlprefix) + 'serial=' + serial + '/requestAttachments');
        };

        self.showRequestAttachmentImage = function (urlprefix, serial,id,ucmDocumentId) {
            return $http.get(urlSrvc.getUrl(urlprefix) + 'serial=' + serial + '/requestAttachments/' + id +'/ucmDocumentId=' + ucmDocumentId + '/download'
            , {responseType: 'arraybuffer'});

        };

        self.deleteRequestAttachment = function (urlprefix, id, serial) {
            return $http.delete(urlSrvc.getUrl(urlprefix) + 'serial=' + serial + '/requestAttachments/' + id);
        };


    });
};