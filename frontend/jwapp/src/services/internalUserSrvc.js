module.exports = function(app){
    app.service('internalUserSrvc', function($http, urlSrvc){
        var self = this;
        var usersUrl = urlSrvc.getUrl('internalUsers');

        self.getAll = function(){
            return $http.get(usersUrl);
        };

        self.save = function(user){
            return $http.post(usersUrl, attachmentType);
        };

        self.getById = function(id){
            return $http.get(usersUrl + id);
        };

        self.update = function(user){
            return $http.put(usersUrl + user.id, user);
        };

        self.delete = function(id){
            return $http.delete(usersUrl + id);
        };
    });
}