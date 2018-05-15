module.exports = function(app){
    app.config(function($stateProvider, $urlRouterProvider, $locationProvider, templateSrvcProvider,){
        $urlRouterProvider.otherwise('/login');
        $stateProvider
            .state('login', {
                url : '/login',
                template : templateSrvcProvider.getTemplate('login')
            }).state('root', {
                parent: '',
                data: {
                    label : 'home'
                },
                url : '/home',
                views : {
                    '' : {
                        template : templateSrvcProvider.getTemplate('layout')
                    },
                    'content@root' : {
                        template : templateSrvcProvider.getTemplate('home')
                    }
                }
            }).state('root.organizationUnits', {
                parent: 'root',
                data: {
                    label : 'organizationUnits'
                },
                url : '/organizationUnits',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('organizationUnits')
                    }
                }
            }).state('root.attachmentTypes', {
                parent: 'root',
                data: {
                    label : 'attachmentTypes'
                },
                url : '/attachmentTypes',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('attachmentTypes')
                    }
                }
            })
    });
};