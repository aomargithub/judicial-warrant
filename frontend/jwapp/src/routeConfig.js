module.exports = function(app){
    app.config(function($stateProvider, $urlRouterProvider, templateSrvcProvider){
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
            }).state('root.internalUsers', {
                parent: 'root',
                data: {
                    label : 'internalUsers'
                },
                url : '/internalUsers',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('internalUsers')
                    }
                }
            }).state('root.externalUsers', {
                parent: 'root',
                data: {
                    label : 'externalUsers'
                },
                url : '/externalUsers',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('externalUsers')
                    }
                }
            }).state('root.requestTypeAttachmentTypes',{
                parent: 'root',
                data: {
                    label :'requestTypeAttachmentTypes'
                },
                url : '/requestTypeAttachmentTypes',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('requestTypeAttachmentTypes')
                    }
                    
                }
            }).state('root.capacityDelegations',{
                parent: 'root',
                data: {
                    label :'capacityDelegations'
                },
                url : '/capacityDelegations',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('capacityDelegations')
                    }
                    
                }
            });
    });
};