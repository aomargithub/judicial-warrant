module.exports = function(app){
    app.config(function(urlSrvcProvider){
        urlSrvcProvider.addEnvironment('dev').protocol('http').server('localhost').port('8081').endPointBase('/judicialwarrant/api');
        urlSrvcProvider.addEnvironment('test').protocol('http').server('localhost').port('8081').endPointBase('/judicialwarrant/api');
        urlSrvcProvider.addEnvironment('staging').protocol('http').server('localhost').port('8081').endPointBase('/judicialwarrant/api');
        urlSrvcProvider.addEnvironment('prod').protocol('http').server('localhost').port('8081').endPointBase('/judicialwarrant/api');
        urlSrvcProvider.setCurrentEnvironment('dev');
        
        
        urlSrvcProvider.addUrl('login', '/login');
        urlSrvcProvider.addUrl('logout', '/logout');
        urlSrvcProvider.addUrl('organizationUnits', '/organizationUnits/');
        urlSrvcProvider.addUrl('attachmentTypes', '/attachmentTypes/');
        urlSrvcProvider.addUrl('requestTypes', '/requestTypes/');
        urlSrvcProvider.addUrl('users', '/users/');
        urlSrvcProvider.addUrl('roles', '/roles/');
        urlSrvcProvider.addUrl('requestTypeAttachmentTypes','/requestTypeAttachmentTypes/');
        urlSrvcProvider.addUrl('capacityDelegations','/capacityDelegations/');
        urlSrvcProvider.addUrl('entitledRegistrations','/entitledRegistrations/');
        urlSrvcProvider.addUrl('requestAttachments','/requestAttachments/');
        urlSrvcProvider.addUrl('requests','/requests/');
        urlSrvcProvider.addUrl('myRequests','/myRequests/');
        urlSrvcProvider.addUrl('entitledTrainnings','/entitledTrainnings/');
        urlSrvcProvider.addUrl('requestEntitledRegistrations','/requestEntitledRegistrations/');
        urlSrvcProvider.addUrl('userProfiles','/userProfiles/');
    });
};