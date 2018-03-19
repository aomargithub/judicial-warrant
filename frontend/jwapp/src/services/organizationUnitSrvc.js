module.exports = function(app){
    app.service('organizationUnitSrvc', function($http, urlSrvc){
        var self = this;
        var organizationUnitsUrl = urlSrvc.getUrl('organizationUnits');
        self.save = function(organizationUnit){
            
        };

        self.getAll = function(){
            return $http.get(organizationUnitsUrl);
        };

        self.save = function(organizationUnit){
            return $http.post(organizationUnitsUrl, organizationUnit);
        };
    });
};