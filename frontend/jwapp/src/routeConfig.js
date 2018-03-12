module.exports = function(app){
    app.config(function($stateProvider, $urlRouterProvider, templateSrvcProvider){
        $urlRouterProvider.otherwise('/login');

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