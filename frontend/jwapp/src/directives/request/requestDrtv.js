module.exports = function(app){
    app.directive('request', function(){
        return {
            controllerAs: 'requestDrtvCtrl',
            controller: 'requestDrtvCtrl',
            template: require('./request-drtv.html'),
            replace: true
        };
    });
}