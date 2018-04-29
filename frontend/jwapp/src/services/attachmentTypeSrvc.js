module.exports = function(app){
    app.service('attachmentTypeSrvc', function($http, urlSrvc){
        var self = this;
        var attachmentTypesUrl = urlSrvc.getUrl('attachmentTypes');
        

        self.getAll = function(){
            return $http.get(attachmentTypesUrl);
        };

        self.save = function(attachmentType){
            return $http.post(attachmentTypesUrl, attachmentType);
        };

        self.getById = function(id){
            return $http.get(attachmentTypesUrl + id);
        };

        self.update = function(attachmentType){
            return $http.put(attachmentTypesUrl + attachmentType.id, attachmentType);
        };

        self.delete = function(id){
            return $http.delete(attachmentTypesUrl + id);
        };
    });
};