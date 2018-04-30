module.exports = function(app){
    app.config(function($stateProvider, $urlRouterProvider, $locationProvider, templateSrvcProvider){
        $urlRouterProvider.otherwise('/login');
       // $locationProvider.hashPrefix('');
        $stateProvider
            .state('login', {
                url : '/login',
                template : templateSrvcProvider.getTemplate('login')
            }).state('home', {
                url : '/home',
                template : templateSrvcProvider.getTemplate('home')
            }).state('home.organizationUnits', {
                url : '/organizationUnits',
                template : templateSrvcProvider.getTemplate('organizationUnits')
            })
    });
};