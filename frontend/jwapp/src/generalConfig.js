module.exports = function(app){
    app.config(function($httpProvider){
        $httpProvider.interceptors.push('authenticationInterceptor');
    }).run(function($rootScope){
        $rootScope.messages = require('./messages.json');
    });
    app.constant('authenticationTokenName', 'x-auth-token');
};