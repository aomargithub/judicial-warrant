module.exports = function(app){
    app.directive('requestTypesMenu', function(){
        return {
            controllerAs : 'requestTypesMenuDrtvCtrl',
            controller : 'requestTypesMenuDrtvCtrl',
            template : require('./request-types-menu-drtv.html'),
            replace: true
        };
    });
};