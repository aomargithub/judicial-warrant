module.exports = function(app){
    app.config(function($stateProvider, $urlRouterProvider, templateSrvcProvider,){
        $urlRouterProvider.otherwise('/login');
        $stateProvider
            .state('login', {
                url : '/login',
                template : templateSrvcProvider.getTemplate('login')
            }).state('home', {
                parent: '',
                data: {
                   
                    label : 'home'
                },
                url : '/home',
                views : {
                    '' : {
                        template : templateSrvcProvider.getTemplate('layout')
                    },
                    'content@home' : {
                        template : templateSrvcProvider.getTemplate('home')
                    }
                }
            }).state('organizationUnits', {
                parent: 'home',
                data: {
                    label : 'organizationUnits'
                },
                url : '/organizationUnits',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('organizationUnits')
                    }
                }
            }).state('attachmentTypes', {
                parent: 'home',
                data: {
                    label : 'attachmentTypes'
                },
                url : '/attachmentTypes',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('attachmentTypes')
                    }
                }
            }).state('internalUsers', {
                parent: 'home',
                data: {
                    label : 'internalUsers'
                },
                url : '/internalUsers',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('internalUsers')
                    }
                }
            }).state('externalUsers', {
                parent: 'home',
                data: {
                    label : 'externalUsers'
                },
                url : '/externalUsers',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('externalUsers')
                    }
                }
            }).state('requestTypeAttachmentTypes',{
                parent: 'home',
                data: {
                    label :'requestTypeAttachmentTypes' 
                },
                url : '/requestTypeAttachmentTypes',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('requestTypeAttachmentTypes')
                    }
                    
                }
            }).state('CAPACITY_DELEGATION',{
                parent: 'home',
                data: {
                    label :'capacityDelegations'
                },
                url : '/capacityDelegations',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('capacityDelegations')
                    }
                    
                }
            }).state('EDITE_CAPACITY_DELEGATION',{
                parent: 'home',
                data: {
                    label :'capacityDelegations'
                },
                url : '/capacityDelegations/:serial',
                params: {
                    serial: null
                },  resolve: {
                    requests: ['$stateParams', 'capacityDelegationSrvc',
                        function($stateParams,capacityDelegationSrvc) {
        
                        return capacityDelegationSrvc.getCapacityDelegations($stateParams.serial).then(function (response) {                 
                                return   response.data;
 
                        })
                    }]
                   
                },
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('capacityDelegations')
                    }
                    
                }
            }).state('ENTITLED_REGISTRATION',{
                parent: 'home',
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
            }).state('EDITE_ENTITLED_REGISTRATION',{
                parent: 'home',
                data: {
                    label :'entitledRegistrations'
                },
                url : '/entitledRegistrations/:serial',
                params: {
                    serial: null
                },  resolve: {
                    requests: ['$stateParams', 'entitledRegistrationSrvc',
                        function($stateParams,entitledRegistrationSrvc) {
        
                        return entitledRegistrationSrvc.getBySerial($stateParams.serial).then(function (response) {                 
                                return   response.data;
 
                        })
                    }]
                   
                },
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('entitledRegistrations')
                    }
                    
                }
            }).state('requests',{
                parent: 'home',
                data: {
                    label :'requests'
                },
                url : '/requests',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('requests')
                    }
                    
                }
            }).state('myRequests',{
                parent : 'home',
                data: {
                    label: 'myRequests'
                },
                url : '/myRequests',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('myRequests')
                    }
                }
            }).state('entitledTrainnings',{
                parent : 'home',
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
            }).state('editeEntitledTrainnings',{
                parent : 'home',
                data: {
                    label: 'entitledTrainnings'
                },
                url : '/entitledTrainnings/:serial',
                params: {
                    serial: null
                },  resolve: {
                    requests: ['$stateParams', 'entitledRegistrationSrvc',
                        function($stateParams,entitledRegistrationSrvc) {
        
                        return    entitledRegistrationSrvc.getEntitleds($stateParams.serial).then(function(response){
                             return   vm.entitleds = response.data;
                            
 
                        })
                    }]
                   
                },
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('entitledTrainnings')
                    }
                }
            }).state('requestEntitledRegistrations',{
                parent : 'home',
                data: {
                    label: 'requestEntitledRegistrations'
                },
                url : '/requestEntitledRegistrations',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('requestEntitledRegistrations')
                    }
                }
            }).state('userProfiles',{
                parent : 'home',
                data: {
                    label: 'userProfiles'
                },
                url : '/userProfiles',
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('userProfiles')
                    }
                }
            });
    });
};