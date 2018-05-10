module.exports = function(app){
    app.directive('breadCrumbs', function(){
        return {
            controllerAs : 'breadCrmbsDrtvCtrl',
            controller : 'breadCrumbsDrtvCtrl',
            template : require('./breadCrumbs-drtv.html'),
            replace: true
        };
    });
};  