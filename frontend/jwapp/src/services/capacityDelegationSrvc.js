module.exports = function (app) {
    app.service('capacityDelegationSrvc', function ($http, urlSrvc) {
        var self = this;
        var capacityDelegationsUrl = urlSrvc.getUrl('capacityDelegations');

        self.uploadAttachment = function (requestAttachment, capacityDelegation) {

            return $http({
                method: 'post',
                url: capacityDelegationsUrl + 'serial=' + capacityDelegation.request.serial + '/requestAttachments',
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: function () {
                    var formData = new FormData();

                    formData.append('file', requestAttachment.file), {
                        'Content-Type': 'multipart/form-data'
                    };
                    formData.append('dto', new Blob([angular.toJson(requestAttachment)], {
                        type: "application/json"
                    }));

                    return formData;
                },
                // requestAttachment: {  file: requestAttachment }
            })
        };

        self.getRequestAttachments = function (requestSerial) {
            return $http.get(capacityDelegationsUrl + 'serial=' + requestSerial + '/requestAttachments');
        };

        self.deleteRequestAttachment = function (id, capacityDelegation) {
            return $http.delete(capacityDelegationsUrl + 'serial=' + capacityDelegation.request.serial + '/requestAttachments/' + id);
        };



        self.getAll = function () {
            return $http.get(capacityDelegationsUrl);
        };

        self.save = function (capacityDelegation) {
            return $http.post(capacityDelegationsUrl, capacityDelegation);
        };

        self.getById = function (id) {
            return $http.get(capacityDelegationsUrl + id);
        };

        self.update = function (capacityDelegation) {
            return $http.put(capacityDelegationsUrl + capacityDelegation.id, capacityDelegation);
        };

        self.delete = function (id) {
            return $http.delete(capacityDelegationsUrl + id);
        };
    });
};