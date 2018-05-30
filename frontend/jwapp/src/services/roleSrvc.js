module.exports = function(app){
    app.service('roleSrvc', function($http, urlSrvc){
        var self = this;
        var rolesUrl = urlSrvc.getUrl('roles');

        self.getInternal = function(){
            return $http.get(rolesUrl + "?isInternal=true");
        }
    });
}