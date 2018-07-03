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

        
        self.getAll = function (status) {
            return $http.get(capacityDelegationsUrl + "?currentStatus=" + status);
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


        self.submission = function(serial, capacityDelegationChangeStatusRequest){
            return $http.put(capacityDelegationsUrl + 'serial=' + serial + '/submission', capacityDelegationChangeStatusRequest)
        }
    });
};