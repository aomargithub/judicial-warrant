module.exports = function(app){
    app.service('requestSrcv', function($http, urlSrvc, appSessionSrvc){
        var self = this;
        var requestesUrl = urlSrvc.getUrl('requests');
        

        self.getAll = function(){
            return $http.get(requestesUrl);
        };
        self.activeRequest = function(serial){
            return $http.post(requestesUrl + 'serial=' + serial + '/submission')
        }

    });
}