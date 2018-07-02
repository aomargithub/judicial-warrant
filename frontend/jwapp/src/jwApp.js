(function(){
    var angular = require('angular');
        uiRouter = require('angular-ui-router')
        ngCookies = require('angular-cookies'),
        ngAnimate = require('angular-animate'),
        uiBootstrap = require('angular-ui-bootstrap'),
        jwApp = angular.module('jwApp', [uiRouter, ngCookies, ngAnimate, uiBootstrap]);
    
    require('./index')(jwApp);
})();
