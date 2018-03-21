(function(){
    var angular = require('angular');
        uiRouter = require('angular-ui-router')
        ngCookies = require('angular-cookies'),
        ngAnimate = require('angular-animate'),
        jwApp = angular.module('jwApp', [uiRouter, ngCookies, ngAnimate]);
    
    require('./index')(jwApp);
})();
