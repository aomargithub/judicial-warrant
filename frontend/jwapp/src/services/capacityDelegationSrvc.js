module.exports = function(app){
    app.service('capacityDelegationSrvc', function($http, urlSrvc){
        var self = this;
        var capacityDelegationsUrl = urlSrvc.getUrl('capacityDelegations');
       

        self.getAll = function(){
            return $http.get(capacityDelegationsUrl);
        };

        self.save = function(capacityDelegation){
            return $http.post(capacityDelegationsUrl, capacityDelegation);
        };

        self.getById = function(id){
            return $http.get(capacityDelegationsUrl + id);
        };

        self.update = function(capacityDelegation){
            return $http.put(capacityDelegationsUrl + capacityDelegation.id, capacityDelegation);
        };

        self.delete = function(id){
            return $http.delete(capacityDelegationsUrl + id);
        };
    });
};