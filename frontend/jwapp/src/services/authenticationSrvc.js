module.exports = function (app) {
    app.service('authenticationSrvc', function ($http, urlSrvc, appSessionSrvc, httpStatusSrvc) {
        var self = this;

        self.login = function (credentials) {
            var loginUrl = urlSrvc.getUrl('login');
            var headers = {
                authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
            };
            return $http.get(loginUrl, { headers: headers }).then(function success(response) {
                if (response.status = 200) {
                    appSessionSrvc.createSession(response.data);
                }else{
                    appSessionSrvc.invalidateCurrentSession();
                }
                return httpStatusSrvc.getStatus(response.status);

            }, function error(response) {
                appSessionSrvc.invalidateCurrentSession();
                return httpStatusSrvc.getStatus(response.status);
            });
        };

        self.logout = function(){
            var logoutUrl = urlSrvc.getUrl('logout');
            return $http.get(logoutUrl).then(function success(response) {
                if (response.status = 200) {
                    appSessionSrvc.invalidateCurrentSession();
                }
                return httpStatusSrvc.getStatus(response.status);

            }, function error(response) {
                return httpStatusSrvc.getStatus(response.status);
            });
        };
    });
}