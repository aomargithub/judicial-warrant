module.exports = function(app){
    app.directive('organizationUnitDrtv', function(){
        return {
            controllerAs: 'organizationUnitDrtvCtrl',
            controller: 'organizationUnitDrtvCtrl',
            template: require('./organizationUnit-drtv.html'),
            replace: true
        };
    });
}