(function(){
    var angular = require('angular');
        uiRouter = require('angular-ui-router')
        ngCookies = require('angular-cookies'),
        ngAnimate = require('angular-animate'),
        uiBootstrap = require('angular-ui-bootstrap'),
        blockUI = require('angular-block-ui'),
        jwApp = angular.module('jwApp', [uiRouter, ngCookies, ngAnimate, uiBootstrap, blockUI]);
    
    require('./index')(jwApp);
})();
