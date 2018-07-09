module.exports = function(app){
    app.directive('userProfile', function(){
        return {
            controllerAs : 'userProfileDrtvCtrl',
            controller : 'userProfileDrtvCtrl',
            template : require('./userProfile-drtv.html'),
            replace: true
        };
    });
}