(function(){
    var angular = require('angular');
        uiRouter = require('angular-ui-router')
        ngCookies = require('angular-cookies'),
        ngAnimate = require('angular-animate'),
        ngbreadcrumbs = require('angular-breadcrumbs'),
        angularuirouterbreadcrumbs = require('ui-router-breadcrumbs'),
        jwApp = angular.module('jwApp', [uiRouter, ngCookies, ngAnimate,ngbreadcrumbs,angularuirouterbreadcrumbs]);
    
    require('./index')(jwApp);
})();
