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

        self.getById = function(id){
            return $http.get(organizationUnitsUrl + id);
        };

        self.update = function(organizationUnit){
            return $http.put(organizationUnitsUrl + organizationUnit.id, organizationUnit);
        };
    });
};