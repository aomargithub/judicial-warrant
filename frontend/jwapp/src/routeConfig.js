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
            }).state('root.CAPACITY_DELEGATION',{
                parent: 'root',
                data: {
                    label :'capacityDelegations'
                },
                url : '/capacityDelegations/:serial',
                params: {
                    serial: null
                },
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('capacityDelegations')
                    }
                    
                }
            }).state('root.ENTITLED_REGISTRATION',{
                parent: 'root',
                data: {
                    label :'entitledRegistrations'
                },
                url : '/entitledRegistrations/:serial',
                params: {
                    serial: null
                },
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('entitledRegistrations')
                    }
                    
                }
            }).state('root.requests',{
                parent: 'root',
                data: {
                    label :'requests'
                },
                url : '/requests',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('requests')
                    }
                    
                }
            }).state('root.myRequests',{
                parent : 'root',
                data: {
                    label: 'myRequests'
                },
                url : '/myRequests',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('myRequests')
                    }
                }
            }).state('root.entitledTrainnings',{
                parent : 'root',
                data: {
                    label: 'entitledTrainnings'
                },
                url : '/entitledTrainnings/:serial',
                params: {
                    serial: null
                },
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('entitledTrainnings')
                    }
                }
            }).state('root.requestEntitledRegistrations',{
                parent : 'root',
                data: {
                    label: 'requestEntitledRegistrations'
                },
                url : '/requestEntitledRegistrations',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('requestEntitledRegistrations')
                    }
                }
            });
    });
};