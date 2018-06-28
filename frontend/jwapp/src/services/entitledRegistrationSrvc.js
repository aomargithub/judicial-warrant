module.exports = function (app) {
    app.service('entitledRegistrationSrvc', function ($http, urlSrvc) {
        var self = this;
        var entitledRegistrationsUrl = urlSrvc.getUrl('entitledRegistrations');


        self.save = function (entitledRegistration) {
            return $http.post(entitledRegistrationsUrl, entitledRegistration);
        };
        

        self.update = function (id, entitledRegistration) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + entitledRegistration.request.serial);
        };

        self.uploadAttachment = function (requestAttachment, entitledRegistration) {

            return $http({
                method: 'post',
                url: entitledRegistrationsUrl + 'serial=' + entitledRegistration.request.serial + '/requestAttachments',
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

        self.getRequestAttachments = function (requestSerial) {
            return $http.get(entitledRegistrationsUrl + 'serial=' + requestSerial + '/requestAttachments');
        };

        self.deleteRequestAttachment = function (id, entitledRegistration) {
            return $http.delete(entitledRegistrationsUrl + 'serial=' + entitledRegistration.request.serial + '/requestAttachments/' + id);
        };



        self.getAll = function () {
            return $http.get(entitledRegistrationsUrl);
        };

    });
};