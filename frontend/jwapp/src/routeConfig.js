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
            }).state('home.organizationUnits', {
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
            }).state('home.attachmentTypes', {
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
            }).state('home.internalUsers', {
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
            }).state('home.externalUsers', {
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
            }).state('home.requestTypeAttachmentTypes',{
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
            }).state('home.CAPACITY_DELEGATION',{
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
            }).state('home.ENTITLED_REGISTRATION',{
                parent: 'home',
                data: {
                    label :'entitledRegistrations'
                },
                url : '/entitledRegistrations/:serial',
                params: {
                    serial: null
                }, resolve: {
                    entitleds: ['$stateParams', 'entitledRegistrationSrvc',
                        function($stateParams, entitledRegistrationSrvc) {
        
                            return entitledRegistrationSrvc.getBySerial($stateParams.serial).then(function success(response) {
                                return  response.data;    
                               });
                              
                    }]
                   
                },
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('entitledRegistrations')
                    }
                    
                }
            }).state('home.requests',{
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
            }).state('home.myRequests',{
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
            }).state('home.entitledTrainnings',{
                parent : 'home',
                data: {
                    label: 'entitledTrainnings'
                },
                url : '/entitledTrainnings/:serial',
                params: {
                    serial: null
                }, resolve: {
                    entitledTrainnings: ['$stateParams', 'entitledRegistrationSrvc',
                        function($stateParams, entitledRegistrationSrvc) {
        
                            return entitledRegistrationSrvc.getEntitleds($stateParams.serial).then(function(response){
                                return  vm.entitleds = response.data;
                            });
                              
                    }]
                   
                },
                views : {
                    content : {
                        template : templateSrvcProvider.getTemplate('entitledTrainnings')
                    }
                }
            }).state('home.requestEntitledRegistrations',{
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
            }).state('home.userProfiles',{
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