module.exports = function(app){
    app.directive('requestEntitledRegistration', function(){
        return {
            controllerAs: 'requestEntitledRegistrationDrtvCtrl',
            controller: 'requestEntitledRegistrationDrtvCtrl',
            template: require('./requestEntitledRegistration-drtv.html'),
            replace: true
        };
    });
}