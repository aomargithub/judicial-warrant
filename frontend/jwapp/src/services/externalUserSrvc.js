module.exports = function(app){
    app.service('externalUserSrvc', function($http, urlSrvc){
        var self = this;
        var usersUrl = urlSrvc.getUrl('users');

        self.getAll = function(){
            return $http.get(usersUrl + 'role?isInternal=false');
        };

        self.save = function(user){ 
            return $http.post(usersUrl , user);
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