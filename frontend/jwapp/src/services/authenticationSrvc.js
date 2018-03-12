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
                    appSessionSrvc.setIsAuthenticated(true);
                    appSessionSrvc.setCurrentUser(response.data.userDetails);
                    appSessionSrvc.setAuthenticationToken(response.data.userDetails.token.value);
                    appSessionSrvc.setMaxInactiveInterval(response.data.userDetails.token.maxInactiveInterval);
                }else{
                    appSessionSrvc.empty();
                }
                return httpStatusSrvc.getStatus(response.status);

            }, function error(response) {

                appSessionSrvc.empty();
                return httpStatusSrvc.getStatus(response.status);
            });
        };
    });
}