module.exports = function(app){
    app.factory('authenticationInterceptor', function(appSessionSrvc, authenticationTokenName){
        var authenticationInterceptor = {
            request : function(config){
                if(!config.url.endsWith('/login')){
                    var token = appSessionSrvc.getAuthenticationToken();
                    config.headers[authenticationTokenName] = token;

                    if(config.method === 'PUT'){
                        var data = config.data;

                        if(data.version){
                            config.headers["If-Match"] = data.version;
                        }
                    }
                }

                return config;
            }
        };

        return authenticationInterceptor;
    });
};