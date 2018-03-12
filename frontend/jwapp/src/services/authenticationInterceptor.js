module.exports = function(app){
    app.factory('authenticationInterceptor', function(appSessionSrvc, authenticationTokenName){
        var authenticationInterceptor = {
            request : function(config){
                if(!config.url.endsWith('/login')){
                    var token = appSessionSrvc.getAuthenticationToken();
                    config.headers[authenticationTokenName] = token;
                }

                return config;
            }
        };

        return authenticationInterceptor;
    });
};