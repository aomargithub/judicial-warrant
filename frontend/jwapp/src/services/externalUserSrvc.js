module.exports = function(app){
    app.service('externalUserSrvc', function($http, urlSrvc){
        var self = this;
        var externalusersUrl = urlSrvc.getUrl('users');

        self.getAll = function(){
            return $http.get(externalusersUrl);
        };

        self.save = function(user){ 
            return $http.post(externalusersUrl , user);
        };

        self.getById = function(id){
            return $http.get(externalusersUrl + id);
        };

        self.update = function(user){
            return $http.put(externalusersUrl + user.id, user);
        };

        self.delete = function(id){
            return $http.delete(externalusersUrl + id);
        };
    });
}