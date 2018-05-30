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
        urlSrvcProvider.addUrl('internalUsers', '/internalUsers/');
        urlSrvcProvider.addUrl('roles', '/roles/');
    });
};