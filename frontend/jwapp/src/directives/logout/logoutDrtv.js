module.exports = function(app){
    app.directive('logout', function(){
        return {
            controllerAs : 'logoutDrtvCtrl',
            controller : 'logoutDrtvCtrl',
            template : require('./logout-drtv.html'),
        };
    });
};