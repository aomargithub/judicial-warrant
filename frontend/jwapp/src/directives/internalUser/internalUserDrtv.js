module.exports = function(app){
    app.directive('internalUser', function(){
        return {
            controllerAs : 'internalUserDrtvCtrl',
            controller : 'internalUserDrtvCtrl',
            template : require('./internal-user-drtv.html'),
            replace: true
        };
    });
}