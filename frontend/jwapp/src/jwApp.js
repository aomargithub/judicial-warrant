(function(){
    var angular = require('angular');
        uiRouter = require('angular-ui-router')
        ngCookies = require('angular-cookies')
        jwApp = angular.module('jwApp', [uiRouter, ngCookies]);
    
    require('./index')(jwApp);
})();
