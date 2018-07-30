module.exports = function(app){
    app.directive('externalUser', function(){
        return {
            controllerAs : 'externalUserDrtvCtrl',
            controller : 'externalUserDrtvCtrl',
            template : require('./external-user-drtv.html'),
            replace: true
        }; 
    });
}