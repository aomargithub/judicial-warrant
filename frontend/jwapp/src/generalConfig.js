module.exports = function(app){
    app.config(function($httpProvider){
        $httpProvider.interceptors.push('authenticationInterceptor');
        $httpProvider.interceptors.push('versionInterceptor');
    }).run(function($rootScope){
        $rootScope.messages = require('./messages.json');
    });
    app.constant('authenticationTokenName', 'x-auth-token');
};