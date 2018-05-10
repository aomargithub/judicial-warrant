(function(){
    var angular = require('angular');
        uiRouter = require('angular-ui-router')
        ngCookies = require('angular-cookies'),
        ngAnimate = require('angular-animate'),
        ngbreadcrumbs = require('angular-breadcrumbs'),
        jwApp = angular.module('jwApp', [uiRouter, ngCookies, ngAnimate,ngbreadcrumbs,ui-router-breadcrumbs]);
    
    require('./index')(jwApp);
})();
