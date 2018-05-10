module.exports = function(app){
    app.config(function($stateProvider, $urlRouterProvider, $locationProvider, templateSrvcProvider,){
        $urlRouterProvider.otherwise('/login');
        $stateProvider
            .state('login', {
                url : '/login',
                template : templateSrvcProvider.getTemplate('login')
            }).state('home', {
                breadcrumb: 'home',
                url : '/home',
                template : templateSrvcProvider.getTemplate('home')
            }).state('home.organizationUnits', {
                breadcrumb: {
                    class: 'highlight',
                    text: 'organizationUnits',
                    stateName: 'home.organizationUnits'
                  },
                url : '/organizationUnits',
                template : templateSrvcProvider.getTemplate('organizationUnits')
            }).state('home.attachmentTypes', {
                url : '/attachmentTypes',
                template : templateSrvcProvider.getTemplate('attachmentTypes')
            })
    });
};