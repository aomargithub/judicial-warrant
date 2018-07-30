module.exports = function(app){
    app.directive('myRequest', function(){
        return {
            controllerAs: 'myRequestDrtvCtrl',
            controller: 'myRequestDrtvCtrl',
            template: require('./myRequest-drtv.html'),
            replace: true
        };
    });
}