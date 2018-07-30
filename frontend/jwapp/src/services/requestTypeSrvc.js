module.exports = function(app){
    app.service('requestTypeSrvc', function($http, urlSrvc, appSessionSrvc){
        var self = this;
        var requestTypesUrl = urlSrvc.getUrl('requestTypes');
        

        self.getAll = function(){
            return $http.get(requestTypesUrl);
        };

        self.getActiveByCurrentUserRole = function(){
            return $http.get(requestTypesUrl + '?isActive=true&isInternal=' + ((appSessionSrvc.getCurrentUser().role === 'ROLE_USER') ? false : true));
        }

        self.getById = function(id){
            return $http.get(requestTypesUrl + id);
        };

        self.getByCode = function(code){
            return $http.get(requestTypesUrl + 'code=' +  code);
        };
    });
}