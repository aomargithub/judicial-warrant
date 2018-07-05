module.exports = function (app) {
    app.service('entitledRegistrationSrvc', function ($http, urlSrvc) {
        var self = this;
        var entitledRegistrationsUrl = urlSrvc.getUrl('entitledRegistrations');

        self.getAll = function () {
            return $http.get(entitledRegistrationsUrl);
        };
        self.submission = function (requestSerial, entitledRegistrationChangeStatusRequest) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + requestSerial + '/submission' , entitledRegistrationChangeStatusRequest);
        };

        self.inCompletion = function (requestSerial, entitledRegistrationChangeStatusRequest) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + requestSerial + '/inCompletion' , entitledRegistrationChangeStatusRequest);
        };

        self.rejection = function (requestSerial, entitledRegistrationChangeStatusRequest) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + requestSerial + '/rejection' , entitledRegistrationChangeStatusRequest);
        };

        self.inProgress = function (requestSerial, entitledRegistrationChangeStatusRequest) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + requestSerial + '/inProgress' , entitledRegistrationChangeStatusRequest);
        };

        self.getBySerial = function (serial) {
            return $http.get(entitledRegistrationsUrl + 'serial=' + serial);
        };

        self.save = function (entitledRegistration) {
            return $http.post(entitledRegistrationsUrl, entitledRegistration);
        };
        

        self.update = function (id, entitledRegistration) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + entitledRegistration.request.serial);
        };

        self.uploadAttachment = function (requestAttachment, serial) {

            return $http({
                method: 'post',
                url: entitledRegistrationsUrl + 'serial=' + serial + '/requestAttachments',
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

        self.showRequestAttachmentImage = function (serial,id,ucmDocumentId) {
            return $http.get(entitledRegistrationsUrl + 'serial=' + serial + '/requestAttachments/' + id +'/ucmDocumentId=' + ucmDocumentId + '/download'
            , {responseType: 'arraybuffer'});

        };

        self.showEntitledAttachmentImage = function (serial,id,ucmDocumentId) {
            return $http.get(entitledRegistrationsUrl + 'serial=' + serial + '/entitledAttachments/' + id +'/ucmDocumentId=' + ucmDocumentId + '/download'
            , {responseType: 'arraybuffer'});

        };


        self.deleteRequestAttachment = function (id, entitledRegistration) {
            return $http.delete(entitledRegistrationsUrl + 'serial=' + entitledRegistration.request.serial + '/requestAttachments/' + id);
        };

        self.saveEntitled = function (requestSerial, entitled) {
            return $http.post(entitledRegistrationsUrl + 'serial=' + requestSerial + '/entitleds', entitled);
        };

        self.updateEntitled = function (requestSerial, entitled) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + requestSerial + '/entitleds/' + entitled.id, entitled);
        };

        self.deleteEntitled = function (requestSerial, id) {
            return $http.delete(entitledRegistrationsUrl + 'serial=' + requestSerial + '/entitleds/' + id);
        };

        self.getEntitleds = function (requestSerial) {
            return $http.get(entitledRegistrationsUrl + 'serial=' + requestSerial + '/entitleds');
        };

        self.getEntitled = function (requestSerial, id) {
            return $http.get(entitledRegistrationsUrl + 'serial=' + requestSerial + '/entitleds/', id);
        };


        self.getEntitledInTraining = function (requestSerial, id,changeStatusRequest) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + requestSerial + '/entitleds/'+ id + '/inTraining', changeStatusRequest);
        };
        self.getEntitledPassed = function (requestSerial, id,changeStatusRequest) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + requestSerial + '/entitleds/'+ id +  '/passed', changeStatusRequest);
        };
        self.getEntitledFailture  = function (requestSerial, id,changeStatusRequest) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + requestSerial + '/entitleds/'+ id +  '/failture', changeStatusRequest);
        };

        self.acceptEntitled  = function (requestSerial, id,changeStatusRequest) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + requestSerial + '/entitleds/'+ id +  '/acception', changeStatusRequest);
        };

        self.cardRecievedEntitledCardRecieved  = function (requestSerial, id,changeStatusRequest) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + requestSerial + '/entitleds/'+ id +  '/cardRecieved', changeStatusRequest);
        };

        self.rejectionEntitled  = function (requestSerial, id,changeStatusRequest) {
            return $http.put(entitledRegistrationsUrl + 'serial=' + requestSerial + '/entitleds/'+ id +  '/rejection', changeStatusRequest);
        };
        

        self.uploadEntitledAttachment = function (entitledAttachment, serial, entitledId) {

            return $http({
                method: 'post',
                url: entitledRegistrationsUrl + 'serial=' + serial + '/entitleds/' + entitledId +'/entitledAttachments',
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: function () {
                    var formData = new FormData();

                    formData.append('file', entitledAttachment.file), {
                        'Content-Type': 'multipart/form-data'
                    };
                    formData.append('entitledAttachment', new Blob([angular.toJson(entitledAttachment)], {
                        type: "application/json"
                    }));

                    return formData;
                },
            })
        };

        self.deleteEntitledAttachment = function (serial, id) {
            return $http.delete(entitledRegistrationsUrl + 'serial=' + serial + '/entitleds/entitledAttachments/' + id);
        };

        self.getAllEntitledAttachments = function (serial, entitledId) {
            return $http.get(entitledRegistrationsUrl + 'serial=' + serial + '/entitleds/' + entitledId + '/entitledAttachments');
        };

    });
};